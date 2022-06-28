//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.client.FunctionFactory;
import com.gsw.integradores.nfe.client.function.Function;
import com.gsw.integradores.nfe.client.function.FunctionXmlInCallEnum;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.exception.ExceptionUtils;

public class FunctionEventIn extends Function {
    public static final String J_1BNFE_EVENT_IN = "J_1BNFE_EVENT_IN";
    public static final String DOCNUM = "DOCNUM";
    public static final String EXT_EVENT = "EXT_EVENT";
    public static final String SEQNUM = "SEQNUM";
    public static final String EXT_SEQNUM = "EXT_SEQNUM";
    public static final String ACCKEY = "ACCKEY";
    public static final String AUTHCODE = "AUTHCODE";
    public static final String REPLY_TMPL = "REPLY_TMPL";
    public static final String CODE = "CODE";
    public static final String MSGTYP = "MSGTYP";
    public static final String EXT_REPLY_TMPL = "EXT_REPLY_TMPL";

    public FunctionEventIn(JCoDestination destination) {
        super(destination);

        try {
            this.function = FunctionFactory.getFunction(destination, "J_1BNFE_EVENT_IN");
        } catch (JCoException var3) {
            LogUtil.error("erro ao criar a funcao J_1BNFE_EVENT_IN");
            LogUtil.error(var3);
        }

    }

    public void callEventInOk(FeedbackNfeERP feedback) {
        HashMap inParamMap = new HashMap();
        String dataHora = feedback.getDataHoraAut().replaceAll("[^0-9]+", "");
        inParamMap.put("DOCNUM", feedback.getDocNum());
        inParamMap.put("EXT_EVENT", feedback.getTpEvento());
        inParamMap.put("EXT_SEQNUM", "null".equals(feedback.getNseqEvento())?"0":feedback.getNseqEvento());
        inParamMap.put("SEQNUM", feedback.getnSeqInterno());
        inParamMap.put("ACCKEY", feedback.getChave());
        inParamMap.put("AUTHCODE", feedback.getnProt());
        inParamMap.put("REPLY_TMPL", dataHora.length() > 14?dataHora.substring(0, 14):dataHora);
        inParamMap.put("CODE", feedback.getStatus().toString());
        inParamMap.put("MSGTYP", FunctionXmlInCallEnum.AUTHORIZATION_OK.getiMsgType());
        this.callEvent(inParamMap);
    }

    public void callCancEventInOk(FeedbackNfeERP feedback) {
        HashMap inParamMap = new HashMap();
        String dataHora = feedback.getDataHoraAut().replaceAll("[^0-9]+", "");
        dataHora = dataHora.length() > 14?dataHora.substring(0, 14):dataHora;
        inParamMap.put("DOCNUM", feedback.getDocNum());
        inParamMap.put("EXT_EVENT", feedback.getTpEvento());
        inParamMap.put("EXT_SEQNUM", "null".equals(feedback.getNseqEvento())?"0":feedback.getNseqEvento());
        inParamMap.put("SEQNUM", feedback.getnSeqInterno());
        inParamMap.put("ACCKEY", feedback.getChave());
        inParamMap.put("AUTHCODE", feedback.getnProt());
        inParamMap.put("REPLY_TMPL", dataHora);
        inParamMap.put("CODE", feedback.getStatus().toString());
        inParamMap.put("MSGTYP", FunctionXmlInCallEnum.AUTHORIZATION_OK.getiMsgType());
        inParamMap.put("EXT_REPLY_TMPL", dataHora);
        this.callEvent(inParamMap);
    }

    public void callEventInReject(FeedbackNfeERP feedback) {
        HashMap inParamMap = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataHora = sdf.format(new Date(System.currentTimeMillis()));
        inParamMap.put("DOCNUM", feedback.getDocNum());
        inParamMap.put("EXT_EVENT", feedback.getTpEvento());
        inParamMap.put("EXT_SEQNUM", "null".equals(feedback.getNseqEvento())?"0":feedback.getNseqEvento());
        inParamMap.put("SEQNUM", feedback.getnSeqInterno());
        inParamMap.put("ACCKEY", feedback.getChave());
        inParamMap.put("REPLY_TMPL", dataHora);
        inParamMap.put("CODE", feedback.getStatus().toString());
        inParamMap.put("MSGTYP", FunctionXmlInCallEnum.REJECT.getiMsgType());
        this.callEvent(inParamMap);
    }

    protected JCoParameterList callEvent(Map<String, String> inParamMap) {
        JCoParameterList result = null;
        JCoParameterList inParam = this.function.getTableParameterList();

        try {
            JCoTable e = inParam.getTable("IT_EVENTS");
            e.appendRow();
            Iterator var6 = inParamMap.entrySet().iterator();

            while(var6.hasNext()) {
                Entry entry = (Entry)var6.next();
                e.setValue((String)entry.getKey(), (String)entry.getValue());
            }

            inParam.setValue("IT_EVENTS", e);
            LogUtil.info("Chamando funÃ§Ã£o " + this.function.getName() + " no SAP...");
            LogUtil.info("ParÃ¢metro utilizados: " + inParam.toXML());
            
            this.function.execute(this.destination);
            result = this.function.getExportParameterList();
            LogUtil.info("Recebendo retorno da funÃ§Ã£o " + this.function.getName());
            LogUtil.info("Retorno: " + (result != null?result.toXML():"result Ã© nulo!"));
        } catch (Exception var10) {
            LogUtil.error("Erro na chamada da funcao" + this.function.getName() + " - " + ExceptionUtils.getStackTrace(var10));
        } finally {
            inParam.clear();
        }

        return result;
    }
}
