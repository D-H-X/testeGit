//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.OnExceptionDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.ThreadsDefinition;

public class NfsFromWebservice2Queue extends DfeDefaultRouteBuilder {
    public NfsFromWebservice2Queue() {
    }

    public void configure() throws Exception {
        ((OnExceptionDefinition)((OnExceptionDefinition)this.onException(Throwable.class).maximumRedeliveries("10").logRetryAttempted(true).log("DOCNUM: " + this.header("transactionId").convertToString() + " Retrying to Send Body: " + this.body().convertToString())).retriesExhaustedLogLevel(LoggingLevel.ERROR).logExhausted(true).redeliveryDelay("10000").useOriginalMessage().log("DOCNUM: " + this.header("transactionId") + " ERROR recebido: " + this.property("CamelExceptionCaught"))).logStackTrace(true).continued(true);
        ((ChoiceDefinition)((ThreadsDefinition)((ThreadsDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{ws.querie.nfs}}").id(RoutesEnum.FROM_WS_TO_QUEUEIN.id)).log(LoggingLevel.INFO, "Lendo fila \'ws.querie.nfs\'")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.GET))).setHeader("Cache-Control", this.constant("no-cache"))).to("{{dfe.url}}/{{nfs.ws.path}}/getRetorno?throwExceptionOnFailure=true")).log(LoggingLevel.INFO, "GET no webservice realizado fila \'ws.querie.nfs\'.")).to("bean:statusResponseBuilder?method=build")).setHeader("ListFeedbackNfsERP", this.simple("${body}"))).to("bean:markCheckRequestBuilder?method=buildBatch")).choice().when(this.body().isNotNull()).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Content-Type", this.constant("application/json"))).to("{{dfe.url}}/{{nfs.ws.path}}?throwExceptionOnFailure=true")).log(LoggingLevel.DEBUG, "NFS Marcada como consultada!")).end().split(this.header("ListFeedbackNfsERP")).parallelProcessing().timeout((long)this.evalInt("{{dfe.timeout.retornoerp}}")).threads(this.evalInt("{{dfe.querie.consumer.threads}}"), this.evalInt("{{dfe.querie.consumer.threads}}")).setHeader("feedback", this.simple("${body}"))).setHeader("transactionId", this.simple("${body.docNum}"))).choice().when(this.header("transactionId").isNotNull()).to("bean:statusResponseBuilder?method=callSyncResponse")).end().log(LoggingLevel.INFO, "Finalizando Rota nfs \'ws.querie.nfs\'");
    }
}
