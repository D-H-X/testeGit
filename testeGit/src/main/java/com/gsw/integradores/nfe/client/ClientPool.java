//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

public class ClientPool {
    public ClientPool() {
    }

    public static JCoDestination getDestination(String destinationName) {
        JCoDestination destination = null;

        try {
//        	destination = JCoDestinationManager.getDestination("CLIENT_POOL");
            destination = JCoDestinationManager.getDestination(destinationName);
            destination.ping();
        } catch (JCoException var3) {
            LogUtil.error("erro ao iniciar o SAP destination");
            LogUtil.error(var3);
        }

        return destination;
    }
}
