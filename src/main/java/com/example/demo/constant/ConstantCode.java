package com.example.demo.constant;

/**
 * @author gyf
 * @date 2020/6/20 23:24
 */
public enum ConstantCode {
    /**
     * 返回操作码
     */
    SUCCESS_CODE(50100L);
    private Long code;

    ConstantCode(Long code) {
        this.code = code;
    }
    public Long value(){
        return this.code;
    }
}
