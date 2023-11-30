/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.dao.impl;

import id.co.ahm.jxf.dao.DefaultHibernateDao;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.mo.msc.app432.constant.Msc432Constant;
import id.co.ahm.mo.msc.app432.util.Msc432QueryUtil;
import id.co.ahm.mo.msc.app432.vo.Msc432VoColor;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDtlRouting;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDtlmLayout;
import id.co.ahm.mo.msc.app432.vo.Msc432VoLine;
import id.co.ahm.mo.msc.app432.vo.Msc432VoOutplant;
import id.co.ahm.mo.msc.app432.vo.Msc432VoPlant;
import id.co.ahm.mo.msc.app432.vo.Msc432VoRoute;
import id.co.ahm.mo.msc.app432.vo.Msc432VoTransfRouting;
import id.co.ahm.mo.msc.app432.vo.Msc432VoType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import id.co.ahm.mo.msc.app432.dao.Msc432ObjectDao;
import id.co.ahm.mo.msc.app432.vo.Msc432VoEmail;

/**
 *
 * @author patria
 */
@Repository("msc432ObjectDao")
public class Msc432ObjectDaoImpl extends DefaultHibernateDao<Object, Serializable> implements Msc432ObjectDao {

    @Override
    public List<Msc432VoPlant> getPlantData() {
        List<Msc432VoPlant> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_PLANT;
        sql += "    ORDER BY VPLANTID ASC    ";
        SQLQuery criteria = getCurrentSession().createSQLQuery(sql);

        List<Object[]> list = criteria.list();
        for (Object[] obj : list) {
            int i = 0;
            Msc432VoPlant data = new Msc432VoPlant();
            data.setPlantId((String) obj[i++]);
            data.setPlantDesc((String) obj[i++]);
            result.add(data);
        }
        return result;
    }

