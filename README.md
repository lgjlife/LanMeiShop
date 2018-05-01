<h1>蓝莓商城</h1>
基于Spring + Spring MVC + Mybatis 的商城系统

<br>
	<a href="http://blog.csdn.net/u011676300/">
			CSDN博客
	</a>
<br>


<h1>技术选型</h1>

<h3>一.开发环境</h4>
<ol>
	<li>
		IDE：
		<a href="https://www.eclipse.org/downloads/eclipse-packages/">
		    Eclipse 4.7.2
		</a>		
	</li>
	<li>
		项目构建工具：
		<a href="https://maven.apache.org/">
			Maven
		</a>
	</li>
	<li>
		数据库：
		<a href="https://www.mysql.com/">
			Mysql 5.7.12
		</a>
	</li>
	<li>
		版本管理：
		<a href="https://github.com/">
			github
		</a>
	</li>
</ol>


<h3>二.后端技术</h4>
<ol>
	<li>
		JAVA版本：9.0.1	
	</li>
	<li>
		核心框架：
		<a href="https://projects.spring.io/spring-framework/">	
			Spring Framework 5.0.4.RELEASE
		</a>
	</li>
	<li>
		持久层框架：
		<a href="http://www.mybatis.org/mybatis-3/">	
			Mybatis 3.3.0
		</a>
	</li>
	<li>
		视图框架：
		<a href="https://projects.spring.io/spring-framework/">	
			Spring MVC 5.0.4.RELEASE
		</a>
	</li>
	<li>
		Mybatis 代码生成器(插件方式):
		<a href="http://www.mybatis.org/generator/">	
			mybatis-generator 1.3.6
		</a>
	</li>
	<li>
		日志管理：
		<a href="https://www.slf4j.org/">slf4j 1.7.5</a>+
		<a href="https://logging.apache.org/log4j/2.x/manual/configuration.html/">log4j2
		</a>
	</li>
	<li>
		Mybatis 分页插件: 
		<a href="https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md">
			pagehelper 6.1.2
		</a>
	</li>
	<li>
		短信验证服务: 
		<a href="https://www.aliyun.com/product/sms?spm=5176.8195934.765261.387.5c464183yLVTAN">
			阿里云短信服务
		</a>
	</li>
	<li>
		任务调度: 
		<a href="http://www.quartz-scheduler.org/">
			quartz
		</a>
	</li>
	<li>
		数据库连接池：
		<a href="http://druid.io/">
			druid
		</a>
	</li>
	<li>
		安全管理框架：
		<a href="http://shiro.apache.org/">
			shiro
		</a>
	</li>
	<li>
		验证码库：
		<a >
			kaptcha
		</a>
	</li>
</ol>

<h4>三.前端技术</h4>

<ol>
	<li>
		JS框架：Jquery	
	</li>
	<li>
		CSS框架：bootstrap
	</li>
	<li>
		时间选择控件：<a href="https://github.com/AuspeXeu/bootstrap-datetimepicker">bootstrap-datetimepicker</a>
	</li>
</ol>	

<h4>三.前后端交互</h4>
&nbsp&nbsp&nbsp&nbsp主要使用AJAX传输数据！

<h1>目录介绍</h1>
<h3>目录介绍</h3>
<ol>
	<li>lanmei-os-web</li>--商城web项目
	<li>lanmei-os-user</li>--用户模块，登录注册相关
	<li>lanmei-cms-web</li>--后台管理web项目
	<li>lanmei-admin</li>--后台管理 管理员相关模块(新增/登陆/权限管理等)
	<li>doc</li>--开发日志			
	<li>mysql</li>--数据库文件，创建数据表相关
</ol>
<h1>完成模块</h1>
<ol>
	<li>
		用户注册模块
		<ul type="disc">
			<li>注册界面。</li>
			<li>密码强度校验：正则表达式。</li>
			<li>手机短信验证码：使用阿里云短信</li>
			<li>密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密</li>
			<li>密码加密保存：MD5算法加密</li>
		</ul>
	</li>
	<li>
		用户登录模块
		<ul type="disc">
			<li>登录界面</li>
			<li>登录方式选择：手机/邮箱/用户名</li>
			<li>动态验证码:kaptcha</li>			
			<li>密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密</li>
			<li>shiro登录验证</li>
			<li>帐号登录日志管理</li>
			<li>redis持久化session,还未实现关闭浏览器还能保持登录状态功能</li>
		</ul>
	</li>
	<li>
		后台新增管理员模块
		<ul type="disc">
			<li>新增管理员界面。</li>
			<li>姓名/工号/默认密码/生成动态邀请码/邮件告知新增的管理员</li>
			<li>邮件服务使用Spring mail库</li>
			<li>密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密</li>
			<li>密码加密保存：MD5算法加密</li>
		</ul>
	</li>
	<li>
		后台管理员登陆模块
		<ul type="disc">
			<li>登录界面</li>
			<li>登录方式选择：工号</li>
			<li>其他输入：邀请码/验证码/密码</li>
			<li>动态验证码:kaptcha</li>			
			<li>密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密</li>
			<li>shiro登录验证</li>
			<li>帐号登录日志管理</li>
			<li>redis持久化session,还未实现关闭浏览器还能保持登录状态功能</li>
		</ul>
	</li>
</ol>
<h1>个人博客相关文章</h1>
<ol>
	<li>
		<a  href="https://blog.csdn.net/u011676300/article/details/79946220">[蓝莓商城]Js中使用正则表达式判断密码强度</a>
	</li>
	<li>
		<a  href="https://blog.csdn.net/u011676300/article/details/79855398">slf4j + Log4j2 日志记录框架配置和使用详解</a>
	</li>
	<li>
		<a  href="https://blog.csdn.net/u011676300/article/details/79950339">js中倒计时实现</a>
	</li>
	<li>
		<a  href="https://blog.csdn.net/u011676300/article/details/80086709">[蓝莓商城]使用Spring的mail API发送邮件</a>
	</li>
</ol>

