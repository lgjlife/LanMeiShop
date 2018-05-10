package org.lanmei.commodity.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class TreeNodeDto {

	@JsonSerialize
	private  Integer  id; //本级ＩＤ 
	@JsonSerialize
    private  Integer  pid; //父级ＩＤ
	@JsonSerialize
	private  String  name; //名称
	@JsonSerialize
	private String isParent;
	
	public TreeNodeDto() {
		super();
	}     
	
	public TreeNodeDto(Integer id, Integer pid, String name,String isParent) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getIsparent() {
		return isParent;
	}

	public void setIsparent(String isParent) {
		this.isParent = isParent;
	}



	
	
	
	
}
