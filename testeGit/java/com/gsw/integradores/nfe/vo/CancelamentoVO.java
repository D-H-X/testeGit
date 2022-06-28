//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class CancelamentoVO {
    private final Long transactionId;
    private String versao;
    private String codCidade;
    private String tipoAmbienteSistema;
    private String cnpjPrestador;
    private String inscricaoPrestador;
    private String numeroNfe;
    private String codigoVerificacao;
    private String motivoCancelamento;
    private String codigoCancelamento;
    private String docNum;

    @JsonCreator
    public CancelamentoVO(@JsonProperty("transactionId") Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getCodCidade() {
        return this.codCidade;
    }

    public void setCodCidade(String codCidade) {
        this.codCidade = codCidade;
    }

    public String getTipoAmbienteSistema() {
        return this.tipoAmbienteSistema;
    }

    public void setTipoAmbienteSistema(String tipoAmbienteSistema) {
        this.tipoAmbienteSistema = tipoAmbienteSistema;
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

    public String getCodigoVerificacao() {
        return this.codigoVerificacao;
    }

    public void setCodigoVerificacao(String codigoVerificacao) {
        this.codigoVerificacao = codigoVerificacao;
    }

    public String getMotivoCancelamento() {
        return this.motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getCodigoCancelamento() {
        return this.codigoCancelamento;
    }

    public void setCodigoCancelamento(String codigoCancelamento) {
        this.codigoCancelamento = codigoCancelamento;
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

    public int hashCode() {
        byte result = 1;
        int result1 = 31 * result + (this.transactionId == null?0:this.transactionId.hashCode());
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
            CancelamentoVO other = (CancelamentoVO)obj;
            if(this.transactionId == null) {
                if(other.transactionId != null) {
                    return false;
                }
            } else if(!this.transactionId.equals(other.transactionId)) {
                return false;
            }

            return true;
        }
    }

    public String getNumeroNfe() {
        return this.numeroNfe;
    }

    public void setNumeroNfe(String numeroNfe) {
        this.numeroNfe = numeroNfe;
    }
}
