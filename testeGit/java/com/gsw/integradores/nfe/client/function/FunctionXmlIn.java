package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.client.FunctionFactory;
import com.gsw.integradores.nfe.client.function.Function;
import com.gsw.integradores.nfe.client.function.FunctionXmlInCallEnum;
import com.gsw.integradores.nfe.client.function.FunctionZContingencia;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Util;
import com.gsw.integradores.nfe.vo.AccessKeyVO;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import com.gsw.integradores.nfe.vo.ZContingenciaVO;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import java.util.HashMap;
import java.util.Map;

public class FunctionXmlIn
extends Function {
    public FunctionXmlIn(JCoDestination destination) {
        super(destination);
        try {
            this.function = FunctionFactory.getFunction((JCoDestination)destination, (String)"J_1B_NFE_XML_IN");
            return;
        }
        catch (JCoException e) {
            LogUtil.error((String)"erro ao criar a funcao J_1B_NFE_XML_IN", (Throwable)e);
        }
    }

    public void callXmlInCancelledOkNfs(FeedbackNfsERP feedback) {
        this.call(this.populateMapNfs(feedback, feedback.getStatus().toString(), FunctionXmlInCallEnum.CANCEL_OK.getiMsgType()), this.function);
    }

    public void callXmlInRejectNfs(FeedbackNfsERP feedback) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_MSGTYP", FunctionXmlInCallEnum.REJECT.getiMsgType());
        inParamMap.put("I_CODE", "999");
        this.call(inParamMap, this.function);
    }

    public void callXmlInInutilizacaoNfs(FeedbackNfsERP feedback) {
        Map inParamMap = this.populateMapNfs(feedback, feedback.getStatus() != null ? feedback.getStatus().toString() : "", FunctionXmlInCallEnum.INUTILIZACAO_OK.getiMsgType());
        this.call(inParamMap, this.function);
    }

    private Map<String, Object> populateMapNfs(FeedbackNfsERP feedback, String iCod, String iMsgtype) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_AUTHCODE", "000000000000000");
        inParamMap.put("I_AUTHDATE", Util.getDateToXmlIn((String)feedback.getDataAprovacao()));
        inParamMap.put("I_AUTHTIME", Util.getTimeToXmlIn((String)feedback.getDataAprovacao()));
        inParamMap.put("I_MSGTYP", iMsgtype);
        inParamMap.put("I_CODE", iCod);
        return inParamMap;
    }

    public void callXmlInOkNfe(FeedbackNfeERP feedback) {
        if (feedback.getnProt() == null || feedback.getnProt().equals("")) {
            feedback.setnProt("000000000000000");
        }
        this.call(this.populateMapNfe(feedback, feedback.getcStat().toString(), FunctionXmlInCallEnum.AUTHORIZATION_OK.getiMsgType()), this.function);
    }

    public void callXmlInOkNfs(FeedbackNfsERP feedback) {
        this.call(this.populateMapNfs(feedback, feedback.getStatus().toString(), FunctionXmlInCallEnum.AUTHORIZATION_OK.getiMsgType()), this.function);
    }

    public void callXmlInCancelledOkNfe(FeedbackNfeERP feedback) {
        this.call(this.populateMapNfe(feedback, feedback.getStatus().toString(), FunctionXmlInCallEnum.CANCEL_OK.getiMsgType()), this.function);
    }

    public void callXmlInInutilizacaoOkNfe(FeedbackNfeERP feedback) {
        this.call(this.populateMapNfe(feedback, feedback.getStatus().toString(), FunctionXmlInCallEnum.INUTILIZACAO_OK.getiMsgType()), this.function);
    }

    public void callXmlInRejectCancelledNfe(FeedbackNfeERP feedback) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_ACCKEY", (Object)this.createAccessKeyStructure(feedback.getChave()));
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_AUTHCODE", feedback.getnProt());
        inParamMap.put("I_MSGTYP", FunctionXmlInCallEnum.REJECT_CANCEL.getiMsgType());
        inParamMap.put("I_CODE", feedback.getStatus().toString());
        this.call(inParamMap, this.function);
    }

    public void callXmlInRejectInutilizacaoNfe(FeedbackNfeERP feedback) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_ACCKEY", (Object)this.createAccessKeyStructure(feedback.getChave()));
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_AUTHCODE", feedback.getnProt());
        inParamMap.put("I_AUTHDATE", Util.getDateToXmlIn((String)feedback.getDataHoraAut()));
        inParamMap.put("I_AUTHTIME", Util.getTimeToXmlIn((String)feedback.getDataHoraAut()));
        inParamMap.put("I_MSGTYP", FunctionXmlInCallEnum.REJECT_INUTILIZACAO.getiMsgType());
        inParamMap.put("I_CODE", feedback.getStatus().toString());
        this.call(inParamMap, this.function);
    }

    public void callXmlInRejectNfe(FeedbackNfeERP feedback) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_ACCKEY", (Object)this.createAccessKeyStructure(feedback.getChave()));
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_AUTHCODE", feedback.getnProt());
        inParamMap.put("I_MSGTYP", FunctionXmlInCallEnum.REJECT.getiMsgType());
        inParamMap.put("I_CODE", feedback.getcStat().toString());
        this.call(inParamMap, this.function);
    }

    public void callXmlInDenialNfe(FeedbackNfeERP feedback) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_ACCKEY", (Object)this.createAccessKeyStructure(feedback.getChave()));
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_AUTHCODE", feedback.getnProt());
        inParamMap.put("I_MSGTYP", FunctionXmlInCallEnum.DENIAL.getiMsgType());
        inParamMap.put("I_CODE", feedback.getcStat().toString());
        this.call(inParamMap, this.function);
    }

    private Map<String, Object> populateMapNfe(FeedbackNfeERP feedback, String iCod, String iMsgtype) {
        HashMap<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("I_ACCKEY", (Object)this.createAccessKeyStructure(feedback.getChave()));
        inParamMap.put("I_DOCNUM", feedback.getDocNum());
        inParamMap.put("I_AUTHCODE", feedback.getnProt());
        inParamMap.put("I_AUTHDATE", Util.getDateToXmlIn((String)feedback.getDataHoraAut()));
        inParamMap.put("I_AUTHTIME", Util.getTimeToXmlIn((String)feedback.getDataHoraAut()));
        inParamMap.put("I_MSGTYP", this.corrigeMsgType(iCod, iMsgtype));
        inParamMap.put("I_CODE", this.corrigeICod(iCod));
        if (feedback.getDtEntCont() == null || feedback.getDtEntCont().length() <= 0) {
            if (feedback.getMotCont() == null) return inParamMap;
            if (feedback.getMotCont().length() <= 0) return inParamMap;
        }
        new FunctionZContingencia(this.destination).callRegisterNewAccesskey(new ZContingenciaVO(feedback));
        return inParamMap;
    }

    private String corrigeMsgType(String cod, String iMsgtype) {
        int iCod;
        try {
            iCod = Integer.valueOf(cod);
        }
        catch (Exception e) {
            iCod = 0;
        }
        if (iCod == 100) return "1";
        if (iCod == 990) {
            return "1";
        }
        if (iCod < 201) return iMsgtype;
        if (iCod <= 999) {
            return "2";
        }
        if (iCod == 110) return "3";
        if (iCod == 241) return "3";
        if (iCod == 302) return "3";
        if (iCod == 205) {
            return "3";
        }
        if (iCod == 101) {
            return "4";
        }
        if (iCod != 102) return iMsgtype;
        return "5";
    }

    private String corrigeICod(String cod) {
        int iCod;
        try {
            iCod = Integer.valueOf(cod);
        }
        catch (Exception e) {
            return cod;
        }
        if (iCod == 155) {
            return "151";
        }
        if (iCod != 135) return cod;
        return "101";
    }

    private JCoStructure createAccessKeyStructure(String accessKey) {
        JCoStructure structure = this.function.getImportParameterList().getStructure("I_ACCKEY");
        if (accessKey == null) return structure;
        if (accessKey.trim().equals("")) return structure;
        AccessKeyVO vo = new AccessKeyVO(accessKey);
        structure.setValue("REGIO", vo.getcUF());
        structure.setValue("NFYEAR", vo.getAA());
        structure.setValue("NFMONTH", vo.getmm());
        structure.setValue("STCD1", vo.getCnpj());
        structure.setValue("MODEL", vo.getMod());
        structure.setValue("SERIE", vo.getSerie());
        structure.setValue("NFNUM9", vo.getnNF());
        structure.setValue("DOCNUM9", String.valueOf(vo.getTpEmis()) + vo.getcNF());
        structure.setValue("CDV", vo.getDv());
        return structure;
    }
}
