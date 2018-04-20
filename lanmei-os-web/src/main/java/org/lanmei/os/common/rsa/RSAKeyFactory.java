package org.lanmei.os.common.rsa;

import java.security.KeyPair;
import java.util.LinkedList;
import java.util.List;

/**
 * RSA公租与私钥工厂，用于保存并生产公钥与私钥信息
 * 
 * @author mazhaoyong
 */
public class RSAKeyFactory {

	private RSAKeyFactory() {
		this.generate();
	}

	private static RSAKeyFactory instance = null;
	private int size = 10;// 默认生成好的key数量

	/**
	 * 单例
	 * 
	 * @return
	 */
	public synchronized static RSAKeyFactory getInstance() {
		if (null == instance) {
			instance = new RSAKeyFactory();
		}
		return instance;
	}

	/**
	 * 当前缓存key
	 */
	private List<KeyPair> keypairs = new LinkedList<KeyPair>();
	private static final Object obj = new Object();

	/**
	 * 初始化，生成一些默认的key，用于等待使用
	 */
	private  void generate() {
		synchronized(obj){
			while (keypairs.size() < this.size) {
				try {
					keypairs.add(RSAUtilNew.generateKeyPair());
				} catch (Exception e) {
					e.printStackTrace();
					// TODO
					break;
				}
			}
		}
		
	}

	public synchronized  KeyPair getKeyPair() {
		while (this.keypairs.size() == 0) {
			generate();
		}
		KeyPair keys = this.keypairs.get(0);
		this.keypairs.remove(0);
		return keys;
	}

}
