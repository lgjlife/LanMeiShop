package com.lanmei.common.utils.session;

/**
 * @program: com-lanmei-parent
 * @description: Session key工具类
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-09-07 03:20
 **/
public class SessionKeyUtil {

    /**
     * @description: 当前登陆的OsUser 对象
     * @see  org.lanmei.user.dao.model.OsUser
    */
    public static final String currentLoginUser = "currentLoginUser";
    /**
     * @description: 当前登陆的OsUser 对象
     * @see  org.lanmei.user.dao.model.OsUser
     */
    public static final String currentRegisterUser = "currentRegisterUser";
    /**
     * @description: 当前登陆的CmsAdmin 对象
     * @see   com.lanmei.admin.dao.model.CmsAdmin
     */
    public static final String currentLoginAdmin = "currentLoginAdmin";

    /**
     * @description: RSAkeyPair
     * @see   com.lanmei.admin.dao.model.CmsAdmin
     */
    public static final String RSAkeyPair = "RSAkeyPair";


    /**
     * @description: 登录验证码
     */
    public static  final String loginVerificationCode = "loginVerificationCode";
}
