package org.lanmei.commodity.dto;

import org.lanmei.common.enums.CommodityState;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

///@JsonSerialize
public class ClassificationDto {
	//@JsonSerialize
	private boolean success;//判断请求是否成功
	
	//@JsonSerialize
	private  CommodityState state;
	//@JsonSerialize
	private  String msg;
	
	public ClassificationDto(boolean success,CommodityState state) {
		super();
		this.success = success;
		this.state = state;
	}

	public ClassificationDto(boolean success,CommodityState state, String msg) {
		super();
		this.success = success;
		this.state = state;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public CommodityState getState() {
		return state;
	}

	public void setState(CommodityState state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}	
	
	
}
