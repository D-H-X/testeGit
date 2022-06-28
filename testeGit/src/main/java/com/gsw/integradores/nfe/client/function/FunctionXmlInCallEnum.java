//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

public enum FunctionXmlInCallEnum {
    AUTHORIZATION_OK("1"),
    REJECT("2"),
    DENIAL("3"),
    CANCEL_OK("4"),
    INUTILIZACAO_OK("5"),
    REJECT_CANCEL("6"),
    REJECT_INUTILIZACAO("7");

    private String iMsgType;

    private FunctionXmlInCallEnum(String iMsgType) {
        this.iMsgType = iMsgType;
    }

    public String getiMsgType() {
        return this.iMsgType;
    }
}
