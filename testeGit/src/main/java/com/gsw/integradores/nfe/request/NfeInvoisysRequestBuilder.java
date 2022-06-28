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
public class NfeInvoisysRequestBuilder {
	
	private JCoFunction function;
	private JCoTable zICMSSTHeader;
	private JCoDestination destination;
	
    public NfeInvoisysRequestBuilder() {
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


    	  /*String docNum = (String)exchange.getIn().getHeader("transactionId", String.class);
        String xml = (String)exchange.getIn().getBody(String.class);
        String dataEntradaContigencia = (String)exchange.getIn().getHeader("dataEntrada", String.class);
        String motivoContigencia = (String)exchange.getIn().getHeader("motivo", String.class);
        String serie = (String)exchange.getIn().getHeader("serie", String.class);
        String numero = (String)exchange.getIn().getHeader("numeroNota", String.class);
        String cnpj = (String)exchange.getIn().getHeader("cnpjEmissor", String.class);
        String chave = (String)exchange.getIn().getHeader("chave", String.class);
        String result = this.build(docNum, xml, dataEntradaContigencia, motivoContigencia, serie, numero, cnpj, chave);*/
    	String result = exchange.getIn().getBody().toString();
    	LogUtil.info("valor invoisys:  " + result);
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
        
       
        LogUtil.info("VERSÃO ARQUIVO INTEGRADOR 30112020-0000");
       
        JCoTable table = function.getTableParameterList().getTable("XML_ICMS_ST_HEADER");
        int records = table.getRow();
        table.setRow(0);
        System.out.println(records);
        System.out.println(table.getString("IMP_DEST"));
        LogUtil.info("Valor variavel IMP_DEST" + table.getString("IMP_DEST"));
    
        LogUtil.info("Valor variavel invoisys: " + table.getString("INVOISYS"));
    
        
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
