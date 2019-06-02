<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>增加新闻类别</title>
    
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
          <h1 class="title">增加新闻类别</h1>
         <form action="admin/insertClassify" id="frm" method="post"> 
    	<table >
    		<tr><td>新闻类别</td><td><input id="classify_name" name="classify_name"  maxlength="50" /></td><td id="err_classify_name"></td></tr>
    		<tr><td>排序权重</td><td><input id="classify_feight" name="classify_feight"  maxlength="10" /></td><td id="err_classify_feight"></td></tr>
    		
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
