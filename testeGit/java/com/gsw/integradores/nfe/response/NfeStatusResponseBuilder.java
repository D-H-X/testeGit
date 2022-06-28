//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.response;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.repository.NfeRepositoryWriter;
import com.gsw.integradores.nfe.service.FunctionService;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.gsw.integradores.nfe.vo.ResponseVO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.Header;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class NfeStatusResponseBuilder {
    private static String CLASSE_RETORNO;
    private FunctionService functionService;

    public NfeStatusResponseBuilder(FunctionService functionService) {
        try {
            Properties e = new Properties();
            e.load(ClassLoader.getSystemResourceAsStream("system-config.properties"));
            CLASSE_RETORNO = e.getProperty("classe.retorno.nfe");
            this.functionService = functionService;
        } catch (IOException var3) {
            LogUtil.error("Erro ao ler propertie system-config.properties", var3);
            throw new RuntimeException(var3);
        }
    }

    public ResponseVO buildResponse(String body, @Header("CamelHttpResponseCode") Integer responseCode) {
        return new ResponseVO(responseCode, body);
    }

    public List<LinkedHashMap<String, Object>> buildPrint(ResponseVO responseVO) throws Exception {
        Object list = new ArrayList();
        if(responseVO.getHttpCode().intValue() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            Map response = (Map)mapper.readValue(responseVO.getJson(), Map.class);
            LogUtil.info(response.toString());
            list = (List)response.get("impressao");
        }

        return (List)list;
    }

    public List<LinkedHashMap<String, Object>> buildPrintResponseVO(String body, @Header("CamelHttpResponseCode") Integer responseCode) throws Exception {
        ResponseVO responseVO = this.buildResponse(body, responseCode);
        return this.buildPrint(responseVO);
    }

    public static List<FeedbackNfeERP> getListFeedbackERP(ResponseVO responseVO) throws Exception {
        NfeStatusResponseBuilder a = new NfeStatusResponseBuilder((FunctionService)null);
        List list = null;
        if(responseVO.getHttpCode().intValue() == 200) {
            list = a.createListFeedback(responseVO.getJson());
        } else {
            LogUtil.error("Status HTTP: " + responseVO.getHttpCode());
            LogUtil.error("Falha ao comunicar-se com o Webservice!");
            LogUtil.error("Favor rever as parametrizaÃ§Ãµes do webservice e/ou do usuÃ¡rio");
            LogUtil.error(String.format("responseCode = \'%1$s\' body = %2$s", new Object[]{responseVO.getHttpCode(), responseVO.getJson()}));
        }

        return list;
    }

    public List<FeedbackNfeERP> build(ResponseVO responseVO) throws Exception {
        LogUtil.info("---------------------------------------------");
        LogUtil.info("Buscando retorno das NFE-e do Portal V3 - " + responseVO.getHttpCode());
        LogUtil.info("Buscando retorno das NFE-e do Portal V3 - " + responseVO.getJson());
        LogUtil.info("---------------------------------------------");
        
        List list = null;
        if(responseVO.getHttpCode().intValue() == 200) {
            list = this.createListFeedback(responseVO.getJson());
        } else {
            LogUtil.error("Status HTTP: " + responseVO.getHttpCode());
            LogUtil.error("Falha ao comunicar-se com o Webservice!");
            LogUtil.error("Favor rever as parametrizaÃ§Ãµes do webservice e/ou do usuÃ¡rio");
            LogUtil.error(String.format("responseCode = \'%1$s\' body = %2$s", new Object[]{responseVO.getHttpCode(), responseVO.getJson()}));
        }

        LogUtil.info("---------------------------------------------");
        return list;
    }

    private List<FeedbackNfeERP> createListFeedback(String body) throws Exception {
        LinkedHashMap notas = new LinkedHashMap();
        ObjectMapper mapper = new ObjectMapper();
        Map response = (Map)mapper.readValue(body, Map.class);
        LogUtil.info("******Retorno NF-e do webservice******");
        LogUtil.info(response.toString());
        LogUtil.info("******Retorno NF-e do webservice******");
        Map retorno = (Map)response.get("retorno");
        List listRps = (List)retorno.get("nfes");
        Iterator listCCe = listRps.iterator();

        Map listFeedbackNfsERP;
        while(listCCe.hasNext()) {
            Map listInutilizacao = (Map)listCCe.next();
            listFeedbackNfsERP = (Map)listInutilizacao.get("nota");
            LogUtil.info("Nota: " + (String)listInutilizacao.get("referencia") + " " + listFeedbackNfsERP.toString());
            FeedbackNfeERP mapFeedback = new FeedbackNfeERP((String)listInutilizacao.get("referencia"));
            BeanUtils.copyProperties(mapFeedback, listFeedbackNfsERP);
            notas.put("nf" + (String)listInutilizacao.get("referencia"), mapFeedback);
        }

        List listInutilizacao1 = (List)retorno.get("loteInutilizado");
        Iterator listFeedbackNfsERP1 = listInutilizacao1.iterator();

        while(listFeedbackNfsERP1.hasNext()) {
            Map listCCe1 = (Map)listFeedbackNfsERP1.next();
            Map mapFeedback2 = (Map)listCCe1.get("nota");
            LogUtil.info("Inutilizado: " + (String)listCCe1.get("referencia") + " " + mapFeedback2.toString());
            FeedbackNfeERP mapFeedback1 = new FeedbackNfeERP((String)listCCe1.get("referencia"));
            BeanUtils.copyProperties(mapFeedback1, mapFeedback2);
            notas.put("inut" + (String)listCCe1.get("referencia"), mapFeedback1);
        }

        List listCCe2 = (List)retorno.get("loteCartaCorrecao");
        Iterator mapFeedback3 = listCCe2.iterator();

        while(mapFeedback3.hasNext()) {
            listFeedbackNfsERP = (Map)mapFeedback3.next();
            Map mapFeedback4 = (Map)listFeedbackNfsERP.get("nota");
            LogUtil.info("CC-e: " + (String)listFeedbackNfsERP.get("referencia") + " " + mapFeedback4.toString());
            String docNum = (String)listFeedbackNfsERP.get("referencia");
            FeedbackNfeERP feedback = new FeedbackNfeERP(docNum);
            BeanUtils.copyProperties(feedback, mapFeedback4);
            notas.put("cce" + (String)listFeedbackNfsERP.get("referencia"), feedback);
        }

        ArrayList listFeedbackNfsERP2 = new ArrayList(notas.values());
        return listFeedbackNfsERP2;
    }

    public void callSyncResponse(FeedbackNfeERP feedbackNfeERP) throws Exception {
        NfeRepositoryWriter writer = (NfeRepositoryWriter)Class.forName(CLASSE_RETORNO).newInstance();
        writer.write(feedbackNfeERP);
    }
}