    @Override
    public int getCountTypeData(DtoParamPaging input) {

        String sql = Msc432Constant.SQL_MCTYPE;

        sql += " AND (UPPER(VMCTYPEID) like UPPER('%'||:PARAM||'%') OR upper(VMCDESC) like upper('%' || :PARAM|| '%')) ";

        Query query = getCurrentSession().createSQLQuery(Msc432QueryUtil.countQuery(sql));

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));

        Number number = (Number) query.uniqueResult();
        int count = number.intValue();

        return count;
    }

    @Override
    public List<Msc432VoType> getTypeData(DtoParamPaging input) {
        List<Msc432VoType> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_MCTYPE;

        sql += " AND (UPPER(VMCTYPEID) like UPPER('%'||:PARAM||'%') OR upper(VMCDESC) like upper('%' || :PARAM|| '%')) ";

        Map<String, String> COL_ORDER = new LinkedHashMap<String, String>() {
            {
                put("typeId", "VMCTYPEID");
                put("typeDesc", "VMCDESC");

            }
        };

        sql = Msc432QueryUtil.orderClause(input, sql, "VMCTYPEID asc", COL_ORDER);

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoType vo = new Msc432VoType();
                vo.setTypeId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setTypeDesc(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public int getCountColorData(DtoParamPaging input) {

        String sql = Msc432Constant.SQL_COLOR;

        sql += " AND (UPPER(MCOL_VCOLORID) like UPPER('%'||:PARAM||'%') OR UPPER((SELECT VCOLORDESC FROM AHMMOMSC_MSTCOLORS WHERE VCOLORID = MCOL_VCOLORID)) like upper('%' || :PARAM|| '%')) ";

        Query query = getCurrentSession().createSQLQuery(Msc432QueryUtil.countQuery(sql));

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));
        query.setParameter("typeid", (String) input.getSearch().get("typeId"));

        Number number = (Number) query.uniqueResult();
        int count = number.intValue();

        return count;
    }

    @Override
    public List<Msc432VoColor> getColorData(DtoParamPaging input) {
        List<Msc432VoColor> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_COLOR;

        sql += " AND (UPPER(MCOL_VCOLORID) like UPPER('%'||:PARAM||'%') OR UPPER((SELECT VCOLORDESC FROM AHMMOMSC_MSTCOLORS WHERE VCOLORID = MCOL_VCOLORID)) like upper('%' || :PARAM|| '%')) ";

        Map<String, String> COL_ORDER = new LinkedHashMap<String, String>() {
            {
                put("colorId", "MCOL_VCOLORID");
                put("colorDesc", "VCOLORDESC");

            }
        };

        sql = Msc432QueryUtil.orderClause(input, sql, "MCOL_VCOLORID asc", COL_ORDER);

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));
        query.setParameter("typeid", (String) input.getSearch().get("typeId"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoColor vo = new Msc432VoColor();
                vo.setColorId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setColorDesc(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public int getCountLineData(DtoParamPaging input) {

        String sql = Msc432Constant.SQL_LINE;

        sql += " AND (UPPER(vlineid) like UPPER('%'||:PARAM||'%') OR upper(vlinedesc) like upper('%' || :PARAM|| '%')) ";

        Query query = getCurrentSession().createSQLQuery(Msc432QueryUtil.countQuery(sql));

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));
        query.setParameter("plantid", (String) input.getSearch().get("plantId"));

        Number number = (Number) query.uniqueResult();
        int count = number.intValue();

        return count;
    }

    @Override
    public List<Msc432VoLine> getLineData(DtoParamPaging input) {
        List<Msc432VoLine> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_LINE;

        sql += " AND (UPPER(vlineid) like UPPER('%'||:PARAM||'%') OR upper(vlinedesc) like upper('%' || :PARAM|| '%')) ";

        Map<String, String> COL_ORDER = new LinkedHashMap<String, String>() {
            {
                put("colorId", "MCOL_VCOLORID");
                put("colorDesc", "VCOLORDESC");

            }
        };

        sql = Msc432QueryUtil.orderClause(input, sql, "", COL_ORDER);

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));
        query.setParameter("plantid", (String) input.getSearch().get("plantId"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoLine vo = new Msc432VoLine();
                vo.setLineId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setLineDesc(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public int getCountRouteData(DtoParamPaging input) {

        String sql = Msc432Constant.SQL_ROUTING;

        sql += " AND (UPPER(VROUTEID) like UPPER('%'||:PARAM||'%')) ";

        Query query = getCurrentSession().createSQLQuery(Msc432QueryUtil.countQuery(sql));

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));
        query.setParameter("plantid", (String) input.getSearch().get("plantId"));
        query.setParameter("typeid", (String) input.getSearch().get("typeId"));
        query.setParameter("colorid", (String) input.getSearch().get("colorId"));
        query.setParameter("lineid", (String) input.getSearch().get("lineId"));

        Number number = (Number) query.uniqueResult();
        int count = number.intValue();

        return count;
    }

    @Override
    public List<Msc432VoRoute> getRouteData(DtoParamPaging input) {
        List<Msc432VoRoute> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_ROUTING;

        sql += " AND (UPPER(VROUTEID) like UPPER('%'||:PARAM||'%')) ";

        Map<String, String> COL_ORDER = new LinkedHashMap<String, String>() {
            {
                put("routeId", "VROUTEID");
                put("lineId", "MLINES_VLINEID");

            }
        };

        sql = Msc432QueryUtil.orderClause(input, sql, "", COL_ORDER);

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setFirstResult(input.getOffset());
        query.setMaxResults(input.getLimit());

        query = (SQLQuery) Msc432QueryUtil.setValueQueryInterest(query, (String) input.getSearch().get("any"));
        query.setParameter("plantid", (String) input.getSearch().get("plantId"));
        query.setParameter("typeid", (String) input.getSearch().get("typeId"));
        query.setParameter("colorid", (String) input.getSearch().get("colorId"));
        query.setParameter("lineid", (String) input.getSearch().get("lineId"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoRoute vo = new Msc432VoRoute();
                vo.setRouteId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setLineId(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public List<Msc432VoTransfRouting> exportExcel(Map<String, Object> input) {
        List<Msc432VoTransfRouting> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_TRANSFER_EXPORT;

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("routeId", (String) input.get("routeId"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoTransfRouting vo = new Msc432VoTransfRouting();
                vo.setSeq(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setLevel(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPart(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPartDesc(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setWctFrom(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setWctDest(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setProcess(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPercentWct(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPercentUnit(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setRouteId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setParentSeq(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setTransferDate(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public List<Msc432VoDtlRouting> getDtlRoutesData(Map<String, Object> input) {
        List<Msc432VoDtlRouting> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_LIST_DTLROUTING;

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("routeid", (String) input.get("routeId"));

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoDtlRouting vo = new Msc432VoDtlRouting();
                vo.setRouteId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPartNum(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setWctFr(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setWctId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setProcessId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setiLevel(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setnPercent(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setIsplseq(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setIseq(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDsplPartNum(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setRmlayLayoutId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setRmlayPlantId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDsplvsplId(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setPartNumChild(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDsplIsplSeq(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public List<Msc432VoDtlmLayout> getDtlDtmlLayout(String makerId, String vPlant, String partChild, String partParentTmp, String nSplSeq, String initSeq) {
        List<Msc432VoDtlmLayout> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_LIST_DTLLAYOUT;

        if (!makerId.equals("")) {
            sql += "    AND RMLAY_VMLAYOUTID = UPPER(:layoutid) ";
        }
        if (!vPlant.equals("")) {
            sql += "    AND RMLAY_MPLANT_VPLANTID = UPPER(:plantid) ";
        }
        if (!partChild.equals("")) {
            sql += "    AND DSPL_MPART_VPARTNUM_CHILD = UPPER(:partchild) ";
        }
        if (!partParentTmp.equals("")) {
            sql += "    AND DSPL_MPART_VPARTNUM = UPPER(:partparent) ";
        }
        if (!nSplSeq.equals("")) {
            sql += "    AND DSPL_ISPLSEQ = UPPER(:isplseq) ";
        }
        if (!initSeq.equals("")) {
            sql += "    AND ISEQUENCE = UPPER(:iseq) ";
        }

        Query query = getCurrentSession().createSQLQuery(sql);

        if (!makerId.equals("")) {
            query.setParameter("layoutid", makerId);
        }
        if (!vPlant.equals("")) {
            query.setParameter("plantid", vPlant);
        }
        if (!partChild.equals("")) {
            query.setParameter("partchild", partChild);
        }
        if (!partParentTmp.equals("")) {
            query.setParameter("partparent", partParentTmp);
        }
        if (!nSplSeq.equals("")) {
            query.setParameter("isplseq", nSplSeq);
        }
        if (!initSeq.equals("")) {
            query.setParameter("iseq", initSeq);
        }

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoDtlmLayout vo = new Msc432VoDtlmLayout();
                vo.setRmlay_Vmlayoutid(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setRmlay_Mplant_Vplantid(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDspl_Rspl_Vsplid(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDspl_Mpart_Vpartnum_Child(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDspl_Mpart_Vpartnum(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setDspl_Isplseq(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setIlevel(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setIsequence(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setNqty(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagman(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagpur(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagimp(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagckd(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagsub(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagzl(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagaccs(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagbct(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagreg(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setVflengfrm(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBflagsim(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setVblockid(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setMcol_Vcolorid(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setBnonprodord(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public List<Msc432VoOutplant> getOutplant(String kodeOut) {
        List<Msc432VoOutplant> result = new ArrayList<>();
        String sql = Msc432Constant.SQL_OUTPLANT;

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("vitemcode", kodeOut);

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoOutplant vo = new Msc432VoOutplant();
                vo.setVitemcode(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setVitemname(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
        }

        return result;
    }

    @Override
    public boolean checkRoute(String plantId, String lineId, String typeId, String colorId, String routingId) {
        boolean result = false;
        String sql = Msc432Constant.SQL_ROUTING;

        sql += " AND UPPER(VROUTEID) = UPPER(:routeid) ";

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("plantid", plantId);
        query.setParameter("typeid", typeId);
        query.setParameter("colorid", colorId);
        query.setParameter("lineid", lineId);
        query.setParameter("routeid", routingId);

        if (query.list().size() > 0) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean checkPlant(String plantId) {
        boolean result = false;
        String sql = Msc432Constant.SQL_PLANT;

        sql += " AND UPPER(VPLANTID) = UPPER(:plantid) ";

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("plantid", plantId);

        if (query.list().size() > 0) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean checkLine(String plantId, String lineId) {
        boolean result = false;
        String sql = Msc432Constant.SQL_LINE;

        sql += " AND UPPER(VLINEID) = UPPER(:lineid) ";

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("plantid", plantId);
        query.setParameter("lineid", lineId);

        if (query.list().size() > 0) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean checkType(String typeId) {
        boolean result = false;
        String sql = Msc432Constant.SQL_MCTYPE;

        sql += " AND UPPER(VMCTYPEID)  = UPPER(:typeid) ";

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("typeid", typeId);

        if (query.list().size() > 0) {
            result = true;
        }

        return result;
    }

    @Override
    public boolean checkColor(String typeId, String colorId) {
        boolean result = false;
        String sql = Msc432Constant.SQL_COLOR;

        sql += "  AND UPPER(MCOL_VCOLORID) = UPPER(:colorid) ";

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("typeid", typeId);
        query.setParameter("colorid", colorId);

        if (query.list().size() > 0) {
            result = true;
        }

        return result;
    }

    @Override
    public String getTransferDate(String routeId) {
        String result = "";
        String sql = Msc432Constant.SQL_GET_TRANSFER_DATE;

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("routeid", routeId);

        result = query.uniqueResult() == null ? "" : query.uniqueResult().toString();

        return result;
    }

    @Override
    public boolean checkJMS() {
        boolean result = false;
        String sql = Msc432Constant.SQL_CHECK_JMS;

        Query query = getCurrentSession().createSQLQuery(sql);

        if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                if((obj[3] == null ? "" : obj[3].toString()).equalsIgnoreCase("_41_OK") || (obj[3] == null ? "" : obj[3].toString()).equalsIgnoreCase("_42_FAILED")){
                    result = false;
                    break;
                }else{
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public List<Msc432VoEmail> getEmailByNrp(String nrp) {
        List<Msc432VoEmail> result = new ArrayList<Msc432VoEmail>();
        String sql = Msc432Constant.SQL_GET_EMAIL;

        Query query = getCurrentSession().createSQLQuery(sql);

        query.setParameter("nrp", nrp);

         if (query.list().size() > 0) {
            List<Object[]> list = query.list();
            for (Object[] obj : list) {
                int i = 0;
                Msc432VoEmail vo = new Msc432VoEmail();
                vo.setNama(obj[i] == null ? "" : obj[i].toString());
                i++;
                vo.setEmail(obj[i] == null ? "" : obj[i].toString());
                i++;
                result.add(vo);
            }
         }
        return result;
    }

}
