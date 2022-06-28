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

public class InvFromQueue2SAP extends DfeDefaultRouteBuilder {
    public InvFromQueue2SAP() {
    }

    public void configure() throws Exception {


//		String token = "PcrY0FH9KS7NdKcm2Cp-jKyavN7LOOzEhzufIqaSNU2J2e_TdRaPphTAA6a0b0KbBwqtjokzzXMkBvB4t2GDcisOxmNocOZGypBDrj6Kq0JbXmwAJNzixBLEoTBKQnhrYcjtByZgtPZe1oTJkUqbsleIu7SQQgC0gK-9ltdqg_dhYb4kE4pF5U4iYTvupu78sUKb0zSOnh1MaV-Nunf6W783rShzuZnMBIFN3d7RPHQAbCDh7nLUBWzjjl-7NRmuT6CtlKdLQcdX3WX7YcVUIDKxS3rBe_aiqz9pStCtNoJA5TWTqQ6ycbsFMxEfAlE5WXzfjjCqU2OZW7P1h80Y1QYo8XhGpSYij2a7rpXFjZw";
		
		String ws = "35220331565104004830550010001101221710130599";
/*
		((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) ((RouteDefinition) //((RouteDefinition) // ((RouteDefinition)

//		      RouteDefinition wsConfig = from("{{inv.queue.status.response.in}}").id(RoutesEnum.INV_WS_TO_QUEUEIN_RETORNO.id).to("bean:InvStatusResponseBuilder?method=build");

		this.from("{{inv.queue.in}}").id(RoutesEnum.INV_WS_TO_QUEUEIN_RETORNO.id)).log(LoggingLevel.INFO,
				"Lendo fila \'inv.queue.in\'"))

						.to("bean:InvStatusResponseBuilder?method=build"))
				.setHeader("Content-Type", this.constant(
								"application/json"))).setHeader("CamelHttpMethod", this.constant(HttpMethod.GET)))

										.setHeader("Authorization", this.constant("Bearer " + token))

										.setHeader("Cache-Control", this.constant("no-cache")))
				.to("https://sandbox-api.invoisys.com.br/api/nfe/getbychave/" +  ws  +"?throwExceptionOnFailure=false")).log(
//												.to("https://sandbox-api.invoisys.com.br/api/nfe/getbychave/35210231565104004830550010001100031571379414?throwExceptionOnFailure=false")).log(
														LoggingLevel.INFO,
														"GET no webservice realizado. - nfe.invoisys.queue.status.response.in"))
																.to("bean:nfeInvoisysSendResponseBuilder?method=buildRet"))
																		.marshal().json(JsonLibrary.Jackson)
																		.to("{{inv.queue.in}}").log(LoggingLevel.INFO,
																				"Finalizando Rota - INVOISYS **********\'nfe.invoisys.queue.out\'");
	*/
		
	

	
    }
}

