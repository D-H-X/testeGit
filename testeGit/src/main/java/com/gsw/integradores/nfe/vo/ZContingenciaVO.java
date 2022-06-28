//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.vo;

import com.gsw.integradores.nfe.vo.FeedbackNfeERP;
import java.util.Calendar;
import java.util.Date;

public class ZContingenciaVO {
    private String docnum;
    private String chvNfe;
    private String chvNfr;
    private Date data;

    public ZContingenciaVO() {
    }

    public ZContingenciaVO(String docnum) {
        this.docnum = docnum;
    }

    public ZContingenciaVO(FeedbackNfeERP feedback) {
        this.docnum = feedback.getDocNum();
        this.chvNfr = feedback.getChave();
        this.data = Calendar.getInstance().getTime();
    }

    public String getDocnum() {
        return this.docnum;
    }

    public void setDocnum(String docnum) {
        this.docnum = docnum;
    }

    public String getChvNfe() {
        return this.chvNfe;
    }

    public void setChvNfe(String chvNfe) {
        this.chvNfe = chvNfe;
    }

    public String getChvNfr() {
        return this.chvNfr;
    }

    public void setChvNfr(String chvNfr) {
        this.chvNfr = chvNfr;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
