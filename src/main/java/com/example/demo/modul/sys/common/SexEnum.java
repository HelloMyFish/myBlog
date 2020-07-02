package com.example.demo.modul.sys.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gyf
 * @date 2020/6/25 13:40
 */
public enum  SexEnum {
    /**
     * 男
     */
    MAN("1050"),
    /**
     * 女
     */
    WOMEN("1100"),
    /**
     * 不愿透露
     */
    HIDE("1150");
    private String code;
    private static final Logger logger = LoggerFactory.getLogger(SexEnum.class);

    private SexEnum(String code) {
        this.code = code;
    }

    public String value(){
        return code;
    }
    public static SexEnum instanceByCode(String code){
        switch (code){
            case "1050": return MAN;
            case "1100": return WOMEN;
            case "1150": return HIDE;
            default:
                logger.info("code:{}", code);
        }
        return MAN;
    }

}
