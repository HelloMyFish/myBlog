package com.example.demo.config;

import com.example.demo.constant.BusinessErrorCode;
import com.example.demo.model.ResultData;
import com.example.demo.sys.entity.SysUser;
import com.example.demo.util.encryption.MD5Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.pept.encoding.OutputObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author gyf
 * @date 2020/6/25 23:11
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ResponseAdvice.class);
    /**
     * 选择哪些类，或哪些方法需要走beforeBodyWrite
     * 从arg0中可以获取方法名和类名
     * arg0.getMethod().getDeclaringClass().getName()为获取方法名
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        logger.info("=============ResponseBodyAdvice==============");
        if("com.example.demo.sys.controller.SysUserController".equals(methodParameter.getMethod().getDeclaringClass().getName())){
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        try {
            //arg0转换为OutputObject类型
//            ObjectMapper objectMapper=new ObjectMapper();
            JSONObject jsonObject = JSONObject.fromObject(o);
            String objectStr = jsonObject.toString();
            return ResultData.ofSuccess(MD5Util.md5(objectStr));
            /* out = objectMapper.readValue(org.json.JSONObject.valueToString(o), OutputObject.class);
           //获取加密密钥
            String oldEncryptKey = out.getBean().get("oldEncryptKey");
            //获取加密字符串
            DesSpecial des = new DesSpecial();
            String encryptData = des.strEnc(JSON.toJSONString(out.getBean()), oldEncryptKey, null, null);
            //封装数据（清除原来数据，放入加密数据）
            out.getBean().clear();
            out.getBean().put("data", encryptData);
            return out;*/
        } catch (Exception e) {
            logger.error("返回报文处理出错", e);
//            out.setReturnCode(ReturnInfoEnums.PROCESS_ERROR.getCode());
//            out.setReturnMessage(ReturnInfoEnums.PROCESS_ERROR.getMessage());
            return ResultData.ofFail(BusinessErrorCode.API_GATEWAY_ERROR);
        }
    }
}
