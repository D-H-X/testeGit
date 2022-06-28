//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackNfeERP {
    private String tpAmb;
    private String cNF;
    private String mod;
    private String xMsg;
    private String chave;
    private String tipoMensagemRetorno = "";
    private String nProt;
    private String serie;
    private String cnpjEmissor;
    private String dataHoraAut;
    private String femis;
    private String cMsg;
    private String mes;
    private String xDescricao;
    private String cDV;
    private String ano;
    private Long idProcessamento;
    private Long idProcessamentoLote;
    private String dtEntCont;
    private Integer cStat;
    private String motCont;
    private String cUF;
    private String tipoEnvioNota;
    private Integer status;
    @JsonProperty("docNum")
    private String docNum;
    private String nseqEvento;
    private String tpEvento;
    private String nSeqInterno;
    private String dataHoraAutTimeZone;

    @JsonCreator
    public FeedbackNfeERP(@JsonProperty("docNum") String docNum) {
        this.docNum = docNum;
    }

    public String getTpAmb() {
        return this.tpAmb;
    }

    public void setTpAmb(String tpAmb) {
        this.tpAmb = tpAmb;
    }

    public String getcNF() {
        return this.cNF;
    }

    public void setcNF(String cNF) {
        this.cNF = cNF;
    }

    public String getMod() {
        return this.mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getxMsg() {
        return this.xMsg;
    }

    public void setxMsg(String xMsg) {
        this.xMsg = xMsg;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getTipoMensagemRetorno() {
        return this.tipoMensagemRetorno;
    }

    public void setTipoMensagemRetorno(String tipoMensagemRetorno) {
        this.tipoMensagemRetorno = tipoMensagemRetorno;
    }

    public String getnProt() {
        return this.nProt;
    }

    public void setnProt(String nProt) {
        this.nProt = nProt;
    }

    public String getSerie() {
        return this.serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCnpjEmissor() {
        return this.cnpjEmissor;
    }

    public void setCnpjEmissor(String cnpjEmissor) {
        this.cnpjEmissor = cnpjEmissor;
    }

    public String getDataHoraAut() {
        return this.dataHoraAut;
    }

    public void setDataHoraAut(String dataHoraAut) {
        this.dataHoraAut = dataHoraAut;
    }

    public String getFemis() {
        return this.femis;
    }

    public void setFemis(String femis) {
        this.femis = femis;
    }

    public String getcMsg() {
        return this.cMsg;
    }

    public void setcMsg(String cMsg) {
        this.cMsg = cMsg;
    }

    public String getMes() {
        return this.mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getxDescricao() {
        return this.xDescricao;
    }

    public void setxDescricao(String xDescricao) {
        this.xDescricao = xDescricao;
    }

    public String getcDV() {
        return this.cDV;
    }

    public void setcDV(String cDV) {
        this.cDV = cDV;
    }

    public String getAno() {
        return this.ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDtEntCont() {
        return this.dtEntCont;
    }

    public void setDtEntCont(String dtEntCont) {
        this.dtEntCont = dtEntCont;
    }

    public String getMotCont() {
        return this.motCont;
    }

    public void setMotCont(String motCont) {
        this.motCont = motCont;
    }

    public String getcUF() {
        return this.cUF;
    }

    public void setcUF(String cUF) {
        this.cUF = cUF;
    }

    public String getTipoEnvioNota() {
        return this.tipoEnvioNota;
    }

    public void setTipoEnvioNota(String tipoEnvioNota) {
        this.tipoEnvioNota = tipoEnvioNota;
    }

    public String getDocNum() {
        return this.docNum;
    }

    public void setDocNum(String docnum) {
    	this.docNum   =    docnum;
    }
    
    
    public Long getIdProcessamento() {
        return this.idProcessamento;
    }

    public void setIdProcessamento(Long idProcessamento) {
        this.idProcessamento = idProcessamento;
    }

    public Integer getcStat() {
        return this.cStat;
    }

    public void setcStat(Integer cStat) {
        this.cStat = cStat;
    }

    public int hashCode() {
        byte result = 1;
        int result1 = 31 * result + (this.docNum == null?0:this.docNum.hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            FeedbackNfeERP other = (FeedbackNfeERP)obj;
            if(this.docNum == null) {
                if(other.docNum != null) {
                    return false;
                }
            } else if(!this.docNum.equals(other.docNum)) {
                return false;
            }

            return true;
        }
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getIdProcessamentoLote() {
        return this.idProcessamentoLote;
    }

    public void setIdProcessamentoLote(Long idProcessamentoLote) {
        this.idProcessamentoLote = idProcessamentoLote;
    }

    public String getNseqEvento() {
        return this.nseqEvento;
    }

    public void setNseqEvento(String nseqEvento) {
        this.nseqEvento = nseqEvento;
    }

    public String getTpEvento() {
        return this.tpEvento;
    }

    public void setTpEvento(String tpEvento) {
        this.tpEvento = tpEvento;
    }

    public String getnSeqInterno() {
        return this.nSeqInterno;
    }

    public void setnSeqInterno(String nSeqInterno) {
        this.nSeqInterno = nSeqInterno;
    }

    public String getDataHoraAutTimeZone() {
        return this.dataHoraAutTimeZone;
    }

    public void setDataHoraAutTimeZone(String dataHoraAutTimeZone) {
        this.dataHoraAutTimeZone = dataHoraAutTimeZone;
    }
}
