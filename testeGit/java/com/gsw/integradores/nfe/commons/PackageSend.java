//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.commons;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

public class PackageSend {
    public static final String ACTION_ENVIAR_PACOTE = "EnviarPacote";
    public static final String ACTION_CANCELAR = "Cancelar";
    public static final String ACTION_INUTILIZAR = "Inutilizar";
    public static final String ACTION_CCE = "EnviarCartaCorrecaoNfe";
    private String action;
    private Object contents;
    private String referencia;
    private List<?> ids;

    public PackageSend() {
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("txt_conteudo")
    public Object getContents() {
        return this.contents;
    }

    @JsonProperty("txt_conteudo")
    public void setContents(Object txtContents) {
        this.contents = txtContents;
    }

    public List<?> getIds() {
        return this.ids;
    }

    public void setIds(List<?> ids) {
        this.ids = ids;
    }

    public String getReferencia() {
        return this.referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
