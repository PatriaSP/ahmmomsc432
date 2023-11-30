/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.service.impl;

import id.co.ahm.jx.email.service.EmailService;
import id.co.ahm.jx.jms.service.JMSService;
import id.co.ahm.jx.jms.service.VoInputParamJMS;
import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.jxf.vo.VoPstUserCred;
import id.co.ahm.mo.msc.app000.model.MomscMstflroutes;
import id.co.ahm.mo.msc.app000.model.AhmmomscMstflroutesPk;
import id.co.ahm.mo.msc.app432.constant.Msc432Constant;
import id.co.ahm.mo.msc.app432.dao.Msc432AhmmomscMstflroutesDao;
import id.co.ahm.mo.msc.app432.service.Msc432Service;
import id.co.ahm.mo.msc.app432.vo.Msc432VoColor;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDtlRouting;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDtlmLayout;
import id.co.ahm.mo.msc.app432.vo.Msc432VoLine;
import id.co.ahm.mo.msc.app432.vo.Msc432VoOutplant;
import id.co.ahm.mo.msc.app432.vo.Msc432VoPlant;
import id.co.ahm.mo.msc.app432.vo.Msc432VoRoute;
import id.co.ahm.mo.msc.app432.vo.Msc432VoTransfRouting;
import id.co.ahm.mo.msc.app432.vo.Msc432VoType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import id.co.ahm.mo.msc.app432.dao.Msc432ObjectDao;
import id.co.ahm.mo.msc.app432.vo.Msc432VoDataUpload;
import id.co.ahm.mo.msc.app432.vo.Msc432VoEmail;
import id.co.ahm.mo.msc.app432.vo.Msc432VoError;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author patria
 */
@Service
@Transactional
public class Msc432ServiceImpl implements Msc432Service {

    @Autowired
    @Qualifier("msc432ObjectDao")
    private Msc432ObjectDao msc432ObjectDao;

    @Autowired
    @Qualifier("msc432AhmmomscMstflroutesDao")
    private Msc432AhmmomscMstflroutesDao msc432AhmmomscMstflroutesDao;

    @Autowired
    @Qualifier("mailService")
    EmailService emailService;

    @Autowired
    @Qualifier(value = "jmsService")
    JMSService jmsService;

