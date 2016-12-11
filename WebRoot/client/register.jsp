<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>用户注册页面</title>
  </head>
  
  <body style="text-align: center">
  	<form action="${pageContext.request.contextPath }/client/RegisterServlet" method="post">
  		<table frame="border" width="300px">
  		<caption><h2>用户注册</h2></caption>
  			<tr>
  				<td>用户名</td>
  				<td>
  					<input type="text" name="username">
  				</td> 
  			</tr>
  			<tr>
  				<td>密码</td>
  				<td>
  					<input type="password" name="password">
  				</td> 
  			</tr>
  			<tr>
  				<td>电话</td>
  				<td>
  					<input type="text" name="phone">
  				</td> 
  			</tr>
  			<tr>
  				<td>手机</td>
  				<td>
  					<input type="text" name="cellphone">
  				</td> 
  			</tr>
  			<tr>
  				<td>邮箱</td>
  				<td>
  					<input type="text" name="email">
  				</td> 
  			</tr>
  			<tr>
  				<td>地址</td>
  				<td>
  					<input type="text" name="address">
  				</td> 
  			</tr>
  			<tr>
  				<td></td>
  				<td>
  					<input type="submit" value="提交">
  				</td> 
  			</tr>
  		</table>
  	</form>
  </body>
</html>
