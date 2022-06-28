//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.server;

import com.gsw.integradores.nfe.commons.DocType;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Util;
import com.gsw.integradores.nfe.depara.XmlInEnum;
import com.gsw.integradores.nfe.sap.SapReaderNfe;
import com.gsw.integradores.nfe.sap.SapReaderNfs;
import com.gsw.integradores.nfe.service.FunctionService;
import com.gsw.integradores.nfe.vo.ZXmlOutImportVO;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

public class ConnectionHandler implements JCoServerFunctionHandler {
    private SapReaderNfs readerNfs;
    private SapReaderNfe readerNfe;
    private FunctionService functionService;

    public ConnectionHandler(SapReaderNfs readerNfs, SapReaderNfe sapReaderNfe, FunctionService functionService) {
        this.readerNfs = readerNfs;
        this.readerNfe = sapReaderNfe;
        this.functionService = functionService;
    }

    public void handleRequest(JCoServerContext serverCtx, JCoFunction function) {
        LogUtil.info("***** Função " + function.getName() + " capturada pelo IntegradorSAP - GSW *****");
        JCoParameterList record = function.getImportParameterList();
        LogUtil.info("============== DADOS DA FUNCAO SAP ==============");
        LogUtil.info(function.toXML());
        if("true".equalsIgnoreCase(Util.getProperties().getProperty("salvaRecord"))) {
            Util.salvarRecord(record);
        }

        String docType;
        String accessKey;
        String zCancInut;
        JCoParameterList zCancInut1;
        if(function.getName().equalsIgnoreCase("J_1BNFE_OUTNFE_CREATE")) {
            JCoParameterList docStat = this.callZXmlOut(record, record.getStructure("IS_NFE_HEADER").getString("DOCNUM"));
            DocType exportZXmlOut = this.functionService.verifyDocType(docStat);
            docType = docStat.getString("DOCSTAT");
            if("2".equals(docType)) {
                accessKey = record.getStructure("IS_NFE_HEADER").getString("DOCNUM");
                zCancInut = record.getStructure("IS_NFE_HEADER").getString("ACCESSKEY");
                zCancInut1 = this.functionService.callZCancelInutilizacao(zCancInut, accessKey);
                if(exportZXmlOut.equals(DocType.NFS)) {
                    LogUtil.info("Iniciando Inutilização NF-S. DOCNUM: " + zCancInut1.getString("DOCNUM") + " CHAVE: " + zCancInut);
                    this.readerNfs.readInutilizacao310(record, zCancInut1, this.functionService);
                    LogUtil.info("Finalizando Inutilização NF-S. DOCNUM: " + zCancInut1.getString("DOCNUM") + " CHAVE: " + zCancInut);
                } else {
                    LogUtil.info("Iniciando Inutilização NF-E. DOCNUM: " + zCancInut1.getString("DOCNUM") + " CHAVE: " + zCancInut);
                    //this.readerNfe.readInutilizacao310(record, zCancInut1);
                    this.readerNfe.readInutilizacao400(record, zCancInut1);
                    LogUtil.info("Finalizando Inutilização NF-E. DOCNUM: " + zCancInut1.getString("DOCNUM") + " CHAVE: " + zCancInut);
                }
            } else if(exportZXmlOut.equals(DocType.NFS)) {
                LogUtil.info("Iniciando Emissão NF-S. DOCNUM: " + record.getStructure("IS_NFE_HEADER").getString("DOCNUM"));
                this.readerNfs.read310(record, docStat);
                LogUtil.info("Finalizando Emissão NF-S. DOCNUM: " + record.getStructure("IS_NFE_HEADER").getString("DOCNUM"));
            } else {
                LogUtil.info("Iniciando Emissão NF-E. DOCNUM: " + record.getStructure("IS_NFE_HEADER").getString("DOCNUM"));
//                this.readerNfe.read310(record, docStat);
                LogUtil.info("Finalizando Emissão NF-E. DOCNUM: " + record.getStructure("IS_NFE_HEADER").getString("DOCNUM"));
            }
        } else if(function.getName().equalsIgnoreCase("J_1B_NFE_XML_OUT")) {
            String docStat1 = record.getStructure("XML_IN").getString("DOCSTAT");
            JCoTable exportZXmlOut1;
            if("1".equals(docStat1)) {
                exportZXmlOut1 = record.getTable("XML_EXT2");
                exportZXmlOut1.firstRow();
                docType = exportZXmlOut1.getString("DOCNUM");
                JCoParameterList accessKey1 = this.functionService.callZContingenciaAccesskeySearchByDocnum(docType);
                zCancInut = accessKey1.getString("E_CHV_NFR");
                if(zCancInut == null || zCancInut.length() == 0) {
                    zCancInut = record.getStructure("XML_IN").getString("ID");
                }

                zCancInut1 = null;

                try {
                    zCancInut1 = this.functionService.callZCancelInutilizacao(zCancInut, docType);
                } catch (Exception var13) {
                    LogUtil.error(var13);
                }

                String ie = zCancInut1.getString("IE");
                String xJust = exportZXmlOut1.getString("VALUE");
                LogUtil.info("Iniciando Cancelamento por Evento. DOCNUM: " + docType);
                this.readerNfe.readEventCancellation(zCancInut, docType, ie, xJust);
                LogUtil.info("Finalizando Cancelamento por Evento. DOCNUM: " + docType);
            } else if("2".equals(docStat1)) {
                exportZXmlOut1 = record.getTable("XML_EXT2");
                exportZXmlOut1.firstRow();
                docType = exportZXmlOut1.getString("DOCNUM");
                accessKey = record.getStructure("XML_IN").getString("ID");
                JCoParameterList zCancInut2 = this.functionService.callZCancelInutilizacao(accessKey, docType);
                LogUtil.info("Iniciando Inutilização NF-E. DOCNUM: " + zCancInut2.getString("DOCNUM") + " CHAVE: " + accessKey);
                this.readerNfe.readInutilizacao(record, zCancInut2);
                LogUtil.info("Finalizando Inutilização NF-E. DOCNUM: " + zCancInut2.getString("DOCNUM") + " CHAVE: " + accessKey);
            } else {
                JCoParameterList exportZXmlOut2 = function.getImportParameterList();
                DocType docType1 = DocType.NFE;
                if(docType1.equals(DocType.NFS)) {
                    LogUtil.info("Iniciando Emissão NF-S. DOCNUM: " + record.getStructure("XML_IN").getString("DOCNUM"));
                    this.readerNfs.read(record, exportZXmlOut2);
                    LogUtil.info("Finalizando Emissão NF-S. DOCNUM: " + record.getStructure("XML_IN").getString("DOCNUM"));
                } else {
                    LogUtil.info("Iniciando Emissão NF-E. DOCNUM: " + record.getStructure("XML_IN").getString("DOCNUM"));
                    this.readerNfe.read(record, exportZXmlOut2);
                    LogUtil.info("Finalizando Emissão NF-E. DOCNUM: " + record.getStructure("XML_IN").getString("DOCNUM"));
                }
            }
        } else if(function.getName().equals("J_1BNFE_CCE_OUT")) {
            LogUtil.info("Iniciando Emissão de CC-e. DOCNUM: " + record.getString("IV_DOCNUM"));
            this.readerNfe.readCCe(record);
            LogUtil.info("Finalizando Emissão de CC-e. DOCNUM: " + record.getString("IV_DOCNUM"));
        }

    }

    private JCoParameterList callZXmlOut(JCoRecord record, String docNum) {
        JCoTable itemTab = record.getTable("XML_ITEM_TAB");
        ZXmlOutImportVO importVO = new ZXmlOutImportVO();
        importVO.setDocNum(docNum);
        if(!itemTab.isEmpty()) {
            String cnpj = record.getStructure("XML_IN").getString(XmlInEnum.CNPJ_TOMADOR.getSapKey());
            if(cnpj != null && !cnpj.equals("") && !cnpj.equals("00000000000000")) {
                importVO.setCpfCnpj(record.getStructure("XML_IN").getString(XmlInEnum.CPF_TOMADOR.getSapKey()));
            } else {
                importVO.setCpfCnpj(record.getStructure("XML_IN").getString(XmlInEnum.CNPJ_TOMADOR.getSapKey()));
            }

            importVO.setItmNum(itemTab.getString("ITMNUM"));
            importVO.setcProd(itemTab.getString("CPROD"));
            importVO.setcProd(itemTab.getString("CPROD"));
            importVO.setItmNum(itemTab.getString("ITMNUM"));
        }

        return this.functionService.callZXmlOut(importVO);
    }
}
