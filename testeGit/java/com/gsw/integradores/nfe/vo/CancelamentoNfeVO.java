//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class CancelamentoNfeVO {
    @JsonProperty("transactionId")
    private final Long transactionId;
    private String versao;
    private String chave;
    private String cnpj;
    private String xJustificativa;
    private String ie;
    private String docNum;
    private String nSeqInterno;

    @JsonCreator
    public CancelamentoNfeVO(@JsonProperty("transactionId") Long transactionId) {
        this.transactionId = transactionId;
    }

    @JsonCreator
    public CancelamentoNfeVO() {
        this((Long)null);
    }

    public String getVersao() {
        return this.versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getChave() {
        return this.chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getxJustificativa() {
        return this.xJustificativa;
    }

    public void setxJustificativa(String xJustificativa) {
        this.xJustificativa = xJustificativa;
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

    public String getnSeqInterno() {
        return this.nSeqInterno;
    }

    public void setnSeqInterno(String nSeqInterno) {
        this.nSeqInterno = nSeqInterno;
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
            CancelamentoNfeVO other = (CancelamentoNfeVO)obj;
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
}
