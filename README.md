# Bookstore-by-Servlet-Jsp
a web project using Servlet and Jsp

1.搭建环境
	1.1 导开发包
		mysql驱动，dbutils框架，c3p0连接池，beanutils框架，log4j，commons fileupload，commons io，jstl开发包
	
	1.2 创建组织程序的包
		domain
		dao
	  dao.impl
		service
		web.controller
		utils
		
		创建组织jsp的目录：
		在WebRoot下新建manager目录，保存后台相关的jsp
		1.在webroot下新建一个manger.jsp页面，这个页面代表后台首页，这个页面是个分真页面，代码如下：
		2、在WebRoot下新建client目录，保存前台相关的jsp
		
		3.创建工程所需的库
		create database bookstore;
		use bookstore;
		create table user
		(
			id varchar(40) primary key,
			username varchar(40) not null unique,
			password varchar(40) not null,
			phone varchar(40) not null,
			cellphone varchar(40) not null,
			email varchar(40) not null unique,
			address varchar(225) not null
		);

		create table category
		(
			id varchar(40) primary key,
			name varchar(40) not null unique,
			description varchar(255)
		);

		create table book
		(
			id varchar(40) primary key,
			name varchar(40) not null unique,
			price decimal(8,2) not null,
			author varchar(40) not null,
			image varchar(225) not null,
			description varchar(40),
			category_id varchar(40),
			constraint category_id_FK foreign key(category_id) references category(id)
		);

		create table orders
		(
			id varchar(40) primary key,
			ordertime datetime not null,
			state boolean not null,
			price decimal(8,2) not null,
			user_id varchar(40),
			constraint user_id_FK foreign key(user_id) references user(id)
		);

		create table orderitem
		(
			id varchar(40) primary key,
			quantity int not null,
			price decimal(8,2) not null,
			order_id varchar(40),
			book_id varchar(40),
			constraint order_id_FK foreign key(order_id) references orders(id),
			constraint book_id_FK foreign key(book_id) references book(id)
		);
		4.创建一些全局的工具类和过滤器
		JdbcUtils
		WebUtils
		CharacterEncodingFilter
		HtmlFilter
		TransactionFilter
		DaoFactory

2.设计实体
		Category
			private String id;
			private String name;
			private String description;
		Book 
			private String id;
			private String name;
			private double price;
			private String author;
			private String image;  //记住书的图片的位置
			private String description;
			private Category category;

		Order
			private String id;
			private Date ordertime;  //下单时间
			private boolean state;   //订单状态
			private double price;    //订单总价

			private User user;    //记住下单人
			private Set orderitems;   //记住订单所有的订单项 

		OrderItem
			private String id;
			private int quantity;
			private double price;
			private Book book;   //记住订单项代表的是哪本书



		User
			private String id;
			private String username;
			private String password;
			private String phone;
			private String cellphone;
			private String email;
			private String address;

4.写dao

5.写service

6.做web层	
		
//为保存备份信息，新建一个库

		create table dbback
		(
			id varchar(40) primary key,
			filename varchar(155) not null,
			backtime datetime not null,
			description varchar(255)
		);

		
		
