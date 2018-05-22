package org.lanmei.common.enums;
/**
 * 
 * @author lgj
 *
 */
public enum CommodityState {
	
	ADD_CLASSIFICATION_SUCCESS,       //增添分类成功
	ADD_CLASSIFICATION_FAIL,          //增添分类失败
	
	RENAME_CLASSIFICATION_SUCCESS,    //重命名分类成功
	RENAME_CLASSIFICATION_FAIL,       //重命名分类失败
	
	DELETE_CLASSIFICATION_SUCCESS,    //删除分类成功
	DELETE_CLASSIFICATION_FAIL,       //删除分类失败
	
	ADD_COMMODITY_SUCCESS,            //添加商品成功
	ADD_COMMODITY_FAIL,               //添加商品失败
	ADD_COMMODITY_IMG_SUCCESS,        //添加商品图片成功
	ADD_COMMODITY_IMG_FAIL,           //添加商品图片失败
	
	COMMODITY_NAME_REPEAT ,            //商品名称重复
	COMMODITY_NAME_NOT_REPEAT,          //商品名称未重复
	
	COMMODITY_DELETE_SUCCESS ,        //商品删除成功
	COMMODITY_DELETE_FAIL,             //商品删除失败
	
	SET_SKU_ATTR_SUCCESS,              //设置销售属性成功
	SET_SKU_ATTR_FAIL,                 //设置销售属性失败
	DELETE_SKU_ATTR_SUCCESS,           //删除销售属性成功
	DELETE_SKU_ATTR_FAIL,              //删除销售属性失败
}
