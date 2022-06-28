//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.client.FunctionFactory;
import com.gsw.integradores.nfe.client.function.Function;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoParameterList;
import java.util.HashMap;

public class FunctionZCancelInutilizacao extends Function {
    public static final String Z_MSAF_DFE_CANCEL = "/MSAFDFE/MSAF_DFE_CANCEL";
    public static final String DOCNUM = "DOCNUM";
    public static final String I_DOCNUM = "I_DOCNUM";
    public static final String REASON1 = "REASON1";
    public static final String IE = "IE";

    public FunctionZCancelInutilizacao(JCoDestination destination) {
        super(destination);

        try {
            this.function = FunctionFactory.getFunction(destination, "ZRFC_MSAF_CANCEL");
        } catch (JCoException var3) {
            LogUtil.error("erro ao criar a funcao ZRFC_MSAF_CANCEL", var3);
        }

    }

    public JCoParameterList callZCancelInutilizacao(String accessKey, String docnum) {
        HashMap inParamMap = new HashMap();
        inParamMap.put("CHV_NFE", accessKey);
        return this.call(inParamMap, this.function);
    }
}
