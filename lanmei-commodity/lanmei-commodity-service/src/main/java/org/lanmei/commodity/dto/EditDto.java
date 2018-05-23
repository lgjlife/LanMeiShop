package org.lanmei.commodity.dto;

import org.lanmei.common.enums.CommodityState;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

///@JsonSerialize
public class EditDto<E> {
	//@JsonSerialize
	private boolean success;//判断请求是否成功
	
	//@JsonSerialize
	private  E data;
	//@JsonSerialize
	private  String msg;
	
	
	
	public EditDto() {
		super();
	}


	public EditDto(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	
	
	
	public EditDto(boolean success, E data, String msg) {
		super();
		this.success = success;
		this.data = data;
		this.msg = msg;
	}


	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

	
	
}
