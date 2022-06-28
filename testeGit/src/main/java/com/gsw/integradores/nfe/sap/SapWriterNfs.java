//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.gsw.integradores.nfe.sap;

import com.gsw.integradores.nfe.repository.NfsRepositoryWriter;
import com.gsw.integradores.nfe.service.FunctionService;
import com.gsw.integradores.nfe.vo.FeedbackNfsERP;
import org.apache.camel.Body;

public class SapWriterNfs implements NfsRepositoryWriter {
    private FunctionService functionService;

    public SapWriterNfs(FunctionService functionService) {
        this.functionService = functionService;
    }

    public void write(@Body FeedbackNfsERP feedback) {
        if(feedback.getStatus().intValue() != 10) {
            if(feedback.getStatus().intValue() == 100) {
                this.functionService.callZXmlInOkNfs(feedback);
            } else if(feedback.getStatus().intValue() == 101) {
                this.functionService.callXmlInCancelledOkNfs(feedback);
            } else if(feedback.getStatus().intValue() == 200 || feedback.getStatus().intValue() == 201 || feedback.getStatus().intValue() == 999) {
                this.functionService.callXmlInRejectNfs(feedback);
            }
        }

    }
}
