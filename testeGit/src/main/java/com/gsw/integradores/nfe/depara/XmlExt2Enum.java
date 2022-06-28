//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.depara;

public enum XmlExt2Enum {
    SITUACAO_INTERNA("SITUACAO_INTERNA", "codigoServicoInterno");

    private String sapKey;
    private String fieldMsaf;

    private XmlExt2Enum(String sapKey, String fieldMsaf) {
        this.sapKey = sapKey;
        this.fieldMsaf = fieldMsaf;
    }

    public String getSapKey() {
        return this.sapKey;
    }

    public void setSapKey(String sapKey) {
        this.sapKey = sapKey;
    }

    public String getFieldMsaf() {
        return this.fieldMsaf;
    }

    public void setFieldMsaf(String fieldMsaf) {
        this.fieldMsaf = fieldMsaf;
    }
}
