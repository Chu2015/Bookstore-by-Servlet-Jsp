<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'listdbback.jsp' starting page</title>

  </head>
  
  <body>
  	<table frame="border" rules="all" cellpadding="20%">
  		<tr>
			<td>备份文件名</td>
			<td>备份时间</td>
			<td>备份原因</td>
			<td>操作</td>
		</tr>
		<c:forEach var="dbback" items="${list }">
			<tr>
				<td>${dbback.filename }</td>
				<td>${dbback.backtime }</td>
				<td>${dbback.description }</td>
				<td><a href="${pageContext.request.contextPath }/manager/DbServlet?method=restore&id=${dbback.id}">恢复</a></td>
			</tr>
		</c:forEach>
  	</table>
  </body>
</html>
