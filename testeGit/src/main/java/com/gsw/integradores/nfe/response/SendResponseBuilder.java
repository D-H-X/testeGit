//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.response;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.camel.Header;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SendResponseBuilder {
    public static final String MESSAGE_SEND = "Aguardando Processamento";

    public SendResponseBuilder() {
    }

    public FeedbackNfsERP build(InputStream body, @Header("CamelHttpResponseCode") int responseCode, @Header("transactionId") String docNum) {
        FeedbackNfsERP feedbackNfsERP = new FeedbackNfsERP(docNum);
        feedbackNfsERP.setStatus(Integer.valueOf(999));
        String texto = this.getResponseString(body);
        LogUtil.info(texto);
        if(responseCode / 100 == 2) {
            feedbackNfsERP.setDescricaoStatus("Aguardando Processamento");
            feedbackNfsERP.setStatus(Integer.valueOf(10));
            LogUtil.info("DOCNUM: " + docNum + " Mensagem recebida de retorno do POST HttpCode: " + responseCode + " Body: " + texto);
        } else if(responseCode == 400) {
            try {
                ObjectMapper e = new ObjectMapper();
                Map temp = (Map)e.readValue(texto, Map.class);
                LogUtil.info(temp.toString());
                if(temp.get("result") instanceof List) {
                    List resultList = (List)temp.get("result");
                    if(!resultList.isEmpty()) {
                        BeanUtils.copyProperties(feedbackNfsERP, resultList.get(0));
                    }
                } else {
                    BeanUtils.copyProperties(feedbackNfsERP, temp);
                }
            } catch (Exception var9) {
                feedbackNfsERP.setStatus(Integer.valueOf(999));
                feedbackNfsERP.setDescricaoStatus("Erro ao realizar parse do retorno -" + var9.getMessage());
                LogUtil.error("DOCNUM: " + docNum + " Erro ao fazer parse do retorno", var9);
            }

            LogUtil.info("DOCNUM: " + docNum + " Montando os dados de retorno ao ERP HttpCode: " + responseCode + " Body: " + texto);
        } else if(responseCode != 403 && responseCode != 401) {
            feedbackNfsERP.setDescricaoStatus("Nao foi enviar nota para o webservice");
            LogUtil.error("DOCNUM: " + docNum + " Nao foi possivel realizar parse do resultado HttpCode: " + responseCode + " Body: " + texto);
        } else {
            feedbackNfsERP.setDescricaoStatus("Nao foi possivel logar no webservice");
            LogUtil.error("DOCNUM: " + docNum + " Nao foi possivel logar no webservice. HttpCode: " + responseCode);
        }

        return feedbackNfsERP;
    }

    private String getResponseString(InputStream body) {
        String texto = "";

        try {
            int e1;
            if(body instanceof InputStream) {
                for(boolean e = true; (e1 = body.read()) != -1; texto = texto + (char)e1) {
                    ;
                }
            }
        } catch (Exception var5) {
            LogUtil.error("Erro ao ler stream", var5);
        }

        try {
            body.close();
        } catch (IOException var4) {
            LogUtil.error("Erro ao fechar o stream", var4);
        }

        return texto;
    }
}
