//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.client.FunctionFactory;
import com.gsw.integradores.nfe.client.function.Function;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import java.util.HashMap;
import java.util.Map;

public class FunctionZUpdateActive extends Function {
    public static final String Z_MSAF_DFE_UPDATEACTIVE = "/MSAFDFE/MSAF_DFE_UPDATEACTIVE";

    public FunctionZUpdateActive(JCoDestination destination) {
        super(destination);

        try {
            this.function = FunctionFactory.getFunction(destination, "/MSAFDFE/MSAF_DFE_UPDATEACTIVE");
        } catch (JCoException var3) {
            LogUtil.error("erro ao criar a funcao /MSAFDFE/MSAF_DFE_UPDATEACTIVE", var3);
        }

    }

    public void callZMsafDfeUpdateactiveNFS(FeedbackNfsERP feedback) {
        this.call(this.populateMapNfs(feedback, feedback.getStatus().toString(), feedback.getDescricaoStatus()), this.function);
    }

    public void callZMsafDfeUpdateactiveNFE(FeedbackNfeERP feedback) {
        this.call(this.populateMapNfe(feedback, feedback.getcStat().toString(), feedback.getxDescricao()), this.function);
    }

    private Map<String, Object> populateMapNfe(FeedbackNfeERP feedback, String iCod, String reason) {
        return this.populateFields(feedback.getDocNum(), iCod, (String)null, (String)null, reason);
    }

    private Map<String, Object> populateMapNfs(FeedbackNfsERP feedback, String iCod, String reason) {
        String numeroNfe = feedback.getNumeroNfe();
        Integer camposPrefNo = Integer.valueOf(Properties.dfe_truncaPrefNo.getInt());
        if(!camposPrefNo.equals(Integer.valueOf(0)) && numeroNfe.length() > camposPrefNo.intValue()) {
            numeroNfe = numeroNfe.substring(numeroNfe.length() - camposPrefNo.intValue(), numeroNfe.length());
        }

        return this.populateFields(feedback.getDocNum(), iCod, numeroNfe, feedback.getCodigoVerificacao(), reason);
    }

    private Map<String, Object> populateFields(String docnum, String icod, String prefno, String checod, String reason) {
        String reason1 = null;
        String reason2 = null;
        String reason3 = null;
        String reason4 = null;
        if(reason != null) {
            if(reason.length() >= 0) {
                reason1 = reason.substring(0, reason.length() >= 64?64:reason.length());
            }

            if(reason.length() >= 65) {
                reason2 = reason.substring(64, reason.length() >= 128?128:reason.length());
            }

            if(reason.length() >= 129) {
                reason3 = reason.substring(128, reason.length() >= 192?192:reason.length());
            }

            if(reason.length() >= 193) {
                reason4 = reason.substring(192, reason.length() >= 256?256:reason.length());
            }
        }

        HashMap inParamMap = new HashMap();
        inParamMap.put("P_DOCNUM", docnum);
        inParamMap.put("P_CODE", icod);
        inParamMap.put("P_PREFNO", prefno);
        inParamMap.put("P_CHECOD", checod);
        inParamMap.put("P_REASON1", reason1);
        inParamMap.put("P_REASON2", reason2);
        inParamMap.put("P_REASON3", reason3);
        inParamMap.put("P_REASON4", reason4);
        return inParamMap;
    }
}
