package com.example.demo.config;

import com.example.demo.config.exception.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gyf
 * @date 2020/6/21 16:04
 */
@ControllerAdvice
public class ExceptionGlobalHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionGlobalHandler.class);
    /**
     *
     * @param ex
     * @return 登录失败抛出异常
     */
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Map<String, Object> handleException(LoginException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("msg", ex.getMessage());
        return map;
    }

    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public Map<String, Object> handleException(BindException ex) {
        Map<String, Object> map = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                logger.error("{} -> {}", fieldError.getDefaultMessage(),
                        fieldError.getDefaultMessage());
            }
            if (fieldErrors.isEmpty()) {
                logger.error("validExceptionHandler error fieldErrors is empty");
            }
            map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            map.put("msg", fieldErrors.get(0));
        }
        return map;
    }
    /**
     * BusinessException 类捕获
     */
//    @ExceptionHandler(value = BusinessException.class)
//    public ModelAtt handlerBusinessException(BusinessException e) {
//        outPutError(BusinessException.class, CommonErrorCode.BUSINESS_ERROR, e);
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
//        map.put("msg", ex.getMessage());
//        return Result.ofFail(e.getCode(), e.getMessage());
//    }
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("msg", ex.getMessage());
        return map;
    }
//    private Result getBindResultDTO(BindingResult bindingResult) {
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        if (logger.isDebugEnabled()) {
//            for (FieldError error : fieldErrors) {
//                logger.error("{} -> {}", error.getDefaultMessage(),
//                        error.getDefaultMessage());
//            }
//        }
//
//        if (fieldErrors.isEmpty()) {
//            logger.error("validExceptionHandler error fieldErrors is empty");
//            R.ofFail(CommonErrorCode.BUSINESS_ERROR.getCode(), "");
//        }
//
//        return Result.ofFail(CommonErrorCode.PARAM_ERROR.getCode(),
//                fieldErrors.get(0).getDefaultMessage());
//    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @InitBinder
    public void formatDate(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(LocalDateTime.class, new CustomLocalDateTimeEditor(DATE_TIME_FORMATTER, true));
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(List.class, new CustomString2ListEditor());
        webDataBinder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class,Boolean.TRUE));
    }
}
