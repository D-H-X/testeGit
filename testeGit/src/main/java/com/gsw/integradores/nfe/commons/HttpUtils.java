//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.commons;

import com.gsw.integradores.nfe.commons.Properties;
import java.io.File;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class HttpUtils {
    PropertiesComponent pc = null;

    public HttpUtils() {
        this.prepareProperties();
    }

    public void getNfe() throws Exception {
        HttpClient client = this.getNewClient();
        URI l = new URI(Properties.dfe_url.getValue());
        LogUtil.info("DIEGO - VALOR DA VARIAVEL URL - CLASSE HTTPUTIL: " + l);
        HttpHost host = new HttpHost(l);
        LogUtil.info("DIEGO - VALOR DA VARIAVEL HOST - CLASSE HTTPUTIL: " + host);
        client.getHostConfiguration().setHost(host);
        GetMethod method = new GetMethod(l.getPath());
        LogUtil.info("DIEGO - VALOR DA VARIAVEL METHOD - CLASSE HTTPUTIL: " + method);
        method.setFollowRedirects(false);
        client.executeMethod(method);
    }

    private HttpClient getNewClient() {
        HttpClient httpClient = new HttpClient();
        String proxyHost = Properties.proxy_host.getValue();
        String proxyPort = Properties.proxy_port.getValue();
        String proxyDomain = "";
        String proxyUsername = Properties.proxy_username.getValue();
        String proxyPassword = Properties.proxy_password.getValue();
        if(proxyHost != null && proxyHost.length() > 0) {
            if(proxyUsername != null && proxyUsername.trim().length() > 0) {
                if(proxyDomain != null && proxyDomain.trim().length() > 0) {
                    httpClient.getState().setProxyCredentials(new AuthScope(AuthScope.ANY), new NTCredentials(proxyUsername, proxyPassword, proxyHost, proxyDomain));
                } else {
                    httpClient.getState().setProxyCredentials(new AuthScope(AuthScope.ANY), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
                }
            } else {
                httpClient.getHostConfiguration().setProxy(proxyHost, Integer.parseInt(proxyPort));
            }
        }

        String wsUsername = Properties.dfe_username.getValue();
        String wsPassword = Properties.dfe_password.getValue();
        LogUtil.info("DIEGO - VALOR DA VARIAVEL USERNAME - CLASSE HTTPUTILS: " + wsUsername);
        LogUtil.info("DIEGO - VALOR DA VARIAVEL PASSWORD - CLASSE HTTPUTILS: " + wsPassword);
        
        if(wsUsername != null && wsUsername.trim().length() > 0) {
            UsernamePasswordCredentials params = new UsernamePasswordCredentials(wsUsername, wsPassword);
            LogUtil.info("DIEGO - VALOR DA VARIAVEL PARAM - CLASSE HTTPUTILS: " + params);
            httpClient.getState().setCredentials(new AuthScope(AuthScope.ANY), params);
            LogUtil.info("DIEGO - VALOR DA VARIAVEL HTTPCLIENT - CLASSE HTTPUTILS: " + httpClient);
            httpClient.getParams().setAuthenticationPreemptive(true);
        }

        HttpClientParams params1 = httpClient.getParams();
        params1.setParameter("http.connection.timeout", Integer.valueOf(10000));
        params1.setParameter("http.socket.timeout", Integer.valueOf(10000));
        LogUtil.info("DIEGO - VALOR DA VARIAVEL PARAMS1 - CLASSE HTTPUTIL: " + params1);
        return httpClient;
    }

    private void prepareProperties() {
        this.pc = new PropertiesComponent();
        File file = new File("config/config.properties");
        if(file.exists()) {
            this.pc.setLocation("file:config/config.properties,classpath:system-config.properties");
        } else {
            this.pc.setLocation("classpath:config/config.properties,classpath:system-config.properties");
        }

    }

    protected String prop(String expression) {
        String result = "";

        try {
            result = this.pc.parseUri(expression);
        } catch (Exception var4) {
            ;
        }

        return result.trim().length() == 0?null:result;
    }

    protected String prop(String expression, String defaultValue) {
        String result = "";

        try {
            result = this.pc.parseUri(expression);
        } catch (Exception var5) {
            ;
        }

        return result.trim().length() == 0?defaultValue:result;
    }
}
