//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.sap;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.camel.ProducerTemplate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.commons.Util;
import com.gsw.integradores.nfe.depara.XmlExt1Enum;
import com.gsw.integradores.nfe.depara.XmlExt2Enum;
import com.gsw.integradores.nfe.depara.XmlInEnum;
import com.gsw.integradores.nfe.depara.XmlItemTabEnum;
import com.gsw.integradores.nfe.depara.ZXmlOutEnum;
import com.gsw.integradores.nfe.service.FunctionService;
import com.gsw.integradores.nfe.vo.CancelamentoVO;
import com.gsw.integradores.nfe.vo.DeducaoVO;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import com.gsw.integradores.nfe.vo.ItemVO;
import com.gsw.integradores.nfe.vo.NfsVO;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class SapReaderNfs {
    private ProducerTemplate producer;
    private String ambiente;
    private String versao;
    private String versaoDados;
    private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");

    public SapReaderNfs(ProducerTemplate producer) {
        this.producer = producer;
        this.ambiente = Properties.dfe_tipoAmbienteSistema.getValue();
        this.versao = Properties.dfe_versaoLayout.getValue();
        this.versaoDados = Properties.dfe_versaoDados.getValue();
    }

    public void read(JCoRecord record, JCoParameterList zXmlOut) {
        try {
            NfsVO e = null;
            e = this.convertToNfsVo(record, zXmlOut);
            String json = this.convertToJson(e).replaceAll("Ã�", "A");
            LogUtil.info(json);
            this.toQueue(e, json);
        } catch (Exception var5) {
            LogUtil.error("Erro ao converter", var5);
        }

    }

    public void read310(JCoRecord record, JCoParameterList zXmlOut) {
        try {
            NfsVO e = null;
            e = this.convertToNfsVo310(record, zXmlOut);
            String json = this.convertToJson(e).replaceAll("Ã�", "A");
            LogUtil.info(json);
            this.toQueue(e, json);
        } catch (Exception var5) {
            LogUtil.error("Erro ao converter", var5);
        }

    }

    public void readCancellation(JCoRecord record, JCoParameterList zXmlOut, JCoParameterList zCancInut) {
        try {
            JCoStructure e = record.getStructure("XML_IN");
            CancelamentoVO cancelamentoVO = new CancelamentoVO(Long.valueOf(zCancInut.getString("DOCNUM")));
            cancelamentoVO.setDocNum(zCancInut.getString("DOCNUM"));
            cancelamentoVO.setCnpjPrestador(e.getString(XmlInEnum.CNPJ_PRESTADOR.getSapKey()));
            cancelamentoVO.setCodCidade(zXmlOut.getString("CITYC"));
            cancelamentoVO.setCodigoCancelamento(zXmlOut.getString(ZXmlOutEnum.COD_CANCELAMENTO.getSapKey()));
            cancelamentoVO.setInscricaoPrestador(zXmlOut.getString("MUNIC_INSC"));
            cancelamentoVO.setMotivoCancelamento(zCancInut.getString("REASON1"));
            cancelamentoVO.setNumeroNfe(zXmlOut.getString(ZXmlOutEnum.NUMERO_NFE.getSapKey()));
            cancelamentoVO.setTipoAmbienteSistema(this.ambiente);
            cancelamentoVO.setVersao(this.versao);
            cancelamentoVO.setCodigoVerificacao(zXmlOut.getString(ZXmlOutEnum.COD_VERIFICACAO.getSapKey()));
            String json = this.convertToJson(cancelamentoVO);
            LogUtil.info(json);
            this.toQueueCancellation(cancelamentoVO, json);
        } catch (Exception var7) {
            LogUtil.error("Erro ao converter", var7);
        }

    }

    protected NfsVO convertToNfsVo(JCoRecord record, JCoParameterList zXmlOut) throws Exception {
        NfsVO nfsVO = null;
        TreeMap mapDePara = new TreeMap();
        JCoStructure structureXmlIN = record.getStructure("XML_IN");
        nfsVO = new NfsVO(Long.valueOf(structureXmlIN.getLong(XmlInEnum.DOC_NUM.getSapKey())));
        this.deparaXmlIn(structureXmlIN, mapDePara);
        this.deparaXmlExt1(record.getTable("XML_EXT1"), mapDePara);
        this.deparaXmlExt2(record.getTable("XML_EXT2"), mapDePara);
        List itens = this.deparaXmlItemTab(record.getTable("XML_ITEM_TAB"), mapDePara);
        this.deparaZXmlOutNfsVo(zXmlOut, mapDePara);
        this.formatDate(mapDePara);
        LogUtil.info(mapDePara.toString());
        BeanUtils.copyProperties(nfsVO, mapDePara);
        if(nfsVO.getCodigoServicoInterno() != null && !nfsVO.getCodigoServicoInterno().equals("")) {
            nfsVO.setCodigoCnae((String)null);
            nfsVO.setItemListaServico((String)null);
            nfsVO.setCodigoTributacaoMunicipio((String)null);
        }

        try {
            nfsVO.setNumeroFatura(Integer.valueOf((String)mapDePara.get("numeroRps")).toString());
        } catch (Exception var9) {
            LogUtil.error("Erro ao transformar o NÃƒÂºmero RPS em numÃƒÂ©rico");
        }

        ArrayList deducoesVO = new ArrayList();
        DeducaoVO deducao = new DeducaoVO();
        deducao.setValorDeduzir((String)mapDePara.get(ZXmlOutEnum.VALOR_DEDUZIR.getFieldMsaf()));
        deducoesVO.add(deducao);
        nfsVO.setDeducoesVO(deducoesVO);
        nfsVO.setVersao(this.versao);
        nfsVO.setTipoAmbienteSistema(this.ambiente);
        nfsVO.setAliquotaServicos(Util.formatAliquota(nfsVO.getAliquotaServicos()));
        nfsVO.setPaisTomador(Util.preencheAEsquerdaCom(nfsVO.getPaisTomador(), "0", 5));
        nfsVO.setVersaoDados(this.versaoDados);
        nfsVO.setItensVO(itens);
        return nfsVO;
    }

    protected NfsVO convertToNfsVo310(JCoRecord record, JCoParameterList zXmlOut) throws Exception {
        NfsVO nfsVO = null;
        JCoStructure structureXmlIN = record.getStructure("IS_NFE_HEADER");
        nfsVO = new NfsVO(Long.valueOf(structureXmlIN.getString("DOCNUM")));
        nfsVO.setDocNum(structureXmlIN.getString("DOCNUM"));
        JCoStructure structureXmlIDE = record.getStructure("IS_NFE_IDE");
        nfsVO.setNumeroRps(structureXmlIDE.getString("N_NF"));
        nfsVO.setSerieRps(structureXmlIDE.getString("SERIE"));
        JCoStructure partnerStructure = record.getStructure("IS_NFE_PARTNER_IDS");
        String partnerEmitenteId = partnerStructure.getString("PARTNER_ID_EMIT");
        String partnerTomadorId = partnerStructure.getString("PARTNER_ID_DEST");
        String partnerEntregaId = partnerStructure.getString("PARTNER_ID_ENTREGA");
        JCoTable parceiros = record.getTable("IT_NFE_PARTNER");

        int itensVO;
        for(itensVO = 0; itensVO < parceiros.getNumRows() && !parceiros.getString("ID").equals(partnerEmitenteId); ++itensVO) {
            parceiros.nextRow();
        }

        nfsVO.setCnpjPrestador(parceiros.getString("CNPJ"));
        nfsVO.setInscricaoPrestador(parceiros.getString("IE"));
        nfsVO.setBairroPrestador(parceiros.getString("X_BAIRRO"));
        nfsVO.setNumeroEnderecoPrestador(parceiros.getString("NRO"));
        nfsVO.setCepPrestador(parceiros.getString("CEP"));
        nfsVO.setUfPrestador(parceiros.getString("UF"));
        nfsVO.setEnderecoPrestador(parceiros.getString("X_LGR"));
        nfsVO.setCodCidade(parceiros.getString("C_MUN"));
        nfsVO.setRazaoSocialPrestador(parceiros.getString("X_NOME"));
        nfsVO.setComplementoEnderecoPrestador(parceiros.getString("X_CPL"));
        nfsVO.setPaisPrestador(parceiros.getString("C_PAIS"));
        nfsVO.setCodigoCnae(parceiros.getString("CNAE"));
        parceiros.firstRow();

        for(itensVO = 0; itensVO < parceiros.getNumRows() && !parceiros.getString("ID").equals(partnerTomadorId); ++itensVO) {
            parceiros.nextRow();
        }

        nfsVO.setCidadeTomador(parceiros.getString("C_MUN"));
        nfsVO.setUfTomador(parceiros.getString("UF"));
        nfsVO.setNumeroEnderecoTomador(parceiros.getString("NRO"));
        nfsVO.setEnderecoTomador(parceiros.getString("X_LGR"));
        if(parceiros.getString("CNPJ") != null && !parceiros.getString("CNPJ").equals("") && !parceiros.getString("CNPJ").equals("00000000000000")) {
            nfsVO.setCpfCnpjTomador(parceiros.getString("CNPJ"));
        } else {
            nfsVO.setCpfCnpjTomador(parceiros.getString("CPF"));
        }

        nfsVO.setRazaoSocialTomador(parceiros.getString("CNPJ"));
        nfsVO.setInscricaoEstadualTomador(parceiros.getString("X_NOME"));
        nfsVO.setComplementoEnderecoTomador(parceiros.getString("X_CPL"));
        nfsVO.setBairroTomador(parceiros.getString("X_BAIRRO"));
        nfsVO.setPaisTomador(parceiros.getString("C_PAIS"));
        nfsVO.setCepTomador(parceiros.getString("CEP"));
        nfsVO.setEmailTomador(parceiros.getString("EMAIL"));
        nfsVO.setInscricaoEstadualTomador(parceiros.getString("IE"));
        nfsVO.setDescricaoRps(record.getStructure("IS_NFE_INFADIC").getString("INF_CPL"));
        nfsVO.setFormaPagamento(record.getTable("IT_NFE_EXT1").getString("FIELD3"));
        nfsVO.setCodigoServicoInterno(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "SITUACAO_INTERNA"));
        ArrayList var22 = new ArrayList();
        boolean infAdProd = Properties.dfe_campoInfAdProd.getBollean();
        JCoTable itemsTableDet = record.getTable("IT_NFE_DET");

        for(int dataEmissaoRps = 0; dataEmissaoRps < itemsTableDet.getNumRows(); ++dataEmissaoRps) {
            JCoTable valoresStructure = record.getTable("IT_NFE_DET_PROD");
            valoresStructure.firstRow();

            for(int valoresRetidosStructure = 0; valoresRetidosStructure < valoresStructure.getNumRows(); ++valoresRetidosStructure) {
                if(valoresStructure.getString("ID").equals(itemsTableDet.getString("ID"))) {
                    ItemVO dadosFatura = new ItemVO();
                    if(infAdProd) {
                        dadosFatura.setDiscriminacaoServico(itemsTableDet.getString("INF_AD_PROD"));
                    } else {
                        dadosFatura.setDiscriminacaoServico(valoresStructure.getString("X_PROD"));
                    }

                    String deducoesVO = valoresStructure.getString("V_UN_COM");
                    if(deducoesVO != null) {
                        try {
                            BigDecimal deducao = new BigDecimal(deducoesVO);
                            deducao = deducao.setScale(4);
                            deducoesVO = deducao.toString();
                        } catch (Exception var21) {
                            LogUtil.error("Valor UnitÃƒÂ¡rio (XML_ITEMM_TAB.VUNCOM_V20) com formataÃƒÂ§ÃƒÂ£o incorreta.");
                        }
                    }

                    dadosFatura.setValorUnitario(deducoesVO);
                    dadosFatura.setQuantidade(valoresStructure.getString("Q_COM"));
                    dadosFatura.setValorTotal(valoresStructure.getString("V_PROD"));
                    var22.add(dadosFatura);
                    valoresStructure.nextRow();
                }
            }

            itemsTableDet.nextRow();
        }

        nfsVO.setItensVO(var22);
        nfsVO.setTipoRps(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "TIPO_RPS", "1"));
        nfsVO.setSituacaoRps(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "SITUACAO_RPS", "1"));
        nfsVO.setOptanteSimplesNacional(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "OPTANTE_SIMPLES", "2"));
        nfsVO.setIncentivadorCultural(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "INCENTIVO_CULTURAL", "2"));
        String var23 = structureXmlIDE.getString("DH_EMI");
        var23 = var23.replaceAll("-", "") + "000000";
        var23 = Util.formatDateTimeFromStringSap(var23);
        nfsVO.setDataEmissaoRps(var23);
        nfsVO.setItemListaServico(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "LISTA_SERVICO"));
        nfsVO.setCodigoTributacaoMunicipio(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "TRIBUTACAO_MUNICIPIO"));
        nfsVO.setNaturezadaOperacao(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "NATUREZA_OPERACAO"));
        nfsVO.setTipoRecolhimento(Util.getValueFromExt2(record.getTable("IT_NFE_EXT2"), "TIPO_RECOLHIMENTO"));
        if(nfsVO.getCpfCnpjTomador() != null && Integer.valueOf(nfsVO.getCpfCnpjTomador()).intValue() > 11) {
            nfsVO.setIndicacaoCpfCnpj("2");
        } else {
            nfsVO.setIndicacaoCpfCnpj("1");
        }

        JCoStructure var24 = record.getStructure("IS_NFE_ISSQNTOT");
        JCoStructure var25 = record.getStructure("IS_NFE_RETTRIB");
        nfsVO.setValorServicos(var24.getString("V_SERV"));
        nfsVO.setBaseCalculo(var24.getString("V_BC"));
        nfsVO.setValorPis(var24.getString("V_PIS"));
        nfsVO.setValorCofins(var24.getString("V_COFINS"));
        nfsVO.setValorIss(var24.getString("V_ISS"));
        nfsVO.setValorIssRetido(var24.getString("V_ISSRET"));
        nfsVO.setValorInss(var25.getString("V_BCRET_PREV"));
        nfsVO.setValorIr(var25.getString("V_BCIRRF"));
        nfsVO.setValorCsll(var25.getString("V_RET_CSLL"));
        nfsVO.setDataCompetencia(Util.formatDateTimeFromStringSap(var25.getString("D_COMPET")));
        JCoStructure var26 = record.getStructure("IS_NFE_FAT");
        nfsVO.setValorFatura(var26.getString("V_LIQ"));
        parceiros.firstRow();

        for(int var27 = 0; var27 < parceiros.getNumRows() && !parceiros.getString("ID").equals(partnerEntregaId); ++var27) {
            parceiros.nextRow();
        }

        nfsVO.setMunicipioPrestacao(parceiros.getString("C_MUN"));
        if(nfsVO.getCodigoServicoInterno() != null && !nfsVO.getCodigoServicoInterno().equals("")) {
            nfsVO.setCodigoCnae((String)null);
            nfsVO.setItemListaServico((String)null);
            nfsVO.setCodigoTributacaoMunicipio((String)null);
        }

        try {
            nfsVO.setNumeroFatura(Integer.valueOf(nfsVO.getNumeroRps()).toString());
        } catch (Exception var20) {
            LogUtil.error("Erro ao transformar o NÃƒÂºmero RPS em numÃƒÂ©rico de: " + nfsVO.getNumeroRps());
        }

        ArrayList var28 = new ArrayList();
        DeducaoVO var29 = new DeducaoVO();
        var29.setValorDeduzir(var24.getString("V_DEDUCAO"));
        var28.add(var29);
        nfsVO.setDeducoesVO(var28);
        nfsVO.setVersao(this.versao);
        nfsVO.setTipoAmbienteSistema(this.ambiente);
        nfsVO.setAliquotaServicos(Util.formatAliquota(nfsVO.getAliquotaServicos()));
        nfsVO.setPaisTomador(Util.preencheAEsquerdaCom(nfsVO.getPaisTomador(), "0", 5));
        nfsVO.setVersaoDados(this.versaoDados);
        LogUtil.info("DOCNUM: " + nfsVO.getDocNum() + " Nota ServiÃƒÂ§o: " + nfsVO.toString());
        return nfsVO;
    }

    private void formatDate(Map<String, String> mapDePara) {
        String dataSap = (String)mapDePara.get(ZXmlOutEnum.DATA_EMISSAO_RPS.getFieldMsaf());
        if(StringUtils.isNotBlank(dataSap)) {
            mapDePara.put(ZXmlOutEnum.DATA_EMISSAO_RPS.getFieldMsaf(), Util.formatDateTimeFromStringSap(dataSap));
        }

        String dataSapCompetencia = (String)mapDePara.get(ZXmlOutEnum.DATA_COMPETENCIA.getFieldMsaf());
        if(StringUtils.isNotBlank(dataSapCompetencia)) {
            mapDePara.put(ZXmlOutEnum.DATA_COMPETENCIA.getFieldMsaf(), Util.formatDateTimeFromStringSap(dataSapCompetencia));
        }

    }

    private void deparaZXmlOutNfsVo(JCoParameterList zXmlOut, Map<String, String> map) {
        ZXmlOutEnum[] var6;
        int var5 = (var6 = ZXmlOutEnum.values()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            ZXmlOutEnum xmlZOut = var6[var4];

            try {
                map.put(xmlZOut.getFieldMsaf(), zXmlOut.getString(xmlZOut.getSapKey()));
            } catch (Exception var8) {
                LogUtil.error("Problemas ao recuperar campo " + xmlZOut.getFieldMsaf() + " da funÃƒÂ§ÃƒÂ£o Z_MSAF_DFE_XML_OUT. Mensagem: " + var8.getMessage());
            }
        }

    }

    private void deparaXmlIn(JCoStructure structureXmlIN, Map<String, String> map) {
        XmlInEnum[] var6;
        int var5 = (var6 = XmlInEnum.values()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            XmlInEnum cnpj = var6[var4];
            map.put(cnpj.getFieldMsaf(), structureXmlIN.getString(cnpj.getSapKey()));
        }

        String var7 = structureXmlIN.getString(XmlInEnum.CNPJ_TOMADOR.getSapKey());
        if(var7 != null && !var7.equals("") && !var7.equals("00000000000000")) {
            map.put(XmlInEnum.CNPJ_TOMADOR.getFieldMsaf(), var7);
        } else {
            map.put(XmlInEnum.CPF_TOMADOR.getFieldMsaf(), structureXmlIN.getString(XmlInEnum.CPF_TOMADOR.getSapKey()));
        }

    }

    private void deparaXmlExt1(JCoTable jcoTableXmlExt1, Map<String, String> map) {
        XmlExt1Enum[] var6;
        int var5 = (var6 = XmlExt1Enum.values()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            XmlExt1Enum xmlExt1Enum = var6[var4];

            try {
                map.put(xmlExt1Enum.getFieldMsaf(), jcoTableXmlExt1.getString(xmlExt1Enum.getSapKey()));
            } catch (Exception var8) {
                LogUtil.error("Campo XML_EXT1." + xmlExt1Enum.getSapKey() + " nÃƒÂ£o existe!");
            }
        }

    }

    private void deparaXmlExt2(JCoTable jcoTableXmlExt2, Map<String, String> map) {
        XmlExt2Enum[] var6;
        int var5 = (var6 = XmlExt2Enum.values()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            XmlExt2Enum xmlExt2Enum = var6[var4];

            try {
                map.put(xmlExt2Enum.getFieldMsaf(), Util.getValueFromExt2(jcoTableXmlExt2, xmlExt2Enum.getSapKey()));
            } catch (Exception var8) {
                LogUtil.error("Campo XML_EXT1." + xmlExt2Enum.getSapKey() + " nÃƒÂ£o existe!");
            }
        }

    }

    private List<ItemVO> deparaXmlItemTab(JCoTable jCoTable, Map<String, String> map) {
        ArrayList itensVO = new ArrayList();
        boolean infAdProd = Properties.dfe_campoInfAdProd.getBollean();

        for(int i = 0; i < jCoTable.getNumRows(); ++i) {
            XmlItemTabEnum[] var9;
            int e = (var9 = XmlItemTabEnum.values()).length;

            for(int vuncom_v20 = 0; vuncom_v20 < e; ++vuncom_v20) {
                XmlItemTabEnum item = var9[vuncom_v20];
                if(infAdProd || !item.getSapKey().equals(XmlItemTabEnum.DISCRIMINACAO_INFADPROD.getSapKey())) {
                    map.put(item.getFieldMsaf(), jCoTable.getString(item.getSapKey()));
                }
            }

            ItemVO var11 = new ItemVO();
            var11.setDiscriminacaoServico((String)map.get(XmlItemTabEnum.DISCRIMINACAO_SERVICO.getFieldMsaf()));
            var11.setQuantidade(jCoTable.getString(XmlItemTabEnum.QUANTIDADE.getSapKey()));
            String var12 = jCoTable.getString(XmlItemTabEnum.VALOR_UNITARIO.getSapKey());
            if(var12 != null) {
                try {
                    BigDecimal var13 = new BigDecimal(var12);
                    var13 = var13.setScale(4);
                    var12 = var13.toString();
                } catch (Exception var10) {
                    LogUtil.error("Valor UnitÃƒÂ¡rio (XML_ITEMM_TAB.VUNCOM_V20) com formataÃƒÂ§ÃƒÂ£o incorreta.");
                }
            }

            var11.setValorUnitario(var12);
            var11.setValorTotal(jCoTable.getString(XmlItemTabEnum.VALOR_TOTAL.getSapKey()));
            itensVO.add(var11);
            jCoTable.nextRow();
        }

        return itensVO;
    }

    private String convertToJson(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    private void toQueue(NfsVO nfsVo, String jsonVo) {
        this.producer.sendBody("{{nfs.queue.out}}", jsonVo);
    }

    private void toQueueCancellation(CancelamentoVO cancelamentoVO, String jsonVo) {
        this.producer.sendBody("{{nfs.queue.cancellation.out}}", jsonVo);
    }

    public void readInutilizacao(JCoRecord record, JCoParameterList zCancInut, FunctionService functionService) {
        FeedbackNfsERP feedback = new FeedbackNfsERP(zCancInut.getString("DOCNUM"));
        feedback.setStatus(Integer.valueOf(102));
        feedback.setCodigoVerificacao("000000000000000");
        feedback.setDataAprovacao(this.formater.format(new Date()));
        functionService.callXmlInInutilizacaoNfs(feedback);
    }

    public void readInutilizacao310(JCoRecord record, JCoParameterList zCancInut, FunctionService functionService) {
        FeedbackNfsERP feedback = new FeedbackNfsERP(zCancInut.getString("DOCNUM"));
        feedback.setStatus(Integer.valueOf(102));
        feedback.setCodigoVerificacao("000000000000000");
        feedback.setDataAprovacao(this.formater.format(new Date()));
        functionService.callXmlInInutilizacaoNfs(feedback);
    }
}
