/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.constant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author patria
 */
public class Msc432Constant {

    // HEADER EXPORT EXCEL
    public static final Map<String, Object> HEADER_FOR_EXPORT = new LinkedHashMap<String, Object>() {
        {
            put("Seq", "");
            put("Level", "");
            put("Part Finish Good", "");
            put("Part Description", "");
            put("WCT Origin", "");
            put("WCT Destination", "");
            put("Process", "");
            put("Percent To WCT", "");
            put("Percent To Unit", "");
        }
    };

    // HEADER EXPORT EXCEL P2 RUTING FLAT DATA
    public static final Map<String, Object> HEADER_FOR_EXPORT_ROUTING_FLAT = new LinkedHashMap<String, Object>() {
        {
            put("ID Routing", "");
            put("Seq", "");
            put("Level", "");
            put("Part Finish Good", "");
            put("Part Description", "");
            put("WCT Origin", "");
            put("WCT Destination", "");
            put("Process", "");
            put("Percent To WCT", "");
            put("Percent To Unit", "");
            put("Transfer Date", "");
        }
    };

    // HEADER EXPORT EXCEL DATA FLAT p2
    public static final Map<String, Object> HEADER_FOR_EXPORT_DATA_FLAT = new LinkedHashMap<String, Object>() {
        {
            put("ID Routing", "");
            put("Seq", "");
            put("Level", "");
            put("Part Finish Good", "");
            put("Part Description", "");
            put("WCT Origin", "");
            put("WCT Destination", "");
            put("Process", "");
            put("Percent To WCT", "");
            put("Percent To Unit", "");
            put("Transfer Date", "");
        }
    };

    // HEADER EXPORT EXCEL DATA FLAT p2
    public static final Map<String, Object> HEADER_FOR_UPLOAD = new LinkedHashMap<String, Object>() {
        {
            put("No", "");
            put("Plant ID", "");
            put("Line ID", "");
            put("Type ID", "");
            put("Color ID", "");
            put("Routing ID", "");
        }
    };

    // HEADER EXPORT EXCEL UPLOAD DATA p2
    public static final Map<String, Object> HEADER_FOR_EXPORT_UPLOAD = new LinkedHashMap<String, Object>() {
        {
            put("No", "");
            put("Plant ID", "");
            put("Line ID", "");
            put("Type ID", "");
            put("Color ID", "");
            put("Routing ID", "");
            put("Transfer Date", "");
        }
    };

    public static final String SQL_PLANT = "SELECT  VPLANTID,VPLANTID||' - '||VPLANTDESC plantdesc \n"
            + "FROM AHMMOMSC_MSTPLANTS   \n"
            + "WHERE SYSDATE BETWEEN DBEGINEFF AND DENDEFF  ";

    public static final String SQL_MCTYPE = "SELECT VMCTYPEID, VMCDESC\n"
            + "FROM AHMMOMSC_HDRMCTYPES\n"
            + "WHERE SYSDATE BETWEEN DBEGINEFF AND DENDEFF\n";

    public static final String SQL_COLOR = "SELECT MCOL_VCOLORID, (SELECT VCOLORDESC FROM AHMMOMSC_MSTCOLORS WHERE VCOLORID = MCOL_VCOLORID) VCOLORDESC\n"
            + "FROM\n"
            + "AHMMOMSC_MSTTYPECOLS\n"
            + "WHERE TRUNC(SYSDATE) BETWEEN TRUNC(DBEGINEFF) AND TRUNC(DENDEFF)\n"
            + "AND UPPER(RTYPE_VMCTYPEID) = UPPER(:typeid)  ";

    public static final String SQL_LINE = "SELECT \n"
            + "  VLINEID,\n"
            + "  VLINEDESC,\n"
            + "  MPLANT_VPLANTID,\n"
            + "  VLINETYPE,\n"
            + "  MWCT_VWCTID\n"
            + "FROM AHMMOMSC_MSTLINES\n"
            + "WHERE SYSDATE BETWEEN DBEGINEFF AND DENDEFF\n"
            + "AND UPPER(MPLANT_VPLANTID) = UPPER(:plantid) ";

    public static final String SQL_ROUTING = "select VROUTEID,MLINES_VLINEID from AHMMOMSC_HDRROUTINGS\n"
            + "where (UPPER(RMLAY_MPLANT_VPLANTID) like UPPER('%'||:plantid||'%'))\n"
            + "AND (UPPER(MTPCOL_RTYPE_VMCTYPEID) like UPPER('%'||:typeid||'%'))\n"
            + "AND (UPPER(MTPCOL_MCOL_VCOLORID) like UPPER('%'||:colorid||'%'))\n"
            + "AND (UPPER(MLINES_VLINEID) like UPPER('%'||:lineid||'%'))\n"
            + "AND BFLRELEASE = 'T'  ";

