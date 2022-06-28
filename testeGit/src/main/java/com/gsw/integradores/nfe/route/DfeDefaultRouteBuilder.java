//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.route.DfeCamelContext;
import org.apache.camel.builder.RouteBuilder;

public abstract class DfeDefaultRouteBuilder extends RouteBuilder {
    public DfeDefaultRouteBuilder() {
    }

    protected String eval(String expression) {
        return ((DfeCamelContext)this.getContext()).prop(expression);
    }

    protected int evalInt(String expression) {
        return ((DfeCamelContext)this.getContext()).evalIntProp(expression);
    }

    protected boolean evalBoolean(String expression) {
        return ((DfeCamelContext)this.getContext()).evalBooleanProp(expression);
    }
}
