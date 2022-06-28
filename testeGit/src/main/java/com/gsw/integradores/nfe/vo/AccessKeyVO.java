//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

public class AccessKeyVO {
    private String cUF;
    private String AA;
    private String mm;
    private String cnpj;
    private String mod;
    private String serie;
    private String nNF;
    private String tpEmis;
    private String cNF;
    private String dv;

    public String getcUF() {
        return this.cUF;
    }

    public String getmm() {
        return this.mm;
    }

    public String getAA() {
        return this.AA;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getMod() {
        return this.mod;
    }

    public String getSerie() {
        return this.serie;
    }

    public String getnNF() {
        return this.nNF;
    }

    public String getTpEmis() {
        return this.tpEmis;
    }

    public String getcNF() {
        return this.cNF;
    }

    public String getDv() {
        return this.dv;
    }

    public AccessKeyVO(String accessKey) {
        if(accessKey.length() == 44) {
            this.cUF = accessKey.substring(0, 2);
            this.AA = accessKey.substring(2, 4);
            this.mm = accessKey.substring(4, 6);
            this.cnpj = accessKey.substring(6, 20);
            this.mod = accessKey.substring(20, 22);
            this.serie = accessKey.substring(22, 25);
            this.nNF = accessKey.substring(25, 34);
            this.tpEmis = accessKey.substring(34, 35);
            this.cNF = accessKey.substring(35, 43);
            this.dv = accessKey.substring(43, 44);
        }

    }
}
