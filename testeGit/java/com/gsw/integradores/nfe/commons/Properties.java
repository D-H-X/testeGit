//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.commons;

import com.gsw.integradores.nfe.commons.Util;

public enum Properties {
    dfe_tipoAmbienteSistema("2"),
    dfe_url((String)null),
    dfe_username((String)null),
    dfe_password((String)null),
    dfe_utilizaDataPortal("false"),
    dfe_timeout((String)null),
    dfe_timeout_retornoerp((String)null),
    dfe_querie_consumer_threads((String)null),
    dfe_truncaPrefNo("0"),
    dfe_campoInfAdProd("false"),
    dfe_cancelamentoEvento("true"),
    dfe_versaoDados("2.0"),
    dfe_versaoLayout("2.1"),
    dfe_executaFuncaoZServ("false"),
    dfe_salvaRecord("false"),
    dfe_modulos("nfe"),
    dfe_config((String)null),
    ws_querie_interval((String)null),
    sap_serverName((String)null),
    sap_destinationName((String)null),
    nfe_queue_out_concurrent_consumers((String)null),
    nfe_queue_cancellation_out_concurrent_consumers((String)null),
    nfe_queue_inutilizacao_out_concurrent_consumers((String)null),
    nfe_queue_cce_out_concurrent_consumers((String)null),
    nfe_queue_in_concurrent_consumers((String)null),
    nfe_queue_status_response_in_concurrent_consumers((String)null),
    nfe_queue_print_response_in_concurrent_consumers((String)null),
    nfs_queue_out_concurrent_consumers((String)null),
    nfs_queue_cancellation_out_concurrent_consumers((String)null),
    nfs_queue_in_concurrent_consumers((String)null),
    broker_active((String)null),
    broker_host((String)null),
    broker_jmx_port((String)null),
    broker_name((String)null),
    proxy_host((String)null),
    proxy_port((String)null),
    proxy_username((String)null),
    proxy_password((String)null),
    timezone_usarValorFixo("N");

    private String valorPadrao;

    private Properties(String valorPadrao) {
        this.valorPadrao = valorPadrao;
    }

    public String getValue() {
        try {
            String e = Util.getProperties().getProperty(this.obterNomePropriedade());
            return e.length() > 0?e:this.valorPadrao;
        } catch (Exception var2) {
            return this.valorPadrao;
        }
    }

    public boolean getBollean() {
        return Boolean.getBoolean(this.getValue());
    }

    public int getInt() {
        return Integer.valueOf(this.getValue()).intValue();
    }

    public String[] getArray() {
        String[] array = this.getValue().split(",");

        for(int i = 0; i < array.length; ++i) {
            array[i] = array[i].trim();
        }

        return array;
    }

    public boolean contemModulo(String modulo) {
        return this.containsIgnoreCase(this.getArray(), modulo);
    }

    private boolean containsIgnoreCase(String[] array, String item) {
        String[] var6 = array;
        int var5 = array.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            String str = var6[var4];
            if(str.equalsIgnoreCase(item)) {
                return true;
            }
        }

        return false;
    }

    private String obterNomePropriedade() {
        return this.toString().replace('_', '.');
    }
}