    @Override
    public DtoResponse getPlant(DtoParamPaging input, VoPstUserCred userCred) {
        List<Msc432VoPlant> data = msc432ObjectDao.getPlantData();
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, data);
    }

    @Override
    public DtoResponse getType(DtoParamPaging input, VoPstUserCred userCred) {
        List<Msc432VoType> data = msc432ObjectDao.getTypeData(input);
        int count = msc432ObjectDao.getCountTypeData(input);
        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, data, count);
    }

    @Override
    public DtoResponse getColor(DtoParamPaging input, VoPstUserCred userCred) {
        List<Msc432VoColor> data = msc432ObjectDao.getColorData(input);
        int count = msc432ObjectDao.getCountColorData(input);
        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, data, count);
    }

    @Override
    public DtoResponse getLine(DtoParamPaging input, VoPstUserCred userCred) {
        List<Msc432VoLine> data = msc432ObjectDao.getLineData(input);
        int count = msc432ObjectDao.getCountLineData(input);
        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, data, count);
    }

    @Override
    public DtoResponse getRouting(DtoParamPaging input, VoPstUserCred userCred) {
        List<Msc432VoRoute> data = msc432ObjectDao.getRouteData(input);
        int count = msc432ObjectDao.getCountRouteData(input);
        return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, data, count);
    }

    @Override
    public Workbook exportExcel(Map<String, Object> input) {
        List<Msc432VoTransfRouting> results = msc432ObjectDao.exportExcel(input);
        Object[] header = Msc432Constant.HEADER_FOR_EXPORT.keySet().toArray();

        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("Transfer Routing Flat");

        // Create title
        String titleValue = "TRANSFER KE ROUTING FLAT";
        SXSSFCell titleCell = sheet.createRow(0).createCell(0);
        XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont titleFontStyle = (XSSFFont) wb.createFont();
        titleFontStyle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        titleFontStyle.setFontHeightInPoints((short) 11);
        titleFontStyle.setFontName("Calibri");
        titleFontStyle.setUnderline(XSSFFont.U_SINGLE);
        titleStyle.setFont(titleFontStyle);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleCell.setCellValue(titleValue);
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length - 1));

        // Create title
        SXSSFCell routeIdCell = sheet.createRow(2).createCell(0);
        XSSFCellStyle routeStyle = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont routeFontStyle = (XSSFFont) wb.createFont();
        routeFontStyle.setFontHeightInPoints((short) 11);
        routeFontStyle.setFontName("Calibri");
        routeFontStyle.setUnderline(XSSFFont.U_SINGLE);
        routeStyle.setFont(routeFontStyle);
        routeStyle.setAlignment(HorizontalAlignment.LEFT);
        routeIdCell.setCellValue("Id Routing : " + input.get("routeId"));
        routeIdCell.setCellStyle(routeStyle);

        int rHeader = 4;
        SXSSFRow row;
        SXSSFCell cell;

        //Style Header
        XSSFCellStyle styleHeader = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontHeader = (XSSFFont) wb.createFont();
        fontHeader.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fontHeader.setFontName("Calibri");
        fontHeader.setFontHeightInPoints((short) 11);
        styleHeader.setBorderBottom((short) 1);
        styleHeader.setBorderTop((short) 1);
        styleHeader.setBorderLeft((short) 1);
        styleHeader.setBorderRight((short) 1);
        styleHeader.setAlignment(HorizontalAlignment.LEFT);
        styleHeader.setFont(fontHeader);

        //Style data
        XSSFCellStyle styleData = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontData = (XSSFFont) wb.createFont();
        fontData.setFontName("Calibri");
        fontData.setFontHeightInPoints((short) 11);
        styleData.setBorderBottom((short) 1);
        styleData.setBorderTop((short) 1);
        styleData.setBorderLeft((short) 1);
        styleData.setBorderRight((short) 1);
        styleData.setAlignment(HorizontalAlignment.LEFT);
        styleData.setFont(fontData);

        //Style Data Number
        XSSFCellStyle styleDataNumber = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontDataNumber = (XSSFFont) wb.createFont();
        fontDataNumber.setFontName("Calibri");
        fontDataNumber.setFontHeightInPoints((short) 11);
        styleDataNumber.setBorderBottom((short) 1);
        styleDataNumber.setBorderTop((short) 1);
        styleDataNumber.setBorderLeft((short) 1);
        styleDataNumber.setBorderRight((short) 1);
        styleDataNumber.setAlignment(HorizontalAlignment.RIGHT);
        styleDataNumber.setFont(fontDataNumber);

        // Create Header
        row = sheet.createRow(rHeader);
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue((String) header[i]);
            cell.setCellStyle(styleHeader);
            sheet.trackColumnForAutoSizing(i);
            sheet.autoSizeColumn(i, true);
        }

        // Create Cell Data
        for (Msc432VoTransfRouting data : results) {
            rHeader++;
            row = sheet.createRow(rHeader);

            cell = row.createCell(0);
            cell.setCellValue(data.getSeq());
            cell.setCellStyle(styleDataNumber);

            cell = row.createCell(1);
            cell.setCellValue(data.getLevel());
            cell.setCellStyle(styleDataNumber);

            cell = row.createCell(2);
            cell.setCellValue(data.getPart());
            cell.setCellStyle(styleData);

            cell = row.createCell(3);
            cell.setCellValue(data.getPartDesc());
            cell.setCellStyle(styleData);

            cell = row.createCell(4);
            cell.setCellValue(data.getWctFrom());
            cell.setCellStyle(styleData);

            cell = row.createCell(5);
            cell.setCellValue(data.getWctDest());
            cell.setCellStyle(styleData);

            cell = row.createCell(6);
            cell.setCellValue(data.getProcess());
            cell.setCellStyle(styleData);

            cell = row.createCell(7);
            cell.setCellValue(data.getPercentWct());
            cell.setCellStyle(styleDataNumber);

            cell = row.createCell(8);
            cell.setCellValue(data.getPercentUnit());
            cell.setCellStyle(styleDataNumber);

        }
        for (int i = 0; i < header.length; i++) {
            sheet.trackColumnForAutoSizing(i);
            sheet.autoSizeColumn(i, true);
        }

        return wb;
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponse getTransferRouting(Map<String, Object> input, VoPstUserCred userCred) {
        Map<String, Object> msg = new HashMap<>();

        String wctFr = "";
        String partParent = "";
        String partParentTmp = "";
        String routeId = "";
        String partChild = "";
        String makerId = "";
        String partP0 = "";
        String WctFr0 = "";
        String vPlant = "";
        int nSplSeq;
        int ilevel;
        int pointerLevel = 1;
        int pointerSeq = 1;
        boolean flParent = false;

        routeId = (String) input.get("routeId");

        if (routeId.isEmpty()) {
            msg.put("error", "Routing Data Plant ID {" + (String) input.get("plantId") + "}, "
                    + "M/C Type ID {" + (String) input.get("typeId") + "}, "
                    + "Color ID {" + (String) input.get("colorId") + "}, "
                    + "Line ID {" + (String) input.get("lineId") + "}, "
                    + "Routing Code {" + (String) input.get("routeId") + "}, does not exist in Routing table");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }

        //search list data mstflroutes
        List<Msc432VoTransfRouting> listRoutes = msc432AhmmomscMstflroutesDao.getRoutes(input);
        if (listRoutes.size() != 0) {
            //delete all routes on mstflroutes table
            int index = 1;
            for (Msc432VoTransfRouting data : listRoutes) {
                if (index == 1) {
                    index++;
                } else {
                    AhmmomscMstflroutesPk dataPK = new AhmmomscMstflroutesPk();
                    dataPK.setDroute_isequence(new BigDecimal(data.getSeq()));
                    dataPK.setDroute_mpart_vpartnum(data.getPart());
                    dataPK.setDroute_mwct_vwctid_wctfr(data.getWctFrom());
                    dataPK.setDroute_mwct_vwctid(data.getWctDest());
                    dataPK.setDroute_rroute_vrouteid(data.getRouteId());
                    MomscMstflroutes dataRoutes = new MomscMstflroutes();
                    dataRoutes = msc432AhmmomscMstflroutesDao.findOne(dataPK);
                    if (dataRoutes != null) {
                        msc432AhmmomscMstflroutesDao.delete(dataRoutes);
                        msc432AhmmomscMstflroutesDao.flush();
                    }
                }
            }
        }

        List<Msc432VoDtlRouting> listDtlRoutes = msc432ObjectDao.getDtlRoutesData(input);
        if (listDtlRoutes.size() > 0) {
            int nQty;

            //get first data
            ilevel = Integer.parseInt(listDtlRoutes.get(0).getiLevel());
            if (ilevel != 0) {
                msg.put("error", "Routing Data {" + (String) input.get("plantId") + "} not start from Level 0");
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
            }
            makerId = listDtlRoutes.get(0).getRmlayLayoutId();
            WctFr0 = listDtlRoutes.get(0).getWctFr();
            partP0 = listDtlRoutes.get(0).getPartNum();
            partParentTmp = listDtlRoutes.get(0).getDsplPartNum();
            partChild = listDtlRoutes.get(0).getPartNum();
            nSplSeq = Integer.parseInt(listDtlRoutes.get(0).getIsplseq());
            vPlant = (String) input.get("plantId");
            int initSeq = Integer.parseInt(listDtlRoutes.get(0).getIseq());

            List<Msc432VoDtlmLayout> listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, partChild, partParentTmp, String.valueOf(nSplSeq), String.valueOf(initSeq));

            if (listDtmlLayout.size() > 0) {
                nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
            } else {
                msg.put("error", "Seq : {" + initSeq + "}, Part : {" + partChild + "}, Part Parent : {" + partParentTmp + "}, Spl Seq : {" + nSplSeq + "}, Maker Layout Id : {" + makerId + "}, Code Plant : {" + vPlant + "}, Routing ID: {" + routeId + "}. Not Found in Maker Layout");
                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
            }

            listDtlRoutes.get(0).setNpersenTo(String.valueOf(nQty * 100));
            int nPersen0 = Integer.parseInt(listDtlRoutes.get(0).getNpersenTo());
            listDtlRoutes.get(0).setSeqParent("0");

            //loop list detail routes from index 1
            int index = 1;
            for (index = 1; index < listDtlRoutes.size(); index++) {

                wctFr = listDtlRoutes.get(index).getWctFr();
                partParent = listDtlRoutes.get(index).getPartNum();
                int initLevel = Integer.parseInt(listDtlRoutes.get(index).getiLevel());
                initSeq = Integer.parseInt(listDtlRoutes.get(index).getIseq());

                if (initLevel == 1) {
                    if (listDtlRoutes.get(index).getNpersenTo() == null) {
                        listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, listDtlRoutes.get(index).getPartNum(), listDtlRoutes.get(index).getDsplPartNum(), listDtlRoutes.get(index).getIsplseq(), listDtlRoutes.get(index).getIseq());
                        if (listDtmlLayout.size() > 0) {
                            nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                        } else {
                            msg.put("error", "Seq : {" + initSeq + "}, Part : {" + partChild + "}, Part Parent : {" + partParentTmp + "}, Spl Seq : {" + nSplSeq + "}, Maker Layout Id : {" + makerId + "}, Code Plant : {" + vPlant + "}, Routing ID: {" + routeId + "}. Not Found in Maker Layout");
                            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                        }
                        if ((listDtlRoutes.get(index).getWctId().equals(WctFr0)) && (listDtlRoutes.get(index).getDsplPartNum().equals(partP0))) {
                            int nPersen = Integer.parseInt(listDtlRoutes.get(index).getnPercent());
                            listDtlRoutes.get(index).setNpersenTo(String.valueOf(nPersen * nPersen0 * nQty));
                            listDtlRoutes.get(index).setSeqParent("0");
                        }
                    }
                } else if (cekOutplant(wctFr) && cekOutplant(listDtlRoutes.get(index).getWctFr())) {
                    if (listDtlRoutes.get(index).getSeqParent() == null) {
                        partParentTmp = listDtlRoutes.get(index).getDsplPartNum();
                        partChild = listDtlRoutes.get(index).getPartNum();
                        nSplSeq = Integer.parseInt(listDtlRoutes.get(index).getIsplseq());
                        listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, partChild, partParentTmp, String.valueOf(nSplSeq), String.valueOf(initSeq));
                        if (listDtmlLayout.size() > 0) {
                            nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                        } else {
                            msg.put("error", "Seq : {" + initSeq + "}, Part : {" + partChild + "}, Part Parent : {" + partParentTmp + "}, Spl Seq : {" + nSplSeq + "}, Maker Layout Id : {" + makerId + "}, Code Plant : {" + vPlant + "}, Routing ID: {" + routeId + "}. Not Found in Maker Layout");
                            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                        }
                        int x = initSeq - 1;
                        int y = initLevel - 1;
                        partChild = partParentTmp;
                        while (y >= initLevel && x >= initSeq) {
                            listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, "", "", "", String.valueOf(x));
                            if (listDtmlLayout.size() > 0) {
                                if (listDtmlLayout.get(0).getDspl_Mpart_Vpartnum_Child().equals(partChild)) {
                                    if (Integer.parseInt(listDtmlLayout.get(0).getIlevel()) == y) {
                                        if (x > initSeq) {
                                            nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                                        }
                                        y--;
                                    }
                                    partChild = listDtmlLayout.get(0).getDspl_Mpart_Vpartnum();
                                } else if (Integer.parseInt(listDtmlLayout.get(0).getIlevel()) == y) {
                                    msg.put("error", "Seq : {" + listDtlRoutes.get(index).getIseq() + "}, Part : {" + listDtlRoutes.get(index).getPartNum() + "}, Part Parent : {" + listDtlRoutes.get(index).getDsplPartNum() + "}, Routing ID: {" + routeId + "}. The Parent Part not found in Maker Layout");
                                    return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                                }
                            }
                            x--;
                        }
                        if (listDtlRoutes.get(index).getSeqParent() == null) {
                            int number = Integer.parseInt(listDtlRoutes.get(index).getnPercent());
                            listDtlRoutes.get(index).setNpersenTo(String.valueOf(number * nQty));
                            listDtlRoutes.get(index).setSeqParent("0");
                        }
                    }
                }

                int nPersenParent = listDtlRoutes.get(index).getNpersenTo() == null ? 0 : Integer.parseInt(listDtlRoutes.get(index).getNpersenTo());
                int index2 = index + 1;
                if (index2 < listDtlRoutes.size()) {
                    pointerLevel = Integer.parseInt(listDtlRoutes.get(index2).getiLevel());
                    pointerSeq = Integer.parseInt(listDtlRoutes.get(index2).getIseq());
                    while (!((pointerLevel <= initLevel) && (pointerSeq != initSeq)) && index2 < listDtlRoutes.size()) {

                        partParentTmp = listDtlRoutes.get(index2).getDsplPartNum();
                        partChild = listDtlRoutes.get(index2).getPartNum();
                        nSplSeq = Integer.parseInt(listDtlRoutes.get(index2).getIsplseq());
                        if ((listDtlRoutes.get(index2).getWctId()).equals(wctFr) && (listDtlRoutes.get(index2).getDsplPartNum()).equals(partParent) && !cekOutplant(listDtlRoutes.get(index2).getWctId())) {
                            listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, partChild, partParentTmp, String.valueOf(nSplSeq), String.valueOf(pointerSeq));
                            if (listDtmlLayout.size() > 0) {
                                nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                            } else {
                                msg.put("error", "Seq : {" + pointerSeq + "}, Part : {" + partChild + "}, Part Parent : {" + partParentTmp + "}, Spl Seq : {" + nSplSeq + "}, Maker Layout Id : {" + makerId + "}, Code Plant : {" + vPlant + "}, Routing ID: {" + routeId + "}. Not Found in Maker Layout");
                                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                            }
                            int nPersenTmp = listDtlRoutes.get(index2).getNpersenTo() == null ? 0 : Integer.parseInt(listDtlRoutes.get(index2).getNpersenTo());
                            int number1 = Integer.parseInt(listDtlRoutes.get(index2).getnPercent());
                            listDtlRoutes.get(index2).setNpersenTo(String.valueOf(nPersenTmp + (number1 * (int) (nPersenParent * 0.01) * nQty)));
                            listDtlRoutes.get(index2).setSeqParent(String.valueOf(initSeq));
                        } else if (listDtlRoutes.get(index2).getWctId().equals(wctFr) && !cekOutplant(listDtlRoutes.get(index2).getWctFr()) && cekOutplant(listDtlRoutes.get(index2).getWctId()) && initSeq != pointerSeq && initLevel != pointerLevel) {
                            listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, partChild, partParentTmp, String.valueOf(nSplSeq), String.valueOf(pointerSeq));
                            if (listDtmlLayout.size() > 0) {
                                nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                            } else {
                                msg.put("error", "Seq : {" + pointerSeq + "}, Part : {" + partChild + "}, Part Parent : {" + partParentTmp + "}, Spl Seq : {" + nSplSeq + "}, Maker Layout Id : {" + makerId + "}, Code Plant : {" + vPlant + "}, Routing ID: {" + routeId + "}. Not Found in Maker Layout");
                                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                            }
                            int number2;
                            if (listDtlRoutes.get(index2).getSeqParent() == null || (listDtlRoutes.get(index2).getSeqParent()).equals(initSeq + "")) {
                                number2 = listDtlRoutes.get(index2).getNpersenTo() == null ? 0 : Integer.parseInt(listDtlRoutes.get(index2).getNpersenTo());
                            } else {
                                number2 = 0;
                            }
                            int number1 = Integer.parseInt(listDtlRoutes.get(index2).getnPercent());
                            listDtlRoutes.get(index2).setNpersenTo(String.valueOf(number2 + (number1 * (int) (nPersenParent * 0.01) * nQty)));
                            listDtlRoutes.get(index2).setSeqParent(String.valueOf(initSeq));
                        } else if (cekOutplant(listDtlRoutes.get(index2).getWctId()) && initSeq != pointerSeq && initLevel != pointerLevel) {
                            listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, partChild, partParentTmp, String.valueOf(nSplSeq), String.valueOf(pointerSeq));
                            if (listDtmlLayout.size() > 0) {
                                nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                            } else {
                                msg.put("error", "Seq : {" + pointerSeq + "}, Part : {" + partChild + "}, Part Parent : {" + partParentTmp + "}, Spl Seq : {" + nSplSeq + "}, Maker Layout Id : {" + makerId + "}, Code Plant : {" + vPlant + "}, Routing ID: {" + routeId + "}. Not Found in Maker Layout");
                                return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                            }
                            int i = pointerSeq - 1;
                            int j = pointerLevel - 1;
                            partChild = partParentTmp;
                            flParent = false;
                            while (j >= initLevel && i >= initSeq) {
                                listDtmlLayout = msc432ObjectDao.getDtlDtmlLayout(makerId, vPlant, "", "", "", String.valueOf(i));
                                if (listDtmlLayout.size() > 0) {
                                    if (listDtmlLayout.get(0).getDspl_Mpart_Vpartnum_Child().equals(partChild)) {
                                        if (Integer.parseInt(listDtmlLayout.get(0).getIlevel()) == j) {
                                            if (i > initSeq) {
                                                nQty = listDtmlLayout.get(0).getNqty() == null ? 1 : Integer.parseInt(listDtmlLayout.get(0).getNqty());
                                            }
                                            j--;
                                        }
                                        partChild = (String) listDtmlLayout.get(0).getDspl_Mpart_Vpartnum();
                                    } else if (Integer.parseInt(listDtmlLayout.get(0).getIlevel()) == j) {
                                        msg.put("error", "Seq : {" + listDtlRoutes.get(index2).getIseq() + "}, Part : {" + listDtlRoutes.get(index2).getPartNum() + "}, Part Parent : {" + listDtlRoutes.get(index2).getDsplPartNum() + "}, Routing ID: {" + routeId + "}. The Parent Part not found in Maker Layout");
                                        return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                                    }
                                }
                                i--;
                            }
                            if (initSeq == i + 1) {
                                flParent = true;
                            }
                            if ((initLevel < pointerLevel - 1 || (cekOutplant(wctFr) && initLevel == pointerLevel - 1)) && flParent) {
                                int number2 = 0;
                                if (cekOutplant(wctFr)) {
                                    if (listDtlRoutes.get(index2).getSeqParent() != null && listDtlRoutes.get(index2).getSeqParent().equals(initSeq + "")) {
                                        number2 = listDtlRoutes.get(index2).getNpersenTo() == null ? 0 : Integer.parseInt(listDtlRoutes.get(index2).getNpersenTo());
                                    }
                                } else if (listDtlRoutes.get(index2).getSeqParent() == null || listDtlRoutes.get(index2).getSeqParent().equals(initSeq + "")) {
                                    number2 = listDtlRoutes.get(index2).getNpersenTo() == null ? 0 : Integer.parseInt(listDtlRoutes.get(index2).getNpersenTo());
                                }
                                int number1 = Integer.parseInt(listDtlRoutes.get(index2).getnPercent());
                                listDtlRoutes.get(index2).setNpersenTo(String.valueOf(number2 + (number1 * (int) (nPersenParent * 0.01) * nQty)));
                                listDtlRoutes.get(index2).setSeqParent(String.valueOf(initSeq));
                            } else if (pointerLevel == 2) {
                                if (listDtlRoutes.get(index2).getSeqParent() == null) {
                                    int number = Integer.parseInt(listDtlRoutes.get(index2).getnPercent());
                                    listDtlRoutes.get(index2).setNpersenTo(String.valueOf(number * nQty));
                                    listDtlRoutes.get(index2).setSeqParent("0");
                                }
                            }
                        }
                        if (index2 + 1 < listDtlRoutes.size()) {
                            pointerLevel = Integer.parseInt(listDtlRoutes.get(index2 + 1).getiLevel());
                            pointerSeq = Integer.parseInt(listDtlRoutes.get(index2 + 1).getIseq());
                        }
                        index2++;
                    }
                }
            }

            for (Msc432VoDtlRouting data : listDtlRoutes) {
                
                if (data.getSeqParent() == null || data.getSeqParent().isEmpty()) {
                    msg.put("error", "Seq : {" + data.getIseq() + "}, Part : {" + data.getPartNum() + "}, WCT Parent : {" + data.getWctId() + "} on Routing : {" + data.getRouteId() + "}. The Part Parent does not exist, please confirm SPL to PQE");
                    return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
                }

                AhmmomscMstflroutesPk dataPK = new AhmmomscMstflroutesPk();
                dataPK.setDroute_isequence(new BigDecimal(data.getIseq()));
                dataPK.setDroute_mpart_vpartnum(data.getPartNum());
                dataPK.setDroute_mwct_vwctid_wctfr(data.getWctFr());
                dataPK.setDroute_mwct_vwctid(data.getWctId());
                dataPK.setDroute_rroute_vrouteid(data.getRouteId());
                MomscMstflroutes dataRoutes = new MomscMstflroutes();
                dataRoutes = msc432AhmmomscMstflroutesDao.findOne(dataPK);
                if (dataRoutes != null) {
                    dataRoutes.setIparentseq(new BigDecimal(data.getSeqParent()));
                    dataRoutes.setMproc_vprocessid(data.getProcessId());
                    String npctUnit = data.getNpersenTo().length() > 2 ? data.getNpersenTo().substring(0, 3) : data.getNpersenTo();
                    if (Integer.parseInt(npctUnit) > 100) {
                        npctUnit = npctUnit.replace("0", "");
                    }
                    dataRoutes.setNpcttounit(new BigDecimal(npctUnit));
                    msc432AhmmomscMstflroutesDao.update(dataRoutes);
                    msc432AhmmomscMstflroutesDao.flush();
                } else {
                    MomscMstflroutes dataInsert = new MomscMstflroutes();
                    dataInsert.setAhmmomscMstflroutesPk(dataPK);
                    dataInsert.setIparentseq(new BigDecimal(data.getSeqParent()));
                    dataInsert.setMproc_vprocessid(data.getProcessId());
                    String npctUnit = data.getNpersenTo().length() > 2 ? data.getNpersenTo().substring(0, 3) : data.getNpersenTo();
                    if (Integer.parseInt(npctUnit) > 100) {
                        npctUnit = npctUnit.replace("0", "");
                    }
                    dataInsert.setNpcttounit(new BigDecimal(npctUnit));
                    msc432AhmmomscMstflroutesDao.save(dataInsert);
                    msc432AhmmomscMstflroutesDao.flush();
                }
            }
        } else {
            msg.put("error", "Routing ID:{" + routeId + "} not found in Maker Layout Table");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }

        msg.put("success", "Process has been executed successfully.");
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
    }

    private boolean cekOutplant(String kodeOut) {
        List<Msc432VoOutplant> listOutplant = msc432ObjectDao.getOutplant(kodeOut);

        if (listOutplant.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public DtoResponse uploadExcel(MultipartFile file, VoPstUserCred userCred) {
        InputStream is = null;
        Workbook workbook = null;
        List<Msc432VoError> errors = new ArrayList<>();
        List<Msc432VoDataUpload> result = new ArrayList<>();
        boolean stat = false;
        try {
            is = file.getInputStream();

            if ((file.getOriginalFilename().endsWith("xlsx")) || (file.getOriginalFilename().endsWith("XLSX"))
                    || (file.getOriginalFilename().endsWith("xls")) || (file.getOriginalFilename().endsWith("XLS"))) {
                workbook = new XSSFWorkbook(is);
            } else {
                workbook = new HSSFWorkbook(is);
            }
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            //check template excel is valid or not
            if (validateTemplate(sheet, errors)) {

                int rowPointer = 1;
                Row row = iterator.next();
                //if row not null 
                if (iterator != null) {
                    boolean checkGhostData = true;
                    int counter = 0;
                    //loop to check when row not null but there's no data or template empty
                    while (iterator != null && counter < 5) {
                        if (iterator.hasNext()) {
                            row = iterator.next();
                        } else {
                            iterator = null;
                        }
                        if (isRowEmpty(row)) {
                            checkGhostData = false;
                        } else {
                            checkGhostData = true;
                            break;
                        }
                        counter++;
                    }
                    if (checkGhostData) {
                        iterator = sheet.iterator();
                        row = iterator.next();
                        row = iterator.next();
                        //loop every row data in excel
                        while (iterator != null) {
                            //check if row empty or not
                            if (!isRowEmpty(row)) {
                                //check every data is valid or not
                                if (validateSheetData(row, errors, ++rowPointer)) {
                                    //if data is valid then continue
                                    DataFormatter df = new DataFormatter();

                                    Msc432VoDataUpload data = new Msc432VoDataUpload();
                                    data.setNo(df.formatCellValue(row.getCell(0)).trim());
                                    data.setPlantId(df.formatCellValue(row.getCell(1)).trim());
                                    data.setLineId(df.formatCellValue(row.getCell(2)).trim());
                                    data.setTypeId(df.formatCellValue(row.getCell(3)).trim());
                                    data.setColorId(df.formatCellValue(row.getCell(4)).trim());
                                    data.setRouteId(df.formatCellValue(row.getCell(5)).trim());
                                    data.setDate(msc432ObjectDao.getTransferDate(data.getRouteId()));
                                    result.add(data);
                                    stat = true;
                                }
                            } else {
                                rowPointer++;
                            }
                            //condition for looping 
                            if (iterator.hasNext()) {
                                row = iterator.next();
                            } else {
                                iterator = null;
                            }
                        }
                    } else {
                        errors.add(new Msc432VoError("Template file",
                                "Template file kosong, silakan dilakukan pengisian terlebih dahulu!")
                        );
                    }
                } else {
                    errors.add(new Msc432VoError("Template file",
                            "Template file kosong, silakan dilakukan pengisian terlebih dahulu!")
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(Msc432Service.class.getName()).log(Level.SEVERE, null, e);
        }
        Map<String, Object> msg = new HashMap<String, Object>();
        if (stat == true && errors.isEmpty()) {
            msg.put("m", "Upload Success!");
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, result);
        } else {
            msg.put("m", "Upload Failed!");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, errors);
        }
    }

    //check template files
    private boolean validateTemplate(Sheet sheet, List<Msc432VoError> errorList) {
        if (!isValidHeader(sheet)) {
            errorList.add(new Msc432VoError("Format template file",
                    "Template tidak sesuai, silakan download template yang disediakan!")
            );
            return false;
        }
        return true;
    }

    //check header is valid or not
    private boolean isValidHeader(Sheet sheet) {
        Object[] headers = Msc432Constant.HEADER_FOR_UPLOAD.keySet().toArray();
        boolean isValid = true;
        try {
            Row row = sheet.iterator().next();
            DataFormatter df = new DataFormatter();
            for (int i = 0; i < headers.length; i++) {
                if (!headers[i].equals(df.formatCellValue(row.getCell(i)).trim())) {
                    isValid = false;
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Logger.getLogger(Msc432ServiceImpl.class.getName()).log(Level.SEVERE, e.getMessage());
            return false;
        }
        return isValid;
    }

    //check empty row
    private boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            String val = row.getCell(c).toString();
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && !val.equals("")) {
                return false;
            }
        }
        return true;
    }

    //validate data
    private boolean validateSheetData(Row row, List<Msc432VoError> errorList, int rowPointer) {
        DataFormatter df = new DataFormatter();
        int i = 1;
        boolean ret = true;
        if (!validateDataType(row, errorList, rowPointer, i)) {
            ret = false;
        }
        return ret;
    }

    //validate every cell data except header
    private boolean validateDataType(Row row, List<Msc432VoError> errorList,
            int rowPointer, int i) {

        int temp = errorList.size();
        DataFormatter df = new DataFormatter();
        String cellRow = String.valueOf(rowPointer);
        String plantId = row.getCell(i) == null ? "" : row.getCell(i).toString().replace(".0", "");
        i++;
        String lineId = row.getCell(i) == null ? "" : row.getCell(i).toString().replace(".0", "");
        i++;
        String typeId = row.getCell(i) == null ? "" : row.getCell(i).toString().replace(".0", "");
        i++;
        String colorId = row.getCell(i) == null ? "" : row.getCell(i).toString().replace(".0", "");
        i++;
        String routingId = row.getCell(i) == null ? "" : row.getCell(i).toString().replace(".0", "");
        i++;

        String errMsg = String.format("Row[%s]: ", cellRow);
        String errTemp = "";
        //check plant
        boolean checkPlant = msc432ObjectDao.checkPlant(plantId);
        if (!checkPlant) {
            errTemp += "Invalid Plant ID;";
        }

        //check line
        boolean checkLine = msc432ObjectDao.checkLine(plantId, lineId);
        if (!checkLine) {
            errTemp += "Invalid Line ID;";
        }

        //check type
        boolean checkType = msc432ObjectDao.checkType(typeId);
        if (!checkType) {
            errTemp += "Invalid Type ID;";
        }

        //check color
        boolean checkColor = msc432ObjectDao.checkColor(typeId, colorId);
        if (!checkColor) {
            errTemp += "Invalid Color ID;";
        }

        //check route
        boolean checkRoute = msc432ObjectDao.checkRoute(plantId, lineId, typeId, colorId, routingId);
        if (!checkRoute) {
            errTemp += "Invalid Routing ID;";
        }

        if (!errTemp.isEmpty() || !errTemp.equals("")) {
            errorList.add(new Msc432VoError(cellRow, errMsg + errTemp));
        }
        return temp == errorList.size();
    }

    @Override
    public Workbook downloadTemplate() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet worksheet = workbook.createSheet("Template");
        XSSFRow rowHeader = worksheet.createRow(0);

        XSSFFont textBold = workbook.createFont();

        textBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        /*Header Style*/
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setFont(textBold);

        int cellPointer = 0;
        for (Map.Entry<String, Object> data : Msc432Constant.HEADER_FOR_UPLOAD.entrySet()) {
            generateTempalte(rowHeader, cellPointer, workbook, data, headerStyle, worksheet);
            worksheet.autoSizeColumn(cellPointer);
            cellPointer++;
        }
        return workbook;
    }

    private void generateTempalte(XSSFRow rowHeader,
            int cellPointer, XSSFWorkbook workbook, Map.Entry<String, Object> data, CellStyle style, XSSFSheet sheet) {

        XSSFCell[] cell = new XSSFCell[10];
        cell[cellPointer] = rowHeader.createCell(cellPointer);
        cell[cellPointer].setCellStyle(style);
        cell[cellPointer].setCellValue(data.getKey());

    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponse processUploadTransferRouting(Map<String, Object> input, VoPstUserCred userCred) {
        uploadTransferRouting(input, userCred);
        Map<String, Object> msg = new HashMap<>();
        msg.put("m", "Transfer Routing Flat will be processed immediately.");
        return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponse uploadTransferRouting(Map<String, Object> input, VoPstUserCred userCred) {
        Map<String, Object> msg = new HashMap<String, Object>();

        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"1\"\">");
        sb.append("<tr><th>No</th><th>Plant</th><th>Line</th><th>Type</th><th>Color</th><th>Routing ID</th><th>Tanggal Transfer</th><th>Status</th><th>Error Message</th></tr>");

        List<Msc432VoDataUpload> dataTable = (List<Msc432VoDataUpload>) input.get("dataTransfer");

        DtoResponse ret = null;
        boolean checkStatus = true;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date dateStart = new Date();
        boolean result = true;
        for (int i = 0; i < dataTable.size(); i++) {
            ret = getTransferRouting((Map<String, Object>) dataTable.get(i), userCred);
            if (ret.getStatus().equals("0")) {
                checkStatus = false;
                result = false;
            } else {
                checkStatus = true;
            }
            Map<String, Object> value = (Map<String, Object>) dataTable.get(i);
            sb.append("<tr><td>" + (i + 1) + "</td><td>" + value.get("plantId") + "</td><td>" + value.get("lineId") + "</td>" + "<td>" + value.get("typeId") + "</td><td>" + value.get("colorId") + "</td><td>" + value.get("routeId") + "</td>" + "<td>" + formatter.format(new Date()) + "</td>" + "<td>" + ((checkStatus == true) ? "OK" : "Failed.") + "</td><td>" + ((checkStatus == true) ? "" : ret.getMessage().get("error")) + "</td></tr>");
        }

        sb.append("</table>");

        Date dateFinish = new Date();
        sendEmailListError(sb.toString(), result == false ? "FAILED" : "OK", formatter.format(dateStart).toString(), formatter.format(dateFinish).toString(), userCred.getUserid());

        if (result) {
            msg.put("m", "Success transfer routing flat");
            return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, msg, null);
        } else {
            msg.put("m", "Error Found. Detail Error has been sent to your email account. Please check your inbox");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }
    }

    @Transactional(readOnly = false)
    public boolean sendEmailListError(String vListError, String vStatus, String vTimeBegin, String vTimeEnd, String nrp) {
        String subject = "[ahmas] Process Status for [ahmmomsc432] is [" + vStatus + "]";
        String emailFrom = "noreply@astra-honda.com";
        List<Msc432VoEmail> emailTo = msc432ObjectDao.getEmailByNrp(nrp);
        String result = "";
        boolean ret = false;
        for (Msc432VoEmail data : emailTo) {
            String vHead = "<head>"
                    + "<style>body           {font-family:'Verdana'; font-size:11pt;}.tblUtama          {border-collapse: collapse;}.tblUtama, .tblUtama td, .tblUtama th  {border: 1px solid black; font-size:9pt;}th             {valign:'middle'}td             {padding-left:3px; padding-right:3px;}"
                    + "</style>"
                    + "</head>";
            String vBody = "<body> "
                    + "<b>Process Request Status</b><br><br>"
                    + "Dear Sir/Madam, <b>" + data.getNama().toUpperCase() + "</b><br><br>"
                    + "Hereby, we would like to inform status of your process request:  <br><br>"
                    + "<table>"
                    + "<tr>"
                    + "<td><b>Application ID</b></td>"
                    + "<td>:</td>"
                    + "<td>ahmmomsc432</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td><b>Parameters</b></td>"
                    + "<td>:</td>"
                    + "<td>ahmmomsc432</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td><b>Start Time</b></td>"
                    + "<td>:</td>"
                    + "<td>" + vTimeBegin + "</td>"
                    + "</tr>" + ""
                    + "<tr>"
                    + "<td><b>End Time</b></td>"
                    + "<td>:</td>"
                    + "<td>" + vTimeEnd + "</td>"
                    + "</tr>" + ""
                    + "<tr>"
                    + "<td><b>Status</b></td>"
                    + "<td>:</td>"
                    + "<td " + (vStatus.equals("OK") ? "style='color: blue' " : "style='color: blue'") + ">" + vStatus + "</td>"
                    + "</tr>" + ""
                    + "<tr>"
                    + "<td><b>Notes</b></td>"
                    + "<td>:</td>"
                    + "<td " + (vStatus.equals("OK") ? "" : "style='color: red'") + ">" + (vStatus.equals("OK") ? "-" : "Please check detail error in table below.") + "</td>"
                    + "</tr>"
                    + "</table><br><br>";
            String vMsgdata = vHead + vBody + vListError + "<br><br>" + "We hope this information is useful for you. Any furher progress will be updated later.<br><br> Thank You."
                    + "<br><br><hr><br><i>This e-mail is generated by system. Please do not reply this email.</i>";
            result = emailService.callProcSendMail(subject, emailFrom, data.getEmail(), null, vMsgdata);
            if (result == null) {
                ret = true;
            } else {
                ret = false;
            }
        }

        return ret;
    }

    @Override
    public Workbook exportExcelRoutingFlat(Map<String, Object> input) {

        Object[] header = Msc432Constant.HEADER_FOR_EXPORT_ROUTING_FLAT.keySet().toArray();
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("Routing_Flat_Data");

        //Style Header
        XSSFCellStyle styleHeader = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontHeader = (XSSFFont) wb.createFont();
        fontHeader.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fontHeader.setFontName("Calibri");
        fontHeader.setFontHeightInPoints((short) 11);
        styleHeader.setBorderBottom((short) 1);
        styleHeader.setBorderTop((short) 1);
        styleHeader.setBorderLeft((short) 1);
        styleHeader.setBorderRight((short) 1);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setFont(fontHeader);
        styleHeader.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        styleHeader.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        //Style data
        XSSFCellStyle styleData = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontData = (XSSFFont) wb.createFont();
        fontData.setFontName("Calibri");
        fontData.setFontHeightInPoints((short) 11);
        styleData.setBorderBottom((short) 1);
        styleData.setBorderTop((short) 1);
        styleData.setBorderLeft((short) 1);
        styleData.setBorderRight((short) 1);
        styleData.setAlignment(HorizontalAlignment.LEFT);
        styleData.setFont(fontData);

        //Style Data Number
        XSSFCellStyle styleDataNumber = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontDataNumber = (XSSFFont) wb.createFont();
        fontDataNumber.setFontName("Calibri");
        fontDataNumber.setFontHeightInPoints((short) 11);
        styleDataNumber.setBorderBottom((short) 1);
        styleDataNumber.setBorderTop((short) 1);
        styleDataNumber.setBorderLeft((short) 1);
        styleDataNumber.setBorderRight((short) 1);
        styleDataNumber.setAlignment(HorizontalAlignment.RIGHT);
        styleDataNumber.setFont(fontDataNumber);

        int rHeader = 0;
        SXSSFRow row;
        SXSSFCell cell;

        // Create Header
        row = sheet.createRow(rHeader);
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue((String) header[i]);
            cell.setCellStyle(styleHeader);
            sheet.trackColumnForAutoSizing(i);
            sheet.autoSizeColumn(i, true);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < ((input.size() - 1) / 7); i++) {
            Map<String, Object> value = new HashMap<String, Object>();
            value.put("routeId", input.get("dataTransfer[" + i + "][routeId]"));
            List<Msc432VoTransfRouting> results = msc432ObjectDao.exportExcel(value);
            // Create Cell Data
            for (Msc432VoTransfRouting data : results) {
                rHeader++;
                row = sheet.createRow(rHeader);

                cell = row.createCell(0);
                cell.setCellValue(data.getRouteId());
                cell.setCellStyle(styleData);

                cell = row.createCell(1);
                DecimalFormat formatDec = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                DecimalFormatSymbols symbols = formatDec.getDecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                formatDec.setDecimalFormatSymbols(symbols);
                cell.setCellValue(formatDec.format(Double.parseDouble(data.getSeq())));
                cell.setCellStyle(styleDataNumber);

                cell = row.createCell(2);
                cell.setCellValue(data.getLevel());
                cell.setCellStyle(styleDataNumber);

                cell = row.createCell(3);
                cell.setCellValue(data.getPart());
                cell.setCellStyle(styleData);

                cell = row.createCell(4);
                cell.setCellValue(data.getPartDesc());
                cell.setCellStyle(styleData);

                cell = row.createCell(5);
                cell.setCellValue(data.getWctFrom());
                cell.setCellStyle(styleData);

                cell = row.createCell(6);
                cell.setCellValue(data.getWctDest());
                cell.setCellStyle(styleData);

                cell = row.createCell(7);
                cell.setCellValue(data.getProcess());
                cell.setCellStyle(styleData);

                cell = row.createCell(8);
                cell.setCellValue(data.getPercentWct());
                cell.setCellStyle(styleDataNumber);

                cell = row.createCell(9);
                cell.setCellValue(data.getPercentUnit());
                cell.setCellStyle(styleDataNumber);

                cell = row.createCell(10);
                Date transferDate = null;
                try {
                    transferDate = formatter.parse(data.getTransferDate());
                } catch (ParseException ex) {
                    Logger.getLogger(Msc432ServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");
                cell.setCellValue(formatter1.format(transferDate).toString());
                cell.setCellStyle(styleDataNumber);
            }

            for (int j = 0; j < header.length; j++) {
                sheet.trackColumnForAutoSizing(j);
                sheet.autoSizeColumn(j, true);
            }
        }

        return wb;
    }

    @Override
    public Workbook exportExcelUploadData(Map<String, Object> input) {
        Object[] header = Msc432Constant.HEADER_FOR_EXPORT_UPLOAD.keySet().toArray();
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = (SXSSFSheet) wb.createSheet("Routing_Flat_Data");

        //Style Header
        XSSFCellStyle styleHeader = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontHeader = (XSSFFont) wb.createFont();
        fontHeader.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        fontHeader.setFontName("Calibri");
        fontHeader.setFontHeightInPoints((short) 11);
        styleHeader.setBorderBottom((short) 1);
        styleHeader.setBorderTop((short) 1);
        styleHeader.setBorderLeft((short) 1);
        styleHeader.setBorderRight((short) 1);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setFont(fontHeader);

        //Style data
        XSSFCellStyle styleData = (XSSFCellStyle) wb.createCellStyle();
        XSSFFont fontData = (XSSFFont) wb.createFont();
        fontData.setFontName("Calibri");
        fontData.setFontHeightInPoints((short) 11);
        styleData.setBorderBottom((short) 1);
        styleData.setBorderTop((short) 1);
        styleData.setBorderLeft((short) 1);
        styleData.setBorderRight((short) 1);
        styleData.setAlignment(HorizontalAlignment.LEFT);
        styleData.setFont(fontData);

        int rHeader = 0;
        SXSSFRow row;
        SXSSFCell cell;

        // Create Header
        row = sheet.createRow(rHeader);
        for (int i = 0; i < header.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue((String) header[i]);
            cell.setCellStyle(styleHeader);
            sheet.trackColumnForAutoSizing(i);
            sheet.autoSizeColumn(i, true);
        }

        for (int i = 0; i < ((input.size() - 1) / 7); i++) {
            rHeader++;
            row = sheet.createRow(rHeader);

            cell = row.createCell(0);
            cell.setCellValue(i + 1);
            cell.setCellStyle(styleData);

            cell = row.createCell(1);
            cell.setCellValue(input.get("dataTransfer[" + i + "][plantId]").toString());
            cell.setCellStyle(styleData);

            cell = row.createCell(2);
            cell.setCellValue(input.get("dataTransfer[" + i + "][lineId]").toString());
            cell.setCellStyle(styleData);

            cell = row.createCell(3);
            cell.setCellValue(input.get("dataTransfer[" + i + "][typeId]").toString());
            cell.setCellStyle(styleData);

            cell = row.createCell(4);
            cell.setCellValue(input.get("dataTransfer[" + i + "][colorId]").toString());
            cell.setCellStyle(styleData);

            cell = row.createCell(5);
            cell.setCellValue(input.get("dataTransfer[" + i + "][routeId]").toString());
            cell.setCellStyle(styleData);

            cell = row.createCell(6);
            cell.setCellValue(input.get("dataTransfer[" + i + "][date]").toString());
            cell.setCellStyle(styleData);
        }

        for (int j = 0; j < header.length; j++) {
            sheet.trackColumnForAutoSizing(j);
            sheet.autoSizeColumn(j, true);
        }

        return wb;
    }

    @Override
    @Transactional(readOnly = false)
    public DtoResponse uploadTransferRoutingJMS(Map<String, Object> input, VoPstUserCred userCred, String token, String jxid, String tkid) {

        Map<String, Object> msg = new HashMap<String, Object>();

        boolean checkJMS = msc432ObjectDao.checkJMS();
        if (checkJMS) {
            msg.put("m", "Proses Transfer Flat Routing sedang berlangsung !");
            return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
        }

        List<String> key = new ArrayList<>();
        key.add("AHMMOMSC432");

        VoInputParamJMS inputparam = new VoInputParamJMS();
        inputparam.setAppId("ahmmomsc432");
        inputparam.setParam(input);
        inputparam.setUrl("/ahmmomsc432-ahs/rest/mo/msc432/upload-transfer-JMS");
        inputparam.setKey(key);
        inputparam.setHdr(token);
        inputparam.setTkid(tkid);
        inputparam.setJxid(jxid);
        DtoResponse dto = jmsService.sendToJms(inputparam);
        return dto;
    }
}
