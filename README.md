

#蓝莓商城
基于Spring + Spring MVC + Mybatis 的商城系统

#版本说明
* Project-JSP分支:前端使用JSP,编译器使用Eclipse;
* master分支:前端使用thmeleaf模板，编译器使用IDEA;

#我的博客

[CSDN博客](http://blog.csdn.net/u011676300/)

#如何运行项目
* 下载项目工程,默认为master分支
* 安装Java和Tomcat,并配置好环境
* 安装MySQL数据库
* 由于缓存使用Redis进行持久化，所以需要安装Redis,否则运行时会报错（代码500），网上安装教程很多，这里不进行说明
* IDEA导入项目工程
* 创建lanmei数据库，配置IDEA连接至lanmei数据库后，执行数据库脚本文件[数据库脚本文件](https://github.com/Mrlgj/LanMeiShop/tree/master/mysql)
* 根据你的MySQL数据库配置，修改数据库配置文件。
* [商城前台MySQL数据库配置文件](https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-os-web/src/main/resources/mysqljdbc.properties)

* [商城后台管理系统MySQL数据库配置文件](https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-cms-web/src/main/resources/mysqljdbc.properties)

* 根据你的Redis数据库配置，修改Redis配置,修改的是hostName和port.
* [商城前台Redis数据库配置文件](https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-cms-web/src/main/resources/spring/applicationContext-cache.xml)
* [商城后台管理系统Redis数据库配置文件](https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-cms-web/src/main/resources/spring/applicationContext-cache.xml)
* 配置IDEA中的Tomcat(本地路径，端口号，Application Context ),我设置的是port:8080,Application Context:lanmei-os,运行后的访问地址为http：//localhost:8080/lanmei-os/***/***
* Application Context可以配置前台：lanmei-os,后台：lanmei-cms
* 后台管理项目帐号：201801，密码：zxcvbnm123
* 前台项目需先进行注册后再登录
* 由于没有使用延迟加载，所以启动时时间可能会过长，如果Ｔomcat出现启动超时，请适当增加超时时间后再重新启动
* 注意：由于已经对数据库配置文件的密码进行加密处理，因此需要根据自己的mysql数据库用户名和密码进行加密处理，否则运行将会因为连接不上数据库而报错，参考[这篇文章](https://blog.csdn.net/u011676300/article/details/80740530)进行操作
* 有任何问题可以到本博客下进行讨论，欢迎交流！[蓝莓商城项目交流](https://blog.csdn.net/u011676300/article/details/80276014)


#技术选型

##一.开发环境
* 开发环境：linux
* 编译器：[IDEA-Community-2018.2.3](https://www.jetbrains.com/idea/download/#section=linux)
* 项目构建工具：[Maven](https://maven.apache.org)
* 数据库：[Mysql 5.7.12](https://www.mysql.com/)
* 缓存数据库：[Redis 4.0.9](https://redis.io/)
* 版本管理：[github](https://github.com/)



##二.后端技术
* JAVA版本：9.0.1	
* 核心框架：[Spring Framework 5.0.4.RELEASE](https://projects.spring.io/spring-framework/)
* 持久层框架：[Mybatis 3.3.0](http://www.mybatis.org/mybatis-3/)
* WEB层框架：[Spring MVC 5.0.4.RELEASE](https://projects.spring.io/spring-framework/)
* Mybatis代码生成器(插件方式):[mybatis-generator 1.3.6](http://www.mybatis.org/generator/)
* 日志管理：[log4j2](https://logging.apache.org/log4j/2.x/manual/configuration.html/)
          [slf4j 1.7.5](https://www.slf4j.org/)
* Mybatis分页插件:[pagehelper 6.1.2](https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md)
* 短信验证服务:[阿里云短信服务](https://www.aliyun.com/product/sms?spm=5176.8195934.765261.387.5c464183yLVTAN)
* 任务调度:[quartz](http://www.quartz-scheduler.org/)
* 数据库连接池：[druid](http://druid.io/)
* 安全管理框架：[shiro](http://shiro.apache.org/)
* 图片验证码：kaptcha	

#三.前端技术
* html
* thmeleaf模板
* JS框架：Jquery
* CSS框架：bootstrap
* 多级列表树插件：[zTree](http://www.treejs.cn/v3/api.php)
* 富文本编辑器:[wangEditor](http://www.wangeditor.com/)
	

#四.前后端交互
主要使用AJAX传输数据！

#五.推荐学习书籍</h4>
* 跟我学Shiro教程
* 深入浅出MyBatis技术原理与实战
* MyBatis从入门到精通
* 精通Spring+4.x++企业应用开发实战


#目录介绍
* [lanmei-admin](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-admin)
后台管理 管理员相关模块(新增/登陆/权限管理等，权限未做好) 秒杀活动管理，商品管理等
* [lanmei-cms-web](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-cms-web)后台管理web项目
* [lanmei-common](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-common)放置公共类
* [lanmei-commodity](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-commodity)商品模块
* [lanmei-os-user](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-os-user)用户模块，登录注册相关
* [lanmei-os-web](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-os-web)商城web项目
* [lanmei-seckill](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-seckill)秒杀模块
* [lanmei-sysaop](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-aop)系统Ａop处理
* [lanmei-task-scheduling](https://github.com/Mrlgj/LanMeiShop/tree/master/lanmei-task-%20scheduling)任务管理模块，使用quart，定时备份mysqls数据库
* [mysql脚本](https://github.com/Mrlgj/LanMeiShop/tree/master/mysql)	数据库脚本文件，创建数据表相关


#完成模块
#### 用户注册模块
* 注册界面。
* 密码强度校验：正则表达式。
* 手机短信验证码：使用阿里云短信
* 密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密
* 密码加密保存：MD5算法加密
	
#### 用户登录模块
* 登录界面
* 登录方式选择：手机/邮箱/用户名
* 动态验证码:kaptcha			
* 密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密
* shiro登录验证
* 帐号登录日志管理
* redis持久化session,还未实现关闭浏览器还能保持登录状态功能

#### 后台新增管理员模块

* 新增管理员界面。
* 姓名/工号/默认密码/生成动态邀请码/邮件告知新增的管理员
* 邮件服务使用Spring mail库
* 密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密
* 密码加密保存：MD5算法加密
#### 后台管理员登陆模块

* 登录界面
* 登录方式选择：工号
* 其他输入：邀请码/验证码/密码
* 动态验证码:kaptcha			
* 密码加密传输：RSA算法加密。客户端使用公钥加密，服务端使用私钥解密
* shiro登录验证
* 帐号登录日志管理
* redis持久化session,还未实现关闭浏览器还能保持登录状态功能

	
	
#### 秒杀模块

* 后台管理
* 已结束和未结束秒杀活动展示
* 管理秒杀活动（新增，删除，修改）
* 前台秒杀活动列表页面			
* 秒杀页面，未开始则进行倒计时
* 秒杀逻辑实现
* 为防止软件提前刷，造成服务器压力，秒杀开始后服务端才暴露秒杀地址
* 秒杀地址动态部分由seckillId和随机生成的数字组合，再经过MD5加密而成
* 生成的MD5保存在Session中，执行秒杀操作时再进行比对
* 使用spring的事务管理，执行秒杀过程出现异常则进行数据库回滚
* 首次使用spring test 进行web层测试，提高效率。数据曾和服务曾未单独进行测试
* 首次使用DTO进行层间数据传送
	
#### AOP模块

* １．日志记录模块
* 绑定的是切面注解，只有添加注解SyslogAnno才会监测
* 使用AOP的前置通知和异常通知	
* 记录访问类，描述，异常，时间等信息
* 相关信息持久化到数据库
* 并在进入的方法时打印请求路径
* ２．方法执行时间监测模块
* 绑定的是切面注解，只有添加注解TimeMeasurementAnno才会监测
* 使用AOP的环绕通知	
* 记录方法，平均时间，当前时间，运行次数
* 相关信息持久化到数据库
* 使用＠Order注解控制两个AOP执行顺序.＠Order的值越低，优先级越高，越先执行
* 后续会增加后台实时查看和删除功能

		
#### 数据库配置文件密码加密
* cms.web使用的是druid配置方式进行加密处理
* os.web使用的是Java编码方式进行加密处理

	
###后台商品管理模块
* 商品类别管理：使用树形结构．新增，删除，修改
* 商品管理．添加，删除，列表展示
* 根据SKU概念设计商品库存表
* 商品属性管理
* 商品描述管理，使用富文本编辑器进行编辑
	
#### 数据库定时备份
* 使用quartz实现定时任务
* 使用Runtime模块执行备份指令
* [quartz bean配置](https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-os-web/src/main/resources/spring/applicationContext-quartz.xml)
* [备份实现](https://github.com/Mrlgj/LanMeiShop/blob/master/lanmei-task-%20scheduling/src/main/java/org/lanmei/backup/DatabaseBackup.java)

#个人博客相关文章
* [Js中使用正则表达式判断密码强度](https://blog.csdn.net/u011676300/article/details/79946220)
* [slf4j + Log4j2 日志记录框架配置和使用详解](https://blog.csdn.net/u011676300/article/details/79855398)
* [js中倒计时实现](https://blog.csdn.net/u011676300/article/details/79950339)
* [https://blog.csdn.net/u011676300/article/details/80086709](使用Spring的mail API发送邮件)
* [Java利用递归实现查找树的节点的所有子节点和所有的终结节点](https://blog.csdn.net/u011676300/article/details/80313266)
* [使用wangEditor实现富文本编辑（后端为Java）](https://blog.csdn.net/u011676300/article/details/80345152)
* [properties文件加密处理](https://blog.csdn.net/u011676300/article/details/80740530)


