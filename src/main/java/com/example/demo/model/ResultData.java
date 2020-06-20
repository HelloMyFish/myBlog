package com.example.demo.model;

/**
 * @author gyf
 * @date 2020/6/20 18:28
 */
public class ResultData<T> {
    private Boolean result;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
