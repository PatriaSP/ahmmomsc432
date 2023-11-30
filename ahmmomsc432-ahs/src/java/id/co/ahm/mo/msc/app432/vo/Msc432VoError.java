/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.vo;

/**
 *
 * @author patria
 */
public class Msc432VoError {

    private String baris;
    private String MessageError;

    public Msc432VoError(String baris, String MessageError){
        this.baris = baris;
        this.MessageError = MessageError;
    }
    
    public String getBaris() {
        return baris;
    }

    public void setBaris(String baris) {
        this.baris = baris;
    }

    public String getMessageError() {
        return MessageError;
    }

    public void setMessageError(String MessageError) {
        this.MessageError = MessageError;
    }
    
}
