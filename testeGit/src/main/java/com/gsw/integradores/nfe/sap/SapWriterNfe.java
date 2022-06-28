//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.sap;

import com.gsw.integradores.nfe.client.ClientPool;
import com.gsw.integradores.nfe.commons.IntegradorUtil;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.repository.NfeRepositoryWriter;
import com.gsw.integradores.nfe.service.FunctionService;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import org.apache.camel.Body;

public class SapWriterNfe implements NfeRepositoryWriter {
    private FunctionService functionService;

    public SapWriterNfe(FunctionService functionService) {
        this.functionService = functionService;
    }

    public SapWriterNfe() {
        this.functionService = this.obterNovoFunctionService();
    }

    private FunctionService obterNovoFunctionService() {
        return new FunctionService(ClientPool.getDestination(Properties.sap_destinationName.getValue()));
    }

    public void write(@Body FeedbackNfeERP feedback) {
        if(this.functionService == null) {
            this.functionService = this.obterNovoFunctionService();
        }

        
        if(feedback.getDocNum().equals("000000000")) {
        
        feedback.setDocNum(IntegradorUtil.lerArq(feedback.getChave()));

        if(feedback.getnSeqInterno().length() > 2 ) {
        	feedback.setChave(feedback.getnSeqInterno());
        	System.out.println("*********************************************************************");
        	System.out.println("ALTERANDO A CHAVE DA NOTA DE CONTINGENCIA PARA RETORNO A ZCONTINGENCA");
        	System.out.println("*********************************************************************");
        	
        }
        
        }
        
        
        LogUtil.info("==============================================================================");
        LogUtil.info("cStat: " +  feedback.getcStat());
        LogUtil.info("Status: " + feedback.getStatus());
        LogUtil.info("TipoMensagemRetorno: " + feedback.getTipoMensagemRetorno());
        LogUtil.info("==============================================================================");
        
        
        if(feedback.getcStat() != null && feedback.getcStat().intValue() == 135) {
        	if(feedback.getStatus().intValue() == 135) {
                this.functionService.callEventInOk(feedback);
            } else {
                this.functionService.callEventInReject(feedback);
            }
        } else if(feedback.getcStat() != null && feedback.getcStat().intValue() != 10) {
        	
            if(feedback.getTipoMensagemRetorno().equals("1")) {
            	this.functionService.callXmlInOkNfe(feedback);
            } else if(Integer.valueOf(990).equals(feedback.getcStat())) {
            	this.functionService.callXmlInOkNfe(feedback);
                LogUtil.info("Realizando retorno de NF-e em contingÃªncia - Finalizando NF-e no SAP");
                LogUtil.info("DocNum: " + feedback.getDocNum());
            } else if(feedback.getTipoMensagemRetorno().equals("2")) {
            	if(!Integer.valueOf(90).equals(feedback.getcStat()) && !Integer.valueOf(990).equals(feedback.getcStat())
            			&& !Integer.valueOf(10).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(20).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(25).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(30).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(40).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(41).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(42).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(50).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(55).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(60).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(70).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(80).equals(feedback.getcStat()) 
            			&& !Integer.valueOf(40).equals(feedback.getcStat())) {
                    this.functionService.callXmlInRejectNfe(feedback);
                } else {
                    LogUtil.info("DocNum: " + feedback.getDocNum());
                    if(!Integer.valueOf(90).equals(feedback.getcStat()) && !Integer.valueOf(990).equals(feedback.getcStat())) {
                        LogUtil.info("Nota em processamento no portal - " + feedback.getcStat());
                    } else {
                        LogUtil.info("Nota represada(90) ou em contingÃªncia(990) - " + feedback.getcStat());
                        this.functionService.callXmlInOkNfe(feedback);
                    }
                }
            } else if(feedback.getTipoMensagemRetorno().equals("3")) {
                this.functionService.callXmlInDenial(feedback);
            } else if(feedback.getTipoMensagemRetorno().equals("4")) {
                if(Properties.dfe_cancelamentoEvento.getBollean()) {
                    if(feedback.getStatus().intValue() == 101) {
                        this.functionService.callCancEventInOk(feedback);
                    } else {
                        this.functionService.callEventInReject(feedback);
                    }
                } else if(feedback.getStatus().intValue() == 101) {
                    this.functionService.callXmlInCancelledOkNfe(feedback);
                } else {
                    this.functionService.callXmlInRejectCancelledNfe(feedback);
                }
            } else if(feedback.getTipoMensagemRetorno().equals("5")) {
                if(feedback.getStatus().intValue() == 102) {
                    this.functionService.callXmlInInutilizacaoOkNfe(feedback);
                } else {
                    this.functionService.callXmlInRejectInutilizacaoNfe(feedback);
                }
            }
        }

    }
}
