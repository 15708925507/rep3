<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.entity.Classify,com.entity.Article" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Map> resultList=(List)request.getAttribute("resultList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基于JSP的新闻发布系统</title>
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
    <div class="index-main-box w">
    <% 
    	for(Map map:resultList){
    		Classify classify=(Classify)map.get("classify");
    		List<Article> articleList=(List)map.get("articleList");
     %>
    	<div class="panel">
    	   <div>
    	    	<h2><%=classify.getClassify_name()%><a href="articleList.html?classify_id=<%=classify.getClassify_id() %>" class="more" target="_blank">更多</a></h2>
    	    	<ul>
    	    	   <% for(Article a:articleList){ %>
    	    		<li><a href="item.html?article_id=<%=a.getArticle_id() %>" target="_blank" title="<%=a.getArticle_title() %>"><%=a.getArticle_title() %></a> <span><%=a.getArticle_date() %></span></li>
    	    		<%} %>
  
    	    	
    	    	</ul>
    	   </div>
    	</div>
    <%} %>
    	
    	    		
    	    	
    	    		
    	    		
    <jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>
