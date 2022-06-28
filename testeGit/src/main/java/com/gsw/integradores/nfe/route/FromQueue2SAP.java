//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class FromQueue2SAP extends DfeDefaultRouteBuilder {
    public FromQueue2SAP() {
    }

    public void configure() throws Exception {
        ((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{nfs.queue.in}}").id(RoutesEnum.FROM_QUEUEIN_TO_SAP.id)).log(LoggingLevel.DEBUG, "Lendo fila \'nfs.queue.in\'")).errorHandler(this.defaultErrorHandler().maximumRedeliveries(3).backOffMultiplier(2.0D).retryAttemptedLogLevel(LoggingLevel.WARN)).unmarshal().json(JsonLibrary.Jackson, FeedbackNfsERP.class).to("bean:sapWriter?method=write")).log(LoggingLevel.DEBUG, "Finalizando Rota \'nfs.queue.in\'");
    }
}
