//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class InutilizacaoNfeVO {
    private final Long transactionId;
    private String versao;
    private String cnpj;
    private String tpAmb;
    private String nNFIni;
    private String nNFFin;
    private String serie;
    private String xJustificativa;
    private String ano;
    private String ie;
    private String docNum;

    @JsonCreator
    public InutilizacaoNfeVO(@JsonProperty("transactionId") Long transactionId) {
        this.transactionId = transactionId;
    }

    public InutilizacaoNfeVO() {
        this((Long)null);
    }

    public String getVersao() {
        //return "3.10";
    	return "4.00";
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTpAmb() {
        return this.tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getnNFIni() {
        return this.nNFIni;
    }

    public void setnNFIni(String nNFIni) {
        this.nNFIni = nNFIni;
    }

    public String getnNFFin() {
        return this.nNFFin;
    }

    public void setnNFFin(String nNFFin) {
        this.nNFFin = nNFFin;
    }

    public String getSerie() {
        return this.serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getxJustificativa() {
        return this.xJustificativa;
    }

    public void setxJustificativa(String xJustificativa) {
        this.xJustificativa = xJustificativa;
    }

    public String getAno() {
        return this.ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getIe() {
        return this.ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getDocNum() {
        return this.docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }
}
