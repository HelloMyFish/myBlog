package com.example.demo.config;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;

import java.beans.PropertyEditorSupport;
import java.util.Collections;

/**
 * @author gyf
 * @date 2020/6/21 17:38
 */
public class CustomString2ListEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(@Nullable String text) throws IllegalArgumentException {
        if(StringUtils.isBlank(text)){
            this.setValue(Collections.EMPTY_LIST);
        }else{
            String[] split = StringUtils.split(text, ",");
            this.setValue(Lists.newArrayList(split));
        }
    }

}
