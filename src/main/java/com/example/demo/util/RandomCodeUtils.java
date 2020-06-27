package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author gyf
 * @date 2020/6/26 19:33
 */
public class RandomCodeUtils {
    private static final Logger logger = LoggerFactory.getLogger(RandomCodeUtils.class);
    public static String produceCode(Integer length){
        if(length==null || length<1){
            logger.error("错误的验证码长度:{}",length);
            throw new RuntimeException("错误的验证码长度");
        }
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            int i1 = random.nextInt(10);
            stringBuilder.append(i1);
        }
        logger.info("生成验证码:{}", stringBuilder.toString());
        return stringBuilder.toString();
    }
}
