//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.route;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.route.DfeCamelContext;
import com.ning.http.client.RequestBuilder;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Exchange;
import org.apache.camel.component.ahc.AhcEndpoint;
import org.apache.camel.component.ahc.DefaultAhcBinding;
import org.apache.camel.component.http.HttpClientConfigurer;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.spi.Registry;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.params.HttpClientParams;

public class IntegradorCamelContext extends DfeCamelContext {
    public IntegradorCamelContext(Registry registry) {
        super(registry);
        this.prepareHttpClientAuthentication();
        this.prepareActiveMQBroker();
        this.prepareActiveMQComponent();
    }

    private void prepareActiveMQComponent() {
        LogUtil.info("Iniciando a troca de porta dos Components JMS do ActiveMQ Component");
        LogUtil.info("DIEGO - EXECUTANDO O METODO prepareActiveMQComponent DA CLASSE INTEGRADORCAMELCONTEXT");
        ActiveMQComponent amqc = (ActiveMQComponent)this.getComponent("activemq");
        amqc.setBrokerURL(this.prop("{{broker.host}}"));
        LogUtil.info("Porta dos Components JMS do ActiveMQ Component alterada para: " + this.prop("{{broker.host}}"));
        LogUtil.info("FIM da troca de porta dos Components JMS do ActiveMQ Component");
    }

    private void prepareHttpClientAuthentication() {
        HttpClientConfigurer config = new HttpClientConfigurer() {
            public void configureHttpClient(HttpClient httpClient) {
                String proxyHost = IntegradorCamelContext.this.prop("{{proxy.host}}", "");
                String proxyPort = IntegradorCamelContext.this.prop("{{proxy.port}}", "");
                String proxyDomain = IntegradorCamelContext.this.prop("{{proxy.domain}}", "");
                String proxyUsername = IntegradorCamelContext.this.prop("{{proxy.username}}", "");
                String proxyPassword = IntegradorCamelContext.this.prop("{{proxy.password}}", "");
                if(proxyHost != null && proxyHost.length() > 0) {
                    HostConfiguration wsUsername = httpClient.getHostConfiguration();
                    wsUsername.setProxy(proxyHost, Integer.parseInt(proxyPort));
                    if(proxyUsername != null && proxyUsername.trim().length() > 0) {
                        if(proxyDomain != null && proxyDomain.trim().length() > 0) {
                            httpClient.getState().setProxyCredentials(new AuthScope(proxyHost, Integer.parseInt(proxyPort)), new NTCredentials(proxyUsername, proxyPassword, proxyHost, proxyDomain));
                        } else {
                            httpClient.getState().setProxyCredentials(new AuthScope(proxyHost, Integer.parseInt(proxyPort)), new UsernamePasswordCredentials(proxyUsername, proxyPassword));
                        }
                    } else {
                        httpClient.getHostConfiguration().setProxy(proxyHost, Integer.parseInt(proxyPort));
                    }
                }

                String wsUsername1 = IntegradorCamelContext.this.prop("{{dfe.username}}", "");
                String wsPassword = IntegradorCamelContext.this.prop("{{dfe.password}}", "");
                
                
                if(wsUsername1 != null && wsUsername1.trim().length() > 0) {
                    UsernamePasswordCredentials wsTimeout = new UsernamePasswordCredentials(wsUsername1, wsPassword);
                    httpClient.getState().setCredentials(new AuthScope(AuthScope.ANY), wsTimeout);
                    httpClient.getParams().setAuthenticationPreemptive(true);
                }

                int wsTimeout1 = Integer.parseInt(IntegradorCamelContext.this.prop("{{dfe.timeout}}", "30000"));
                httpClient.setConnectionTimeout(wsTimeout1);
                httpClient.setHttpConnectionFactoryTimeout((long)wsTimeout1);
                HttpClientParams params = httpClient.getParams();
                params.setParameter("http.connection.timeout", Integer.valueOf(wsTimeout1));
                params.setParameter("http.socket.timeout", Integer.valueOf(wsTimeout1));
            }
        };
        HttpComponent httpComponent = (HttpComponent)this.getComponent("http");
        httpComponent.setHttpClientConfigurer(config);
        HttpComponent httpsComponent = (HttpComponent)this.getComponent("https");
        httpsComponent.setHttpClientConfigurer(config);
    }

    private void prepareActiveMQBroker() {
        try {
            String e = this.prop("{{broker.active}}");
            if(!"N".equals(e)) {
                BrokerService amq = new BrokerService();
                amq.setBrokerName(this.prop("{{broker.name}}"));
                amq.setBrokerId(this.prop("{{broker.id}}"));
                amq.addConnector(this.prop("{{broker.host}}"));
                amq.getManagementContext().setConnectorPort(Integer.parseInt(this.prop("{{broker.jmx.port}}")));
                amq.start();
                LogUtil.info("BROKER " + this.prop("{{broker.name}}") + " STARTED!!!");
            }

            ActiveMQComponent amq1 = new ActiveMQComponent();
            amq1.setBrokerURL(this.prop("{{broker.host}}"));
        } catch (Exception var3) {
            LogUtil.error("Erro ao iniciar o ActiveMQ Broker", var3);
        }

    }

    static class MyBinder extends DefaultAhcBinding {
        MyBinder() {
        }

        protected void populateHeaders(RequestBuilder builder, AhcEndpoint endpoint, Exchange exchange) {
            exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        }
    }
}
