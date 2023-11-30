/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app000.model;

import id.co.ahm.jxf.model.DefaultEntityImpl;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author patria
 */


@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="MOMSC_MSTFLROUTES")
public class MomscMstflroutes extends DefaultEntityImpl implements Serializable{
    
    @EmbeddedId
    private AhmmomscMstflroutesPk ahmmomscMstflroutesPk;
    
    @Column(name = "MPROC_VPROCESSID", length = 6)
    private String mproc_vprocessid;
    
    @Column(name = "NPCTTOUNIT")
    private BigDecimal npcttounit;
    
    @Column(name = "IPARENTSEQ")
    private BigDecimal iparentseq;

    public AhmmomscMstflroutesPk getAhmmomscMstflroutesPk() {
        return ahmmomscMstflroutesPk;
    }

    public void setAhmmomscMstflroutesPk(AhmmomscMstflroutesPk ahmmomscMstflroutesPk) {
        this.ahmmomscMstflroutesPk = ahmmomscMstflroutesPk;
    }

    public String getMproc_vprocessid() {
        return mproc_vprocessid;
    }

    public void setMproc_vprocessid(String mproc_vprocessid) {
        this.mproc_vprocessid = mproc_vprocessid;
    }

    public BigDecimal getNpcttounit() {
        return npcttounit;
    }

    public void setNpcttounit(BigDecimal npcttounit) {
        this.npcttounit = npcttounit;
    }

    public BigDecimal getIparentseq() {
        return iparentseq;
    }

    public void setIparentseq(BigDecimal iparentseq) {
        this.iparentseq = iparentseq;
    }
    
    
    
    
}
