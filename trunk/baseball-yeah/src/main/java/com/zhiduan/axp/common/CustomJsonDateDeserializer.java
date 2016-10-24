/**
 * Copyright (c) 2016, 指端科技.
 */
package com.zhiduan.axp.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName: CustomJsonDateDeserializer
 * @Description: 日期转换类
 * @author: xulang
 * @date: 2016年08月23日 16:49
 */

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String date = jp.getText();
        return DateTimeUtils.getDateByString(date,"yyyy-MM-dd HH:mm:ss");
    }
}
