<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.action.bean.ActionBean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员信息列表</title>
    
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
  		<h1 class="title" >管理员信息列表<a class="back-btn2"  href="admin/main.jsp">返回主页</a></h1>
  		<table class="tab" cellspacing="0">
  			<tr><td>选择</td><td>序号</td><td>登陆账号</td><td>真实姓名</td></tr>
  			<%
  				ActionBean ab=new ActionBean();
  				out.print(ab.queryManagerAll());
  			 %>
  			 <tr>
  			     <td colspan="2" style="border-bottom: 0"><a href="admin/addManager.jsp" class="btn">添加管理员</a></td>
  			     <td><a class="btn" id="update-btn" href="javascript:void(0)" onclick="updateManager()">修改管理员</a></td>
  			     <td><a class="btn" id="delete-btn" href="javascript:void(0)" onclick="deleteManager()">删除</a></td>
  			 </tr>
  		</table>
  	</div>
   </div>
  </div>
  <jsp:include page="footer.jsp"></jsp:include>
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
  		function updateManager(){
  			var obj=selectRow();
  			if(obj==null) return ;
  			location="admin/editManager.jsp?manager_id="+obj.value;
  			
  			
  		}
  		function deleteManager(){
  			var obj=selectRow();
  			if(obj==null) return ;
  			if(confirm("你确定要删除吗？")){
  				location="admin/deleteManager?manager_id="+obj.value;
  			}
  			
  		}
  	</script>
   		 
  </body>
</html>
