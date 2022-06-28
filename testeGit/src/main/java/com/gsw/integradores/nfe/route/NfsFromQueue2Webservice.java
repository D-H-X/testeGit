//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import com.gsw.integradores.nfe.vo.NfsVO;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.OnExceptionDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfsFromQueue2Webservice extends DfeDefaultRouteBuilder {
    public NfsFromQueue2Webservice() {
    }

    public void configure() throws Exception {
        ((OnExceptionDefinition)((OnExceptionDefinition)this.onException(Throwable.class).maximumRedeliveries("10").logRetryAttempted(true).log("DOCNUM: " + this.header("transactionId").convertToString() + " Retrying to Send Body: " + this.body().convertToString())).retriesExhaustedLogLevel(LoggingLevel.ERROR).logExhausted(true).redeliveryDelay("10000").useOriginalMessage().log("DOCNUM: " + this.header("transactionId") + " ERROR recebido: " + this.property("CamelExceptionCaught"))).logStackTrace(true).continued(true);
        ((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{nfs.queue.out}}").id(RoutesEnum.NFS_FROM_QUEUEOUT_TO_WS.id)).log(LoggingLevel.INFO, "Lendo fila \'nfs.queue.out\'")).unmarshal().json(JsonLibrary.Jackson, NfsVO.class).setHeader("transactionId", this.simple("${body.docNum}"))).to("bean:requestBuilder?method=build")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Cache-Control", this.constant("no-cache"))).to("{{dfe.url}}/{{nfs.ws.path}}?throwExceptionOnFailure=false")).log(LoggingLevel.INFO, "POST no webservice realizado. - nfs.queue.out")).to("bean:sendResponseBuilder?method=build")).marshal().json(JsonLibrary.Jackson).to("{{nfs.queue.in}}")).log(LoggingLevel.INFO, "Finalizando Rota \'nfs.queue.out\'");
    }
}
