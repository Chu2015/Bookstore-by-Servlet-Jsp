<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>购物车页面列表</title>
    <style type="text/css">
    	#head{
    		text-align: center;
    	}
    	.aa{
    		text-align: center;
    	}

    </style>
  </head>
  
  <body>
    <div id="head">
    	<%@include file="/client/head.jsp" %>
    </div>
    <br/><br/>
    <table frame="border" cellpadding="0" cellspacing="0" width="90%">
    	<caption><h2>购物车页面</h2></caption>
    	<tr>
    		<td>书名</td>
    		<td>售价</td>
    		<td>数量</td>
    		<td>小计</td>
    	</tr>

    	<c:forEach var="entry" items="${cart.map }">
    		<tr>
	    		<td>${entry.value.book.name }</td>
	    		<td>${entry.value.book.price }</td>
				<td>${entry.value.quantity }</td>
				<td>${entry.value.price }</td>
			</tr>
    	</c:forEach>
    	
    	<tr>
    		<td>合计</td>
    		<td>${cart.price }</td>
    	</tr>
    </table>
    
    <a style="text-align: center" href="${pageContext.request.contextPath }/client/OrderServlet?method=generate" class="aa">生成订单</a>
  </body>
</html>
