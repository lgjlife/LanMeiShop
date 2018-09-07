package com.lanmei.user.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class alisms {

	private final static Logger logger = LoggerFactory.getLogger("smsClass.class");
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIZ9vHdEp2HHeB";
    static final String accessKeySecret = "T8zg5xPgXNI1EIyYQdTOYRfckzBf9i";
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 logger.debug("----main----");
		getAcsResponse("13925716752","123456");
	}
	
	

    public static String getAcsResponse(String toPhone, String code) {
        SingleSendSmsResponse httpResponse = new SingleSendSmsResponse();
        String result = "";
        try {
        	 logger.debug("----1----");
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms","sms.aliyuncs.com");
          //  IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
          ///  DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

            IAcsClient client = new DefaultAcsClient(profile);
            logger.debug("----2----");
            SingleSendSmsRequest request = new SingleSendSmsRequest();
            request.setSignName("蓝莓");// 控制台创建的签名名称
            request.setTemplateCode("SMS_130916740");// 控制台创建的模板CODE
            
           
            
            JSONObject jsonCode = new JSONObject();
            jsonCode.accumulate("code", code);
            request.setParamString(jsonCode.toString());
            request.setRecNum("13925716752");
            request.setAcceptFormat(FormatType.JSON); // 格式为json
            logger.debug("----3----");
            httpResponse = client.getAcsResponse(request);
            logger.info("send msgcode response: {} ", httpResponse.toString());
        } catch (ClientException e) {
            e.printStackTrace();// //InvalidSignName.Malformed : The specified// sign name is wrongly formed.
            logger.error("send msgcode error :{}", e.getMessage());
            result = e.getErrCode();
        }
        return result;
    }

}
