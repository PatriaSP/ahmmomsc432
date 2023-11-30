/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.mo.msc.app432.rest.advice;

import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import id.co.ahm.mo.msc.app432.exception.Msc432Exception;
import id.co.ahm.mo.msc.app432.rest.Msc432Rest;
import id.co.ahm.mo.msc.app432.vo.Msc432VoError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author patria
 */
@ControllerAdvice(basePackageClasses = Msc432Rest.class)
public class Msc432RestAdvice {
    private static final String ERROR = "error";
    private static final String SERVER_PROCESSING_ERROR = "Server Processing Error";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DtoResponse handleException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        Map<String, Object> mapMsg = new HashMap<>();
        mapMsg.put(ERROR, SERVER_PROCESSING_ERROR);

        return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, mapMsg, null);

    }

    @ExceptionHandler(Msc432Exception.class)
    @ResponseBody
    public DtoResponse handleException(Msc432Exception e, HttpServletResponse response) {
        List<Msc432VoError> errMsgs = null;
        Map<String, Object> mapMsg = new HashMap<>();
        if (e.getErrMsg() != null) {
            mapMsg.put(ERROR, e.getErrMsg());
        }
        if (!e.getErrMsgs().isEmpty()) {
            errMsgs = e.getErrMsgs();
        }

        return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, mapMsg, errMsgs);
    }

}
