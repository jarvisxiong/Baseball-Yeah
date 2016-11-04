package com.rofour.baseball.controller.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Tree  implements Serializable {
    private static final long serialVersionUID = -1476220311068162763L;
    //key
    private Long id;
    //value
    private String text;
    //状态
    private String state;
    //子节点
    private ArrayList<Tree> children;

    public String parentId ;

    public Boolean isChecked ;

    public Map<String, String> attributes ;

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tree> children) {
        this.children = children;
    }


}
