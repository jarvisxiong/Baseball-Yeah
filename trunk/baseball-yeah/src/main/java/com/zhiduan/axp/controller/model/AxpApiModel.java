package com.zhiduan.axp.controller.model;

    
public class AxpApiModel<T> {
	/**
	 * 0 success,-1 fail
	 */
	private int success;
	/**
	 * 错误编码
	 */
	private String code;
	/**
	 * 消息提示
	 */
	private String message;
	/**
	 * 数据
	 */
	private T data;


	public AxpApiModel() {
	}
	public AxpApiModel(int success,String code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}
	public AxpApiModel( int success,String code, String message, T data) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}
	
	@Override
	public String toString() {
		return "AxpApiModel [success=" + success + ", code=" + code + ", message=" + message + ", data=" + data + "]";
	}
}
