//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class CartaCorrecaoNfeVO {
    private final Long transactionId;
    private String versao;
    private String chave;
    private String cnpj;
    private String ie;
    private String tpEnvento;
    private String nSeqInterno;
    private String dtEvento;
    private String correcao;
    private String docNum;

    @JsonCreator
    public CartaCorrecaoNfeVO(@JsonProperty("transactionId") Long transactionId) {
        this.transactionId = transactionId;
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

    public String getIe() {
        return this.ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getTpEnvento() {
        return this.tpEnvento;
    }

    public void setTpEnvento(String tpEnvento) {
        this.tpEnvento = tpEnvento;
    }

    public String getnSeqInterno() {
        return this.nSeqInterno;
    }

    public void setnSeqInterno(String nSeqInterno) {
        this.nSeqInterno = nSeqInterno;
    }

    public String getDtEvento() {
        return this.dtEvento;
    }

    public void setDtEvento(String dtEvento) {
        this.dtEvento = dtEvento;
    }

    public String getCorrecao() {
        return this.correcao;
    }

    public void setCorrecao(String correcao) {
        this.correcao = correcao;
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
            CartaCorrecaoNfeVO other = (CartaCorrecaoNfeVO)obj;
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
