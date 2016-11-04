package com.rofour.baseball.controller.model;

import java.io.Serializable;

/**
* @ClassName: Ztree
* @Description: ztree Model
* @author ZhangLei
* @date 2016年5月11日 下午7:24:11 
*
*/
    
public class Ztree implements Serializable {
	    
	private static final long serialVersionUID = 2343245034034051378L;
	
	Long id;
	String idStr;
	String title;
	String name;
	String checked;
	boolean open=false;
	boolean isHidden;
	Long pId;
	String action;
	
	
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
	public Ztree() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
		setIdStr(String.valueOf(id));
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Ztree(Long id, String title, String name, String checked,
			boolean open, boolean isHidden, Long pId, String action) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;
		this.checked = checked;
		this.open = open;
		this.isHidden = isHidden;
		this.pId = pId;
		this.action = action;
	}
	
	public Ztree(String idStr, String title, String name, String checked,
			boolean open, boolean isHidden, Long pId, String action) {
		super();
		this.idStr = idStr;
		this.title = title;
		this.name = name;
		this.checked = checked;
		this.open = open;
		this.isHidden = isHidden;
		this.pId = pId;
		this.action = action;
	}
}
