//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.client.FunctionFactory;
import com.gsw.integradores.nfe.client.function.Function;
import com.gsw.integradores.nfe.commons.LogUtil;
import com.gsw.integradores.nfe.vo.ZXmlOutImportVO;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoParameterList;
import java.util.HashMap;

public class FunctionZXmlOut extends Function {
    public static final String CPROD = "CPROD";
    public static final String DOCNUM = "DOCNUM";
    public static final String ITMNUM = "ITMNUM";
    public static final String NFSERV = "NFSERV";
    public static final String TRINFO = "TRINFO";
    public static final String PAIS = "PAIS";
    public static final String CPF_CNPJ = "CPF_CNPJ";
    public static final String Z_MSAF_DFE_XML_OUT = "/MSAFDFE/MSAF_DFE_XML_OUT";

    public FunctionZXmlOut(JCoDestination destination) {
        super(destination);

        try {
            this.function = FunctionFactory.getFunction(destination, "/MSAFDFE/MSAF_DFE_XML_OUT");
        } catch (JCoException var3) {
            LogUtil.error("erro ao criar a funcao /MSAFDFE/MSAF_DFE_XML_OUT", var3);
        }

    }

    public JCoParameterList callZXmlOut(ZXmlOutImportVO importVO) {
        HashMap inParamMap = new HashMap();
        inParamMap.put("DOCNUM", importVO.getDocNum());
        inParamMap.put("CPROD", importVO.getcProd());
        inParamMap.put("ITMNUM", importVO.getItmNum());
        inParamMap.put("TRINFO", importVO.getTrInfo());
        inParamMap.put("PAIS", importVO.getPais());
        inParamMap.put("CPF_CNPJ", importVO.getCpfCnpj());
        return this.call(inParamMap, this.function);
    }
}
