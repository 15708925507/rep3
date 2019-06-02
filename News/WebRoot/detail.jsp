<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.entity.Article,com.entity.Classify" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Article article=(Article)request.getAttribute("article");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=article.getArticle_title() %></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	

  </head>
  
  <body>
    <jsp:include page="top.jsp"></jsp:include>
  	<jsp:include page="banner.jsp"></jsp:include>
    <div class="w">
    	<h2 class="title"><%=article.getArticle_title() %></h2>
    	<p class="date">发布日期:<%=article.getArticle_date() %></p>
    	<div class="content">
    		<%=article.getArticle_content() %>
    	
    	</div>
    	
    </div>
    
    
    
    <jsp:include page="foot.jsp"></jsp:include>
 </body>
</html>
