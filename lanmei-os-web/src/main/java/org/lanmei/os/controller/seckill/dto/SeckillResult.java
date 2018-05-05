package org.lanmei.os.controller.seckill.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;


public class SeckillResult<T>   {
	
	private boolean success;//判断请求是否成功
    
	///@JsonSerialize
    private T data;//存放数据
    
    private String error;//错误信息

    
	public SeckillResult(boolean success, T data) {
		super();
		this.success = success;
		this.data = data;
	}
	public SeckillResult(boolean success, String error) {
		super();
		this.success = success;
		this.error = error;
	}
	public SeckillResult(boolean success, T data, String error) {
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
