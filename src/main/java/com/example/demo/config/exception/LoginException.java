package com.example.demo.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gyf
 * @date 2020/6/21 16:13
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "登录失败！")
public class LoginException extends RuntimeException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
