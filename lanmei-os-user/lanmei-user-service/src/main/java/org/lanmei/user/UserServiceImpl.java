package org.lanmei.user;

import org.lanmei.common.BaseService;
import org.lanmei.common.UserStatus;
import org.lanmei.sms.SmsDemo;
import org.lanmei.user.dao.mapper.OsUserMapper;
import org.lanmei.user.dao.model.OsUser;
import org.lanmei.user.service.UserService;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


@Service
public class UserServiceImpl extends BaseService implements  UserService{

	private final static  int  PHONE_VALIDATE_CODE_LENGTH = 6;
	
	private final static Logger logger = LoggerFactory.getLogger("UserServiceImpl.class");	
	{
		logger.debug("UserServiceImpl create bean ......");
	}
	@Autowired
	private OsUserMapper userMapper;
	
	@Autowired
	private DruidDataSource dataSource; 
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	public OsUser getById(Long userId) {
		
		logger.debug("getByIding ...... userMapper = " +  userMapper + " userId = " + userId);
	
		System.out.println(dataSource.getUrl());
		System.out.println(dataSource.getDriverClassName());
		System.out.println(dataSource.getUsername());
		System.out.println(dataSource.getPassword());
		OsUser osuser = userMapper.selectById(userId);
		logger.debug("phone : " + osuser.getPhoneNum());
		return osuser;
	}

	public UserStatus checkPhoneNum(String phoneNum) {
		
		OsUser osuser = userMapper.selectByPhoneNum(phoneNum);
		
		if(osuser == null ) {
			logger.debug("您查找的手机号码" + phoneNum +"不存在");
			return UserStatus.PHONE_NUM_NOT_REGISTER;
		}
		else {
			logger.debug("您查找的手机号码" + phoneNum +"已经注册");
			System.out.println(osuser.toString());			
			return UserStatus.PHONE_NUM_REGISTER;
		}		
	}
	public static int getRandom() {
		
		return (int) (Math.random() * 999999);
	}
	
	public  void sendMsg(String phoneNum,String code ) {
		logger.debug("fdgsfgfd");
		
		try {
			logger.debug("+++++++++");
			SmsDemo.send();
		} catch (Exception e) {
			// TODO: handle exception
		}
		logger.debug("PhoneNum = " + phoneNum  + "  phoneValidateCode = " + code); 
		
	}
}


/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
class AliSms3 {

	private final static Logger logger = LoggerFactory.getLogger("AliSms.class");	
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIZ9vHdEp2HHeB";
    static final String accessKeySecret = "T8zg5xPgXNI1EIyYQdTOYRfckzBf9i";

    public static SendSmsResponse sendSms(String phoneNum ,String Code) throws ClientException {
       //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
      
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
     
       DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
       IAcsClient acsClient = new DefaultAcsClient(profile);
       /*
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("蓝莓");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_130916740");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //"{ \"code\":\"064958\"}"
        String template = "{ \"code\":\"" + Code + "\"}";
        request.setTemplateParam(template);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = null;
        try {
			sendSmsResponse = acsClient.getAcsResponse(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return sendSmsResponse;*/
        return null;
    }

    public static void userSendSms(String phoneNum ,String Code){
    	logger.debug("phoneNum = " + phoneNum  + "  Code = " + Code);
      //发短信
    	  /* 
    	    SendSmsResponse response = null;
      
        try {
			response = sendSms(phoneNum,Code);
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());
       
        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = null;
			try {
				querySendDetailsResponse = querySendDetails(response.getBizId());
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }*/

    }
    public String toString() {
    	
    	return "string ";
    }
}

