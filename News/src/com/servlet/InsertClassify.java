package com.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Classify;
import com.sql.SqlHelper;
import com.tool.Convert;

public class InsertClassify extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public InsertClassify() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classify_name=request.getParameter("classify_name");
		String classify_feight_str=request.getParameter("classify_feight");
		if(classify_name ==null || classify_feight_str ==null){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    输入错误请重新输入。<a href=\"addClassify.jsp\">返回重新输入</a>");
			
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return ;
		}
		int classify_feight=0;
		try{
			classify_feight=Integer.parseInt(classify_feight_str);
		}catch(Exception e){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    输入错误请重新输入。<a href=\"addClassify.jsp\">返回重新输入</a>");
			
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			return ;
			
		}
		String classify_id=UUID.randomUUID().toString();
		Classify classify=new Classify();
		classify.setClassify_id(classify_id);
		classify.setClassify_feight(classify_feight);
		classify.setClassify_name(Convert.ConvertToUtf8(classify_name));
		SqlHelper sqlHelper=new SqlHelper();
		boolean b=sqlHelper.insertClassify(classify);
		sqlHelper.destory();
		if(!b){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    增加错误。<a href=\"addClassify.jsp\">返回重新输入</a>");
			
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}else{
			response.sendRedirect("classifyList.jsp");
		}
		
		
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
