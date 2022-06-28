//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.gsw.integradores.nfe.client.ClientPool;
import com.gsw.integradores.nfe.commons.IntegradorUtil;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.PackageSend;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.vo.CancelamentoNfeVO;
import com.gsw.integradores.nfe.vo.CartaCorrecaoNfeVO;
import com.gsw.integradores.nfe.vo.InutilizacaoNfeVO;
import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;

@Service
public class NfeRequestBuilder {
	
	private JCoFunction function;
	private JCoTable zICMSSTHeader;
	private JCoDestination destination;
	private String usu_imp;
	private String impressao;
//	private String invoisys;
	
    public NfeRequestBuilder() {
    	this.function = this.obterNovoFunction();
    }

    public JCoFunction obterNovoFunction() {
    	LogUtil.info("DIEGO - PASSOU PELO PRIMEIRO OBTERNOVOFUNCTION NA CLASSE NFEREQUESTBUILDER" );
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
    
    
    private JCoTable getCustomTable(JCoFunction funcExt, String table) {
		try {
			return funcExt.getTableParameterList().getTable(table);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    public String build(Exchange exchange) throws Exception {
        String docNum = (String)exchange.getIn().getHeader("transactionId", String.class);
        String xml = (String)exchange.getIn().getBody(String.class);
        String dataEntradaContigencia = (String)exchange.getIn().getHeader("dataEntrada", String.class);
        String motivoContigencia = (String)exchange.getIn().getHeader("motivo", String.class);
        String serie = (String)exchange.getIn().getHeader("serie", String.class);
        String numero = (String)exchange.getIn().getHeader("numeroNota", String.class);
        String cnpj = (String)exchange.getIn().getHeader("cnpjEmissor", String.class);
        String chave = (String)exchange.getIn().getHeader("chave", String.class);
        String result = this.build(docNum, xml, dataEntradaContigencia, motivoContigencia, serie, numero, cnpj, chave);
        LogUtil.info("DOCNUM " + docNum + " envio portal: " + result);
        return result;
    }

    public String build(String docNum, String xml, String dataEntradaContigencia, String motivoContigencia, String serie, String numero, String cnpj, String chave) throws Exception {
       
    	try {
    	PackageSend packageSend = new PackageSend();
        packageSend.setAction("EnviarPacote");
        packageSend.setReferencia(docNum);
        HashMap map = new HashMap();
        map.put("xml", xml);
        
        JCoParameterList input = function.getImportParameterList();     
        input.setValue("I_DOCNUM", docNum);     
		
        try {           
            function.execute(destination);          
        } catch (AbapException e) {
            System.out.println(e.toString());           
        }
        
        impressao = null;
        usu_imp = null;
//        invoisys = null;
        
        LogUtil.info("VERSÃO ARQUIVO INTEGRADOR 30112020-0000");
        LogUtil.info("Valor da variavel de impressão ANTES IF (impressao) : " + impressao);
        LogUtil.info("Valor da variavel de impressão ANTES IF (usu_imp) : " + usu_imp);
//        LogUtil.info("Valor da variavel  ANTES IF (invoisys) : " + invoisys);
        
        JCoTable table = function.getTableParameterList().getTable("XML_ICMS_ST_HEADER");
        int records = table.getRow();
        table.setRow(0);
        String destino_imp = new String();
        destino_imp = table.getString("IMP_DEST");
//                invoisys = table.getString("INVOISYS");
        System.out.println(records);
        System.out.println(table.getString("IMP_DEST"));
        LogUtil.info("Valor variavel IMP_DEST" + table.getString("IMP_DEST"));
        LogUtil.info("Valor variavel destino impressao" + destino_imp);

//        LogUtil.info("Valor variavel invoisys: " + table.getString("INVOISYS"));
//        LogUtil.info("Valor variavel destino invoisys: " + invoisys);

        
        if ("7801".equals(destino_imp) || "7951".equals(destino_imp) || "7964".equals(destino_imp)) {
        	impressao = "cur09_pb_scx_6545";
        	usu_imp = "FABRICA_CURITIBA";
        }else if ("7919".equals(destino_imp) || "7962".equals(destino_imp) || "7805".equals(destino_imp)) {
        	impressao = "cbu05";
        	usu_imp = "FABRICA_CABREUVA";		
        }else if ("7802".equals(destino_imp) || "7952".equals(destino_imp) || "7916".equals(destino_imp)) {
        	impressao = "7lg07_pb_scx_6545_7";
        	usu_imp = "FABRICA_SETE";		
        }else if ("78AG".equals(destino_imp)) {
        	impressao = "am_sm01_pb_scx_6545";
        	usu_imp = "FABRICA_SMATHEUS";
        }else if ("78AF".equals(destino_imp)) {
        	impressao = "ama_pet04_pb_scx_6545";
        	usu_imp = "FABRICA_PETROLINA";
        }else if ("7809".equals(destino_imp) || "7956".equals(destino_imp)) {
        	impressao = "bah01_pb_scx_6545";
        	usu_imp = "FABRICA_BAHIA";
        }else if ("7816".equals(destino_imp) || "7957".equals(destino_imp)) {
        	impressao = "bal03_pb_scx_6545";
        	usu_imp = "FABRICA_PALEGRE";
        }else if ("7806".equals(destino_imp)) {
        	impressao = "bar02_pb_scx_6545";
        	usu_imp = "CD_BARUERI";
        }else if ("7820".equals(destino_imp) || "7963".equals(destino_imp)) {
        	impressao = "cd_uniao_01_pb_scx_6545";
        	usu_imp = "CD_UPALMARES";
        }else if ("7804".equals(destino_imp) || "7953".equals(destino_imp) || "7918".equals(destino_imp)) {
        	impressao = "f_rec07_pb_scx_6545";
        	usu_imp = "FABRICA_RECIFE";
        }else if ("7817".equals(destino_imp)) {
        	impressao = "gpoa01_pb_scx_6545";
        	usu_imp = "DG_PALEGRE";
        }else if ("7812".equals(destino_imp) || "7958".equals(destino_imp)) {
        	impressao = "gua06_pb_scx_6545";
        	usu_imp = "FABRICA_GUARULHOS";
        }else if ("7800".equals(destino_imp) || "7950".equals(destino_imp) || "7915".equals(destino_imp) || "7965".equals(destino_imp)) {
        	impressao = "itu14_pb_sp_4210";
        	usu_imp = "FABRICA_ITU";
        }else if ("7807".equals(destino_imp) || "7954".equals(destino_imp)) {
        	impressao = "luc04_pb_scx_6545";
        	usu_imp = "FABRICA_LUCKY";
        }else if ("7900".equals(destino_imp)) {
        	impressao = "luc_rec02_pb_scx_6545";
        	usu_imp = "FABRICA_LUCKY_PE";
        }else if ("7912".equals(destino_imp) || "7961".equals(destino_imp)) {
        	impressao = "m_bh01_nfe";
        	usu_imp = "CD_MIX_BH";
        }else if ("7803".equals(destino_imp)) {
        	impressao = "m_rj07_pb_scx_6545";
        	usu_imp = "CD_RJANEIRO";
        }else if ("7811".equals(destino_imp)) {
        	impressao = "poa09_pb_scx_6545";
        	usu_imp = "FABRICA_PA";
        }else if ("7818".equals(destino_imp)) {
        	impressao = "rec02_pb_scx_6545";
        	usu_imp = "MIX_CRECIFE";
        }else if ("7815".equals(destino_imp) || "7959".equals(destino_imp)) {
        	impressao = "sor04_pb_scx_6545";
        	usu_imp = "FABRICA_SOROCABA";
        }else if ("7897".equals(destino_imp) || "7898".equals(destino_imp) || "7909".equals(destino_imp) || "78AH".equals(destino_imp) || "7966".equals(destino_imp)) {
        	impressao = "sp13_pb_sp_4210";
        	usu_imp = "ESCRITORIO";
        }else if ("7808".equals(destino_imp)) {
        	impressao =	"tit01_pb_scx_6545";
        	usu_imp = "FABRICA_TIETE";
        }else if ("7819".equals(destino_imp) || "7960".equals(destino_imp)) {
        	impressao =	"up01_pb_scx_6545";
        	usu_imp = "FABRICA_ALAGOAS";
        }
        

        
        
        packageSend.setContents(map);
        this.createContingenciaMap(dataEntradaContigencia, motivoContigencia, serie, numero, cnpj, map);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(packageSend);
        LogUtil.info("envio/" + chave);
        LogUtil.info(json);
        IntegradorUtil.salvarJsonNFE("envio", chave, json);
        return json;
    	} catch (Exception e) {
			e.printStackTrace();
			return null;
    	}
        
    }

    protected void createContingenciaMap(String dataEntrada, String motivo, String serie, String numero, String cnpj, Map<String, Object> map) {

    	LinkedHashMap parametros = new LinkedHashMap();
    	parametros.put("usuarioEmitente", usu_imp);
        parametros.put("dsImpressora", impressao);
        ArrayList param = new ArrayList(1);
        param.add(parametros);
        LinkedHashMap mastersaf = new LinkedHashMap();
        mastersaf.put("parametros", param);
    	
    	if(StringUtils.isNotBlank(motivo) && StringUtils.isNotBlank(dataEntrada)) {
            LinkedHashMap contingencia = new LinkedHashMap();
            contingencia.put("cnpjEmissor", cnpj);
            contingencia.put("serie", serie);
            contingencia.put("numeroNota", numero);
            contingencia.put("dataEntrada", dataEntrada);
            contingencia.put("dataSaida", "");
            contingencia.put("motivo", motivo);
            ArrayList contingencias = new ArrayList(1);
            contingencias.add(contingencia);
//            LinkedHashMap mastersaf = new LinkedHashMap();
            mastersaf.put("contingencias", contingencias);
//            map.put("mastersaf", mastersaf);
        }
   	 map.put("mastersaf", mastersaf);
    }

    public String buildCancellation(CancelamentoNfeVO cancelamentoNfeVO) throws Exception {
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("Cancelar");
        packageSend.setReferencia(cancelamentoNfeVO.getDocNum());
        StringBuilder cancelamentoTxt = new StringBuilder();
        cancelamentoTxt.append("--nota_cancelada_ini--").append(";");
        cancelamentoTxt.append(cancelamentoNfeVO.getVersao()).append(";");
        cancelamentoTxt.append(cancelamentoNfeVO.getChave()).append(";");
        cancelamentoTxt.append(cancelamentoNfeVO.getCnpj()).append(";");
        cancelamentoTxt.append("110111").append(";");
        cancelamentoTxt.append(cancelamentoNfeVO.getxJustificativa()).append(";");
        cancelamentoTxt.append(cancelamentoNfeVO.getIe()).append(";");
        cancelamentoTxt.append("--nota_cancelada_fim--").append(";");
        cancelamentoTxt.append("--arquivo_fim--").append(";");
        HashMap map = new HashMap();
        map.put("xml", cancelamentoTxt.toString());
        packageSend.setContents(map);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(packageSend);
        String nomeArquivo = cancelamentoNfeVO.getChave();
        LogUtil.info("cancelamento/" + nomeArquivo);
        LogUtil.info(json);
        IntegradorUtil.salvarJsonNFE("cancelamento", nomeArquivo, json);
        return json;
    }

    public String buildInutilizacao(InutilizacaoNfeVO inutilizacaoNfeVO) throws Exception {
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("Inutilizar");
        packageSend.setReferencia(inutilizacaoNfeVO.getDocNum());
        HashMap map = new HashMap();
        StringBuilder inutilizacaoTxt = new StringBuilder();
        inutilizacaoTxt.append("--nota_inutilizada_ini--").append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getVersao()).append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getCnpj()).append(";");
        inutilizacaoTxt.append("2").append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getTpAmb()).append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getnNFFin()).append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getnNFFin()).append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getSerie()).append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getxJustificativa()).append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getAno() != null?inutilizacaoNfeVO.getAno():"").append(";");
        inutilizacaoTxt.append(inutilizacaoNfeVO.getIe()).append(";");
        inutilizacaoTxt.append("--nota_inutilizada_fim--").append(";");
        inutilizacaoTxt.append("--arquivo_fim--").append(";");
        map.put("xml", inutilizacaoTxt.toString());
        packageSend.setContents(map);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(packageSend);
        String nomeArquivo = inutilizacaoNfeVO.getnNFFin();
        LogUtil.info(nomeArquivo);
        LogUtil.info(json);
        IntegradorUtil.salvarJsonNFE("inutilizacao", nomeArquivo, json);
        return json;
    }

    public String buildCCe(CartaCorrecaoNfeVO cartaCorrecaoNfeVO) throws Exception {
        PackageSend packageSend = new PackageSend();
        packageSend.setAction("EnviarCartaCorrecaoNfe");
        packageSend.setReferencia(cartaCorrecaoNfeVO.getDocNum());
        HashMap map = new HashMap();
        StringBuilder cceTxt = new StringBuilder();
        cceTxt.append("--carta_correcao_ini--").append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getVersao()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getChave()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getCnpj()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getIe()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getTpEnvento()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getnSeqInterno()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getDtEvento()).append(";");
        cceTxt.append(cartaCorrecaoNfeVO.getCorrecao()).append(";");
        cceTxt.append("--carta_correcao_fim--").append(";");
        cceTxt.append("--arquivo_fim--").append(";");
        map.put("xml", cceTxt.toString());
        packageSend.setContents(map);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(packageSend);
        String nomeArquivo = cartaCorrecaoNfeVO.getChave();
        LogUtil.info("cce/" + nomeArquivo);
        LogUtil.info(json);
        IntegradorUtil.salvarJsonNFE("cce", nomeArquivo, json);
        return json;
    }
}
