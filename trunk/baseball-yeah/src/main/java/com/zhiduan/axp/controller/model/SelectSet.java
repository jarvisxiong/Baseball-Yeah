package com.zhiduan.axp.controller.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-17.
 */
public class SelectSet implements Serializable {
    private static final long serialVersionUID = -3193326252110672751L;

    private String id;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
