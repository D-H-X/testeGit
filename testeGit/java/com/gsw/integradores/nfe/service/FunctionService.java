//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.service;

import com.gsw.integradores.nfe.client.function.FunctionEventIn;
import com.gsw.integradores.nfe.client.function.FunctionXmlIn;
import com.gsw.integradores.nfe.client.function.FunctionZCancelInutilizacao;
import com.gsw.integradores.nfe.client.function.FunctionZContingencia;
import com.gsw.integradores.nfe.client.function.FunctionZXmlOut;
import com.gsw.integradores.nfe.commons.DocType;
import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import com.gsw.integradores.nfe.vo.ZContingenciaVO;
import com.gsw.integradores.nfe.vo.ZXmlOutImportVO;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoParameterList;
import org.apache.commons.lang.StringUtils;

public class FunctionService {
    private FunctionZXmlOut functionZXmlOut;
    private FunctionXmlIn functionXmlIn;
    private FunctionEventIn functionEventIn;
    private FunctionZCancelInutilizacao functionZCancelInutilizacao;
    private FunctionZContingencia functionZContingencia;

    public FunctionService(JCoDestination destination) {
        this.functionZXmlOut = new FunctionZXmlOut(destination);
        this.functionXmlIn = new FunctionXmlIn(destination);
        this.functionEventIn = new FunctionEventIn(destination);
        this.functionZCancelInutilizacao = new FunctionZCancelInutilizacao(destination);
        this.functionZContingencia = new FunctionZContingencia(destination);
    }

    public JCoParameterList callZCancelInutilizacao(String accessKey, String docnum) {
        return this.functionZCancelInutilizacao.callZCancelInutilizacao(accessKey, docnum);
    }

    public JCoParameterList callZXmlOut(ZXmlOutImportVO importVO) {
        return this.functionZXmlOut.callZXmlOut(importVO);
    }

    public JCoParameterList callZContingenciaAccesskeySearchByChvnfe(String accesskey) {
        return this.functionZContingencia.callAccesskeySearchByChvnfe(accesskey);
    }

    public JCoParameterList callZContingenciaAccesskeySearchByDocnum(String docnum) {
        return this.functionZContingencia.callAccesskeySearchByDocnum(docnum);
    }

    public void callZContingenciaRegisterNewAccesskey(ZContingenciaVO contingencia) {
        this.functionZContingencia.callRegisterNewAccesskey(contingencia);
    }

    public void callZXmlInOkNfs(FeedbackNfsERP feedback) {
        this.functionXmlIn.callXmlInOkNfs(feedback);
    }

    public void callXmlInCancelledOkNfs(FeedbackNfsERP feedback) {
        this.functionXmlIn.callXmlInCancelledOkNfs(feedback);
    }

    public void callXmlInRejectNfs(FeedbackNfsERP feedback) {
        this.functionXmlIn.callXmlInRejectNfs(feedback);
    }

    public void callXmlInInutilizacaoNfs(FeedbackNfsERP feedback) {
        this.functionXmlIn.callXmlInInutilizacaoNfs(feedback);
    }

    public void callXmlInOkNfe(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInOkNfe(feedback);
    }

    public void callXmlInCancelledOkNfe(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInCancelledOkNfe(feedback);
    }

    public void callXmlInInutilizacaoOkNfe(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInInutilizacaoOkNfe(feedback);
    }

    public void callXmlInRejectNfe(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInRejectNfe(feedback);
    }

    public void callXmlInRejectCancelledNfe(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInRejectCancelledNfe(feedback);
    }

    public void callXmlInRejectInutilizacaoNfe(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInRejectInutilizacaoNfe(feedback);
    }

    public void callXmlInDenial(FeedbackNfeERP feedback) {
        this.functionXmlIn.callXmlInDenialNfe(feedback);
    }

    public void callEventInOk(FeedbackNfeERP feedback) {
        this.functionEventIn.callEventInOk(feedback);
    }

    public void callCancEventInOk(FeedbackNfeERP feedback) {
        this.functionEventIn.callCancEventInOk(feedback);
    }

    public void callEventInReject(FeedbackNfeERP feedback) {
        this.functionEventIn.callEventInReject(feedback);
    }

    public DocType verifyDocType(JCoParameterList resultZXmlOut) {
        String type = resultZXmlOut.getString("NFSERV");
        return StringUtils.isBlank(type)?DocType.NFE:DocType.NFS;
    }
}
