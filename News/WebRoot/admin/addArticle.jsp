<%@page import="com.action.bean.ActionBean,com.entity.Classify"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ActionBean actionBean=new ActionBean();
List<Classify> list=actionBean.queryClassifyList();
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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" href="<%=basePath%>admin/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="<%=basePath%>admin/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="<%=basePath%>admin/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="<%=basePath%>admin/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="<%=basePath%>admin/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>admin/upload_json.jsp',
				fileManagerJson : '<%=basePath%>admin/file_manager_json.jsp',
				allowFileManager : true,
				afterBlur:function(){
				  this.sync();
				},
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['frm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['frm'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
	

  </head>
  
  <body>
  <div class="main-frm">
    <jsp:include page="top.jsp"></jsp:include>
    <jsp:include page="left.jsp"></jsp:include>
      <div class="right">
        <div class="admin">
          <h1 class="title">增加新闻类别</h1>
         <form name="frm" action="admin/insertArticle" id="frm" method="post"> 
    	<table >
    		<tr><td>新闻标题</td><td><input id="article_title" name="article_title"  maxlength="300" autocomplete="off"/></td><td id="err_article_title"></td></tr>
    		<tr><td>新闻类别</td><td>
    			<select id="classify_id" name="classify_id">
    				<option value="" autocomplete="off">请选择新闻类别 </option>
    				<%
    					for(Classify classify:list){
    					  %>
    					  <option value="<%=classify.getClassify_id()%>"><%=classify.getClassify_name()%> </option>
    					  <% 
    					}
    				 %>
    			</select>
    		
    		</td><td id="err_classify_id"></td></tr>
    		<tr><td>发布时间</td>
    		   <td><input id="article_date" name="article_date"  maxlength="20" autocomplete="off" type="date" /></td>
    		   <td id="err_article_date"></td>
    		</tr>
    		<tr>
    			<td valign="top">新闻正文</td>
    			<td colspan="2">
    				<textarea name="content1" rows="" cols="" style="width:700px;height:400px;"></textarea>
    			</td>
    		</tr>
    		<tr>
    		     <td ><a class="btn" onclick="startPost();" href="javascript:void(0)">保存</a></td>
    		     <td><a class="back-btn" href="admin/articleList.jsp">返回</a></td>
    		</tr>
    	</table>
    	</form>
     </div>
   </div>
  </div>
  
     <script type="text/javascript">
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
   	    function startPost(){
   			var ipts=["article_title","classify_id","article_date"];
   			var lens=[2,1,8]
   			var msg=["请输入标题，至少2位","请选择新闻类别","请输入发布日期，至少8位"];
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
