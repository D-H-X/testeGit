//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfeFromQueue2SAP extends DfeDefaultRouteBuilder {
	public NfeFromQueue2SAP() {
	}

	public void configure() throws Exception {
		RouteDefinition nfeFromQueueinToSap = (RouteDefinition) this.from("{{nfe.queue.in}}")  
		.id(RoutesEnum.NFE_FROM_QUEUEIN_TO_SAP.id);
		RouteDefinition queuein = (RouteDefinition) nfeFromQueueinToSap.log(LoggingLevel.DEBUG,
				"Lendo fila \'nfe.queue.in\'");
		RouteDefinition write = (RouteDefinition) queuein
				.errorHandler(this.defaultErrorHandler().maximumRedeliveries(3).backOffMultiplier(2.0D)
						.retryAttemptedLogLevel(LoggingLevel.WARN))
				.unmarshal().json(JsonLibrary.Jackson, FeedbackNfeERP.class).to("bean:nfeSapWriter?method=write");
		write.log(LoggingLevel.DEBUG, "Finalizando Rota \'nfe.queue.in\'");
	}
}
