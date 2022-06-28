//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.request;

import com.gsw.integradores.nfe.commons.IntegradorUtil;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.PackageSend;
import com.gsw.integradores.nfe.response.NfeStatusResponseBuilder;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.gsw.integradores.nfe.vo.ResponseVO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.camel.Header;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class NfeMarkCheckRequestBuilder {
    public NfeMarkCheckRequestBuilder() {
    }

    public String build(@Header("idProcessamento") Long idProcessamento, @Header("idProcessamentoLote") Long idProcessamentoLote) throws Exception {
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("MarcarConsultado");
        ArrayList ids = new ArrayList();
        ids.add(idProcessamento);
        if(idProcessamentoLote != null && idProcessamentoLote.longValue() != 0L) {
            ids.add(idProcessamentoLote);
        }

        packageSend.setIds(ids);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(packageSend);
        LogUtil.info("JSON resposta Portal: " + json);
        String nomeArquivo = "nfesConsultadas";
        LogUtil.info("consultado/" + nomeArquivo);
        LogUtil.info(json);
        IntegradorUtil.salvarJsonNFE("consultado", nomeArquivo, json);
        return json;
    }

    public String buildBatch(ResponseVO responseVO) throws Exception {
        List listFeedbackERP = NfeStatusResponseBuilder.getListFeedbackERP(responseVO);
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("MarcarConsultado");
        ArrayList ids = new ArrayList();
        String referenciaNotas = "[";
        Iterator json = listFeedbackERP.iterator();

        while(json.hasNext()) {
            FeedbackNfeERP mapper = (FeedbackNfeERP)json.next();
            ids.add(mapper.getIdProcessamento());
            referenciaNotas = referenciaNotas + mapper.getDocNum() + ",";
            if(mapper.getIdProcessamentoLote() != null && mapper.getIdProcessamentoLote().longValue() != 0L) {
                ids.add(mapper.getIdProcessamentoLote());
            }
        }

        if(ids.size() > 0) {
            referenciaNotas = referenciaNotas.substring(0, referenciaNotas.length() - 1) + "]";
            packageSend.setIds(ids);
            ObjectMapper mapper1 = new ObjectMapper();
            String json1 = mapper1.writeValueAsString(packageSend);
            LogUtil.info("JSON resposta Portal Lote " + referenciaNotas + ": " + json1);
            String nomeArquivo = "nfesConsultadas";
            LogUtil.info("consultado/" + nomeArquivo);
            LogUtil.info(json1);
            IntegradorUtil.salvarJsonNFE("consultado", nomeArquivo, json1);
            if("".equals(json1)) {
                json1 = null;
            }

            return json1;
        } else {
            LogUtil.info("JSON resposta Portal Lote: Sem Dados para Serem Enviados...");
            return null;
        }
    }
}
