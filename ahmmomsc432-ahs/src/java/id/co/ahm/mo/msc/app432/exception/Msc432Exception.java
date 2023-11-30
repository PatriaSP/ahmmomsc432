/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.exception;

import id.co.ahm.mo.msc.app432.vo.Msc432VoError;
import java.util.ArrayList;
import java.util.List;

/**
 * @author patria
 */
public class Msc432Exception extends RuntimeException {

    private String errMsg;
    private List<Msc432VoError> errMsgs = new ArrayList<>();

    public Msc432Exception(String errMsg) {
        this.errMsg = errMsg;
    }

    public Msc432Exception(List<Msc432VoError> errMsgs) {
        this.errMsgs = errMsgs;
    }
    
    public Msc432Exception(String errMsg ,List<Msc432VoError> errMsgs) {
        this.errMsg = errMsg;
        this.errMsgs = errMsgs;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<Msc432VoError> getErrMsgs() {
        return errMsgs;
    }
    
    public void setErrMsgs(List<Msc432VoError> errMsgs) {
        this.errMsgs = errMsgs;
    }

    
}
