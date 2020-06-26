package com.example.demo.model;

import com.example.demo.constant.BusinessErrorCode;

/**
 * @author gyf
 * @date 2020/6/20 18:28
 */
public class ResultData<T> {
    /**
     * 是否成功
     */
    private Boolean succ;

    /**
     * 服务器当前时间戳
     */
    private Long ts = System.currentTimeMillis();

    /**
     * 成功数据
     */
    private T data;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误描述
     */
    private String msg;



    public static <T> ResultData<T> ofSuccess(){
        ResultData<T> resultData = new ResultData();
        resultData.succ = Boolean.TRUE;
        return resultData;
    }

    public static <T> ResultData<T> ofSuccess(T object){
        ResultData<T> resultData = new ResultData();
        resultData.data = object;
        resultData.succ = Boolean.TRUE;
        return resultData;
    }

    public static <T> ResultData<T> ofFail(String code, String msg) {
        ResultData<T> result = new ResultData();
        result.succ = false;
        result.code = code;
        result.msg = msg;
        return result;
    }

    public static <T> ResultData<T> ofFail(String code, String msg, T data) {
        ResultData<T> result = new ResultData();
        result.succ = false;
        result.code = code;
        result.msg = msg;
        result.setData(data);
        return result;
    }

    public static <T> ResultData<T> ofFail(BusinessErrorCode resultEnum) {
        ResultData<T> result = new ResultData();
        result.succ = false;
        result.code = resultEnum.getCode();
        result.msg = resultEnum.getMessage();
        return result;
    }


    public Boolean getSucc() {
        return succ;
    }

    public void setSucc(Boolean succ) {
        this.succ = succ;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "succ=" + succ +
                ", ts=" + ts +
                ", data=" + data +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
