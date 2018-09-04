<h1>蓝莓商城</h1>
基于Spring + Spring MVC + Mybatis 的商城系统
JSP版本
	<a href="http://blog.csdn.net/u011676300/">
			CSDN博客
	</a>
<br>

<h1>如何运行项目</h1>
<ol>
	<li>下载项目工程</li>
	<li>安装Java和Tomcat,并配置好环境</li>
	<li>安装MySQL数据库</li>
	<li>由于缓存使用Ｒedis进行持久化，所以需要安装Ｒedis,否则运行时会报错（代码500），网上安装教程很多，这里不进行说明</li>
	<li>Eclipse导入项目工程，没用过其他编译器，不知道其他编译器会不会导入有问题</li>
	<li>选择File -- Import -- Maven --Existing Maven Projects导入所有项目,只能一个一个地导入，按道理应该可以一次性导入的，不确定哪里配置的问题</li>
	<li>如果导入后报错，请先执行一遍Maven - Update Project</li>　
	<li>创建lanmei数据库，并执行数据库脚本文件．
		<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/mysql">
		数据库脚本文件位置</a>,也可以执行<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-example/mysqlBackup">
		该数据库脚本文件</a>，该文件为数据库备份的文件，包含所有的数据表和开发过程中的数据，选择最新日期的脚本文件执行．</li>
	<li>根据你的MySQL数据库配置，修改数据库配置文件
		<a href="https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-os-web/src/main/resources/mysqljdbc.properties">商城前台MySQL数据库配置文件</a>，
		<a href="https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-cms-web/src/main/resources/mysqljdbc.properties">商城后台管理系统MySQL数据库配置文件</a>
	</li>
	<li>根据你的Redis数据库配置，修改Redis配置,修改的是hostName和port.<a href="https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-cms-web/src/main/resources/spring/applicationContext-cache.xml">商城前台Redis数据库配置文件</a>，
		<a href="https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-cms-web/src/main/resources/spring/applicationContext-cache.xml">商城后台管理系统Redis数据库配置文件</a></li>
	<li>配置完毕</li>
	<li>运行Redis</li>
	<li>添加Tomcat服务器</li>
	<li>运行项目：lanmei-os-web(商城前台web项目)，lanmei-cms-web(商城后台web项目)</li>
	<li>后台管理项目帐号：201801，密码：zxcvbnm123</li>
	<li>前台项目需先进行注册后再登录</li>
	<li>由于没有使用延迟加载，所以启动时时间可能会过长，如果Ｔomcat出现启动超时，请适当增加超时时间后再重新启动</li>
	<li>注意：由于已经对数据库配置文件的密码进行加密处理，因此需要根据自己的mysql数据库用户名和密码进行加密处理，否则运行将会因为连接不上数据库而报错，参考这篇文章进行操作<a href="https://blog.csdn.net/u011676300/article/details/80740530">properties文件加密处理</a></li>
	<li>有任何问题可以到本博客下进行讨论，欢迎交流！
	<a href="https://blog.csdn.net/u011676300/article/details/80276014">蓝莓商城项目交流</a>
	</li>
</ol>
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
		缓存数据库：
		<a href="https://redis.io/">
			Redis 4.0.9
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
		多级列表树插件：<a href="http://www.treejs.cn/v3/api.php">zTree</a>
	</li>
	<li>
		富文本编辑器：<a href="http://www.wangeditor.com/">wangEditor</a>
	</li>
</ol>	

<h4>三.前后端交互</h4>
&nbsp&nbsp&nbsp&nbsp主要使用AJAX传输数据！
<h4>四.推荐学习书籍</h4>
<ol>
	<li>
		shiro学习：<<跟我学Shiro教程>>
	</li>
	<li>
		Mybatis　:　<<深入浅出MyBatis技术原理与实战　＋　　MyBatis从入门到精通>>
	</li>
	<li>
		Spring　:　<<精通Spring+4.x++企业应用开发实战>>
	</li>
	
</ol>

