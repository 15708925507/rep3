<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="left-menu">
	<h1 class="title">导航菜单</h1>
	<ul>
		<li><a href="admin/managerList.jsp">用户管理</a></li>
		<li><a href="admin/classifyList.jsp">类别管理</a></li>
		<li><a href="admin/articleList.jsp">文章管理</a></li>
		<li><a href="admin/exit.jsp"><b>退出系统</b></a></li>
	
	</ul>
</div>

