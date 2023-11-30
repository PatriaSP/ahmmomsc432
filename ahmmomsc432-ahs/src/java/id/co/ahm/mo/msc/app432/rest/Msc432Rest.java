/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.rest;

import id.co.ahm.jx.print.constant.PrintConstant;
import id.co.ahm.jx.print.service.impl.PrintServiceImpl;
import id.co.ahm.jx.print.vo.VoPrintParam;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.mo.msc.app432.exception.Msc432Exception;
import id.co.ahm.mo.msc.app432.service.Msc432Service;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author patria
 */
@RestController
@RequestMapping("mo/msc432")
public class Msc432Rest {

    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;

    @Autowired
    private Msc432Service msc432Service;
    
    @RequestMapping(value = "lov-plant", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getPlantLov(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return msc432Service.getPlant(input, tokenPstUtil.getUserCred(token));
    }
    
    @RequestMapping(value = "lov-type", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getTypeLov(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return msc432Service.getType(input, tokenPstUtil.getUserCred(token));
    }
    
    @RequestMapping(value = "lov-color", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getColorLov(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return msc432Service.getColor(input, tokenPstUtil.getUserCred(token));
    }
    
    @RequestMapping(value = "lov-line", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getLineLov(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return msc432Service.getLine(input, tokenPstUtil.getUserCred(token));
    }
    
    @RequestMapping(value = "lov-routing", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getRoutingLov(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody DtoParamPaging input) {
        return msc432Service.getRouting(input, tokenPstUtil.getUserCred(token));
    }
    
    @RequestMapping(value = "process-transfer", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getTransferRouting(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody Map<String, Object> input) {
        return msc432Service.getTransferRouting(input, tokenPstUtil.getUserCred(token));
    }
    
    @GetMapping(value = "/export-excel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = CommonConstant.JXID, defaultValue = "") String token, @RequestParam Map<String, Object> input) {
        OutputStream out = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String filename = "Transfer Routing Flat " + sdf.format(new Date());
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "inline;filename=\"" + filename + ".xlsx\"");

            out = response.getOutputStream();
            Workbook wb = msc432Service.exportExcel(input);

            wb.write(out);
            response.flushBuffer();

        } catch (IOException ex) {
            Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            throw new Msc432Exception(ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @GetMapping(value = "download-template-excel")
    public void downloadTemplateExcel(HttpServletRequest request,
            HttpServletResponse response) throws ParseException {

        OutputStream out = null;
        try {

            String fileName = "ahmmomsc432b02_template.xlsx";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            out = response.getOutputStream();
            Workbook workbook = msc432Service.downloadTemplate();

            workbook.write(out);
            response.flushBuffer();

        } catch (IOException ex) {
            Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            throw new Msc432Exception(ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
//    @PostMapping(value = "upload-excel",
//            consumes = {"multipart/form-data"},
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody
//    DtoResponse upload(
//            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String token,
//            @RequestParam(value = "file", required = true) MultipartFile file) throws Exception, IOException {
//        return msc432Service.uploadExcel(file, tokenPstUtil.getUserCred(token));
//    }
    
    @RequestMapping(value = "upload-transfer", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoResponse getUploadTransferRouting(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @CookieValue(value = CommonConstant.JXID, defaultValue = "") String jxid,
            @CookieValue(value = CommonConstant.TKID, defaultValue = "") String tkid,
            @RequestBody Map<String, Object> input) {
        //if run local change return uploadTransferRoutingJMS(input, tokenPstUtil.getUserCred(token), token, jxid, tkid) to processUploadTransferRouting(input, tokenPstUtil.getUserCred(token))
        return msc432Service.uploadTransferRoutingJMS(input, tokenPstUtil.getUserCred(token), token, jxid, tkid);
    }
    
    @GetMapping(value = "/export-excel-routing-flat")
    public void exportExcelRoutingFlat(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = CommonConstant.JXID, defaultValue = "") String token, @RequestParam Map<String, Object> input) {
        OutputStream out = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String filename = "Routing_Flat_Data";
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "inline;filename=\"" + filename + ".xlsx\"");

            out = response.getOutputStream();
            
            Workbook wb = msc432Service.exportExcelRoutingFlat(input);

            wb.write(out);
            response.flushBuffer();

        } catch (IOException ex) {
            Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            throw new Msc432Exception(ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @GetMapping(value = "/export-excel-upload-data")
    public void exportExcelUplaodData(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = CommonConstant.JXID, defaultValue = "") String token, @RequestParam Map<String, Object> input) {
        OutputStream out = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String filename = "ahmmomsc432_download";
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "inline;filename=\"" + filename + ".xlsx\"");

            out = response.getOutputStream();
            
            Workbook wb = msc432Service.exportExcelUploadData(input);

            wb.write(out);
            response.flushBuffer();

        } catch (IOException ex) {
            Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            throw new Msc432Exception(ex.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Msc432Rest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @RequestMapping(value = "upload-transfer-JMS", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse getUploadTransferRoutingJMS(@RequestHeader(value = CommonConstant.JXID,
            defaultValue = "") String token,
            @RequestBody Map<String, Object> input) {
        return msc432Service.processUploadTransferRouting(input, tokenPstUtil.getUserCred(token));
    }
    
    @PostMapping(value = "upload-excel",
            consumes = {"multipart/form-data"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DtoResponse upload(
            @RequestHeader(value = CommonConstant.JXID, defaultValue = "") String JXID,
            @RequestHeader(value = CommonConstant.TKID, defaultValue = "") String TKID,
            @RequestParam(value = "file", required = true) File file) throws Exception, IOException {
        VoPrintParam vo = new VoPrintParam();
        vo.setFile(file);
        vo.setType(PrintConstant.TIF_TYPE);
        vo.setJxid(JXID);
        vo.setTkid(TKID);
        PrintServiceImpl print = new PrintServiceImpl();
        return print.print(vo);
    }
}
