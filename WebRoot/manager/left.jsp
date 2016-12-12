<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台左侧导航页面</title>
    
    <style type="text/css">
      .dc { 
      		display: none; 
      		margin-left: 10px;
      	  }
	</style>
	
	<script language="javascript">
	      function test(e) {
	            e.style.display = e.style.display == 'block' ? 'none' : 'block' ;       
	      }
	</script>
  </head>
  
  <body>
    <ul>
    	<li>
    		<a href="#" onclick="test(children[0])">分类管理
    			<div>
	    			<a href="${pageContext.request.contextPath }/manager/addcategory.jsp"  target="right">添加分类</a><br/>
	    			<a href="${pageContext.request.contextPath }/servlet/CategoryServlet?method=getAll"  target="right">查看分类</a><br/>
    			</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    	<li>
    		<a href="#" onclick="test(children[0])">图书管理
    			<div>
	    				<a href="${pageContext.request.contextPath }/manager/BookServlet?method=forAddUI"  target="right">添加图书</a><br/>
	    				<a href="${pageContext.request.contextPath }/manager/BookServlet?method=list"  target="right">查看图书</a>
    			</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    	<li>
    		<a href="#" onclick="test(children[0])">订单管理
    			<div>
	    			<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=getAll&state=false"  target="right">待处理订单</a><br/>
	    			<a href="${pageContext.request.contextPath }/manager/OrderServlet?method=getAll&state=true"  target="right">已发货订单</a><br/>
    			</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    	<li>
    		<a href="#" onclick="test(children[0])">订单管理
    			<div>
	    			<a href="${pageContext.request.contextPath }/manager/dbback.jsp"  target="right">数据库备份</a><br/>
	    			<a href="${pageContext.request.contextPath }/manager/DbServlet?method=list"  target="right">数据库恢复</a><br/>
    			</div>
    		</a>
    	</li>
    	
    	<br/><br/>
    	
    </ul>
  </body>
</html>
