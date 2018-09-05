package com.lanmei.commodity.utils;

import com.lanmei.commodity.dao.model.CommodityClassification;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TreeUtils {
	
	public static List<CommodityClassification> getAllChildNode(List<CommodityClassification>  CommodityClassifications,
																List<CommodityClassification> childNode , int pid){
		List<CommodityClassification> lastChildNode = new  ArrayList<CommodityClassification>();
		
	    getChildNode(CommodityClassifications,childNode,lastChildNode,pid);

		return removeStringListDupli(childNode);
	}
	
	public  static List<CommodityClassification> getLastChildNode(List<CommodityClassification>  CommodityClassifications,
			List<CommodityClassification> lastChildNode ,int pid){
		List<CommodityClassification> childNode = new  ArrayList<CommodityClassification>();
        getChildNode(CommodityClassifications,childNode,lastChildNode,pid);
		
	
		
		return removeStringListDupli(lastChildNode);
	}
	public static List<CommodityClassification> getChildNode(List<CommodityClassification>  CommodityClassifications ,
			List<CommodityClassification> childNode,
			List<CommodityClassification> lastChildNode,
			int pid) {
		List<CommodityClassification> tempCommodityClassification =  new  ArrayList<CommodityClassification>();
		List<CommodityClassification> tempCommodityClassification1 =  new  ArrayList<CommodityClassification>();
		for(CommodityClassification node : CommodityClassifications) {			
			if(node.getPid() == pid) {
				//说明存在子节点
			
				tempCommodityClassification1 = getChildNode(CommodityClassifications,childNode,lastChildNode,node.getId());
				if(tempCommodityClassification1.isEmpty()) {
					lastChildNode.add(node);
					/*System.out.println("当前节点为最后子节点" + node.getId());
					for(CommodityClassification node1 : lastChildNode) {
						System.out.println("-----------" + node1.getId() + "    childNodeCount = " + (childNodeCount++));
					}*/
				}
				childNode.add(node);
				tempCommodityClassification.add(node);
			//	System.out.println("当前节点存在子节点" + node.getId());
			}			
		}	
		
		return tempCommodityClassification; 
	}
	
	public static List<CommodityClassification> removeStringListDupli(List<CommodityClassification> stringList) {
	    Set<CommodityClassification> set = new LinkedHashSet<>();
	    set.addAll(stringList);

	    stringList.clear();

	    stringList.addAll(set);
	    return stringList;
	}
}
