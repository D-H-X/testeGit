//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

public enum QueuesEnum {
    QUEUE_CONN_OUT("activemq:queue-conn-out", "fila populada pelo conector - nfs"),
    QUEUE_CONN_IN("activemq:queue-conn-in", "fila a ser consumida pelo conector - status");

    private final String uri;
    public final String descricao;

    private QueuesEnum(String uri, String descricao) {
        this.uri = uri;
        this.descricao = descricao;
    }

    public String uri(String targetFolder) {
        return String.format(this.uri, new Object[]{targetFolder});
    }
}
