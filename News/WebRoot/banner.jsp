<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.entity.Classify" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Classify> list=(List)request.getAttribute("classifyList");
if(list==null) return;
String activeDiv="active";
String currentId="";
if(request.getAttribute("classify_id")!=null){
       activeDiv="";
       currentId=request.getAttribute("classify_id").toString();
}
%>

<div class="banner-box">
	<ul class="w">
		<li class="<%= activeDiv%>"><a href="index.html">首页</a></li>
		<% for(Classify classify:list){ %>
		<li <%=classify.getClassify_id().equals(currentId)?"class=\"active\"":"" %>><a href="articleList.html?classify_id=<%=classify.getClassify_id() %>>" target="_blank"><%=classify.getClassify_name() %></a></li>
		
		<%} %>
	</ul>
</div>
