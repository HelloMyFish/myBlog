package com.example.demo.constant;

/**
 * @author gyf
 * @date 2020/6/20 23:04
 */
public enum  CommonEnum {
    /**
     * 已被删除
     */
    DELETED("1"),
    /**
     * 未被删除
     */
    NOT_DELETED("0");
    private String status;

    private CommonEnum(String status) {
        this.status = status;
    }

    public String value(){
        return this.status;
    }
}
