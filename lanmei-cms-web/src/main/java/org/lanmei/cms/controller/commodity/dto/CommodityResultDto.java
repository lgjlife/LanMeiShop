package org.lanmei.cms.controller.commodity.dto;

public class CommodityResultDto<T> {
  /* private  Integer  id; //本级ＩＤ 
   private 	Integer  pid; //父级ＩＤ
   private  String  name; //名称
*/
	private boolean success;//判断请求是否成功
    
    private T data;//存放数据
    
    private String error;//错误信息
}