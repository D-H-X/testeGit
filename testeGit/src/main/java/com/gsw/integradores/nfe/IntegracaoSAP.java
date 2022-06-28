//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe;

import com.gsw.integradores.nfe.client.ClientPool;
import com.gsw.integradores.nfe.commons.HttpUtils;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.commons.Properties;
import com.gsw.integradores.nfe.request.MarkCheckRequestBuilder;
import com.gsw.integradores.nfe.request.NfeInvoisysRequestBuilder;
import com.gsw.integradores.nfe.request.NfeMarkCheckRequestBuilder;
import com.gsw.integradores.nfe.request.NfeRequestBuilder;
import com.gsw.integradores.nfe.response.InvStatusResponseBuilder;
import com.gsw.integradores.nfe.response.NfeInvoisysSendResponseBuilder;
import com.gsw.integradores.nfe.response.NfeSendResponseBuilder;
import com.gsw.integradores.nfe.response.NfeStatusResponseBuilder;
import com.gsw.integradores.nfe.response.SendResponseBuilder;
import com.gsw.integradores.nfe.response.StatusResponseBuilder;
import com.gsw.integradores.nfe.route.IntegradorCamelContext;
import com.gsw.integradores.nfe.route.InvFromQueue2Webservice;
import com.gsw.integradores.nfe.route.InvFromWebservice2Queue;
import com.gsw.integradores.nfe.route.NfeFromQueue2SAP;
import com.gsw.integradores.nfe.route.NfeFromQueue2Webservice;
import com.gsw.integradores.nfe.route.NfeFromQueueCCe2Webservice;
import com.gsw.integradores.nfe.route.NfeFromQueueCancellation2Webservice;
import com.gsw.integradores.nfe.route.NfeFromQueueInutilizacao2Webservice;
import com.gsw.integradores.nfe.route.NfeFromWebservice2Queue;
import com.gsw.integradores.nfe.route.NfeFromWebservice2QueueRetorno;
import com.gsw.integradores.nfe.sap.SapReaderNfe;
import com.gsw.integradores.nfe.sap.SapReaderNfs;
import com.gsw.integradores.nfe.sap.SapWriterNfe;
import com.gsw.integradores.nfe.sap.SapWriterNfs;
import com.gsw.integradores.nfe.server.ConnectionHandler;
import com.gsw.integradores.nfe.server.StateChangedListener;
import com.gsw.integradores.nfe.server.TIDHandler;
import com.gsw.integradores.nfe.server.ThrowableListener;
import com.gsw.integradores.nfe.service.FunctionService;
import com.ning.http.client.RequestBuilder;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.DefaultServerHandlerFactory.FunctionHandlerFactory;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.main.Main;

public class IntegracaoSAP {
    private final Main main = new Main();
    private static FunctionService functionService;

    public IntegracaoSAP() {
    }

    public static String getVersaoIntegrador() {
        return "20201206.1500.PPC";
    }

    public static void main(String[] args) throws Exception {
        IntegracaoSAP integradorSAP = new IntegracaoSAP();
        integradorSAP.boot();
    }

    public void boot() throws Exception {
        this.main.enableHangupSupport();
        functionService = new FunctionService(ClientPool.getDestination(Properties.sap_destinationName.getValue()));
        IntegradorCamelContext c = new IntegradorCamelContext(this.createRegistry());
//        LogUtil.info("DIEGO - VALOR DA NfeFromQueue2Webservice: " + c + " CLASSE INTEGRACAOSAP");
        c.addRoutes(new NfeFromQueue2Webservice());
        c.addRoutes(new NfeFromQueueCancellation2Webservice());
        c.addRoutes(new NfeFromQueueInutilizacao2Webservice());
        c.addRoutes(new NfeFromWebservice2Queue());
        c.addRoutes(new NfeFromWebservice2QueueRetorno());
        c.addRoutes(new NfeFromQueue2SAP());
        c.addRoutes(new NfeFromQueueCCe2Webservice());
        
        c.addRoutes(new InvFromQueue2Webservice());
        c.addRoutes(new InvFromWebservice2Queue());
        
        
        this.main.getCamelContexts().clear();
        this.main.getCamelContexts().add(c);
        ProducerTemplate producer = c.createProducerTemplate();
        this.createRFCServer(producer);
        LogUtil.info("EXECUTANDO O METODO RUN - CLASSE INTEGRACAOSAP - DIEGO");
        LogUtil.info("DIEGO - VALOR DA NfeFromQueue2Webservice: " + c + " CLASSE INTEGRACAOSAP");
        LogUtil.info("DIEGO - VALOR DA this.main.getCamelContexts(): " + this.main.getCamelContexts() + " CLASSE INTEGRACAOSAP");
        this.main.run();
    }

    private void createRFCServer(ProducerTemplate producer) {
        JCoServer server;
        try {
            server = JCoServerFactory.getServer(Properties.sap_serverName.getValue());
            LogUtil.info("Created JCoServer for SAP!");
        } catch (JCoException var8) {
            LogUtil.error("erro ao iniciar o SAP server", var8);
            throw new RuntimeException("Unable to create the server " + Properties.sap_serverName.getValue() + ", because of " + var8.getMessage(), var8);
        }

        ConnectionHandler connectionHandler = new ConnectionHandler(new SapReaderNfs(producer), new SapReaderNfe(producer), functionService);
        FunctionHandlerFactory factory = new FunctionHandlerFactory();
        factory.registerGenericHandler(connectionHandler);
        server.setCallHandlerFactory(factory);
        TIDHandler myTIDHandler = new TIDHandler();
        server.setTIDHandler(myTIDHandler);
        ThrowableListener erroListener = new ThrowableListener();
        server.addServerErrorListener(erroListener);
        server.addServerExceptionListener(erroListener);
        StateChangedListener slistener = new StateChangedListener();
        server.addServerStateChangedListener(slistener);
        server.start();
        LogUtil.info("JCoServer for SAP has Started!");
    }

    private SimpleRegistry createRegistry() {
    	LogUtil.info("DIEGO - ENTROU NO METODO CREATEREGISTRY - CLASSE INTEGRACAOSAP");
        SimpleRegistry r = new SimpleRegistry();
        r.put("requestBuilder", new RequestBuilder());
        r.put("sendResponseBuilder", new SendResponseBuilder());
        r.put("statusResponseBuilder", new StatusResponseBuilder());
        r.put("markCheckRequestBuilder", new MarkCheckRequestBuilder());
        r.put("sapWriter", new SapWriterNfs(functionService));
        r.put("nfeSapWriter", new SapWriterNfe(functionService));
        r.put("nfeStatusResponseBuilder", new NfeStatusResponseBuilder(functionService));
        r.put("nfeMarkCheckRequestBuilder", new NfeMarkCheckRequestBuilder());
        r.put("nfeRequestBuilder", new NfeRequestBuilder());
        r.put("nfeInvoisysRequestBuilder", new NfeInvoisysRequestBuilder());
        r.put("nfeSendResponseBuilder", new NfeSendResponseBuilder());
        r.put("nfeInvoisysSendResponseBuilder", new NfeInvoisysSendResponseBuilder());
        r.put("InvStatusResponseBuilder", new InvStatusResponseBuilder());
        r.put("httpUtil", new HttpUtils());
        return r;
    }

    public static FunctionService getSAPConnectionService() {
        return functionService;
    }
}
