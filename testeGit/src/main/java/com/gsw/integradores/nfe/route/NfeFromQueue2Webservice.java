//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.OnExceptionDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfeFromQueue2Webservice extends DfeDefaultRouteBuilder {
    public NfeFromQueue2Webservice() {
    }

    public void configure() throws Exception {
        ((OnExceptionDefinition)((OnExceptionDefinition)this.onException(Throwable.class).maximumRedeliveries("10").logRetryAttempted(true).log("DOCNUM: " + this.header("transactionId").convertToString() + " Retrying to Send Body: " + this.body().convertToString())).retriesExhaustedLogLevel(LoggingLevel.ERROR).logExhausted(true).redeliveryDelay("10000").useOriginalMessage().log("DOCNUM: " + this.header("transactionId") + " ERROR recebido: " + this.property("CamelExceptionCaught"))).logStackTrace(true).continued(true);
        ((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{nfe.queue.out}}").id(RoutesEnum.NFE_FROM_QUEUEOUT_TO_WS.id)).log(LoggingLevel.INFO, "Lendo fila \'nfe.queue.out\'")).setHeader("transactionId", this.xpath("//*[name()=\'docNum\']").stringResult())).setHeader("dataEntrada", this.xpath("//*[name()=\'dataEntradaCont\']").stringResult())).setHeader("motivo", this.xpath("//*[name()=\'dsMotivoCont\']").stringResult())).setHeader("numeroNota", this.xpath("//*[name()=\'nNF\']").stringResult())).setHeader("serie", this.xpath("//*[name()=\'serie\']").stringResult())).setHeader("chave", this.xpath("//*[name()=\'infNFe\']//@Id").stringResult())).setHeader("cnpjEmissor", this.xpath("//emit/CNPJ").stringResult())).setBody(this.xpath("//*[name()=\'NFe\']"))).convertBodyTo(String.class)).to("bean:nfeRequestBuilder?method=build")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Cache-Control", this.constant("no-cache"))).to("{{dfe.url}}/{{nfe.ws.path}}?throwExceptionOnFailure=false")).log(LoggingLevel.INFO, "POST no webservice realizado. - nfe.queue.out")).to("bean:nfeSendResponseBuilder?method=build")).marshal().json(JsonLibrary.Jackson).to("{{nfe.queue.in}}")).log(LoggingLevel.INFO, "Finalizando Rota \'nfe.queue.out\'");
    }
}
