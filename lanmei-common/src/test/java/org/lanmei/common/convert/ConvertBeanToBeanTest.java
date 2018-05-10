package org.lanmei.common.convert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ConvertBeanToBeanTest {

	@Test
	public void test() {
		
		From from =  new From("LIMING",11,34,22.3f);
		
		To to = new To();
		System.out.println("from : " + from);
		to = (To)ConvertBeanToBean.convert((Object)from, to);
		System.out.println("to : " + to);	 
		
		To to1 = new To();
		List<From> listFrom = new ArrayList();
		listFrom.add(from);
		listFrom.get(0);
		to1 = (To)ConvertBeanToBean.convert(listFrom.get(0), to1);
		System.out.println("to1 : " + to1);	
		
		List<To> listTo = new ArrayList();
		
	}
}


class To  {
	
	private  String name;
	private  int age;
	
	private  int  id;

	
	public To() {
		super();
	}

	public To(String name, int age, int id) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		
		return ("name = " + name
				+ "  age = " + age
				+ "  id = " + id
				);
	}
	
	
}
class FromParent{
	
	private int ttttt;

	public int getTtttt() {
		return ttttt;
	}

	public void setTtttt(int ttttt) {
		this.ttttt = ttttt;
	}	
}
class From extends FromParent  {
	
	private  String name;
	private  int age;	
	private  int  id;	
	private  float height;

	
	public From() {
	}
	public From(String name, int age, int id, float height) {
		super();
		this.name = name;
		this.age = age;
		this.id = id;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
public String toString() {
		
		return ("name = " + name
				+ "  age = " + age
				+ "  id = " + id
				+ "  height = " + height);
	}
	
}