<h1>目录介绍</h1>
<h3>目录介绍</h3>
<ol>
	<li>doc</li>--开发日志
	<li>lanmei-admin</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-admin">
		后台管理 管理员相关模块(新增/登陆/权限管理等，权限未做好) 秒杀活动管理，商品管理等
		</a>	
	<li>lanmei-cms-web</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-cms-web">
		后台管理web项目</a>
	<li>lanmei-common</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-common">
		放置公共类</a>
	<li>lanmei-commodity</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-commodity">
		商品模块</a>
	<li>lanmei-example</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-example">
		用于测试某个技术点功能实现，目前有quartz定时任务，数据库备份操作</a>	
	<li>lanmei-os-user</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-os-user">
		用户模块，登录注册相关</a>	
	<li>lanmei-os-web</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-os-web">
		商城web项目</a>
	<li>lanmei-seckill</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-seckill">
		秒杀模块</a>
	<li>lanmei-sysaop</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-aop">
		系统Ａop处理</a>
	<li>lanmei-task-scheduling</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-task-%20scheduling">
		任务管理模块，使用quart，定时备份mysqls数据库</a>
	<li>mysql</li>
		--<a href="https://github.com/Mrlgj/LanMeiShop/tree/master/mysql">
		数据库脚本文件，创建数据表相关</a>
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
	<li>
		秒杀模块
		<ul type="disc">
			<li>后台管理</li>
			<li>已结束和未结束秒杀活动展示</li>
			<li>管理秒杀活动（新增，删除，修改）</li>
			<li>前台秒杀活动列表页面</li>			
			<li>秒杀页面，未开始则进行倒计时</li>
			<li>秒杀逻辑实现</li>
			<li>为防止软件提前刷，造成服务器压力，秒杀开始后服务端才暴露秒杀地址</li>
			<li>秒杀地址动态部分由seckillId和随机生成的数字组合，再经过MD5加密而成</li>
			<li>生成的MD5保存在Session中，执行秒杀操作时再进行比对</li>
			<li>使用spring的事务管理，执行秒杀过程出现异常则进行数据库回滚</li>
			<li>首次使用spring test 进行web层测试，提高效率。数据曾和服务曾未单独进行测试</li>
			<li>首次使用DTO进行层间数据传送</li>
		</ul>
	</li>
	<li>
		AOP模块
		<ul type="disc">
			<li>１．日志记录模块</li>
			<li>绑定的是切面注解，只有添加注解SyslogAnno才会监测</li>
			<li>使用AOP的前置通知和异常通知</li>	
			<li>记录访问类，描述，异常，时间等信息</li>
			<li>相关信息持久化到数据库</li>
			<li>并在进入的方法时打印请求路径</li>
			<li>２．方法执行时间监测模块</li>
			<li>绑定的是切面注解，只有添加注解TimeMeasurementAnno才会监测</li>
			<li>使用AOP的环绕通知</li>	
			<li>记录方法，平均时间，当前时间，运行次数</li>
			<li>相关信息持久化到数据库</li>
			<li>使用＠Order注解控制两个AOP执行顺序.＠Order的值越低，优先级越高，越先执行</li>
			<li>后续会增加后台实时查看和删除功能</li>
		</ul>
	</li>	
	<li>数据库配置文件密码加密
		<ul type="disc">
			<li>
				cms.web使用的是druid配置方式进行加密处理
			</li>
			<li>
				os.web使用的是Java编码方式进行加密处理
			</li>
		</ul>
	</li>
	<li>
		后台商品管理模块
		<ul type="disc">
			<li>商品类别管理：使用树形结构．新增，删除，修改</li>
			<li>商品管理．添加，删除，列表展示</li>
			<li>根据SKU概念设计商品库存表</li>
			<li>商品属性管理</li>
			<li>商品描述管理，使用富文本编辑器进行编辑</li>
		</ul>
	</li>
	<li>
		数据库定时备份
		<ul type="disc">
			<li>使用quartz实现定时任务</li>
			<li>使用Runtime模块执行备份指令</li>
			<li><a href="https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-os-web/src/main/resources/spring/applicationContext-quartz.xml">quartz bean配置</a></li>
			<li><a href="https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-task-%20scheduling/src/main/java/org/lanmei/backup/DatabaseBackup.java">备份实现</a></li>
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
	<li>
		<a  href="https://blog.csdn.net/u011676300/article/details/80313266">Java利用递归实现查找树的节点的所有子节点和所有的终结节点</a>
	</li>
	<li>
		<a  href="https://blog.csdn.net/u011676300/article/details/80345152">使用wangEditor实现富文本编辑（后端为Java）</a>
	</li>
	<li>
		<a href="https://blog.csdn.net/u011676300/article/details/80740530">properties文件加密处理</a>
	</li>
	
	
</ol>

