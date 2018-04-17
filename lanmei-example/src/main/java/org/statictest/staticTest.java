package org.statictest;

public class staticTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print1("sda","adsf");
	}
	
	
public static void print1(String val1,String val2) {
		
		classtwo.print(val1, val2);
	}

}


class classtwo{
	
	public static void print(String val1,String val2) {
		
		System.out.println( val1 + val2);
	}
}