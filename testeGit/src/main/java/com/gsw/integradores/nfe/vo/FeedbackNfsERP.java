//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class FeedbackNfsERP implements Serializable {
    private static final long serialVersionUID = -4907171251502790533L;
    private final String docNum;
    private Integer status;
    private String descricaoStatus;
    private Integer codCidade;
    private Integer siafiPrestador;
    private Integer numeroRps;
    private String serieRps;
    private String tipoRps;
    private String situacaoRps;
    private String dataEmissaoRps;
    private String dataAprovacao;
    private String dataCancelamento;
    private String numeroNfe;
    private String cnpjPrestador;
    private String inscricaoPrestador;
    private BigDecimal aliquotaServicos;
    private BigDecimal valorServicos;
    private BigDecimal valorDeduzir;
    private String codigoVerificacao;
    private String codigoCancelamento;
    private Integer numeroLote;
    private String numeroProtocolo;
    private Integer tipoAmbienteSistema;
    private String urlConsulta;
    private Long idProcessamentoRps;
    private Long idProcessamentoLote;

    @JsonCreator
    public FeedbackNfsERP(@JsonProperty("docNum") String docNum) {
        this.docNum = docNum;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescricaoStatus() {
        return this.descricaoStatus;
    }

    public void setDescricaoStatus(String descricaoStatus) {
        this.descricaoStatus = descricaoStatus;
    }

    public Integer getCodCidade() {
        return this.codCidade;
    }

    public void setCodCidade(Integer codCidade) {
        this.codCidade = codCidade;
    }

    public Integer getSiafiPrestador() {
        return this.siafiPrestador;
    }

    public void setSiafiPrestador(Integer siafiPrestador) {
        this.siafiPrestador = siafiPrestador;
    }

    public Integer getNumeroRps() {
        return this.numeroRps;
    }

    public void setNumeroRps(Integer numeroRps) {
        this.numeroRps = numeroRps;
    }

    public String getSerieRps() {
        return this.serieRps;
    }

    public void setSerieRps(String serieRps) {
        this.serieRps = serieRps;
    }

    public String getTipoRps() {
        return this.tipoRps;
    }

    public void setTipoRps(String tipoRps) {
        this.tipoRps = tipoRps;
    }

    public String getSituacaoRps() {
        return this.situacaoRps;
    }

    public void setSituacaoRps(String situacaoRps) {
        this.situacaoRps = situacaoRps;
    }

    public String getDataEmissaoRps() {
        return this.dataEmissaoRps;
    }

    public void setDataEmissaoRps(String dataEmissaoRps) {
        this.dataEmissaoRps = dataEmissaoRps;
    }

    public String getDataAprovacao() {
        return this.dataAprovacao;
    }

    public void setDataAprovacao(String dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public String getDataCancelamento() {
        return this.dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getDocNum() {
        return this.docNum;
    }

    public String getNumeroNfe() {
        return this.numeroNfe;
    }

    public void setNumeroNfe(String numeroNfe) {
        this.numeroNfe = numeroNfe;
    }

    public String getCnpjPrestador() {
        return this.cnpjPrestador;
    }

    public void setCnpjPrestador(String cnpjPrestador) {
        this.cnpjPrestador = cnpjPrestador;
    }

    public String getInscricaoPrestador() {
        return this.inscricaoPrestador;
    }

    public void setInscricaoPrestador(String inscricaoPrestador) {
        this.inscricaoPrestador = inscricaoPrestador;
    }

    public BigDecimal getAliquotaServicos() {
        return this.aliquotaServicos;
    }

    public void setAliquotaServicos(BigDecimal aliquotaServicos) {
        this.aliquotaServicos = aliquotaServicos;
    }

    public BigDecimal getValorServicos() {
        return this.valorServicos;
    }

    public void setValorServicos(BigDecimal valorServicos) {
        this.valorServicos = valorServicos;
    }

    public BigDecimal getValorDeduzir() {
        return this.valorDeduzir;
    }

    public void setValorDeduzir(BigDecimal valorDeduzir) {
        this.valorDeduzir = valorDeduzir;
    }

    public String getCodigoVerificacao() {
        return this.codigoVerificacao;
    }

    public void setCodigoVerificacao(String codigoVerificacao) {
        this.codigoVerificacao = codigoVerificacao;
    }

    public String getCodigoCancelamento() {
        return this.codigoCancelamento;
    }

    public void setCodigoCancelamento(String codigoCancelamento) {
        this.codigoCancelamento = codigoCancelamento;
    }

    public Integer getNumeroLote() {
        return this.numeroLote;
    }

    public void setNumeroLote(Integer numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getNumeroProtocolo() {
        return this.numeroProtocolo;
    }

    public void setNumeroProtocolo(String numeroProtocolo) {
        this.numeroProtocolo = numeroProtocolo;
    }

    public Integer getTipoAmbienteSistema() {
        return this.tipoAmbienteSistema;
    }

    public void setTipoAmbienteSistema(Integer tipoAmbienteSistema) {
        this.tipoAmbienteSistema = tipoAmbienteSistema;
    }

    public String getUrlConsulta() {
        return this.urlConsulta;
    }

    public void setUrlConsulta(String urlConsulta) {
        this.urlConsulta = urlConsulta;
    }

    public static long getSerialversionuid() {
        return -4907171251502790533L;
    }

    public void setResult(String result) {
        this.descricaoStatus = result;
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
        } else if(!(obj instanceof FeedbackNfsERP)) {
            return false;
        } else {
            FeedbackNfsERP other = (FeedbackNfsERP)obj;
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

    public Long getIdProcessamentoRps() {
        return this.idProcessamentoRps;
    }

    public void setIdProcessamentoRps(Long idProcessamentoRps) {
        this.idProcessamentoRps = idProcessamentoRps;
    }

    public Long getIdProcessamentoLote() {
        return this.idProcessamentoLote;
    }

    public void setIdProcessamentoLote(Long idProcessamentoLote) {
        this.idProcessamentoLote = idProcessamentoLote;
    }
}
