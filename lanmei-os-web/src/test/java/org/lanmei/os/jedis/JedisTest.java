package org.lanmei.os.jedis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

import redis.clients.jedis.Jedis;

public class JedisTest {
	
	private final static String HOST="127.0.0.1";
	private final static int PORT=6379;
	
	
	@Test
	public void jedisTest() {
		
		Jedis jedis = new Jedis(HOST,PORT);
		
		/*jedis.set("", value)*/
		
		System.out.println("name = " + jedis.get("name"));
		System.out.println("set = " + jedis.set("name","data"));
	}
	
	@Test
	public void jedisTemplateTest() {
		
		System.out.println("jedisTemplateTest.....");
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext-cache.xml");
		
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ac.getBean("redisTemplate");
		
		User user1 = new User("aaa",1);
		User user2 = new User("bbb",2);
		User user3 = new User("ccc",3);
		User user4 = new User("ddd",4);
		User user5 = new User("eee",5);
		User user6 = new User("fff",6);
		

		redisTemplate.opsForValue().set("test_user1", user1);
		redisTemplate.opsForValue().set("test_user2", user2);
		redisTemplate.opsForValue().set("test_user3", user3);
		redisTemplate.opsForValue().set("test_user4", user4);
		redisTemplate.opsForValue().set("test_user5", user5);
		redisTemplate.opsForValue().set("test_user6", user6);
		
		//User newUser = (User)redisTemplate.opsForValue().get("user");
		
		Set<String> userSet = new HashSet<String>();;
		
		try {
			userSet = (Set<String>)redisTemplate.keys("test"+ "*");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("userSet size = " + userSet.size());
		Iterator<String> interator = userSet.iterator();
		
		while(interator.hasNext()) {
			System.out.println("" + interator.next() );
		}
		
		
		
	}
	

}
class User extends SerializableSerializer{
	
	private String name;
	private  Integer age;
	
	public User(String name,Integer age ) {
		this.name = name;
		this.age =  age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	public String toString() {
		
		return ("name = " + name + " age = " + age);
	}
		
	
}