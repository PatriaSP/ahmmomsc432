/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.dao;

import id.co.ahm.jxf.dao.DefaultDao;
import id.co.ahm.mo.msc.app000.model.MomscMstflroutes;
import id.co.ahm.mo.msc.app000.model.AhmmomscMstflroutesPk;
import id.co.ahm.mo.msc.app432.vo.Msc432VoTransfRouting;
import java.util.List;
import java.util.Map;

/**
 *
 * @author patria
 */
public interface Msc432AhmmomscMstflroutesDao extends DefaultDao<MomscMstflroutes, AhmmomscMstflroutesPk>{

    public List<Msc432VoTransfRouting> getRoutes(Map<String, Object> input);
    
}
