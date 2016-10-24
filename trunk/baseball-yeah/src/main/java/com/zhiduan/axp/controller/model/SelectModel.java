package com.zhiduan.axp.controller.model;

/**
* @ClassName: SelectModel
* @Description: 下拉返回对象
* @author cy
* @date
*
*/
public class SelectModel {
	private String id;

	private String text;


	public java.util.List<SelectModel> getChildren() {
		return children;
	}

	public void setChildren(java.util.List<SelectModel> children) {
		this.children = children;
	}

	private java.util.List<SelectModel> children;

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
