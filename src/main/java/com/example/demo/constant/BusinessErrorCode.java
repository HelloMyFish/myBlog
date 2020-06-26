package com.example.demo.constant;

import org.springframework.http.HttpStatus;

/**
 * @author gyf
 * @date 2020/6/25 15:47
 */
public enum BusinessErrorCode {
    /**
     * 404 Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
     */
    NOT_FOUND("CLOUD-404",
              String.format("哎呀，无法找到这个资源啦(%s)", HttpStatus.NOT_FOUND.getReasonPhrase())),
    /**
     * 405 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
     */
    METHOD_NOT_ALLOWED("CLOUD-405",
                       String.format("请换个姿势操作试试(%s)", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())),
    /**
     * 415 Unsupported Media Type
     */
    UNSUPPORTED_MEDIA_TYPE("CLOUD-415",
                           String.format("呀，不支持该媒体类型(%s)", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())),
    /**
     * 系统异常 500 服务器的内部错误
     */
    EXCEPTION("CLOUD-500", "服务器开小差，请稍后再试"),
    /**
     * 系统限流
     */
    TRAFFIC_LIMITING("CLOUD-429", "哎呀，网络拥挤请稍后再试试"),
    /**
     * 服务调用异常
     */
    API_GATEWAY_ERROR("API-9999", "网络繁忙，请稍后再试"),
    /**
     * 参数错误
     */
    PARAM_ERROR("CLOUD-100", "参数错误"),
    /**
     * 业务异常
     */
    BUSINESS_ERROR("CLOUD-400", "业务异常"),
    /**
     * rpc调用异常
     */
    RPC_ERROR("RPC-510", "呀，网络出问题啦！"),
    /**
     * not_login
     */
    NOT_LOGIN("RPC-501", "请先登录！");
    private String code;
    private String message;
    BusinessErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
