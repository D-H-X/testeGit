//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.response;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.camel.Header;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class NfeSendResponseBuilder {
    public NfeSendResponseBuilder() {
    }

    public FeedbackNfeERP build(InputStream body, @Header("CamelHttpResponseCode") int responseCode, @Header("transactionId") String docNum) {
        FeedbackNfeERP feedback = new FeedbackNfeERP(docNum);
        String texto = this.getResponseString(body);
        LogUtil.info(texto);
        if(responseCode / 100 == 2) {
            feedback.setxDescricao("Aguardando Processamento");
            feedback.setcStat(Integer.valueOf(10));
            LogUtil.info("DOCNUM: " + docNum + " Mensagem recebida de retorno do POST HttpCode: " + responseCode + " Body: " + texto);
        } else if(responseCode == 400) {
            try {
                this.copyProperties(texto, feedback);
            } catch (Exception var7) {
                feedback.setcStat(Integer.valueOf(999));
                feedback.setxDescricao("Erro ao realizar parse do retorno -" + var7.getMessage());
                LogUtil.error("DOCNUM: " + docNum + " Erro ao fazer parse do retorno", var7);
            }

            LogUtil.info("DOCNUM: " + docNum + " Montando os dados de retorno ao ERP HttpCode: " + responseCode + " Body: " + texto);
        } else if(responseCode != 403 && responseCode != 401) {
            feedback.setcStat(Integer.valueOf(999));
            feedback.setxDescricao("Nao foi enviar nota para o webservice");
            LogUtil.error("DOCNUM: " + docNum + " Nao foi possivel realizar parse do resultado HttpCode: " + responseCode + " Body: " + texto);
        } else {
            feedback.setcStat(Integer.valueOf(999));
            feedback.setxDescricao("Nao foi possivel logar no webservice");
            LogUtil.error("DOCNUM: " + docNum + " Nao foi possivel logar no webservice. HttpCode: " + responseCode);
        }

        return feedback;
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

    private void copyProperties(String body, FeedbackNfeERP feedbackNfeERP) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map temp = (Map)mapper.readValue(body, Map.class);
        LogUtil.info(temp.toString());
        if(temp.get("result") instanceof List) {
            List list = (List)temp.get("result");
            BeanUtils.copyProperties(feedbackNfeERP, list.get(0));
        } else if(temp.get("result") instanceof String) {
            feedbackNfeERP.setxDescricao((String)temp.get("result"));
            feedbackNfeERP.setcStat(Integer.valueOf(999));
        }

    }
}
