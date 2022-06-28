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
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.ThreadsDefinition;

public class NfeFromWebservice2QueuePrinter extends DfeDefaultRouteBuilder {
    public NfeFromWebservice2QueuePrinter() {
    }

    public void configure() throws Exception {
        ((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ThreadsDefinition)((ThreadsDefinition)((ThreadsDefinition)((ThreadsDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{ws.querie.printer}}").id(RoutesEnum.FROM_WS_TO_QUEUEIN_IMPRESSAO.id)).log(LoggingLevel.INFO, "Lendo fila \'dfe.querie.printer\'")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.GET))).setHeader("Cache-Control", this.constant("no-cache"))).to("{{dfe.url}}/{{nfe.ws.path}}")).log(LoggingLevel.INFO, "GET no webservice realizado fila \'ws.querie.printer\'.")).to("bean:nfeStatusResponseBuilder?method=buildPrintResponseVO")).split(this.body()).threads(this.evalInt("{{dfe.querie.consumer.threads}}"), this.evalInt("{{dfe.querie.consumer.threads}}")).to("bean:nfeStatusResponseBuilder?method=convertParamsPrint")).setHeader("idProcessamento", this.simple("${body.idProcessamento}"))).setHeader("idNota", this.simple("${body.idNota}"))).to("bean:nfePrinterFacade?method=sendToPrinter")).choice().when(this.body().isEqualTo(Boolean.TRUE)).to("bean:nfeMarkPrintRequestBuilder?method=build")).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Cache-Control", this.constant("no-cache"))).setHeader("Content-Type", this.constant("application/json"))).to("{{dfe.url}}/{{nfe.ws.path}}?throwExceptionOnFailure=true")).log(LoggingLevel.INFO, "POST no webservice realizado.")).log(LoggingLevel.INFO, "Finalizando Rota \'ws.querie.printer\'");
    }
}
