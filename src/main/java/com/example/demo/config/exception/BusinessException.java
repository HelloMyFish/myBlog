package com.example.demo.config.exception;

import com.example.demo.constant.BusinessErrorCode;

/**
 * @author gyf
 * @date 2020/6/25 15:49
 */
public class BusinessException extends RuntimeException {
    private String code;
    private boolean isShowMsg = true;
    /**
     * 使用枚举传参
     *
     * @param errorCode 异常枚举
     */
    public BusinessException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
    /**
     * 使用自定义消息
     *
     * @param code 值
     * @param msg 详情
     */
    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
