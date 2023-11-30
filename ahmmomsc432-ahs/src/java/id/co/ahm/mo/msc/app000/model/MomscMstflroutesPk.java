/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app000.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author patria
 */

@Embeddable
public class MomscMstflroutesPk implements Serializable {
    
    @Column(name = "DROUTE_RROUTE_VROUTEID")
    private String droute_rroute_vrouteid;
    
    @Column(name = "DROUTE_MWCT_VWCTID_WCTFR")
    private String droute_mwct_vwctid_wctfr;
    
    @Column(name = "DROUTE_MWCT_VWCTID")
    private String droute_mwct_vwctid;
    
    @Column(name = "DROUTE_MPART_VPARTNUM")
    private String droute_mpart_vpartnum;
    
    @Column(name = "DROUTE_ISEQUENCE")
    private BigDecimal droute_isequence;

    public String getDroute_rroute_vrouteid() {
        return droute_rroute_vrouteid;
    }

    public void setDroute_rroute_vrouteid(String droute_rroute_vrouteid) {
        this.droute_rroute_vrouteid = droute_rroute_vrouteid;
    }

    public String getDroute_mwct_vwctid_wctfr() {
        return droute_mwct_vwctid_wctfr;
    }

    public void setDroute_mwct_vwctid_wctfr(String droute_mwct_vwctid_wctfr) {
        this.droute_mwct_vwctid_wctfr = droute_mwct_vwctid_wctfr;
    }

    public String getDroute_mwct_vwctid() {
        return droute_mwct_vwctid;
    }

    public void setDroute_mwct_vwctid(String droute_mwct_vwctid) {
        this.droute_mwct_vwctid = droute_mwct_vwctid;
    }

    public String getDroute_mpart_vpartnum() {
        return droute_mpart_vpartnum;
    }

    public void setDroute_mpart_vpartnum(String droute_mpart_vpartnum) {
        this.droute_mpart_vpartnum = droute_mpart_vpartnum;
    }

    public BigDecimal getDroute_isequence() {
        return droute_isequence;
    }

    public void setDroute_isequence(BigDecimal droute_isequence) {
        this.droute_isequence = droute_isequence;
    }
    
    
    
    
    
}
