//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ResponseVO {
    private Integer httpCode;
    private String json;

    public ResponseVO(@JsonProperty("httpCode") Integer httpCode, @JsonProperty("json") String json) {
        this.httpCode = httpCode;
        this.json = json;
    }

    public Integer getHttpCode() {
        return this.httpCode;
    }

    public String getJson() {
        return this.json;
    }
}
