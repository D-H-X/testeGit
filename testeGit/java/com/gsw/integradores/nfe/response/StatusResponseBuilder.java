//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.response;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.repository.NfsRepositoryWriter;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.camel.Header;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class StatusResponseBuilder {
    private static String CLASSE_RETORNO;

    public StatusResponseBuilder() {
        try {
            Properties e = new Properties();
            e.load(ClassLoader.getSystemResourceAsStream("system-config.properties"));
            CLASSE_RETORNO = e.getProperty("classe.retorno.nfs");
        } catch (IOException var2) {
            LogUtil.error("Erro ao ler propertie system-config.properties", var2);
            throw new RuntimeException(var2);
        }
    }

    public List<FeedbackNfsERP> build(InputStream body, @Header("CamelHttpResponseCode") Integer responseCode) throws Exception {
        LogUtil.info("---------------------------------------------");
        LogUtil.info("Buscando retorno das NFS-e do Portal V3 - " + responseCode);
        LogUtil.info("---------------------------------------------");
        List list = null;
        if(responseCode.intValue() == 200) {
            list = this.createListFeedback(body);
        } else if(responseCode.intValue() == 400) {
            list = this.createListFeedbackBadRequest(body);
        } else {
            LogUtil.error("Status HTTP: " + responseCode);
            LogUtil.error("Falha ao comunicar-se com o Webservice!");
            LogUtil.error("Favor rever as parametrizaÃ§Ãµes do webservice e/ou do usuÃ¡rio");
            LogUtil.error(String.format("responseCode = \'%1$s\' body = %2$s", new Object[]{responseCode, body.toString()}));
        }

        LogUtil.info("---------------------------------------------");
        return list;
    }

    private List<FeedbackNfsERP> createListFeedback(InputStream body) throws Exception {
        ArrayList listFeedbackNfsERP = new ArrayList();
        ObjectMapper mapper = new ObjectMapper();
        Map response = (Map)mapper.readValue(body, Map.class);
        Map retorno = (Map)response.get("retorno");
        LogUtil.info("******Retorno NFS-e do webservice******");
        LogUtil.info(response.toString());
        LogUtil.info("******Retorno NFS-e do webservice******");
        List listRps = (List)retorno.get("rpss");
        Iterator var8 = listRps.iterator();

        while(var8.hasNext()) {
            Map map = (Map)var8.next();
            Map mapFeedback = (Map)map.get("nota");
            LogUtil.info("RPS: " + mapFeedback.toString());
            FeedbackNfsERP feedback = new FeedbackNfsERP((String)mapFeedback.get("docNum"));
            BeanUtils.copyProperties(feedback, mapFeedback);
            listFeedbackNfsERP.add(feedback);
        }

        return listFeedbackNfsERP;
    }

    private List<FeedbackNfsERP> createListFeedbackBadRequest(InputStream body) throws Exception {
        ArrayList listFeedbackNfsERP = new ArrayList();
        ArrayList retorno = (ArrayList)this.createMapResponse(body, "result");
        Iterator var5 = retorno.iterator();

        while(var5.hasNext()) {
            Map retornoRps = (Map)var5.next();
            LogUtil.info("RPS http400: " + retornoRps.toString());
            FeedbackNfsERP feedback = new FeedbackNfsERP((String)retornoRps.get("docNum"));
            BeanUtils.copyProperties(feedback, retornoRps);
            listFeedbackNfsERP.add(feedback);
        }

        return listFeedbackNfsERP;
    }

    private List<Map<String, Object>> createMapResponse(InputStream body, String key) throws Exception {
        Object listRps = null;
        ObjectMapper mapper = new ObjectMapper();
        Map response = (Map)mapper.readValue(body, Map.class);
        if(response.get(key) != null) {
            listRps = (List)response.get(key);
        } else {
            listRps = new ArrayList();
            ((List)listRps).add(response);
        }

        return (List)listRps;
    }

    public void callSyncResponse(FeedbackNfsERP feedbackNfsERP) throws Exception {
        NfsRepositoryWriter writer = (NfsRepositoryWriter)Class.forName(CLASSE_RETORNO).newInstance();
        writer.write(feedbackNfsERP);
    }
}
