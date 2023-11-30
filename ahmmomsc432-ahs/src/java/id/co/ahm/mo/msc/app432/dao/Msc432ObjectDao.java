/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.dao;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.mo.msc.app432.vo.Msc432VoColor;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDtlRouting;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDtlmLayout;
import id.co.ahm.mo.msc.app432.vo.Msc432VoEmail;
import id.co.ahm.mo.msc.app432.vo.Msc432VoLine;
import id.co.ahm.mo.msc.app432.vo.Msc432VoOutplant;
import id.co.ahm.mo.msc.app432.vo.Msc432VoPlant;
import id.co.ahm.mo.msc.app432.vo.Msc432VoRoute;
import id.co.ahm.mo.msc.app432.vo.Msc432VoTransfRouting;
import id.co.ahm.mo.msc.app432.vo.Msc432VoType;
import java.util.List;
import java.util.Map;

/**
 *
 * @author patria
 */
public interface Msc432ObjectDao {

    public List<Msc432VoPlant> getPlantData();

    public int getCountTypeData(DtoParamPaging input);
    
    public List<Msc432VoType> getTypeData(DtoParamPaging input);

    public int getCountColorData(DtoParamPaging input);
    
    public List<Msc432VoColor> getColorData(DtoParamPaging input);
    
    public int getCountLineData(DtoParamPaging input);
    
    public List<Msc432VoLine> getLineData(DtoParamPaging input);

    public int getCountRouteData(DtoParamPaging input);
    
    public List<Msc432VoRoute> getRouteData(DtoParamPaging input);

    public List<Msc432VoTransfRouting> exportExcel(Map<String, Object> input);

    public List<Msc432VoDtlRouting> getDtlRoutesData(Map<String, Object> input);

    public List<Msc432VoDtlmLayout> getDtlDtmlLayout(String makerId, String vPlant, String partChild, String partParentTmp, String nSplSeq, String initSeq);

    public List<Msc432VoOutplant> getOutplant(String kodeOut);

    public boolean checkRoute(String plantId, String lineId, String typeId, String colorId, String routingId);

    public boolean checkPlant(String plantId);

    public boolean checkLine(String plantId, String lineId);

    public boolean checkType(String typeId);

    public boolean checkColor(String typeId, String colorId);

    public String getTransferDate(String routeId);

    public boolean checkJMS();

    public List<Msc432VoEmail> getEmailByNrp(String nrp);

}
