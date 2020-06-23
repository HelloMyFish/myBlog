package com.example.demo.config;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author gyf
 * @date 2020/6/21 17:04
 */
public class CustomLocalDateTimeEditor extends PropertyEditorSupport {
    private final DateTimeFormatter dateFormat;
    private final boolean allowEmpty;
    private final int exactDateLength;
    public CustomLocalDateTimeEditor(DateTimeFormatter dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = -1;
    }

    public CustomLocalDateTimeEditor(DateTimeFormatter dateFormat, boolean allowEmpty, int exactDateLength) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
        this.exactDateLength = exactDateLength;
    }
    @Override
    public void setAsText(@Nullable String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object)null);
        } else {
            if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
                throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
            }

            try {
                this.setValue(LocalDateTime.parse(text, dateFormat));
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date)this.getValue();
        if(value==null){
            return "";
        }else{
            LocalDateTime localDateTime = LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault());
            return localDateTime.format(dateFormat);
        }

//        return value != null ? this.dateFormat.format(value) : "";
    }
}
