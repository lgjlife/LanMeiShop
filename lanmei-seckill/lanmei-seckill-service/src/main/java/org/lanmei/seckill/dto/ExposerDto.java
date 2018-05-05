package org.lanmei.seckill.dto;
/**
 * 
 * @author lgj
 * @Date 2018/5/4
 */
public class ExposerDto {

	//是否开启秒杀
    private boolean exposed;
    
    //加密md5
    private String md5;
    
    //id
    private long seckillId;
    
    //系统当前时间（毫秒）
    private long now;
    
    //秒杀开启时间
    private long start;
    
    //秒杀结束时间
    private long end;

    
	public ExposerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExposerDto(boolean exposed, long seckillId) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
	}
	
	

	public ExposerDto(boolean exposed, String md5, long seckillId) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}
	
	

	public ExposerDto(boolean exposed, long seckillId, long now, long start, long end) {
		super();
		this.exposed = exposed;
		this.seckillId = seckillId;
		this.now = now;
		this.start = start;
		this.end = end;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
    
    
}
