<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.action.bean.ActionBean,com.tool.Convert,com.entity.Classify" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ActionBean actionBean=new ActionBean();
//List <Map> list=actionBean.queryArticleMap();
int p=Convert.StringToInt(request.getParameter("p"));
String classify_id=request.getParameter("classify_id");
String article_title=Convert.ConvertToUtf8(request.getParameter("article_title"));
if(p<0){
   response.sendRedirect("articleList.jsp");
}
Map resultMap=actionBean.queryArticleByPage(p,classify_id,article_title);
List <Map> list= (List)resultMap.get("list");
List<Classify> classifyList=(List)resultMap.get("classifyList");
int rows=(Integer)resultMap.get("rows");
if(p>0 && list.size()<1){
   response.sendRedirect("articleList.jsp");
}
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新闻信息管理列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	

  </head>
  
  <body>
  <div class="main-frm">
    <jsp:include page="top.jsp"></jsp:include>
    <jsp:include page="left.jsp"></jsp:include>
   <div class="right">
  	<div class="admin">
  		<h1 class="title" >新闻信息管理列表<a class="back-btn2"  href="admin/main.jsp">返回主页</a></h1>
  		     
  		     <tr>
  		    	<td>
  		    	    <form action="admin/articleList.jsp" method="post">
  		    		<label>新闻类别：</label>
  		    		<select name="classify_id" id="classify_id">
  		    			<option value="">请选择新闻类别  </option>
  		    			<%
  		    			 for(Classify classify:classifyList){
  		    			    out.println("<option "+(classify.getClassify_id().equals(classify_id)?"selected='selected' ":"")+" value=\""+classify.getClassify_id()+"\">"+classify.getClassify_name()+" </option>");
  		    			 }
  		    			 %>
  		    		</select>
  		    		<label>新闻标题：</label>
  		    		<input name="article_title" id="article_title" value="<%= article_title==null?"":"article_title" %>"/>
  		    		<input type="hidden" name="p" value="<%=p%>">
  		    		<input type="submit"  value="搜索"/>
  		    	   </form>
  		    	</td>
  		    
  		    </tr>
  		<table class="tab" cellspacing="0">
  		    
  			<tr><td>选择</td><td>序号</td><td>新闻标题</td><td>类别名称</td><td>发布日期</td></tr>
  			<%
  			   int i=10*p;
  			   int totalPages=((rows%10==0)?(rows/10):(rows/10+1));
  			   for(Map map:list){
  			       %>
  			      <tr>
  			        <td><input type="radio" name="rd" value="<%=map.get("article_id")%>" autocomplete="off" ></td>
  			        <td><%= ++i%></td>
  			        <td><%=map.get("article_title") %></td>
  			        <td><%=map.get("classify_name") %></td>
  			        <td><%=map.get("article_date") %></td>
  			      </tr>
  			       
  			      <% 
  			       
  			   }
  				
  			 %>
  			 <tr>
  			 	<td colspan="5">
  			 		<spn>总共有<%=rows %>条记录</spn>  <spn>当前为第<%=p+1 %>页</spn>
  			 	<% if(p>0) {%>
  			 	  <a href="admin/articleList.jsp?p=0">第一页</a>
  			 	  <a href="admin/articleList.jsp?p=<%=p-1 %>">上一页</a>
  			 	 <% } %>
  			 	 <%if(p<totalPages-1) {%>
  			 	  <a href="admin/articleList.jsp?p=<%=p+1%>">下一页</a>
  			 	  <a href="admin/articleList.jsp?p=<%=totalPages-1%>">最后一页</a>
  			 	  <%} %>
  			 	  <span>共<%=totalPages %>页</span>
  			 	  <span>
  			 	        跳到第
  			 	  		<select onchange="location='admin/articleList.jsp?p='+this.value;">
  			 	  		   <%for(int j=0;j<totalPages;j++) { %>
  			 	  			<option value="<%=j%>" <%=j==p?"selected='selected'":""%>><%=j+1%></option>
  			 	           <%} %>
  			 	        </select>
  			 	        页
  			 	  </span>
  			 	 
  			 	</td>
  			 </tr>
  			 
  			
  			 <tr>
  			     <td  style="border-bottom: 0"><a href="admin/addArticle.jsp" class="btn">添加新闻</a></td>
  			     <td  style="border-bottom: 0"><a href="javascript:void(0)" class="btn" onclick="updateNews()">修改</a></td>
  			     <td  style="border-bottom: 0"><a href="javascript:void(0)" class="btn" onclick="deleteNews()">删除</a></td>
  			     
  			 </tr>
  			
  		</table>
  	</div>
   </div>
  </div>
  
  	<script>
  		function selectRow(){
  		   	var s=document.getElementsByName("rd");
  			var obj=null;
  			for(var i=0;i<s.length;i++){
  			    if(s[i].checked){
  			    	obj=s[i];
  			    	break;
  			    }
  			}
  			if(null==obj){
  				alert("QAQ 请选择后操作");
  				return;
  			}
  			return obj;
  		}
  		function updateNews(){
  			var obj=selectRow();
  			if(obj==null) return ;
  			location="admin/editArticle.jsp?article_id="+obj.value;
  		}
  		function deleteNews(){
  			var obj=selectRow();
  			if(obj==null) return ;
  			if(confirm("您确认删除吗")){
  				location="admin/deleteNews?article_id="+obj.value;
  			}
  		}
  		
  	</script>
   		 
  </body>
</html>
