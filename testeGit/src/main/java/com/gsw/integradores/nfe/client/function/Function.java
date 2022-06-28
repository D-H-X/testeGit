//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.client.function;

import com.gsw.integradores.nfe.commons.LogUtil;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.exception.ExceptionUtils;

public class Function {
    protected JCoDestination destination;
    public static final String I_CODE = "I_CODE";
    public static final String I_MSGTYP = "I_MSGTYP";
    public static final String I_AUTHTIME = "I_AUTHTIME";
    public static final String I_AUTHDATE = "I_AUTHDATE";
    public static final String I_AUTHCODE = "I_AUTHCODE";
    public static final String I_DOCNUM = "I_DOCNUM";
    public static final String I_ACCKEY = "I_ACCKEY";
    protected JCoFunction function;

    public Function(JCoDestination destination) {
        this.destination = destination;
    }

    protected JCoParameterList call(Map<String, Object> inParamMap, JCoFunction function) {
        JCoParameterList result = null;
        JCoParameterList inParam = function.getImportParameterList();
        String docNum = "";
        long begin = 0L;
        long end = 0L;

        try {
            Iterator var11 = inParamMap.entrySet().iterator();

            while(true) {
                Entry e;
                do {
                    do {
                        if(!var11.hasNext()) {
                            try {
                                if("0000-00-00".equals(inParam.getString("I_AUTHDATE"))) {
                                    inParam.setValue("I_AUTHDATE", "00000000");
                                }
                            } catch (Exception var17) {
                                ;
                            }

                            LogUtil.info("DOCNUM: " + docNum + " Chamando função " + function.getName() + " no SAP...");
                            LogUtil.info("DOCNUM: " + docNum + " Parâmetros utilizados: " + inParam.toXML());
                            begin = System.currentTimeMillis();
                            function.execute(this.destination);
                            result = function.getExportParameterList();
                            end = System.currentTimeMillis();
                            LogUtil.info("DOCNUM: " + docNum + " Função " + function.getName() + " chamada com sucesso em " + (double)(end - begin) / 1000.0D + " seg!");
                            LogUtil.info("DOCNUM: " + docNum + " Retorno da função " + function.getName() + " valores: " + (result != null?result.toXML():"result é nulo!"));
                            return result;
                        }

                        e = (Entry)var11.next();
                        inParam.setValue((String)e.getKey(), e.getValue());
                    } while(!"".equals(docNum));
                } while(!((String)e.getKey()).equals("I_DOCNUM") && !((String)e.getKey()).equals("P_DOCNUM") && !((String)e.getKey()).equals("DOCNUM"));

                docNum = (String)e.getValue();
            }
        } catch (JCoException var18) {
            end = System.currentTimeMillis();
            LogUtil.error("DOCNUM: " + docNum + " Erro na chamada da funcao " + function.getName() + " executada em " + (double)(end - begin) / 1000.0D + " seg - " + ExceptionUtils.getStackTrace(var18));
        } catch (Throwable var19) {
            end = System.currentTimeMillis();
            LogUtil.error("DOCNUM: " + docNum + " Erro na chamada da funcao " + function.getName() + " executada em " + (double)(end - begin) / 1000.0D + " seg");
            LogUtil.error(var19);
        } finally {
            inParam.clear();
        }

        return result;
    }
}
