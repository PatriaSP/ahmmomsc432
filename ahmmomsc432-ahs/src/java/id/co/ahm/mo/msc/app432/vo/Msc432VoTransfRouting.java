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
public class Msc432VoTransfRouting {
    
    private String seq;
    private String level;
    private String part;
    private String partDesc;
    private String wctFrom;
    private String wctDest;
    private String process;
    private String percentWct;
    private String percentUnit;
    private String routeId;
    private String parentSeq;
    private String transferDate;

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getWctDest() {
        return wctDest;
    }

    public void setWctDest(String wctDest) {
        this.wctDest = wctDest;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getParentSeq() {
        return parentSeq;
    }

    public void setParentSeq(String parentSeq) {
        this.parentSeq = parentSeq;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getWctFrom() {
        return wctFrom;
    }

    public void setWctFrom(String wctFrom) {
        this.wctFrom = wctFrom;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getPercentWct() {
        return percentWct;
    }

    public void setPercentWct(String percentWct) {
        this.percentWct = percentWct;
    }

    public String getPercentUnit() {
        return percentUnit;
    }

    public void setPercentUnit(String percentUnit) {
        this.percentUnit = percentUnit;
    }
    
}
