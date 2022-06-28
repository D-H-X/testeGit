//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import java.io.File;
import java.util.Properties;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;

public class DfeCamelContext extends DefaultCamelContext {
    public DfeCamelContext(Registry registry) {
        super(registry);
        this.prepareProperties();
    }

    private DfeCamelContext prepareProperties() {
        PropertiesComponent pc = new PropertiesComponent();
        File file = new File("config/config.properties");
        if(file.exists()) {
            pc.setLocation("file:config/config.properties,classpath:system-config.properties");
        } else {
            pc.setLocation("classpath:config/config.properties,classpath:system-config.properties");
        }

        this.addComponent("properties", pc);
        return this;
    }

    public DfeCamelContext overrideProperties(Properties p) {
        this.getPropertiesComponent().setOverrideProperties(p);
        return this;
    }

    protected String prop(String expression) {
        String result = "";

        try {
            result = this.resolvePropertyPlaceholders(expression);
        } catch (Exception var4) {
            ;
        }

        return result.trim().length() == 0?null:result;
    }

    protected String prop(String expression, String defaultValue) {
        String result = "";

        try {
            result = this.resolvePropertyPlaceholders(expression);
        } catch (Exception var5) {
            ;
        }

        return result.trim().length() == 0?defaultValue:result;
    }

    protected int evalIntProp(String expression) {
        return Integer.parseInt(this.prop(expression));
    }

    protected boolean evalBooleanProp(String expression) {
        return "true".equalsIgnoreCase(this.prop(expression));
    }
}
