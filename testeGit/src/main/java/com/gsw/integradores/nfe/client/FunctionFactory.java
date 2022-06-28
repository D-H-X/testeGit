//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;

public class FunctionFactory {
    public FunctionFactory() {
    }

    public static JCoFunction getFunction(JCoDestination destination, String functionName) throws JCoException {
        return destination.getRepository().getFunction(functionName);
    }
}
