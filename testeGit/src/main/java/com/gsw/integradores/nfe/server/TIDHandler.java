//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.server;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerTIDHandler;
import java.util.Hashtable;
import java.util.Map;

public class TIDHandler implements JCoServerTIDHandler {
    Map<String, TIDHandler.TIDState> availableTIDs = new Hashtable();

    public TIDHandler() {
    }

    public boolean checkTID(JCoServerContext serverCtx, String tid) {
        LogUtil.info("TID Handler: checkTID for " + tid);
        if(this.availableTIDs.containsKey(tid)) {
            return false;
        } else {
            this.availableTIDs.put(tid, TIDHandler.TIDState.STARTED);
            return true;
        }
    }

    public void commit(JCoServerContext serverCtx, String tid) {
        LogUtil.info("TID Handler: commit for " + tid);
        this.availableTIDs.put(tid, TIDHandler.TIDState.FINISHED);
    }

    public void rollback(JCoServerContext serverCtx, String tid) {
        LogUtil.info("TID Handler: rollback for " + tid);
        this.availableTIDs.put(tid, TIDHandler.TIDState.FINISHED);
    }

    public void confirmTID(JCoServerContext serverCtx, String tid) {
    }

    private static enum TIDState {
        STARTED,
        FINISHED;

        private TIDState() {
        }
    }
}
