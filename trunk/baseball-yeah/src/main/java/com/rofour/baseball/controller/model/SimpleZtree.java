package com.rofour.baseball.controller.model;

import java.io.Serializable;

/**
 * @ClassName: Ztree
 * @Description: ztree Model
 * @author ZhangLei
 * @date 2016年5月11日 下午7:24:11
 *
 */

public class SimpleZtree implements Serializable {

	private static final long serialVersionUID = -1476850311068162763L;
	private String id;
	private String name;
	private String pId;
	private boolean isParent;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public SimpleZtree(){}

	public SimpleZtree(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