    public static final String SQL_TRANSFER_EXPORT = "Select \n"
            + "DROUTE_ISEQUENCE  SEQ,	\n"
            + "ILEVEL,\n"
            + "DROUTE_MPART_VPARTNUM PARTNUM,\n"
            + "VPARTDESC  Partdesc,\n"
            + "DROUTE_MWCT_VWCTID_WCTFR WCT_FROM,\n"
            + "DROUTE_MWCT_VWCTID WCT,\n"
            + "A.MPROC_VPROCESSID PROSES, \n"
            + "NPERCENT PERSEN_TO_WCT, \n"
            + "NPCTTOUNIT PERSEN_TO_UNIT,\n"
            + "DROUTE_RROUTE_VROUTEID ID_ROUTING, \n"
            + "IPARENTSEQ, A.DCREA\n"
            + "	\n"
            + "from  AHMMOMSC_MSTFLROUTES A, AHMMOMSC_MSTPARTS, AHMMOMSC_DTLROUTINGS\n"
            + "where UPPER(DROUTE_RROUTE_VROUTEID) = UPPER(:routeId)\n"
            + "AND DROUTE_MPART_VPARTNUM =VPARTNUM\n"
            + "AND DROUTE_ISEQUENCE = ISEQUENCE  \n"
            + "and DROUTE_RROUTE_VROUTEID  = RROUTE_VROUTEID\n"
            + "and DROUTE_MPART_VPARTNUM = MPART_VPARTNUM\n"
            + "and DROUTE_MWCT_VWCTID  = MWCT_VWCTID\n"
            + "and DROUTE_MWCT_VWCTID_WCTFR =MWCT_VWCTID_WCTFR\n"
            + "ORDER BY DROUTE_ISEQUENCE, DROUTE_MWCT_VWCTID    ";

    public static final String SQL_LIST_ROUTING = "select DROUTE_RROUTE_VROUTEID,\n"
            + "DROUTE_MWCT_VWCTID_WCTFR,\n"
            + "DROUTE_MWCT_VWCTID,\n"
            + "DROUTE_MPART_VPARTNUM,\n"
            + "DROUTE_ISEQUENCE,\n"
            + "MPROC_VPROCESSID,\n"
            + "NPCTTOUNIT,\n"
            + "IPARENTSEQ\n"
            + "from AHMMOMSC_MSTFLROUTES\n"
            + "where DROUTE_RROUTE_VROUTEID = (select vrouteid from AHMMOMSC_HDRROUTINGS where\n"
            + "UPPER(RMLAY_MPLANT_VPLANTID) = UPPER(:plantid) \n"
            + "            and UPPER(MTPCOL_RTYPE_VMCTYPEID)  = UPPER(:typeid)\n"
            + "            and UPPER(MTPCOL_MCOL_VCOLORID) = UPPER(:colorid)\n"
            + "            and UPPER(MLINES_VLINEID) = UPPER(:lineid)\n"
            + "            and UPPER(vrouteid) = UPPER(:routeid)\n"
            + "            AND BFLRELEASE = 'T')";

    public static final String SQL_LIST_DTLROUTING = "SELECT A.RROUTE_VROUTEID, \n"
            + "       A.MPART_VPARTNUM, \n"
            + "       A.MWCT_VWCTID_WCTFR, \n"
            + "       A.MWCT_VWCTID, \n"
            + "       A.MPROC_VPROCESSID, \n"
            + "       A.ILEVEL, \n"
            + "       A.NPERCENT, \n"
            + "       A.ISPLSEQ, \n"
            + "       A.ISEQUENCE, \n"
            + "       B.DSPL_MPART_VPARTNUM, \n"
            + "       B.RMLAY_VMLAYOUTID, \n"
            + "       B.RMLAY_MPLANT_VPLANTID, \n"
            + "       B.DSPL_RSPL_VSPLID, \n"
            + "       B.DSPL_MPART_VPARTNUM_CHILD, \n"
            + "       B.DSPL_ISPLSEQ\n"
            + "FROM AHMMOMSC_DTLROUTINGS A, AHMMOMSC_DTLMLAYOUTS B, AHMMOMSC_HDRROUTINGS C\n"
            + "WHERE ((A.ISEQUENCE = B.ISEQUENCE) \n"
            + "AND (A.MPART_VPARTNUM = B.DSPL_MPART_VPARTNUM_CHILD )) \n"
            + "AND (( B.RMLAY_MPLANT_VPLANTID = C.RMLAY_MPLANT_VPLANTID ) \n"
            + "AND ( B.RMLAY_VMLAYOUTID = C.RMLAY_VMLAYOUTID )) \n"
            + "AND ( A.RROUTE_VROUTEID = C.VROUTEID)\n"
            + "AND UPPER(A.RROUTE_VROUTEID) = UPPER(:routeid)\n"
            + "ORDER BY A.ISEQUENCE,A.MWCT_VWCTID_WCTFR ASC";

