<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.action.bean.ActionBean" %>
<%@ page import="com.entity.Classify" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String classify_id=request.getParameter("classify_id");
ActionBean actionBean=new ActionBean();
Classify classify=actionBean.queryClassifyById(classify_id);
if(null==classify){
	response.sendRedirect("classifyList.jsp");
	return ;
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改新闻类别</title>
    
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
          <h1 class="title">修改新闻类别</h1>
         <form action="admin/editClassify" id="frm" method="post"> 
         <input type="hidden" id="classify_id" name="classify_id" value="<%=classify.getClassify_id() %>" >
    	<table >
    		<tr><td>新闻类别</td><td><input id="classify_name" name="classify_name"  maxlength="50" type="text" value="<%=classify.getClassify_name()%>"/></td><td id="err_classify_name"></td></tr>
    		<tr><td>排序权重</td><td><input id="classify_feight" name="classify_feight"  maxlength="10" type="text" value="<%=classify.getClassify_feight() %>" /></td><td id="err_classify_feight"></td></tr>
    		
    		<tr>
    		     <td ><a class="btn" onclick="startPost();" href="javascript:void(0)">保存</a></td>
    		     <td><a class="back-btn" href="admin/classifyList.jsp">返回</a></td>
    		</tr>
    	</table>
    	</form>
     </div>
   </div>
  </div>
  <jsp:include page="footer.jsp"></jsp:include>
   
   	<script type="text/javascript" >
   	    
   		function startPost(){
   			var classify_name=document.getElementById("classify_name").value;
   			var classify_feight=document.getElementById("classify_feight").value;
   			if(classify_name==""){
   				document.getElementById("err_classify_name").innerHTML="<span class='err'>请输入类别名称</span>";
   				return;
   			}
   			if(classify_feight==""){
   				document.getElementById("err_classify_feight").innerHTML="<span class='err'>请输入排序权重，值越小越靠前</span>";
   				return;
   			}
   			if(isNaN(parseInt(classify_feight))){
   				document.getElementById("err_classify_feight").innerHTML="<span class='err'>权重必须为整形</span>";
   				return;
   			}
   			document.getElementById("frm").submit();
   		}
   		
   	</script>
  </body>
</html>