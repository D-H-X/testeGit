//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeDefaultRouteBuilder;
import com.gsw.integradores.nfe.route.RoutesEnum;
import com.gsw.integradores.nfe.vo.ResponseVO;
import org.apache.camel.LoggingLevel;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.ThreadsDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NfeFromWebservice2QueueRetorno extends DfeDefaultRouteBuilder {
    public NfeFromWebservice2QueueRetorno() {
    }

    public void configure() throws Exception {
        ((ChoiceDefinition)((ChoiceDefinition)((ThreadsDefinition)((RouteDefinition)((RouteDefinition)((RouteDefinition)this.from("{{nfe.queue.status.response.in}}").id(RoutesEnum.FROM_WS_TO_QUEUEIN_RETORNO.id)).log(LoggingLevel.INFO, "Lendo fila \'nfe.queue.status.response.in\'")).unmarshal().json(JsonLibrary.Jackson, ResponseVO.class).to("bean:nfeStatusResponseBuilder?method=build")).split(this.body()).threads(this.evalInt("{{dfe.querie.consumer.threads}}"), this.evalInt("{{dfe.querie.consumer.threads}}")).setHeader("transactionId", this.simple("${body.docNum}"))).choice().when(this.header("transactionId").isNotNull()).marshal().json(JsonLibrary.Jackson).to("{{nfe.queue.in}}")).log(LoggingLevel.INFO, "DOCNUM: " + this.header("transactionId") + " enviado para a fila nfe.queue.in")).end().log(LoggingLevel.INFO, "Finalizando Rota \'nfe.queue.status.response.in\'");
    }
}
