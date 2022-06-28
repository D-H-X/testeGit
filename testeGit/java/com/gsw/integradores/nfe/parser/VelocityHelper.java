//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.parser;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;

public class VelocityHelper {
    private VelocityEngine ve;

    public VelocityHelper() {
        Properties prop = new Properties();
        prop.setProperty("resource.loader", "classpath");
        prop.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        prop.setProperty("classpath.resource.loader.cache", "false");
        this.ve = new VelocityEngine(prop);
    }

    public String parseFromTemplate(Map<String, Object> values, String template) {
        if(values != null && !values.isEmpty()) {
            if(template == null) {
                throw new IllegalArgumentException("template não pode ser nula");
            } else {
                try {
                    StringWriter ex = new StringWriter();
                    HashMap values1 = new HashMap(values);
                    VelocityContext velocityContext = new VelocityContext(values1);
                    this.ve.mergeTemplate(template, "UTF-8", velocityContext, ex);
                    String resultStr = ex.toString();
                    return resultStr.replaceAll(">+\\s+<", "><");
                } catch (RuntimeException var6) {
                    throw var6;
                } catch (Exception var7) {
                    throw new VelocityException(var7.toString());
                }
            }
        } else {
            return null;
        }
    }
}
