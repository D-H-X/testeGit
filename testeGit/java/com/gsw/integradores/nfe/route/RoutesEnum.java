//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

public enum RoutesEnum {
	
	INV_FROM_QUEUEOUT_TO_WS("da fila populada pelo conector atÃ© REST - INV"),
	INV_WS_TO_QUEUEIN_RETORNO("do webservice INVOISYS atÃ© fila a ser consumida pelo conector para retorno - status"),
	INV_WS_TO_QUEUEIN_RETORNOTO("do webservice INVOISYS atÃ© fila a ser consumida pelo conector para retorno 2- status"),
	
	
    NFS_FROM_QUEUEOUT_TO_WS("da fila populada pelo conector atÃ© webservice - nfs"),
    NFE_FROM_QUEUEOUT_TO_WS("da fila populada pelo conector atÃ© webservice - nfe"),
    FROM_QUEUEOUT_CANC_TO_WS("da fila populada pelo conector atÃ© webservice - cancelamento"),
    NFE_FROM_QUEUEOUT_CANC_TO_WS("da fila populada pelo conector atÃ© webservice - cancelamento"),
    NFE_FROM_QUEUEOUT_INU_TO_WS("da fila populada pelo conector atÃ© webservice - inutilizacao"),
    FROM_WS_TO_QUEUEIN("do webservice atÃ© fila a ser consumida pelo conector - status"),
    NFE_FROM_WS_TO_QUEUEIN("do webservice atÃ© fila a ser consumida pelo conector - status"),
    FROM_WS_TO_QUEUEIN_RETORNO("do webservice atÃ© fila a ser consumida pelo conector para retorno - status"),
    FROM_WS_TO_QUEUEIN_IMPRESSAO("do webservice atÃ© fila a ser consumida pelo conector para impressao - status"),
    FROM_WS_TO_QUEUEIN_IMPRESSAO_IGNORADA("ignora impressao via printer"),
    NFE_FROM_QUEUEOUT_CCE_TO_WS("da fila populada pelo conector atÃ© webservice - cc-e"),
    FROM_EBS_NOTIFICATION_TO_QUEUES("do EBS (notificaÃ§Ã£o) atÃ© as filas de NFE e NFS"),
    FROM_EBS_TO_QUEUEOUT("do EBS atÃ© fila consumida pelo integrador - nfs"),
    FROM_EBS_CANC_TO_QUEUEOUT("do EBS (cancelamento) atÃ© fila consumida pelo integrador - cancelamento"),
    NFE_FROM_EBS_TO_QUEUEOUT("do EBS atÃ© fila consumida pelo integrador - nfe"),
    NFE_FROM_EBS_CANC_TO_QUEUEOUT("do EBS (cancelamento) atÃ© fila consumida pelo integrador - cancelamento nfe"),
    NFE_FROM_EBS_INUT_TO_QUEUEOUT("do EBS (inutilizacao) atÃ© fila consumida pelo integrador - inutilizacao nfe"),
    FROM_QUEUEIN_TO_EBS("da fila populada pelo integrador atÃ© fila consumida pelo conector EBS - status"),
    NFE_FROM_QUEUEIN_TO_EBS("da fila populada pelo integrador atÃ© fila consumida pelo conector EBS - status"),
    FROM_QUEUEIN_TO_SAP("da fila populada pelo integrador atÃ© fila consumida pelo conector SAP - status"),
    NFE_FROM_QUEUEIN_TO_SAP("da fila populada pelo integrador atÃ© fila consumida pelo conector SAP - status");

    public final String id = this.name();
    public final String descricao;
    public static final String KEY_LABEL = "transactionId";

    private RoutesEnum(String descricao) {
        this.descricao = descricao;
    }
}
