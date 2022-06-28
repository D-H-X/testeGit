//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import com.gsw.integradores.nfe.vo.CartaCorrecaoNfeVO;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfeFromQueueCCe2Webservice extends DfeDefaultRouteBuilder {
    public NfeFromQueueCCe2Webservice() {
    }

    public void configure() throws Exception {
        ((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{nfe.queue.cce.out}}").id(RoutesEnum.NFE_FROM_QUEUEOUT_CCE_TO_WS.id)).log(LoggingLevel.INFO, "Lendo fila \'nfe.queue.cce.out\'")).unmarshal().json(JsonLibrary.Jackson, CartaCorrecaoNfeVO.class).setHeader("transactionId", this.simple("${body.docNum}"))).to("bean:nfeRequestBuilder?method=buildCCe")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Cache-Control", this.constant("no-cache"))).to("{{dfe.url}}/{{nfe.ws.path}}?throwExceptionOnFailure=true")).log(LoggingLevel.INFO, "POST no webservice realizado. - nfe.queue.cce.out")).to("bean:nfeSendResponseBuilder?method=build")).marshal().json(JsonLibrary.Jackson).to("{{nfe.queue.in}}")).log(LoggingLevel.INFO, "Finalizando Rota \'nfe.queue.cce.out\'");
    }
}
