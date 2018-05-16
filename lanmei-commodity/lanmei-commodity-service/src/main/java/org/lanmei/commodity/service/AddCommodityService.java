package org.lanmei.commodity.service;

import java.util.List;
import java.util.Map;

import org.lanmei.commodity.dto.ImgResultDto;
import org.lanmei.common.enums.CommodityState;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface AddCommodityService {

	/**
	 * 增加商品
	 * @param map
	 */
	CommodityState addCommodity(Map<String,Object> map);
	/**
	 * 上传商品图片
	 * @param files
	 */
	CommodityState upLoadImg(CommonsMultipartFile[] files,
			String UploadAbsolutePath,
			String UploadRelativePath,
			int commodityId);
	/**
	 * 上传商品图片
	 * @param files
	 */
	ImgResultDto upLoadEditorImg(List<MultipartFile> list,
			String UploadAbsolutePath,
			String UploadRelativePath,
			int commodityId);
	/**
	 * 检查是否存在同名商品　
	 * @param map
	 * @return
	 */
	CommodityState checkName(Map<String,Object> map);
}
