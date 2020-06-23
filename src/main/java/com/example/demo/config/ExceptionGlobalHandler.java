package com.example.demo.config;

import com.example.demo.config.exception.LoginException;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
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
    /**
     *
     * @param ex
     * @return 登录失败抛出异常
     */
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public Map<String, Object> handleException(Exception ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("msg", ex.getMessage());
        return map;
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @InitBinder
    public void formatDate(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(LocalDateTime.class, new CustomLocalDateTimeEditor(DATE_TIME_FORMATTER, true));
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        webDataBinder.registerCustomEditor(List.class, new CustomString2ListEditor());
        webDataBinder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class,Boolean.TRUE));
    }
}
