# Bookstore-by-Servlet-Jsp
a web project using Servlet and Jsp
通过servlet和jsp技术创建一个购买书籍网站，可供用户购买书籍，并且提供后台管理图书和用户订单的功能。

1.搭建环境
	
	1.1 导开发包
		mysql驱动，dbutils框架，c3p0连接池，beanutils框架，log4j，commons fileupload，commons io，jstl开发包
	
	1.2 创建组织程序的包
			domain dao dao.impl service service.impl web.controller	utils
			创建组织jsp的目录：
			在WebRoot下新建manager目录，保存后台相关的jsp
			1.在webroot下新建一个manger.jsp页面
			2、在WebRoot下新建client目录，保存前台相关的jsp
		
	1.3.创建工程所需的库和表			

	1.4.创建一些全局的工具类和过滤器
		JdbcUtils(获取数据库连接池)
		WebUtils(reqeust2Bean)
		CharacterEncodingFilter(解决中文乱码过滤器)
		HtmlFilter(字符转义过滤器)
		TransactionFilter(开启事务过滤器)
		DaoFactory

2.设计实体

	Category Book Order OrderItem User	

3.写dao

4.写service

5.做web层	
		
6.新建一个库为保存备份信息

7.利用注解和动态代理实现权限管理	

		
		
