package org.lanmei.seckill.dto;

import org.lanmei.seckill.state.SeckillState;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

@JsonSerialize
public class ExecutionDto    {
	
	@JsonSerialize
	private int seckillId;
	
	@JsonSerialize
	private SeckillState state;
	
	@JsonSerialize
	private String seckill_name;

	public ExecutionDto(int seckillId, SeckillState state) {
		super();
		this.seckillId = seckillId;
		this.state = state;
	}
	
	public ExecutionDto(int seckillId, SeckillState state, String seckill_name) {
		super();
		this.seckillId = seckillId;
		this.state = state;
		this.seckill_name = seckill_name;
	}
	
	@Override
	public String toString() {
		return ("seckillId = " + seckillId
				+ "   state = " + state
				+ "   seckill_name = "  + seckill_name);
	}
	
}
