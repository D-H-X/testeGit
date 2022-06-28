//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.response;

import com.gsw.integradores.nfe.commons.IntegradorUtil;
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
public class NfeInvoisysSendResponseBuilder {
    public NfeInvoisysSendResponseBuilder() {
    }

    public FeedbackNfeERP build(InputStream body, @Header("CamelHttpResponseCode") int responseCode) {
    	String texto = this.getResponseString(body);
    	String vChave = (texto.substring(texto.indexOf("chaveDeAcesso") + 16, texto.indexOf("chaveDeAcesso") + 60));
    	
    	
    	String docNum = IntegradorUtil.lerArq(vChave);
    	
    	
    	FeedbackNfeERP feedback = new FeedbackNfeERP(docNum);
        
        LogUtil.info(texto);
        if(responseCode / 100 == 2) {
            feedback.setxDescricao("Aguardando Processamento");
            feedback.setcStat(Integer.valueOf(10));
            LogUtil.info("DOCNUM: " + docNum + " Mensagem recebida de retorno do POST HttpCode: " + responseCode + " Body: " + texto);
        } else if(responseCode == 400) {
            try {
                this.copyProperties(texto, feedback);
                feedback.setcStat(Integer.valueOf(999));
                feedback.setxDescricao("Erro ao realizar parse do retorno - " + texto);
                
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

    
    
    public FeedbackNfeERP buildRet(InputStream body, @Header("CamelHttpResponseCode") int responseCode) {
    	String texto     = this.getResponseString(body);
    	String vUF       = (texto.substring(texto.indexOf("chaveDeAcesso") + 16, texto.indexOf("chaveDeAcesso") + 18));
    	String vAno      = (texto.substring(texto.indexOf("chaveDeAcesso") + 18, texto.indexOf("chaveDeAcesso") + 20));
    	String vMes      = (texto.substring(texto.indexOf("chaveDeAcesso") + 20, texto.indexOf("chaveDeAcesso") + 22));
    	String vCnpj     = (texto.substring(texto.indexOf("chaveDeAcesso") + 22, texto.indexOf("chaveDeAcesso") + 36));
    	String vMod      = (texto.substring(texto.indexOf("chaveDeAcesso") + 36, texto.indexOf("chaveDeAcesso") + 38));
    	String vDV       = (texto.substring(texto.indexOf("chaveDeAcesso") + 59, texto.indexOf("chaveDeAcesso") + 60));
    	String vChave    = (texto.substring(texto.indexOf("chaveDeAcesso") + 16, texto.indexOf("chaveDeAcesso") + 60));
    	String vNnf		 = (texto.substring(texto.indexOf("chaveDeAcesso") + 41, texto.indexOf("chaveDeAcesso") + 50));
    	String vCNF      = (texto.substring(texto.indexOf("chaveDeAcesso") + 51, texto.indexOf("chaveDeAcesso") + 59));
    	String vFEmis    = (texto.substring(texto.indexOf("chaveDeAcesso") + 50, texto.indexOf("chaveDeAcesso") + 51));
    	String vTpAmb    = (texto.substring(texto.indexOf("chaveDeAcesso") + 51, texto.indexOf("chaveDeAcesso") + 59));
    	String vSerie    = (texto.substring(texto.indexOf("chaveDeAcesso") + 38, texto.indexOf("chaveDeAcesso") + 41));
    	String vProt     = (texto.substring(texto.indexOf("protocoloDeAutorizacao") + 25, texto.indexOf("protocoloDeAutorizacao") + 40));
    	String vdatAut   = (texto.substring(texto.indexOf("dataDeAutorizacao") + 20, texto.indexOf("dataDeAutorizacao") + 39));
    	String vStatus   = (texto.substring(texto.indexOf("statusSEFAZ") + 14, texto.indexOf("statusSEFAZ") + 17));
    	int vStat =  Integer.parseInt(vStatus); 
    	
    	String docNum = IntegradorUtil.lerArq(vChave);
    	
//    	preencher os valores de feedback de acordo com o que retorna da consulta na api invoisys
    	FeedbackNfeERP feedback = new FeedbackNfeERP(docNum);
        
    	
    	feedback.setAno(vAno);
    	feedback.setcDV(vDV);
    	feedback.setChave(vChave);
    	feedback.setcUF(vUF);
    	feedback.setcMsg("PROCESSO FINALIZADO");
    	feedback.setcNF(vCNF);
    	feedback.setCnpjEmissor(vCnpj);
    	feedback.setcStat(vStat);
    	feedback.setDataHoraAut(vdatAut);
    	feedback.setDataHoraAutTimeZone(null);
    	feedback.setFemis(vFEmis);
    	feedback.setMes(vMes);
    	feedback.setMod(vMod);
    	feedback.setnProt(vProt);
    	feedback.setSerie(vSerie);
    	feedback.setStatus(vStat);
    	feedback.setTipoEnvioNota(vFEmis);
    	
    	
        
        if(responseCode / 100 == 2) {
//            feedback.setxDescricao("Aguardando Processamento");
//            feedback.setcStat(Integer.valueOf(10));
        	if(feedback.getcStat() == 100) {
        		
        		feedback.setTipoMensagemRetorno("1");
        	}
        	
            LogUtil.info("DOCNUM: " + docNum + " Mensagem recebida de retorno do POST HttpCode: " + responseCode + " Body: " + texto);
        } else if(responseCode == 400) {
            try {
                this.copyProperties(texto, feedback);
                feedback.setcStat(Integer.valueOf(999));
                feedback.setxDescricao("Erro ao realizar parse do retorno - " + texto);
                
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
    
    
    
    
    
//    
//    public String buildRet(InputStream body, @Header("CamelHttpResponseCode") int responseCode) {
//
//    	
//    	String texto     = this.getResponseString(body);
//    	String vUF       = (texto.substring(texto.indexOf("chaveDeAcesso") + 16, texto.indexOf("chaveDeAcesso") + 18));
//    	String vAno      = (texto.substring(texto.indexOf("chaveDeAcesso") + 18, texto.indexOf("chaveDeAcesso") + 20));
//    	String vMes      = (texto.substring(texto.indexOf("chaveDeAcesso") + 20, texto.indexOf("chaveDeAcesso") + 22));
//    	String vCnpj     = (texto.substring(texto.indexOf("chaveDeAcesso") + 22, texto.indexOf("chaveDeAcesso") + 36));
//    	String vMod      = (texto.substring(texto.indexOf("chaveDeAcesso") + 36, texto.indexOf("chaveDeAcesso") + 38));
//    	String vDV       = (texto.substring(texto.indexOf("chaveDeAcesso") + 59, texto.indexOf("chaveDeAcesso") + 60));
//    	String vChave    = (texto.substring(texto.indexOf("chaveDeAcesso") + 16, texto.indexOf("chaveDeAcesso") + 60));
//    	String vNnf		 = (texto.substring(texto.indexOf("chaveDeAcesso") + 41, texto.indexOf("chaveDeAcesso") + 50));
//    	String vCNF      = (texto.substring(texto.indexOf("chaveDeAcesso") + 51, texto.indexOf("chaveDeAcesso") + 59));
//    	String vFEmis    = (texto.substring(texto.indexOf("chaveDeAcesso") + 50, texto.indexOf("chaveDeAcesso") + 51));
//    	String vTpAmb    = (texto.substring(texto.indexOf("chaveDeAcesso") + 51, texto.indexOf("chaveDeAcesso") + 59));
//    	String vSerie    = (texto.substring(texto.indexOf("chaveDeAcesso") + 38, texto.indexOf("chaveDeAcesso") + 41));
//    	String vProt     = (texto.substring(texto.indexOf("protocoloDeAutorizacao") + 25, texto.indexOf("protocoloDeAutorizacao") + 40));
//    	String vdatAut   = (texto.substring(texto.indexOf("dataDeAutorizacao") + 20, texto.indexOf("dataDeAutorizacao") + 39));
//    	String vStatus   = (texto.substring(texto.indexOf("statusSEFAZ") + 14, texto.indexOf("statusSEFAZ") + 17));
//    	int    vStat     = Integer.parseInt(vStatus); 
//    	
//    	String docNum = IntegradorUtil.lerArq(vChave);
//    	
////    	preencher os valores de feedback de acordo com o que retorna da consulta na api invoisys
//    	FeedbackNfeERP feedback = new FeedbackNfeERP(docNum);
//        
//    	
//    	feedback.setAno(vAno);
//    	feedback.setcDV(vDV);
//    	feedback.setChave(vChave);
//    	feedback.setcUF(vUF);
//    	feedback.setcMsg("PROCESSO FINALIZADO");
//    	feedback.setcNF(vCNF);
//    	feedback.setCnpjEmissor(vCnpj);
//    	feedback.setcStat(vStat);
//    	feedback.setDataHoraAut(vdatAut);
//    	feedback.setDataHoraAutTimeZone(null);
//    	feedback.setFemis(vFEmis);
//    	feedback.setMes(vMes);
//    	feedback.setMod(vMod);
//    	feedback.setnProt(vProt);
//    	feedback.setSerie(vSerie);
//    	feedback.setStatus(vStat);
//    	feedback.setTipoEnvioNota("TipoEnvioNota");
//    	
//    	if(feedback.getcStat() == 100 ) {
//    		feedback.setTipoMensagemRetorno("1");
//    	}else {
//    		feedback.setTipoMensagemRetorno("2");
//    	}
//    	
//        LogUtil.info(texto);
//        if(responseCode / 100 == 2) {
//            feedback.setxDescricao("Aguardando Processamento");
//            feedback.setcStat(Integer.valueOf(10));
//            LogUtil.info("DOCNUM: " + docNum + " Mensagem recebida de retorno do POST HttpCode: " + responseCode + " Body: " + texto);
//        } else if(responseCode == 400) {
//            try {
//                this.copyProperties(texto, feedback);
//                feedback.setcStat(Integer.valueOf(999));
//                feedback.setxDescricao("Erro ao realizar parse do retorno - " + texto);
//                
//            } catch (Exception var7) {
//                feedback.setcStat(Integer.valueOf(999));
//                feedback.setxDescricao("Erro ao realizar parse do retorno -" + var7.getMessage());
//                LogUtil.error("DOCNUM: " + docNum + " Erro ao fazer parse do retorno", var7);
//            }
//
//            LogUtil.info("DOCNUM: " + docNum + " Montando os dados de retorno ao ERP HttpCode: " + responseCode + " Body: " + texto);
//        } else if(responseCode != 403 && responseCode != 401) {
//            feedback.setcStat(Integer.valueOf(999));
//            feedback.setxDescricao("Nao foi enviar nota para o webservice");
//            LogUtil.error("DOCNUM: " + docNum + " Nao foi possivel realizar parse do resultado HttpCode: " + responseCode + " Body: " + texto);
//        } else {
//            feedback.setcStat(Integer.valueOf(999));
//            feedback.setxDescricao("Nao foi possivel logar no webservice");
//            LogUtil.error("DOCNUM: " + docNum + " Nao foi possivel logar no webservice. HttpCode: " + responseCode);
//        }
//
//        return vChave;
//    }
//    
    

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
