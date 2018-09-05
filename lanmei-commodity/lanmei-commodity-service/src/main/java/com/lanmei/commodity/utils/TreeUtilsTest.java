package com.lanmei.commodity.utils;

import com.lanmei.commodity.dao.model.CommodityClassification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeUtilsTest {

	@Test
	public void test() {
		List<CommodityClassification> lastChildNode = new  ArrayList<CommodityClassification>();
		List<CommodityClassification> childNode = new  ArrayList<CommodityClassification>();
		
		List<CommodityClassification>  CommodityClassifications = new  ArrayList<CommodityClassification>();
		
		CommodityClassifications.add(new CommodityClassification(1,0));
		CommodityClassifications.add(new CommodityClassification(2,0));
		CommodityClassifications.add(new CommodityClassification(3,0));
		
		CommodityClassifications.add(new CommodityClassification(4,1));
		CommodityClassifications.add(new CommodityClassification(5,1));
		
		CommodityClassifications.add(new CommodityClassification(6,2));
		CommodityClassifications.add(new CommodityClassification(7,2));
		
		CommodityClassifications.add(new CommodityClassification(8,3));
		CommodityClassifications.add(new CommodityClassification(9,3));
		
		CommodityClassifications.add(new CommodityClassification(15,8));
		CommodityClassifications.add(new CommodityClassification(10,9));
		CommodityClassifications.add(new CommodityClassification(11,10));
		CommodityClassifications.add(new CommodityClassification(12,10));
		CommodityClassifications.add(new CommodityClassification(13,10));
		CommodityClassifications.add(new CommodityClassification(14,10));
		
		int rootId = 0;
		System.out.println("树的根节点为　rootId　=　" + rootId);
		childNode = TreeUtils.getAllChildNode(CommodityClassifications, childNode, rootId);
		
	
		System.out.println("去除重复值后");
		for(CommodityClassification node : childNode) {			
			System.out.println("childNode id = " + node.getId());
		}
		
		lastChildNode = TreeUtils.getLastChildNode(CommodityClassifications, lastChildNode, rootId);
		
		System.out.println("去除重复值后");
		for(CommodityClassification node : lastChildNode) {			
			System.out.println("lastChildNode id = " + node.getId());
		}
		
	}

}