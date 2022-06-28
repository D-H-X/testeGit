//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.server;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContextInfo;
import com.sap.conn.jco.server.JCoServerErrorListener;
import com.sap.conn.jco.server.JCoServerExceptionListener;

public class ThrowableListener implements JCoServerErrorListener, JCoServerExceptionListener {
    public ThrowableListener() {
    }

    public void serverExceptionOccurred(JCoServer jcoServer, String connectionId, Exception error) {
        LogUtil.error("Erro no lister do sap 1", error);
        LogUtil.error("Error occured on " + jcoServer.getProgramID() + " connection " + connectionId);
    }

    public void serverExceptionOccurred(JCoServer jcoServer, String connectionId, JCoServerContextInfo arg2, Exception error) {
        LogUtil.error("Erro no lister do sap 2", error);
        error.printStackTrace();
        LogUtil.error("Error occured on " + jcoServer.getProgramID() + " connection " + connectionId);
    }

    public void serverErrorOccurred(JCoServer jcoServer, String connectionId, Error error) {
        LogUtil.error("Erro no lister do sap 3", error);
        LogUtil.error("Error occured on " + jcoServer.getProgramID() + " connection " + connectionId);
    }

    public void serverErrorOccurred(JCoServer jcoServer, String connectionId, JCoServerContextInfo arg2, Error error) {
        LogUtil.error("Erro no lister do sap 4", error);
        LogUtil.error("Error occured on " + jcoServer.getProgramID() + " connection " + connectionId);
    }
}
