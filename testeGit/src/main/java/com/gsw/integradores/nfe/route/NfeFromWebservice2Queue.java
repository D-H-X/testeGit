//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import io.netty.handler.codec.http.HttpMethod;
import java.util.UUID;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.OnExceptionDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.ThreadsDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfeFromWebservice2Queue extends DfeDefaultRouteBuilder {
	public NfeFromWebservice2Queue() {
	}

	public void configure() throws Exception {
		  ((OnExceptionDefinition)((OnExceptionDefinition)this.onException(Throwable.class).maximumRedeliveries("10").logRetryAttempted(true).log("DOCNUM: " + this.header("transactionId").convertToString() + " Retrying to Send Body: " + this.body().convertToString())).retriesExhaustedLogLevel(LoggingLevel.ERROR).logExhausted(true).logContinued(true).logRetryStackTrace(true).redeliveryDelay("10000").useOriginalMessage().log("DOCNUM: " + this.header("transactionId") + " ERROR recebido: " + this.property("CamelExceptionCaught"))).logStackTrace(true).continued(true);
	        String wsConfig = Properties.dfe_config.getValue();
	        ((ThreadsDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((ChoiceDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{ws.querie.nfe}}").id(RoutesEnum.NFE_FROM_WS_TO_QUEUEIN.id)).log(LoggingLevel.INFO, "Lendo fila \'ws.querie.nfe\'")).setHeader("Content-Type", this.constant("application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.GET))).choice().when(this.header("CamelHttpQuery").isNotNull()).log("Rota NFE \'dfe.querie\' ativada com o filtro: " + wsConfig)).end().setHeader("Cache-Control", this.constant("no-cache")).to("{{dfe.url}}/{{nfe.ws.path}}/getRetorno" + (wsConfig != null?wsConfig:"")).log(LoggingLevel.INFO, "GET no webservice realizado fila \'ws.querie.nfe\'.").to("bean:nfeStatusResponseBuilder?method=buildResponse").setHeader("ResponseVO", this.body()).to("bean:nfeMarkCheckRequestBuilder?method=buildBatch").choice().when(this.body().isNotNull()).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST))).setHeader("Cache-Control", this.constant("no-cache"))).setHeader("Content-Type", this.constant("application/json"))).to("{{dfe.url}}/{{nfe.ws.path}}?throwExceptionOnFailure=false")).log(LoggingLevel.INFO, "POST no webservice realizado MARK READ")).end().setBody(this.header("ResponseVO")).removeHeader("ResponseVO").to("bean:nfeStatusResponseBuilder?method=build").setHeader("transactionId", this.constant(UUID.randomUUID())).split(this.body()).parallelProcessing().threads(this.evalInt("{{dfe.querie.consumer.threads}}"), this.evalInt("{{dfe.querie.consumer.threads}}")).to("bean:nfeStatusResponseBuilder?method=callSyncResponse")).log(LoggingLevel.INFO, "Finalizando Rota \'ws.querie.nfe\'");
	    }
	}