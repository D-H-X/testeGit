//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import com.gsw.integradores.nfe.vo.CancelamentoVO;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfsFromQueueCancellation2Webservice extends DfeDefaultRouteBuilder {
    public NfsFromQueueCancellation2Webservice() {
    }

    public void configure() throws Exception {
        ((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{nfs.queue.cancellation.out}}").id(RoutesEnum.FROM_QUEUEOUT_CANC_TO_WS.id)).log(LoggingLevel.INFO, "Lendo fila \'nfs.queue.cancellation.out\'")).unmarshal().json(JsonLibrary.Jackson, CancelamentoVO.class).setHeader("transactionId", this.simple("${body.docNum}"))).to("bean:requestBuilder?method=buildCancellation")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Cache-Control", this.constant("no-cache"))).to("{{dfe.url}}/{{nfs.ws.path}}?throwExceptionOnFailure=true")).log(LoggingLevel.INFO, "POST no Webservice NFS Cancelamento realizado.")).log(this.body().convertToString().toString())).to("bean:sendResponseBuilder?method=build")).marshal().json(JsonLibrary.Jackson).to("{{nfs.queue.in}}")).log(LoggingLevel.INFO, "Finalizando Rota \'nfs.queue.cancellation.out\'");
    }
}
