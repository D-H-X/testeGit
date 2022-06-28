//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.server;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerState;
import com.sap.conn.jco.server.JCoServerStateChangedListener;

public class StateChangedListener implements JCoServerStateChangedListener {
    public StateChangedListener() {
    }

    public void serverStateChangeOccurred(JCoServer server, JCoServerState oldState, JCoServerState newState) {
        LogUtil.info("Server state changed from " + oldState.toString() + " to " + newState.toString() + " on server with program id " + server.getProgramID());
    }
}