    public static final String SQL_LIST_DTLLAYOUT = "SELECT RMLAY_VMLAYOUTID, \n"
            + "       RMLAY_MPLANT_VPLANTID, \n"
            + "       DSPL_RSPL_VSPLID, \n"
            + "       DSPL_MPART_VPARTNUM_CHILD, \n"
            + "       DSPL_MPART_VPARTNUM, \n"
            + "       DSPL_ISPLSEQ, \n"
            + "       ILEVEL, \n"
            + "       ISEQUENCE, \n"
            + "       NQTY, \n"
            + "       BFLAGMAN, \n"
            + "       BFLAGPUR, \n"
            + "       BFLAGIMP, \n"
            + "       BFLAGCKD, \n"
            + "       BFLAGSUB, \n"
            + "       BFLAGZL, \n"
            + "       BFLAGACCS, \n"
            + "       BFLAGBCT, \n"
            + "       BFLAGREG, \n"
            + "       VFLENGFRM, \n"
            + "       BFLAGSIM, \n"
            + "       VBLOCKID, \n"
            + "       MCOL_VCOLORID, \n"
            + "       BNONPRODORD\n"
            + "FROM AHMMOMSC_DTLMLAYOUTS \n"
            + "WHERE 1=1\n";

    public static final String SQL_OUTPLANT = "SELECT VITEMCODE, VITEMNAME\n"
            + "FROM AHMMOERP_DTLSETTINGS\n"
            + "WHERE RSET_VID = 'MSCWCTOUTPLANT'\n"
            + "AND BVALID = 'T'\n"
            + "AND UPPER(VITEMCODE)  = UPPER(:vitemcode)";

    public static final String SQL_GET_TRANSFER_DATE = "SELECT DCREA FROM AHMMOMSC_MSTFLROUTES WHERE UPPER(DROUTE_RROUTE_VROUTEID) = UPPER(:routeid) AND ROWNUM <= 1";

    public static final String SQL_CHECK_JMS = "SELECT A.VEVENTID, A.VAPPID, B.IEVENTSEQ, B.VSTATUS, B.VERRMSG FROM AHMITSYS_MSTEVENTS A,AHMITSYS_LOGEVENTS B \n"
            + "WHERE A.VAPPID = 'ahmmomsc432' AND A.VEVENTID = B.MEVENT_VEVENTID AND \n"
            + "(B.VSTATUS = (SELECT VITEMNAME FROM AHMMOERP_DTLSETTINGS WHERE RSET_VID = 'MSTLOGEVENTSSTATUS' AND VITEMCODE = '10')\n"
            + "OR B.VSTATUS = (SELECT VITEMNAME FROM AHMMOERP_DTLSETTINGS WHERE RSET_VID = 'MSTLOGEVENTSSTATUS' AND VITEMCODE = '20')\n"
            + "OR B.VSTATUS = (SELECT VITEMNAME FROM AHMMOERP_DTLSETTINGS WHERE RSET_VID = 'MSTLOGEVENTSSTATUS' AND VITEMCODE = '25')\n"
            + "OR B.VSTATUS = (SELECT VITEMNAME FROM AHMMOERP_DTLSETTINGS WHERE RSET_VID = 'MSTLOGEVENTSSTATUS' AND VITEMCODE = '30')\n"
            + "OR B.VSTATUS = (SELECT VITEMNAME FROM AHMMOERP_DTLSETTINGS WHERE RSET_VID = 'MSTLOGEVENTSSTATUS' AND VITEMCODE = '41')\n"
            + "OR B.VSTATUS = (SELECT VITEMNAME FROM AHMMOERP_DTLSETTINGS WHERE RSET_VID = 'MSTLOGEVENTSSTATUS' AND VITEMCODE = '42'))\n"
            + "ORDER BY IEVENTSEQ DESC";

    public static final String SQL_GET_EMAIL = "SELECT VNAMA,VEMAIL FROM AHMMOERP_MSTKARYAWANS WHERE IIDNRP = :nrp";
}
