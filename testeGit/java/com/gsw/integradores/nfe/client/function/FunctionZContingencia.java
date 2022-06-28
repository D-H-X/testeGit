//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.client.FunctionFactory;
import com.gsw.integradores.nfe.client.function.Function;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.vo.ZContingenciaVO;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoParameterList;
import java.util.HashMap;

public class FunctionZContingencia extends Function {
    public FunctionZContingencia(JCoDestination destination) {
        super(destination);

        try {
            this.function = FunctionFactory.getFunction(destination, "ZRFC_CONTINGENCIA_NFE");
        } catch (JCoException var3) {
            LogUtil.error("erro ao criar a funcao ZRFC_CONTINGENCIA_NFE", var3);
        }

    }

    public JCoParameterList callAccesskeySearchByChvnfe(String accesskey) {
        ZContingenciaVO c = new ZContingenciaVO();
        c.setChvNfe(accesskey);
        return this.callRegisterNewAccesskey(c);
    }

    public JCoParameterList callAccesskeySearchByDocnum(String docnum) {
        return this.callRegisterNewAccesskey(new ZContingenciaVO(docnum));
    }

    public JCoParameterList callRegisterNewAccesskey(ZContingenciaVO contingencia) {
        HashMap paramCont = new HashMap();
        if(contingencia.getDocnum() != null) {
            paramCont.put("I_DOCNUM", contingencia.getDocnum());
        }

        if(contingencia.getChvNfe() != null) {
            paramCont.put("I_CHV_NFE", contingencia.getChvNfe());
        }

        if(contingencia.getChvNfr() != null) {
            paramCont.put("I_CHV_NFR", contingencia.getChvNfr());
        }

        if(contingencia.getData() != null) {
            paramCont.put("I_DATA", contingencia.getData());
        }

        return this.call(paramCont, this.function);
    }
}
