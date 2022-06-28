//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.sap;

import com.gsw.integradores.nfe.IntegracaoSAP;
import com.gsw.integradores.nfe.client.ClientPool;
import com.gsw.integradores.nfe.commons.IntegradorUtil;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.commons.Util;
import com.gsw.integradores.nfe.parser.VelocityHelper;
import com.gsw.integradores.nfe.parser.VelocityParserUtil;
import com.gsw.integradores.nfe.service.FunctionService;
import com.gsw.integradores.nfe.vo.AccessKeyVO;
import com.gsw.integradores.nfe.vo.CancelamentoNfeVO;
import com.gsw.integradores.nfe.vo.CartaCorrecaoNfeVO;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.gsw.integradores.nfe.vo.InutilizacaoNfeVO;
import com.gsw.integradores.nfe.vo.XmlExt2VO;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import java.util.HashMap;
import org.apache.camel.ProducerTemplate;
import org.apache.velocity.tools.generic.EscapeTool;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class SapReaderNfe {
	private ProducerTemplate producer;
	private VelocityHelper velocityHelper = new VelocityHelper();
	private JCoDestination destination;
	private JCoFunction function;
	private String destino;
	String tipoNF;
	
	
	
	public SapReaderNfe(ProducerTemplate producerTemplate) {
		this.producer = producerTemplate;
		this.function = this.obterNovoFunction();
	}

    public JCoFunction obterNovoFunction() {
    	destination = ClientPool.getDestination(Properties.sap_destinationName.getValue());
    	JCoFunction funcExt = null;
    	try {
	    	funcExt = destination
					.getRepository()
					.getFunctionTemplate("ZRFC_EXTR_DADOS_NFE")
					.getFunction();
	    	
    	} catch (JCoException e) {
			e.printStackTrace();
		}	
        return funcExt; //new Function(ClientPool.getDestination(Properties.sap_destinationName.getValue()));
    }

	public void read(JCoRecord record, JCoParameterList zXmlOut) {

		try {
			destino = "";
			
			String vDocnum = record.getStructure("XML_IN").getString("DOCNUM");
			String chaveAcesso = record.getStructure("XML_IN").getString("ID");

	
	//GERA ACESSO A TABELA Z PARA IDENTIFICAR SE É NOTA DO INVOISYS OU DO DFE 
	 
			
			JCoParameterList input = function.getImportParameterList();     
	        input.setValue("I_DOCNUM", vDocnum);     
			
	        try {           
	            function.execute(destination);          
	        } catch (AbapException e) {
	            System.out.println(e.toString());           
	        }
	    
	        
	        JCoTable table = function.getTableParameterList().getTable("XML_ICMS_ST_HEADER");
	        int records = table.getRow();
	        table.setRow(0);
	        
	        
	        try {
	        destino = table.getString("INVOISYS");
	        tipoNF = table.getString("TIPO_NOTA");
	        }catch(Exception e ) {

//	        	TRATATIVA TEMPORARIO ATE AJUSTE DO TUTÃO(15/06/22) --DIEGO
	        	destino = "X";
		        tipoNF  = "D";
	        }
	      //GERA ACESSO A TABELA Z PARA IDENTIFICAR SE É NOTA DO INVOISYS OU DO DFE 
			
			String xmlNfe = this.convertToXml(record, zXmlOut, record.getStructure("XML_IN").getString("VERSION"));
			String cnpj = record.getStructure("XML_IN").getString("C_CNPJ");
		
			if(destino.equalsIgnoreCase("X")) {	
			
				IntegradorUtil.salvarJsonINV(chaveAcesso, vDocnum);
				
				
				
				 //*********CONVERT O XML PARA JSON PARA ENVIO AO PORTAL INVOISYS
		        JSONObject jsonObj = XML.toJSONObject(xmlNfe);
		        //CONVERT ESTE JSON PARA STRING
		        	System.out.println("Não foi ossivel fazer a conversao: " + jsonObj);

		        	
		        String jsonNfe = jsonObj.toString();
		        
		        boolean transp;
		        boolean transporta;
		        transp = jsonNfe.contains("veicTransp");
		        transporta = jsonNfe.contains("transporta\":{");
		        
		        
		        
		        
		        
		        String json = "";
//COMENTEI ESTA LINHA PARA TESTAR A FORMAÇÃO DE LISTA DE NFREF PARA NOTAS NORMAIS TBM		        
//if(tipoNF.equals("M")) {
		        	
		jsonNfe = jsonNfe.replace(",\"nfRef\":{", ",\"nfRef\":[{").replace("},\"nnf\":", "}],\"nnf\":");	
	
		        	
//		        }
		        
		        
		        
		        if(transp && transporta) { 
		        	 json = jsonNfe.replace("detPag\":{", "detPag\":[{").replace("}},\"tot\"", "}]},\"tot\"")
								.replace("det\":{" , "det\":[{").replace("},\"pag" , "}],\"pag")
//								.replace("vol\":{" , "vol\":[{").replace("}}},\"chNFe" , "}]}},\"chNFe");
								.replace("vol\":{" , "vol\":[{").replace("},\"transporta\":{" , "}],\"transporta\":{");
				        LogUtil.info("JSON gerado para envio ao portal INVOISYS: " + json);
		        }else if(transp) {        
		        //TRATA A STRING, INSERINDO OS CARACTER [] PARA REPRESENTAR LISTA NOS NÓS DE DETPAG E PESOL
		        json = jsonNfe.replace("detPag\":{", "detPag\":[{").replace("}},\"tot\"", "}]},\"tot\"")
						.replace("det\":{" , "det\":[{").replace("},\"pag" , "}],\"pag")
						.replace("vol\":{" , "vol\":[{").replace("},\"veicTransp", "}],\"veicTransp" );
		        LogUtil.info("*********************************DIEGO*************************************** " + transp);
		        LogUtil.info("JSON gerado para envio ao portal INVOISYS: " + json);
		        }else if (transporta) {
		        	 json = jsonNfe.replace("detPag\":{", "detPag\":[{").replace("}},\"tot\"", "}]},\"tot\"")
								.replace("det\":{" , "det\":[{").replace("},\"pag" , "}],\"pag")
//								.replace("vol\":{" , "vol\":[{").replace("}}},\"chNFe" , "}]}},\"chNFe");
								.replace("vol\":{" , "vol\":[{").replace("},\"transporta\":{" , "}],\"transporta\":{");
				        LogUtil.info("JSON gerado para envio ao portal INVOISYS: " + json);
				} else{
		        	  //TRATA A STRING, INSERINDO OS CARACTER [] PARA REPRESENTAR LISTA NOS NÓS DE DETPAG E PESOL
			        json = jsonNfe.replace("detPag\":{", "detPag\":[{").replace("}},\"tot\"", "}]},\"tot\"")
							.replace("det\":{" , "det\":[{").replace("},\"pag" , "}],\"pag")
							.replace("vol\":{" , "vol\":[{").replace("}}},\"contingAutomatico" , "}]}},\"contingAutomatico");
			        LogUtil.info("JSON gerado para envio ao portal INVOISYS: " + json);

		        	
		        }
//		        if(tipoNF != "M") {		        
		        this.toQueueInv(chaveAcesso, json);
//		        }
			} else {
				LogUtil.info("XML gerado para envio ao portal: " + xmlNfe);
				this.toQueue(chaveAcesso, xmlNfe);
			}

		} catch (Exception e) {
			System.out.println("DEU ERRO = " + e);
			LogUtil.error("Error reading XML", e);
			e.printStackTrace();
		}
	}

	public void readCancellation(JCoRecord record, JCoTable ext2) {
		try {
			JCoStructure e = record.getStructure("XML_IN");
			CancelamentoNfeVO cancelamentoNfeVO = new CancelamentoNfeVO(Long.valueOf(ext2.getLong("DOCNUM")));
			cancelamentoNfeVO.setChave(e.getString("ID"));
			cancelamentoNfeVO.setCnpj(e.getString("C_CNPJ"));
			cancelamentoNfeVO.setDocNum(ext2.getString("DOCNUM"));
			cancelamentoNfeVO.setIe(ext2.getString("IE"));
			cancelamentoNfeVO.setVersao(e.getString("VERSION"));
			String justificativa = "";
			if (justificativa != null && justificativa.length() >= 15 && justificativa.length() <= 255) {
				cancelamentoNfeVO.setxJustificativa(justificativa);
				String feedback1 = this.convertToJson(cancelamentoNfeVO);
				LogUtil.info(feedback1);
				this.toQueue(cancelamentoNfeVO.getChave(), feedback1, "{{nfe.queue.cancellation.out}}");
			} else {
				LogUtil.info(
						"Campo de Justificativa deve ter tamanho entre 15 e 255 - DocNum: " + ext2.getString("DOCNUM"));
				FeedbackNfeERP feedback = new FeedbackNfeERP(ext2.getString("DOCNUM"));
				feedback.setTipoMensagemRetorno("4");
				feedback.setcStat(Integer.valueOf(999));
				feedback.setStatus(Integer.valueOf(999));
				feedback.setxDescricao("Justificativa deve ter tamanho entre 15 e 255");
				String json = this.convertToJson(feedback);
				LogUtil.info(json);
				this.toQueue(cancelamentoNfeVO.getChave(), json, "{{nfe.queue.in}}");
			}
		} catch (Exception var8) {
			LogUtil.error("Erro ao converter", var8);
		}

	}

	public void readEventCancellationPepsico(JCoRecord record, JCoTable ext2) {
		try {
			JCoStructure e = record.getStructure("XML_IN");
			String accessKey = e.getString("ID");
			String docnum = ext2.getString("DOCNUM");
			CancelamentoNfeVO cancelamentoNfeVO = new CancelamentoNfeVO(Long.valueOf(docnum));
			cancelamentoNfeVO.setChave(accessKey);
			cancelamentoNfeVO.setCnpj((new AccessKeyVO(accessKey)).getCnpj());
			cancelamentoNfeVO.setDocNum(docnum);
			cancelamentoNfeVO.setIe("1");
			cancelamentoNfeVO.setVersao("3.10");
			cancelamentoNfeVO.setnSeqInterno("110111");
			String justificativa = ext2.getString("VALUE");
			if (justificativa != null && justificativa.length() >= 15 && justificativa.length() <= 255) {
				cancelamentoNfeVO.setxJustificativa(justificativa);
				String feedback1 = this.convertToJson(cancelamentoNfeVO);
				LogUtil.info(feedback1);
				this.toQueue(cancelamentoNfeVO.getChave(), feedback1, "{{nfe.queue.cancellation.out}}");
			} else {
				LogUtil.info("Campo de Justificativa deve ter tamanho entre 15 e 255 - DocNum: " + docnum);
				FeedbackNfeERP feedback = new FeedbackNfeERP(docnum);
				feedback.setcStat(Integer.valueOf(135));
				feedback.setStatus(Integer.valueOf(999));
				feedback.setxDescricao("Justificativa deve ter tamanho entre 15 e 255");
				String json = this.convertToJson(feedback);
				LogUtil.info(json);
				this.toQueue(cancelamentoNfeVO.getChave(), json, "{{nfe.queue.in}}");
			}
		} catch (Exception var10) {
			LogUtil.error("Erro ao converter", var10);
		}

	}

	public void readEventCancellation(String accessKey, String docnum, String ie, String xJust) {
		try {
			CancelamentoNfeVO e = new CancelamentoNfeVO(Long.valueOf(docnum));
			e.setChave(accessKey);
			e.setCnpj((new AccessKeyVO(accessKey)).getCnpj());
			e.setDocNum(docnum);
			e.setIe(ie);
			e.setVersao("4.00");
			if (xJust != null && xJust.length() >= 15 && xJust.length() <= 255) {
				e.setxJustificativa(xJust);
				String feedback1 = this.convertToJson(e);
				LogUtil.info(feedback1);
				this.toQueue(e.getChave(), feedback1, "{{nfe.queue.cancellation.out}}");
			} else {
				LogUtil.info("Campo de Justificativa deve ter tamanho entre 15 e 255 - DocNum: " + docnum);
				FeedbackNfeERP feedback = new FeedbackNfeERP(docnum);
				feedback.setcStat(Integer.valueOf(135));
				feedback.setStatus(Integer.valueOf(999));
				feedback.setxDescricao("Justificativa deve ter tamanho entre 15 e 255");
				String json = this.convertToJson(feedback);
				LogUtil.info(json);
				this.toQueue(e.getChave(), json, "{{nfe.queue.in}}");
			}
		} catch (Exception var8) {
			LogUtil.error("Erro ao converter", var8);
		}

	}

	public void readInutilizacao(JCoRecord record, JCoParameterList zCancel) {
		try {
			JCoStructure e = record.getStructure("XML_IN");
			InutilizacaoNfeVO inutilizacaoNfeVO = new InutilizacaoNfeVO(Long.valueOf(zCancel.getLong("DOCNUM")));
			String chave = e.getString("ID");
			AccessKeyVO accessKeyVO = new AccessKeyVO(chave);
			inutilizacaoNfeVO.setAno(accessKeyVO.getAA());
			inutilizacaoNfeVO.setCnpj(accessKeyVO.getCnpj());
			inutilizacaoNfeVO.setDocNum(zCancel.getString("DOCNUM"));
			inutilizacaoNfeVO.setnNFFin(accessKeyVO.getnNF());
			inutilizacaoNfeVO.setnNFIni(accessKeyVO.getnNF());
			inutilizacaoNfeVO.setSerie(accessKeyVO.getSerie());
			inutilizacaoNfeVO.setTpAmb(e.getString("TPAMB"));
			inutilizacaoNfeVO.setVersao(e.getString("VERSION"));
			inutilizacaoNfeVO.setIe(zCancel.getString("IE"));
			inutilizacaoNfeVO.setxJustificativa(Util.removeCaracteresEspeciais(zCancel.getString("REASON1")));
			String justificativa = zCancel.getString("REASON1");
			if (justificativa != null && justificativa.length() >= 15 && justificativa.length() <= 255) {
				inutilizacaoNfeVO.setxJustificativa(Util.removeCaracteresEspeciais(justificativa));
				String feedback1 = this.convertToJson(inutilizacaoNfeVO);
				this.toQueue(chave, feedback1, "{{nfe.queue.inutilizacao.out}}");
			} else {
				LogUtil.info("Campo de Justificativa deve ter tamanho entre 15 e 255 - DocNum: "
						+ zCancel.getString("DOCNUM"));
				FeedbackNfeERP feedback = new FeedbackNfeERP(zCancel.getString("DOCNUM"));
				feedback.setTipoMensagemRetorno("5");
				feedback.setcStat(Integer.valueOf(999));
				feedback.setStatus(Integer.valueOf(999));
				feedback.setxDescricao("Justificativa deve ter tamanho entre 15 e 255");
				String json = this.convertToJson(feedback);
				this.toQueue(chave, json, "{{nfe.queue.in}}");
			}
		} catch (Exception var10) {
			LogUtil.error("Erro ao converter", var10);
		}

	}


	public void readInutilizacao400(JCoRecord record, JCoParameterList zCancel) {
		JCoStructure e = null;
		try {
			e = record.getStructure("IS_NFE_IDE");
			InutilizacaoNfeVO inutilizacaoNfeVO = new InutilizacaoNfeVO(Long.valueOf(zCancel.getLong("DOCNUM")));
			String chave = e.getString("ID");
			AccessKeyVO accessKeyVO = new AccessKeyVO(chave);
			inutilizacaoNfeVO.setAno(accessKeyVO.getAA());
			inutilizacaoNfeVO.setCnpj(accessKeyVO.getCnpj());
			inutilizacaoNfeVO.setDocNum(zCancel.getString("DOCNUM"));
			inutilizacaoNfeVO.setnNFFin(accessKeyVO.getnNF());
			inutilizacaoNfeVO.setnNFIni(accessKeyVO.getnNF());
			inutilizacaoNfeVO.setSerie(accessKeyVO.getSerie());
			inutilizacaoNfeVO.setTpAmb(e.getString("TP_AMB"));
			inutilizacaoNfeVO.setVersao("4.00");
			inutilizacaoNfeVO.setIe(zCancel.getString("IE"));
			inutilizacaoNfeVO.setxJustificativa(zCancel.getString("REASON1"));
			String justificativa = zCancel.getString("REASON1");
			if (justificativa != null && justificativa.length() >= 15 && justificativa.length() <= 255) {
				inutilizacaoNfeVO.setxJustificativa(justificativa);
				String feedback1 = this.convertToJson(inutilizacaoNfeVO);
				this.toQueue(chave, feedback1, "{{nfe.queue.inutilizacao.out}}");
			} else {
				LogUtil.info("Campo de Justificativa deve ter tamanho entre 15 e 255 - DocNum: "
						+ zCancel.getString("DOCNUM"));
				FeedbackNfeERP feedback = new FeedbackNfeERP(zCancel.getString("DOCNUM"));
				feedback.setTipoMensagemRetorno("5");
				feedback.setcStat(Integer.valueOf(999));
				feedback.setStatus(Integer.valueOf(999));
				feedback.setxDescricao("Justificativa deve ter tamanho entre 15 e 255");
				String json = this.convertToJson(feedback);
				this.toQueue(chave, json, "{{nfe.queue.in}}");
			}
		} catch (Exception var10) {
			LogUtil.error("Erro ao converter", var10);
		}
	}

	public void readInutilizacaoXmlExt2(XmlExt2VO ext2, FunctionService functionService) {
		try {
			JCoParameterList e = functionService.callZCancelInutilizacao(ext2.getId(), ext2.getDocNum());
			InutilizacaoNfeVO inutilizacaoNfeVO = new InutilizacaoNfeVO(Long.valueOf(ext2.getDocNum()));
			AccessKeyVO accessKeyVO = new AccessKeyVO(ext2.getId());
			inutilizacaoNfeVO.setAno(accessKeyVO.getAA());
			inutilizacaoNfeVO.setCnpj(accessKeyVO.getCnpj());
			inutilizacaoNfeVO.setDocNum(e.getString("DOCNUM"));
			inutilizacaoNfeVO.setnNFFin(accessKeyVO.getnNF());
			inutilizacaoNfeVO.setnNFIni(accessKeyVO.getnNF());
			inutilizacaoNfeVO.setSerie(accessKeyVO.getSerie());
			inutilizacaoNfeVO.setTpAmb(ext2.getTipoAmbiente());
			inutilizacaoNfeVO.setVersao(ext2.getVersao());
			inutilizacaoNfeVO.setIe(e.getString("IE"));
			inutilizacaoNfeVO.setxJustificativa(e.getString("REASON1"));
			String justificativa = e.getString("REASON1");
			if (justificativa != null && justificativa.length() >= 15 && justificativa.length() <= 255) {
				inutilizacaoNfeVO.setxJustificativa(justificativa);
				this.toQueue(ext2.getId(), this.convertToJson(inutilizacaoNfeVO), "{{nfe.queue.inutilizacao.out}}");
			} else {
				LogUtil.info(
						"Campo de Justificativa deve ter tamanho entre 15 e 255 - DocNum: " + e.getString("DOCNUM"));
				FeedbackNfeERP feedback = new FeedbackNfeERP(e.getString("DOCNUM"));
				feedback.setTipoMensagemRetorno("5");
				feedback.setcStat(Integer.valueOf(999));
				feedback.setStatus(Integer.valueOf(999));
				feedback.setxDescricao("Justificativa deve ter tamanho entre 15 e 255");
				this.toQueue(ext2.getId(), this.convertToJson(feedback), "{{nfe.queue.in}}");
			}
		} catch (Exception var8) {
			LogUtil.error("Erro ao converter", var8);
		}

	}

	public void readCCe(JCoRecord record) {
		try {
			CartaCorrecaoNfeVO e = this.createCCe(record);
			String json = this.convertToJson(e);
			LogUtil.info(json);
			this.toQueue(e.getDocNum(), json, "{{nfe.queue.cce.out}}");
		} catch (Exception var4) {
			LogUtil.error("Erro ao converter");
			LogUtil.error(var4);
		}

	}

	protected CartaCorrecaoNfeVO createCCe(JCoRecord record) {
		String chaveAcesso = record.getString("IV_NFE_ACCESS_KEY");
		String dataSAP = record.getString("IV_TIMESTAMP");
		CartaCorrecaoNfeVO cartaCorrecaoNfeVO = new CartaCorrecaoNfeVO(Long.valueOf(record.getLong("IV_DOCNUM")));
		cartaCorrecaoNfeVO.setChave(chaveAcesso);
		cartaCorrecaoNfeVO.setCorrecao(record.getString("IV_CONTENT"));
		cartaCorrecaoNfeVO.setDocNum(record.getString("IV_DOCNUM"));
		cartaCorrecaoNfeVO.setnSeqInterno(record.getString("IV_INTERNAL_SEQUENCE_NUMBER"));
		cartaCorrecaoNfeVO.setTpEnvento(record.getString("IV_EVENT_TYPE"));
		cartaCorrecaoNfeVO.setVersao("1.00");
		cartaCorrecaoNfeVO.setCnpj((new AccessKeyVO(chaveAcesso)).getCnpj());
		if (!Properties.dfe_utilizaDataPortal.getBollean()) {
			cartaCorrecaoNfeVO.setDtEvento(
					dataSAP.length() >= 14 ? Util.formatDateTimeFromStringSap(dataSAP.substring(0, 14)) : "");
		}

		cartaCorrecaoNfeVO.setIe("");
		return cartaCorrecaoNfeVO;
	}

	private String convertToXml(JCoRecord record, JCoParameterList zXmlOut, String versao) {

		JCoStructure header = record.getStructure("XML_IN");
		JCoTable referenciada = record.getTable("XML_REF");
		JCoTable item = record.getTable("XML_ITEM_TAB");
		JCoTable medicamento = record.getTable("XML_BATCH");
		JCoTable duplicata = record.getTable("XML_DUP");
		JCoTable importacao = record.getTable("XML_IMP");
		JCoTable adicao = record.getTable("XML_ADI");
		JCoTable ext1 = record.getTable("XML_EXT1");
		JCoTable ext2 = record.getTable("XML_EXT2");
		JCoTable vol = record.getTable("XML_VOL");
		HashMap values = new HashMap();
		values.put("header", header);
		values.put("referenciada", referenciada);
		values.put("item", item);
		values.put("medicamento", medicamento);
		values.put("duplicata", duplicata);
		values.put("importacao", importacao);
		values.put("adicao", adicao);
		values.put("ext1", ext1);
		values.put("ext2", ext2);
		values.put("vol", vol);
		values.put("util", new VelocityParserUtil(record));
		values.put("esc", new EscapeTool());
		values.put("zXmlOut", zXmlOut);
		values.put("versao", "Integrador SAP " + IntegracaoSAP.getVersaoIntegrador());

		String templateBaseDir;
//		String vCNPJ = header.getString("C_CNPJ");

		if(tipoNF.equals("D")) {
			templateBaseDir = "templateDanfinha";
		}else if(destino.equalsIgnoreCase("X")) {	
			templateBaseDir = "templateInvoisys";
		} else {
			templateBaseDir = "template400";
		}

		LogUtil.info("VALOR DA VARIAVEL TEMPLATE: " + templateBaseDir);

		return this.velocityHelper.parseFromTemplate(values, "vm/" + templateBaseDir + "/nfe.vm");
	}

	private String convertToXml310(JCoRecord record, JCoParameterList zXmlOut) {
		HashMap values = new HashMap();
		values.put("IS_NFE_HEADER", record.getStructure("IS_NFE_HEADER"));
		values.put("IS_NFE_PARTNER_IDS", record.getStructure("IS_NFE_PARTNER_IDS"));
		values.put("IT_NFE_PARTNER", record.getTable("IT_NFE_PARTNER"));
		values.put("IT_NFE_TEXT", record.getTable("IT_NFE_TEXT"));
		values.put("IT_NFE_VALUE", record.getTable("IT_NFE_VALUE"));
		values.put("IS_NFE_IDE", record.getStructure("IS_NFE_IDE"));
		values.put("IT_NFE_NFREF", record.getTable("IT_NFE_NFREF"));
		values.put("IS_NFE_AVULSA", record.getStructure("IS_NFE_AVULSA"));
		values.put("IT_NFE_AUTXML", record.getTable("IT_NFE_AUTXML"));
		values.put("IT_NFE_DET", record.getTable("IT_NFE_DET"));
		values.put("IT_NFE_DET_PROD", record.getTable("IT_NFE_DET_PROD"));
		values.put("IT_NFE_PROD_DI", record.getTable("IT_NFE_PROD_DI"));
		values.put("IT_NFE_DI_ADI", record.getTable("IT_NFE_DI_ADI"));
		values.put("IT_NFE_PROD_DETEXPORT", record.getTable("IT_NFE_PROD_DETEXPORT"));
		values.put("IT_NFE_PROD_MED", record.getTable("IT_NFE_PROD_MED"));
		values.put("IT_NFE_PROD_ARMA", record.getTable("IT_NFE_PROD_ARMA"));
		values.put("IT_NFE_PROD_COMB", record.getTable("IT_NFE_PROD_COMB"));
		values.put("IT_NFE_PROD_NRECOPI", record.getTable("IT_NFE_PROD_NRECOPI"));
		values.put("IT_NFE_DET_IMPOSTO", record.getTable("IT_NFE_DET_IMPOSTO"));
		values.put("IT_NFE_IMPOSTO_ICMS", record.getTable("IT_NFE_IMPOSTO_ICMS"));
		values.put("IT_NFE_IMPOSTO_IPI", record.getTable("IT_NFE_IMPOSTO_IPI"));
		values.put("IT_NFE_IMPOSTO_II", record.getTable("IT_NFE_IMPOSTO_II"));
		values.put("IT_NFE_IMPOSTO_ISSQN", record.getTable("IT_NFE_IMPOSTO_ISSQN"));
		values.put("IT_NFE_IMPOSTO_PIS", record.getTable("IT_NFE_IMPOSTO_PIS"));
		values.put("IT_NFE_IMPOSTO_PISST", record.getTable("IT_NFE_IMPOSTO_PISST"));
		values.put("IT_NFE_IMPOSTO_COFINS", record.getTable("IT_NFE_IMPOSTO_COFINS"));
		values.put("IT_NFE_IMPOSTO_COFINSST", record.getTable("IT_NFE_IMPOSTO_COFINSST"));
		values.put("IS_NFE_ICMSTOT", record.getStructure("IS_NFE_ICMSTOT"));
		values.put("IS_NFE_ISSQNTOT", record.getStructure("IS_NFE_ISSQNTOT"));
		values.put("IS_NFE_RETTRIB", record.getStructure("IS_NFE_RETTRIB"));
		values.put("IS_NFE_TRANSP", record.getStructure("IS_NFE_TRANSP"));
		values.put("IT_NFE_REBOQUE", record.getTable("IT_NFE_REBOQUE"));
		values.put("IT_NFE_VOL", record.getTable("IT_NFE_VOL"));
		values.put("IS_NFE_FAT", record.getStructure("IS_NFE_FAT"));
		values.put("IT_NFE_DUP", record.getTable("IT_NFE_DUP"));
		values.put("IT_NFE_PAG", record.getTable("IT_NFE_PAG"));
		values.put("IS_NFE_INFADIC", record.getStructure("IS_NFE_INFADIC"));
		values.put("IT_NFE_PROCREF", record.getTable("IT_NFE_PROCREF"));
		values.put("IS_NFE_EXPORTA", record.getStructure("IS_NFE_EXPORTA"));
		values.put("IS_NFE_COMPRA", record.getStructure("IS_NFE_COMPRA"));
		values.put("IS_NFE_CANA", record.getStructure("IS_NFE_CANA"));
		values.put("IT_NFE_CANA_FORDIA", record.getTable("IT_NFE_CANA_FORDIA"));
		values.put("IT_NFE_EXT1", record.getTable("IT_NFE_EXT1"));
		values.put("IT_NFE_EXT2", record.getTable("IT_NFE_EXT2"));
		values.put("util", new VelocityParserUtil(record));
		values.put("esc", new EscapeTool());
		values.put("zXmlOut", zXmlOut);
		return this.velocityHelper.parseFromTemplate(values, "vm/template310/nfe.vm");
	}

	private void toQueue(String chaveAcesso, String xmlNfe) {
		LogUtil.info("DIEGO - *************** XML A SER ENVIADO PARA A FILA: " + xmlNfe);
		this.producer.sendBody("{{nfe.queue.out}}", xmlNfe);
	}

	private void toQueueInv(String chaveAcesso, String xmlNfe) {
		LogUtil.info("DIEGO - VALOR DO XML COLOCADO NA FILA - METODO TOQUEUE INVOISYS- CLASSE SAPREADERNFE");
		this.producer.sendBody("{{inv.queue.out}}", xmlNfe);
	}

	private void toQueue(String headerKey, String jsonVo, String queue) {
		LogUtil.info("PASSOU PELO SEGUNDO QUEUE - DIEGO");
		this.producer.sendBody(queue, jsonVo);
	}

	private String convertToJson(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		LogUtil.info("RETORNO METODO CONVERTTOJSON- DIEGO" + mapper.writeValueAsString(obj));

		return mapper.writeValueAsString(obj);
	}

}