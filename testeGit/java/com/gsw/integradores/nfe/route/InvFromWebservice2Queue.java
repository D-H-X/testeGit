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

public class InvFromWebservice2Queue extends DfeDefaultRouteBuilder {
    public InvFromWebservice2Queue() {
    }

    public void configure() throws Exception {


		String token = "r3pU9G3rILD7luTdDf7WnW6ZtG0REIexJ9mjw1QddyH8XoEkNUQ-AZbEhgXAXySDQCC9Db4i4COGKDIc8Ne6oEhtlqj2dUFEHNPfmM08RmjRUr6vIt4-tEPHtRHv1xf7keFRdmyvlWwwIKMv7hC99AfE4typfHmkJwsZ9sK2Xlm9TxM6PA99ROhC8FSt-078x0NTBd1ly7lTj883yLJM_EmeUw7JjAEuxHJugD99sQ5sBDs8ZggBfsTe3myWU_-GSsEqQhTCKwgp0gCzSMoOBejpXUrNZqKlb6oDy5b91JYywE07xf9RfKo7uy-OXtlsIxL1UHlkqylhTnoHYl2qSh3LDeeuOKz2U9w7fUuZGGQ";
//		((OnExceptionDefinition) ((OnExceptionDefinition) this.onException(Throwable.class).maximumRedeliveries("10")
//				.logRetryAttempted(true)
//				.log("DOCNUM: " + this.header("transactionId").convertToString() + " Retrying to Send Body: "
//						+ this.body().convertToString())).retriesExhaustedLogLevel(LoggingLevel.ERROR)
//								.logExhausted(true).redeliveryDelay("10000").useOriginalMessage()
//								.log("DOCNUM: " + this.header("transactionId") + " ERROR recebido: "
//										+ this.property("CamelExceptionCaught"))).logStackTrace(true).continued(true);
	/*	
		RouteDefinition wsConfig = from("{{inv.queue.status.response.in}}").id(RoutesEnum.INV_WS_TO_QUEUEIN_RETORNOTO.id).to("bean:InvStatusResponseBuilder?method=build");
		String ws = "35220331565104004830550010001101211709378445";
		((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) //((RouteDefinition) // ((RouteDefinition)

//		      RouteDefinition wsConfig = from("{{inv.queue.status.response.in}}").id(RoutesEnum.INV_WS_TO_QUEUEIN_RETORNO.id).to("bean:InvStatusResponseBuilder?method=build");

		this.from("{{inv.queue.status.response.in}}").id(RoutesEnum.INV_WS_TO_QUEUEIN_RETORNO.id)).log(LoggingLevel.INFO,
				"************************Lendo fila \'inv.queue.status.response.in\'***************************"))

						.to("bean:InvStatusResponseBuilder?method=build"))
				.setHeader("Content-Type", this.constant(
								"application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.GET)))

										.setHeader("Authorization", this.constant("Bearer " + token))

										.setHeader("Cache-Control", this.constant("no-cache")))
				.to("https://sandbox-api.invoisys.com.br/api/nfe/getbychave/" +  ws )).log(
//						.to("https://sandbox-api.invoisys.com.br/api/nfe/getbychave/" +  ws  +"?throwExceptionOnFailure=false")).log(
//						.to("{{invoisys.url}}/nfe/getbychave/" +  ws  +"?throwExceptionOnFailure=false")).log(
//												.to("https://sandbox-api.invoisys.com.br/api/nfe/getbychave/35210231565104004830550010001100031571379414?throwExceptionOnFailure=false")).log(
														LoggingLevel.INFO,
														"***********************GET no webservice INVOISYS realizado. - nfe.invoisys.queue.status.response.in*********************"))
																.to("bean:nfeInvoisysSendResponseBuilder?method=buildRet"))
																		.marshal().json(JsonLibrary.Jackson)
																		.to("{{nfe.queue.in}}").log(LoggingLevel.INFO,
																				"Finalizando Rota - INVOISYS **********\'nfe.invoisys.queue.out\'");
*/
	
    }
}

