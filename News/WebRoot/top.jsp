<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="top-box w">
	<div class="sys-logo">
			JSP的新闻发布系统
	</div>
	<form action="search.html" method="get">
		<input name="searchTXT" placeholder="请输入关键字"
		 autocomplete="off"
		 value="<%=request.getAttribute("searchTXT")==null?"":request.getAttribute("searchTXT")%>"/>
		<input type="submit" value="搜索">
	
	</form>
</div>