package org.lanmei.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class TreeTest {

	List<TreeNode> childNode = new  ArrayList<TreeNode>();
	List<TreeNode> lastChildNode = new  ArrayList<TreeNode>();
	@Test
	public void test() {
		
		List<TreeNode>  treeNodes = new  ArrayList<TreeNode>();
		
		treeNodes.add(new TreeNode(1,0));
		treeNodes.add(new TreeNode(2,0));
		treeNodes.add(new TreeNode(3,0));
		
		treeNodes.add(new TreeNode(4,1));
		treeNodes.add(new TreeNode(5,1));
		
		treeNodes.add(new TreeNode(6,2));
		treeNodes.add(new TreeNode(7,2));
		
		treeNodes.add(new TreeNode(8,3));
		treeNodes.add(new TreeNode(9,3));
		
		treeNodes.add(new TreeNode(15,8));
		treeNodes.add(new TreeNode(10,9));
		treeNodes.add(new TreeNode(11,10));
		treeNodes.add(new TreeNode(12,10));
		treeNodes.add(new TreeNode(13,10));
		treeNodes.add(new TreeNode(14,10));
		
		int rootId = 3;
		System.out.println("树的根节点为　rootId　=　" + rootId);
		treeMenuList(treeNodes,3);
		
		for(TreeNode node : childNode) {
			System.out.println(" childNode id = " + node.getId());
		}
		
		System.out.println("树的根节点为　rootId　=　" + rootId);
		childNode = removeStringListDupli(childNode);
		System.out.println("去除重复值后");
		for(TreeNode node : childNode) {			
			System.out.println("childNode id = " + node.getId());
		}
		
		for(TreeNode node : lastChildNode) {
			System.out.println("lastChildNode id = " + node.getId());
		}
		
		lastChildNode = removeStringListDupli(lastChildNode);
		System.out.println("去除重复值后");
		for(TreeNode node : lastChildNode) {			
			System.out.println("lastChildNode id = " + node.getId());
		}
		
	}
	
	public List<TreeNode> treeMenuList(List<TreeNode>  treeNodes ,int pid) {
		List<TreeNode> tempTreeNode =  new  ArrayList<TreeNode>();
		List<TreeNode> tempTreeNode1 =  new  ArrayList<TreeNode>();
		for(TreeNode node : treeNodes) {			
			if(node.getPid() == pid) {
				//说明存在子节点
			
				tempTreeNode1 = treeMenuList(treeNodes,node.getId());
				if(tempTreeNode1.isEmpty()) {
					lastChildNode.add(node);
				}
				childNode.add(node);
				tempTreeNode.add(node);
				System.out.println("当前节点存在子节点");
			}
			
		}
		
		return tempTreeNode; 
	}
	
	public List<TreeNode> removeStringListDupli(List<TreeNode> stringList) {
	    Set<TreeNode> set = new LinkedHashSet<>();
	    set.addAll(stringList);

	    stringList.clear();

	    stringList.addAll(set);
	    return stringList;
	}
	
	
}
/**
 * 1:  1 - 0 
 */

/**
 *  0 --- 1
 *  
 *  0-----1------4
 *        +------5   
 *  +-----2------6
 *  	  +------7
 *  +-----3------8
 *        +------9
 * @author lgj
 *
 */
class TreeNode{
	
	int id;
	int pid;
	String name;
	
	
	
	public TreeNode() {
		super();
	}
	public TreeNode(int id, int pid) {
		super();
		this.id = id;
		this.pid = pid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}