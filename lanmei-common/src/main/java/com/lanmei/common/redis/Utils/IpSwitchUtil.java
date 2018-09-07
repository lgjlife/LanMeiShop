package com.lanmei.common.redis.Utils;


/**
 * @program: demo
 * @description: IP转换工具
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-30 04:11
 **/
public class IpSwitchUtil {

    /** 
     * @description: Ipv4 地址转换成　Long 数据
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/30/18 
    */ 
    public static Long Ipv4ToLong(String ipv4Adress ){

        Long result = 0L;

        String[] address =  ipv4Adress.split("\\.");

        int index = 0;
        while(index < address.length){

            result |= Long.parseLong(address[index]) << ((3-index) * 8);
            index++;
        }

        return  result;
    }

    /** 
     * @description:  将long 型的　ｉpv4地址转换为Ｓtring类型
     * @param:  
     * @return:  
     * @author: Mr.lgj 
     * @date: 8/30/18 
    */ 
    public  static String LongToIpv4(Long ipv4Adress){



        String result = ((ipv4Adress >> 24) & 0xFF) + "."
                        + ((ipv4Adress >> 16) & 0XFF) + "."
                        + ((ipv4Adress >> 8) & 0XFF) + "."
                        + ((ipv4Adress >> 0) & 0XFF);

        return result;

    }



    public static  void main(String[] args){

        String ip = "192.168.123.1";

        Long ipLong = IpSwitchUtil.Ipv4ToLong(ip);
        System.out.println("ipLong is : " + ipLong);
        String newIp = IpSwitchUtil.LongToIpv4(ipLong);
        System.out.println("new Ip is : " + newIp);
        System.out.println("old Ip  is : " + ip);

        String str = "12..34";
        String[] address =  str.split("\\.");

        for(String s : address){
            System.out.println(s);
        }

    }
}
