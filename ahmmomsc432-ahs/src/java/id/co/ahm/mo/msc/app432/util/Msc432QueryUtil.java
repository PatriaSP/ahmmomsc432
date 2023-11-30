/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.util;

import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.util.AhmStringUtil;
import id.co.ahm.mo.msc.app432.vo.Msc432VoQueryFilter;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

/**
 *
 * @author patria
 */
public class Msc432QueryUtil {

    public static String countQuery(String sqlQuery) {
        return "SELECT COUNT(*) FROM (" + sqlQuery + " )";
    }

    public static String orderClause(DtoParamPaging input, String query, String defaultClause, Map<String, String> COL_ORDER) {
        StringBuilder sql = new StringBuilder(query);
        if (input.getSort() != null && !StringUtils.isEmpty(input.getSort())) {
            if (COL_ORDER.containsKey(input.getSort())) {
                sql.append(" ORDER BY ");
                sql.append(COL_ORDER.get(input.getSort()));
                if (input.getOrder().equalsIgnoreCase(CommonConstant.DESC)) {
                    sql.append(" DESC");
                } else {
                    sql.append(" ASC");
                }
            }
        } else {
            if (AhmStringUtil.hasValue(defaultClause)) {
                sql.append(" ORDER BY ").append(defaultClause);
            }
        }
        return sql.toString();
    }

    public static Msc432VoQueryFilter setFilter(Map<String, Object> search) {
        Msc432VoQueryFilter filterResult = new Msc432VoQueryFilter();

        if (search != null && search.size() > 0) {

            for (Map.Entry<String, Object> filter : search.entrySet()) {

                String key = filter.getKey().replace("Search", "");
                String value = (String) filter.getValue();
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("typeid", key)) {
                    filterResult.setTypeId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("typedesc", key)) {
                    filterResult.setTypeDesc(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("colorid", key)) {
                    filterResult.setColorId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("colordesc", key)) {
                    filterResult.setColorDesc(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("lineid", key)) {
                    filterResult.setLineId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("linedesc", key)) {
                    filterResult.setLineDesc(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("routeid", key)) {
                    filterResult.setRouteId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("routetypeid", key)) {
                    filterResult.setRouteTypeId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("routecolorid", key)) {
                    filterResult.setRouteColorId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("routelineid", key)) {
                    filterResult.setRouteLineId(value);
                }
                if (!StringUtils.equals(value, null) && StringUtils.isNotEmpty(value) && StringUtils.equalsIgnoreCase("routeplantid", key)) {
                    filterResult.setRoutePlantId(value);
                }
            }
        }

        return filterResult;
    }

    public static Query setValueQueryInterest(Query q, String filter) {
        return q.setParameter("PARAM", filter);
    }
}
