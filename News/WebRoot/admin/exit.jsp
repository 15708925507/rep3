<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.config.Config" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
javax.servlet.http.HttpSession sess=request.getSession();
sess.removeAttribute(Config.MANAGER_ID);
sess.removeAttribute(Config.LOGIN_SUCCESS);
response.sendRedirect("login.jsp");
%>


