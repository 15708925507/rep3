<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.entity.Article,com.entity.Classify" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

List<Article> lastArticles=(List)request.getAttribute("lastArticles");
List<Map>articles=(List)request.getAttribute("articles");
String searchTXT=request.getAttribute("searchTXT").toString();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文章查询<%=searchTXT %>></title>
    
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
    	<div class="left-box">
    		<h2>最新发布</h2>
    		 <ul>
    		 <% for(Article a:lastArticles){ %>
    		 	<li><a href="item.html?article_id=<%=a.getArticle_id()%>" target="_blank" title="<%=a.getArticle_title() %>"><%=a.getArticle_title() %></a></li>
    		 	<% } %>
    		   
    		 </ul>
    	</div>
    	<div class="right-box">
    		<h2>搜索结果</h2>
    	    <ul>
    	       <%
    	       	 
    	       
    	        for(Map map:articles) {%>
    	    	<li><a href="item.html?article_id=<%=map.get("article_id") %>" target="_blank" title="<%=map.get("article_title") %>"><%= map.get("order_number")%>.<%=map.get("article_title") %></a><span><%=map.get("article_date") %></span></li>
    	    	<% } %>
    		    
    	    </ul>
    	   <p class="page">
    	     
  			 	
    	   </p>
    	</div>
    	
    </div>
    
    
    
    <jsp:include page="foot.jsp"></jsp:include>

  </body>
</html>
