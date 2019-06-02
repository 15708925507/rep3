<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.action.bean.ActionBean" %>
<%@ page import="com.entity.Manager" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String manager_id=request.getParameter("manager_id");
ActionBean actionBean=new ActionBean();
Manager manager=actionBean.queryManagerById(manager_id);
if(null==manager){
	response.sendRedirect("managerList.jsp");
	return ;
}


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改管理员</title>
    
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
    <h1 class="title" >修改管理员</h1>
    <form action="admin/editManager" id="frm" method="post">
    	<table >
    		<tr><td>账号：</td><td><input id="manager_id" name="manager_id" autocomplete="off" maxlength="10" readonly="readonly" value="<%=manager.getManager_id()%>"/></td></tr>
    		<tr><td>真实姓名：</td><td><input id="manager_name" name="manager_name" autocomplete="off" maxlength="20" value="<%=manager.getManager_name() %>" /></td><td id="err_manager_name"></td></tr>
    		
    		<tr><td ><a class="btn" onclick="startPost();" href="javascript:void(0)">保存</a></td><td ><a class="back-btn"  href="admin/managerList.jsp">返回</a></td></tr>
    		
    	</table>
    	
    </form>
    </div>
  </div>
   </div>
   <jsp:include page="footer.jsp"></jsp:include>
   
  
   	<script type="text/javascript" >
   	    function ValiInput(id,msg,n){
   	    	var val=document.getElementById(id).value;
   	    	
   			if(val==""|| val.length<n){
                 document.getElementById("err_"+id).innerHTML="<span class='err'>"+msg+"</span>";
                 return false;
   				
   			}else{
   				document.getElementById("err_"+id).innerHTML="<span class='success'>验证通过</span>";
   				return true;
   			}
   	    	
   	    }
   	    function ValiPwd(){
   	    	if(document.getElementById("re_manager_pwd").value!=document.getElementById("manager_pwd").value){
   	    	    document.getElementById("err_re_manager_pwd").innerHTML="<span class='err'>两次输入的密码不一样</span>";  
   	    	    return false;
   	    	}
   	    	return true;
   	    	
   	    }
   		function startPost(){
   			var ipts=["manager_name"];
   			var lens=[2]
   			var msg=["请输入姓名，至少2位"];
   			var flag=true;
   			for(var i=0;i<ipts.length;i++){
   				flag=ValiInput(ipts[i],msg[i],lens[i])&&flag;
   			}
   			
   			if(!flag)return;
   			document.getElementById("frm").submit();
   		}
   		
   	</script>
  </body>
</html>
