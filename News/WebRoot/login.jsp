<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String msg=request.getParameter("msg")==null?"":("2".equals(request.getParameter("msg"))?"错误的账号或密码":("0".equals(request.getParameter("msg"))?"信息填写不完整":""));
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>JSP系统的后台登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/style.css">

  </head>
  
  <body>
    <div class="login-title">
    	JSP新闻发布系统后台
    </div>
    <div class="login-main">
    	<div class="login-box">
    		<div class="box">
    			<h2>用户登录</h2>
    			<form action="loginServlet" method="post" id="frm" >
    				<p><label>账号:</label><input type="text" name="manager_id" id="manager_id"></p>
    				<p><label>密码:</label><input type="password" name="manager_pwd" id="manager_pwd"></p>
    				<p><a class="btn"href="javascript:void(0)" onclick="postForm()">登录</a></p>
    				<p><span id="msg"class="msg"><%=msg %></span></p>
    			</form>
    		</div>
    	</div>
    	
    </div>
    <jsp:include page="admin/footer.jsp"></jsp:include>
    <script type="text/javascript">
    	function postForm(){
    		var manager_id=document.getElementById("manager_id").value;
    		var manager_pwd=document.getElementById("manager_pwd").value;
    		document.getElementById("msg").innerHTML="";
    		if(manager_pwd=="" || manager_id==""){
    			document.getElementById("msg").innerHTML="请输入用户名或密码";
    			return;
    		}
    		document.getElementById("frm").submit();
    	}
    </script>
  </body>
</html>
