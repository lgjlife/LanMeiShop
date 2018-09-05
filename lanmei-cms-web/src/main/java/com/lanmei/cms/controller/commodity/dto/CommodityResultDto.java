package com.lanmei.cms.controller.commodity.dto;

public class CommodityResultDto<T> {
	
	private boolean success;//判断请求是否成功
    
    private T data;//存放数据
    
    private String error;//错误信息

    
	public CommodityResultDto(boolean success) {
		super();
		this.success = success;
	}
	
	public CommodityResultDto(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}

	public CommodityResultDto(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}

	public CommodityResultDto(boolean success, T data, String error) {
		super();
		this.success = success;
		this.data = data;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
    
	
    
}