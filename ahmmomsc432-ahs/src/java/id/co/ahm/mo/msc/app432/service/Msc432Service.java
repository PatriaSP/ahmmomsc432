/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.service;

import id.co.ahm.jxf.dto.DtoParamPaging;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.vo.VoPstUserCred;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author patria
 */
public interface Msc432Service {

    public DtoResponse getPlant(DtoParamPaging input, VoPstUserCred userCred);

    public DtoResponse getType(DtoParamPaging input, VoPstUserCred userCred);

    public DtoResponse getColor(DtoParamPaging input, VoPstUserCred userCred);

    public DtoResponse getLine(DtoParamPaging input, VoPstUserCred userCred);

    public DtoResponse getRouting(DtoParamPaging input, VoPstUserCred userCred);

    public Workbook exportExcel(Map<String, Object> input);

    public DtoResponse getTransferRouting(Map<String, Object> input, VoPstUserCred userCred);

    public DtoResponse uploadExcel(MultipartFile file, VoPstUserCred userCred);

    public Workbook downloadTemplate();

    public DtoResponse uploadTransferRouting(Map<String, Object> input, VoPstUserCred userCred);

    public Workbook exportExcelRoutingFlat(Map<String, Object> input);

    public Workbook exportExcelUploadData(Map<String, Object> input);

    public DtoResponse uploadTransferRoutingJMS(Map<String, Object> input, VoPstUserCred userCred, String token, String jxid, String tkid);

    public DtoResponse processUploadTransferRouting(Map<String, Object> input, VoPstUserCred userCred);
}
