//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.request;

import com.gsw.integradores.nfe.commons.IntegradorUtil;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.PackageSend;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.camel.Header;
import org.codehaus.jackson.map.ObjectMapper;

public class MarkCheckRequestBuilder {
    public MarkCheckRequestBuilder() {
    }

    public String build(@Header("feedback") FeedbackNfsERP feedback) throws Exception {
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("MarcarConsultado");
        ArrayList ids = new ArrayList();
        ids.add(feedback.getIdProcessamentoLote());
        ids.add(feedback.getIdProcessamentoRps());
        packageSend.setIds(ids);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(packageSend);
        String nomeArquivo = "nfssConsultadas";
        LogUtil.info("consultado/" + nomeArquivo);
        LogUtil.info(json);
        IntegradorUtil.salvarJsonNFS("consultado", nomeArquivo, json);
        return json;
    }

    public String buildBatch(List<FeedbackNfsERP> feedbackNfsERPs) throws Exception {
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("MarcarConsultado");
        ArrayList ids = new ArrayList();
        if(feedbackNfsERPs != null) {
            Iterator json = feedbackNfsERPs.iterator();

            while(json.hasNext()) {
                FeedbackNfsERP mapper = (FeedbackNfsERP)json.next();
                ids.add(mapper.getIdProcessamentoRps());
                if(mapper.getIdProcessamentoLote() != null && mapper.getIdProcessamentoLote().longValue() != 0L) {
                    ids.add(mapper.getIdProcessamentoLote());
                }
            }

            if(ids.size() > 0) {
                packageSend.setIds(ids);
                ObjectMapper mapper1 = new ObjectMapper();
                String json1 = mapper1.writeValueAsString(packageSend);
                LogUtil.info("JSON NFS resposta Portal: " + json1);
                String nomeArquivo = "nfssConsultadas";
                LogUtil.info("consultado/" + nomeArquivo);
                LogUtil.info(json1);
                IntegradorUtil.salvarJsonNFS("consultado", nomeArquivo, json1);
                return json1;
            }

            LogUtil.info("JSON NFS resposta Portal Lote: Sem Dados para Serem Enviados...");
        }

        return null;
    }
}
