/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.dao.impl;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.mo.msc.app000.model.MomscMstflroutes;
import id.co.ahm.mo.msc.app000.model.AhmmomscMstflroutesPk;
import id.co.ahm.mo.msc.app432.constant.Msc432Constant;
import id.co.ahm.mo.msc.app432.dao.Msc432AhmmomscMstflroutesDao;
import id.co.ahm.mo.msc.app432.vo.Msc432VoTransfRouting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author patria
 */
@Repository("msc432AhmmomscMstflroutesDao")
public class Msc432AhmmomscMstflroutesDaoImpl extends DefaultHibernateDao<MomscMstflroutes, AhmmomscMstflroutesPk> implements Msc432AhmmomscMstflroutesDao{

    public List<Msc432VoTransfRouting> getRoutes(Map<String, Object> input) {
        List<Msc432VoTransfRouting> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_LIST_ROUTING;
        
        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("plantid",(String) input.get("plantId"));
        query.setParameter("typeid",(String) input.get("typeId"));
        query.setParameter("colorid",(String) input.get("colorId"));
        query.setParameter("lineid",(String) input.get("lineId"));
        query.setParameter("routeid",(String) input.get("routeId"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoTransfRouting vo = new Msc432VoTransfRouting();
                vo.setRouteId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setWctFrom(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setWctDest(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPart(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setSeq(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setProcess(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPercentUnit(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setParentSeq(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }
        

        return result;
    }
    
}
