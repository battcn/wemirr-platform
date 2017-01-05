# 技术选型 #
 ![](http://static.battcn.com/battcn3.0/battcn3.0-framework.png)
------
## 目录结构 ##
	battcn-parent：Jar包版本管理的工程->下载需首先install一下
	battcn-facades：接口文件,需要install
	battcn-provider：服务提供者
		battcn-provider-parent：服务提供者的公共类,这样多个提供者只需要在pom.xml配置下parent即可
		battcn-provider-system：系统服务提供者,类似于账号,系统管理,权限,因为现在阶段功能以及模块
	不多只是拆分列举,提供参考学习,开发阶段按照自己公司业务划分即可
	battcn-servers：对外HTTP接口,也是服务消费者
		battcn-server-parent：server的公共父类管理
		battcn-server-system：boot项目,简化开发维护,同时可装逼
	battcn-managements：管理工程
		battcn-ms-web：类似battcn2.0的管理系统
	battcn-utils：工具类
	battcn-framework：WEB工具类,如MQ，Redis，Spring

## 测试 ##
	因部分童鞋无法看到效果,故而写了个小案例,只需要配置上 zookeeper 
        (可以看看附件上传的zookeeper 启动bin/zkServer.cmd  或者 zkServer.sh即可)
	1.修改battcn-config项目中的jdbc.properties 地址改成自己的
	2.修改battcn-server-system application.yml zookeeper 地址改为自己的
	3.启动:battcn-provider-system (jetty:run) 默认 dev环境
	4.启动:battcn-server-system ApplicationRun (boot项目,专门做接口开发)
	5.地址:http://localhost:9090/logs/query
	
	
	
	
## 致歉 ##
	因为作者也需要工作,只能在业余之时加快进度的code,现阶段开发也许比较缓慢,请各位看官谅解,
	不过本人承诺保证,在工作之余主要精力就是放在battcn的项目上,这是battcn的第三个版本,
	将他做完善,对部分人有所帮助,那么这套系统也不算失败作品!!!
## 基础框架 ##
	Spring4.3 Spring MVC4.3 Mybatis3.3.8,如果需要学习以及使用battcn首先你要对maven redis mq 
	有所了解,无需精通,但是需要知道
## 缓存技术	##
	Redis3.x版本 集群化
## MQ消息队列 ##
	RabbitMQ
## 数据库主从 ##
	代码层面AOP操作或者采用第三方数据库中间件Mycat做读写分离操作
## 版本控制 ##
	码云GIT做代码的版本控制,多分支开发合并以及部署
## 开发工具  ##
	IDEA || Eclipse 只提交代码,不提交开发工具的配置,避免环境冲突
## 服务模块化 ##
	ZooKeeper Dubbo Maven 不同服务拆分到不同模块,公共的服务提供者
	一：Web管理系统,类似battcn2.0
	二：动态定时任务服务(管理所有的JOB)
	三：单点登录服务,管控系统之间登录的入口
## 扩展  ##
	后续扩展Demo-群项目(书城=技术书籍 评论  或者留言博客板之类的
	找问题或者帮助解决问题都方便点)
## 未完成事项 ##
	以上只是需要实现的,暂时提供基础架子demo,慢慢改进....谢谢各位长期以来对battcn的支持















