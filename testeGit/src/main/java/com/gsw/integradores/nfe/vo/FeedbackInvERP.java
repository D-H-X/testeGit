//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackInvERP {
    private String id;
    private Date dataCreate;
    private Date dateAlter;
    private String chave;
    private String xMsg;
    private String codAleatorio;
    private String numNF;
    private Date dtEmit;
    private String cDV;
    private String tpAmb;
    
    private String tipoMensagemRetorno = "";
    private String nProt;
    private String serie;
    private String cnpjEmissor;
    private String dataHoraAut;
    private String femis;
    private String cMsg;
    private String mes;
    private String xDescricao;
    private Integer status;
    
    
    private String ano;
    private Long idProcessamento;
    private Long idProcessamentoLote;
    private String dtEntCont;
    private Integer cStat;
    private String motCont;
    private String cUF;
    private String tipoEnvioNota;
    
    private String nseqEvento;
    private String tpEvento;
    private String nSeqInterno;
    private String dataHoraAutTimeZone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDataCreate() {
		return dataCreate;
	}
	public void setDataCreate(Date dataCreate) {
		this.dataCreate = dataCreate;
	}
	public Date getDateAlter() {
		return dateAlter;
	}
	public void setDateAlter(Date dateAlter) {
		this.dateAlter = dateAlter;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getxMsg() {
		return xMsg;
	}
	public void setxMsg(String xMsg) {
		this.xMsg = xMsg;
	}
	public String getCodAleatorio() {
		return codAleatorio;
	}
	public void setCodAleatorio(String codAleatorio) {
		this.codAleatorio = codAleatorio;
	}
	public String getNumNF() {
		return numNF;
	}
	public void setNumNF(String numNF) {
		this.numNF = numNF;
	}
	public Date getDtEmit() {
		return dtEmit;
	}
	public void setDtEmit(Date dtEmit) {
		this.dtEmit = dtEmit;
	}
	public String getcDV() {
		return cDV;
	}
	public void setcDV(String cDV) {
		this.cDV = cDV;
	}
	public String getTpAmb() {
		return tpAmb;
	}
	public void setTpAmb(String tpAmb) {
		this.tpAmb = tpAmb;
	}
	public String getTipoMensagemRetorno() {
		return tipoMensagemRetorno;
	}
	public void setTipoMensagemRetorno(String tipoMensagemRetorno) {
		this.tipoMensagemRetorno = tipoMensagemRetorno;
	}
	public String getnProt() {
		return nProt;
	}
	public void setnProt(String nProt) {
		this.nProt = nProt;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getCnpjEmissor() {
		return cnpjEmissor;
	}
	public void setCnpjEmissor(String cnpjEmissor) {
		this.cnpjEmissor = cnpjEmissor;
	}
	public String getDataHoraAut() {
		return dataHoraAut;
	}
	public void setDataHoraAut(String dataHoraAut) {
		this.dataHoraAut = dataHoraAut;
	}
	public String getFemis() {
		return femis;
	}
	public void setFemis(String femis) {
		this.femis = femis;
	}
	public String getcMsg() {
		return cMsg;
	}
	public void setcMsg(String cMsg) {
		this.cMsg = cMsg;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getxDescricao() {
		return xDescricao;
	}
	public void setxDescricao(String xDescricao) {
		this.xDescricao = xDescricao;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public Long getIdProcessamento() {
		return idProcessamento;
	}
	public void setIdProcessamento(Long idProcessamento) {
		this.idProcessamento = idProcessamento;
	}
	public Long getIdProcessamentoLote() {
		return idProcessamentoLote;
	}
	public void setIdProcessamentoLote(Long idProcessamentoLote) {
		this.idProcessamentoLote = idProcessamentoLote;
	}
	public String getDtEntCont() {
		return dtEntCont;
	}
	public void setDtEntCont(String dtEntCont) {
		this.dtEntCont = dtEntCont;
	}
	public Integer getcStat() {
		return cStat;
	}
	public void setcStat(Integer cStat) {
		this.cStat = cStat;
	}
	public String getMotCont() {
		return motCont;
	}
	public void setMotCont(String motCont) {
		this.motCont = motCont;
	}
	public String getcUF() {
		return cUF;
	}
	public void setcUF(String cUF) {
		this.cUF = cUF;
	}
	public String getTipoEnvioNota() {
		return tipoEnvioNota;
	}
	public void setTipoEnvioNota(String tipoEnvioNota) {
		this.tipoEnvioNota = tipoEnvioNota;
	}
	public String getNseqEvento() {
		return nseqEvento;
	}
	public void setNseqEvento(String nseqEvento) {
		this.nseqEvento = nseqEvento;
	}
	public String getTpEvento() {
		return tpEvento;
	}
	public void setTpEvento(String tpEvento) {
		this.tpEvento = tpEvento;
	}
	public String getnSeqInterno() {
		return nSeqInterno;
	}
	public void setnSeqInterno(String nSeqInterno) {
		this.nSeqInterno = nSeqInterno;
	}
	public String getDataHoraAutTimeZone() {
		return dataHoraAutTimeZone;
	}
	public void setDataHoraAutTimeZone(String dataHoraAutTimeZone) {
		this.dataHoraAutTimeZone = dataHoraAutTimeZone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((cDV == null) ? 0 : cDV.hashCode());
		result = prime * result + ((cMsg == null) ? 0 : cMsg.hashCode());
		result = prime * result + ((cStat == null) ? 0 : cStat.hashCode());
		result = prime * result + ((cUF == null) ? 0 : cUF.hashCode());
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((cnpjEmissor == null) ? 0 : cnpjEmissor.hashCode());
		result = prime * result + ((codAleatorio == null) ? 0 : codAleatorio.hashCode());
		result = prime * result + ((dataCreate == null) ? 0 : dataCreate.hashCode());
		result = prime * result + ((dataHoraAut == null) ? 0 : dataHoraAut.hashCode());
		result = prime * result + ((dataHoraAutTimeZone == null) ? 0 : dataHoraAutTimeZone.hashCode());
		result = prime * result + ((dateAlter == null) ? 0 : dateAlter.hashCode());
		result = prime * result + ((dtEmit == null) ? 0 : dtEmit.hashCode());
		result = prime * result + ((dtEntCont == null) ? 0 : dtEntCont.hashCode());
		result = prime * result + ((femis == null) ? 0 : femis.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idProcessamento == null) ? 0 : idProcessamento.hashCode());
		result = prime * result + ((idProcessamentoLote == null) ? 0 : idProcessamentoLote.hashCode());
		result = prime * result + ((mes == null) ? 0 : mes.hashCode());
		result = prime * result + ((motCont == null) ? 0 : motCont.hashCode());
		result = prime * result + ((nProt == null) ? 0 : nProt.hashCode());
		result = prime * result + ((nSeqInterno == null) ? 0 : nSeqInterno.hashCode());
		result = prime * result + ((nseqEvento == null) ? 0 : nseqEvento.hashCode());
		result = prime * result + ((numNF == null) ? 0 : numNF.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tipoEnvioNota == null) ? 0 : tipoEnvioNota.hashCode());
		result = prime * result + ((tipoMensagemRetorno == null) ? 0 : tipoMensagemRetorno.hashCode());
		result = prime * result + ((tpAmb == null) ? 0 : tpAmb.hashCode());
		result = prime * result + ((tpEvento == null) ? 0 : tpEvento.hashCode());
		result = prime * result + ((xDescricao == null) ? 0 : xDescricao.hashCode());
		result = prime * result + ((xMsg == null) ? 0 : xMsg.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedbackInvERP other = (FeedbackInvERP) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (cDV == null) {
			if (other.cDV != null)
				return false;
		} else if (!cDV.equals(other.cDV))
			return false;
		if (cMsg == null) {
			if (other.cMsg != null)
				return false;
		} else if (!cMsg.equals(other.cMsg))
			return false;
		if (cStat == null) {
			if (other.cStat != null)
				return false;
		} else if (!cStat.equals(other.cStat))
			return false;
		if (cUF == null) {
			if (other.cUF != null)
				return false;
		} else if (!cUF.equals(other.cUF))
			return false;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		if (cnpjEmissor == null) {
			if (other.cnpjEmissor != null)
				return false;
		} else if (!cnpjEmissor.equals(other.cnpjEmissor))
			return false;
		if (codAleatorio == null) {
			if (other.codAleatorio != null)
				return false;
		} else if (!codAleatorio.equals(other.codAleatorio))
			return false;
		if (dataCreate == null) {
			if (other.dataCreate != null)
				return false;
		} else if (!dataCreate.equals(other.dataCreate))
			return false;
		if (dataHoraAut == null) {
			if (other.dataHoraAut != null)
				return false;
		} else if (!dataHoraAut.equals(other.dataHoraAut))
			return false;
		if (dataHoraAutTimeZone == null) {
			if (other.dataHoraAutTimeZone != null)
				return false;
		} else if (!dataHoraAutTimeZone.equals(other.dataHoraAutTimeZone))
			return false;
		if (dateAlter == null) {
			if (other.dateAlter != null)
				return false;
		} else if (!dateAlter.equals(other.dateAlter))
			return false;
		if (dtEmit == null) {
			if (other.dtEmit != null)
				return false;
		} else if (!dtEmit.equals(other.dtEmit))
			return false;
		if (dtEntCont == null) {
			if (other.dtEntCont != null)
				return false;
		} else if (!dtEntCont.equals(other.dtEntCont))
			return false;
		if (femis == null) {
			if (other.femis != null)
				return false;
		} else if (!femis.equals(other.femis))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idProcessamento == null) {
			if (other.idProcessamento != null)
				return false;
		} else if (!idProcessamento.equals(other.idProcessamento))
			return false;
		if (idProcessamentoLote == null) {
			if (other.idProcessamentoLote != null)
				return false;
		} else if (!idProcessamentoLote.equals(other.idProcessamentoLote))
			return false;
		if (mes == null) {
			if (other.mes != null)
				return false;
		} else if (!mes.equals(other.mes))
			return false;
		if (motCont == null) {
			if (other.motCont != null)
				return false;
		} else if (!motCont.equals(other.motCont))
			return false;
		if (nProt == null) {
			if (other.nProt != null)
				return false;
		} else if (!nProt.equals(other.nProt))
			return false;
		if (nSeqInterno == null) {
			if (other.nSeqInterno != null)
				return false;
		} else if (!nSeqInterno.equals(other.nSeqInterno))
			return false;
		if (nseqEvento == null) {
			if (other.nseqEvento != null)
				return false;
		} else if (!nseqEvento.equals(other.nseqEvento))
			return false;
		if (numNF == null) {
			if (other.numNF != null)
				return false;
		} else if (!numNF.equals(other.numNF))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (tipoEnvioNota == null) {
			if (other.tipoEnvioNota != null)
				return false;
		} else if (!tipoEnvioNota.equals(other.tipoEnvioNota))
			return false;
		if (tipoMensagemRetorno == null) {
			if (other.tipoMensagemRetorno != null)
				return false;
		} else if (!tipoMensagemRetorno.equals(other.tipoMensagemRetorno))
			return false;
		if (tpAmb == null) {
			if (other.tpAmb != null)
				return false;
		} else if (!tpAmb.equals(other.tpAmb))
			return false;
		if (tpEvento == null) {
			if (other.tpEvento != null)
				return false;
		} else if (!tpEvento.equals(other.tpEvento))
			return false;
		if (xDescricao == null) {
			if (other.xDescricao != null)
				return false;
		} else if (!xDescricao.equals(other.xDescricao))
			return false;
		if (xMsg == null) {
			if (other.xMsg != null)
				return false;
		} else if (!xMsg.equals(other.xMsg))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FeedbackInvERP [id=" + id + ", dataCreate=" + dataCreate + ", dateAlter=" + dateAlter + ", chave="
				+ chave + ", xMsg=" + xMsg + ", codAleatorio=" + codAleatorio + ", numNF=" + numNF + ", dtEmit="
				+ dtEmit + ", cDV=" + cDV + ", tpAmb=" + tpAmb + ", tipoMensagemRetorno=" + tipoMensagemRetorno
				+ ", nProt=" + nProt + ", serie=" + serie + ", cnpjEmissor=" + cnpjEmissor + ", dataHoraAut="
				+ dataHoraAut + ", femis=" + femis + ", cMsg=" + cMsg + ", mes=" + mes + ", xDescricao=" + xDescricao
				+ ", status=" + status + ", ano=" + ano + ", idProcessamento=" + idProcessamento
				+ ", idProcessamentoLote=" + idProcessamentoLote + ", dtEntCont=" + dtEntCont + ", cStat=" + cStat
				+ ", motCont=" + motCont + ", cUF=" + cUF + ", tipoEnvioNota=" + tipoEnvioNota + ", nseqEvento="
				+ nseqEvento + ", tpEvento=" + tpEvento + ", nSeqInterno=" + nSeqInterno + ", dataHoraAutTimeZone="
				+ dataHoraAutTimeZone + "]";
	}

 
 
    
}
