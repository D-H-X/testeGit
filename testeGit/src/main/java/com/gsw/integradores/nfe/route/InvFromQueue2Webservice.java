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

public class InvFromQueue2Webservice extends DfeDefaultRouteBuilder {
	public InvFromQueue2Webservice() {
	}

	public void configure() throws Exception {

		String token = "PcrY0FH9KS7NdKcm2Cp-jKyavN7LOOzEhzufIqaSNU2J2e_TdRaPphTAA6a0b0KbBwqtjokzzXMkBvB4t2GDcisOxmNocOZGypBDrj6Kq0JbXmwAJNzixBLEoTBKQnhrYcjtByZgtPZe1oTJkUqbsleIu7SQQgC0gK-9ltdqg_dhYb4kE4pF5U4iYTvupu78sUKb0zSOnh1MaV-Nunf6W783rShzuZnMBIFN3d7RPHQAbCDh7nLUBWzjjl-7NRmuT6CtlKdLQcdX3WX7YcVUIDKxS3rBe_aiqz9pStCtNoJA5TWTqQ6ycbsFMxEfAlE5WXzfjjCqU2OZW7P1h80Y1QYo8XhGpSYij2a7rpXFjZw";
	/*
		((OnExceptionDefinition) ((OnExceptionDefinition) this.onException(Throwable.class).maximumRedeliveries("10")
				.logRetryAttempted(true)
				.log("DOCNUM: " + this.header("transactionId").convertToString() + " Retrying to Send Body: "
						+ this.body().convertToString())).retriesExhaustedLogLevel(LoggingLevel.ERROR)
								.logExhausted(true).redeliveryDelay("10000").useOriginalMessage()
								.log("DOCNUM: " + this.header("transactionId") + " ERROR recebido: "
										+ this.property("CamelExceptionCaught"))).logStackTrace(true).continued(true);
		((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) // ((RouteDefinition)

		this.from("{{inv.queue.out}}").id(RoutesEnum.INV_FROM_QUEUEOUT_TO_WS.id)).log(LoggingLevel.INFO,
				"Lendo fila \'inv.queue.out\'"))

						.to("bean:nfeInvoisysRequestBuilder?method=build")).setHeader("Content-Type", this.constant(
								"application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.POST)))

										.setHeader("Authorization", this.constant("Bearer " + token))

										.setHeader("Cache-Control", this.constant("no-cache")))
												.to("{{invoisys.url}}/nfe/envio?throwExceptionOnFailure=false")).log(
														LoggingLevel.INFO,
														"POST no webservice realizado. - nfe.invoisys.queue.out"))
																.to("bean:nfeInvoisysSendResponseBuilder?method=build"))
																		.marshal().json(JsonLibrary.Jackson)
																		.to("{{inv.queue.status.response.in}}")).log(
																				LoggingLevel.INFO,
																				"Finalizando Rota \'nfe.invoisys.queue.out\'");
*/
	}
}
