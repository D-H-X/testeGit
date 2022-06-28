//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.depara;

public enum XmlItemTabEnum {
    ALIQUOTA_SERVICO("X_VALIQ", "aliquotaServicos"),
    DISCRIMINACAO_SERVICO("XPROD", "discriminacaoServico"),
    DISCRIMINACAO_INFADPROD("INFADPROD", "discriminacaoServico"),
    QUANTIDADE("QCOM_V20", "quantidade"),
    VALOR_UNITARIO("VUNCOM_V20", "valorUnitario"),
    VALOR_TOTAL("VPROD", "valorTotal");

    private String sapKey;
    private String fieldMsaf;

    private XmlItemTabEnum(String sapKey, String fieldMsaf) {
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
