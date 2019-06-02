package com.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Article;
import com.entity.Classify;
import com.sql.SqlHelper;
import com.tool.Convert;

public class SearchServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public SearchServlet() {
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

		String searchTXT=request.getParameter("searchTXT");
		//searchTXT=Convert.ConvertToUtf8(searchTXT);
		if(searchTXT==null || "".equals(searchTXT)){
			response.sendRedirect("index.html");
			return;
		}
		int p=Convert.StringToInt(request.getParameter("p"));//获取当前页数
		SqlHelper sqlHelper=new SqlHelper();
		//获取导航数据
		List<Classify> list=sqlHelper.queryClassifyAll();
		List<Article> lastArticles=sqlHelper.queryLastArticles();
		//查询分类下的新闻列表
		List<Map> articles=sqlHelper.queryArticleMapByPage(p, null, searchTXT);
		//获取总的记录数
	    int rows=sqlHelper.queryArticleCount(null, searchTXT);
		request.setAttribute("classifyList", list);
		request.setAttribute("lastArticles", lastArticles);
		request.setAttribute("articles", articles);
		request.setAttribute("rows", rows);
		request.setAttribute("p", p);
		request.setAttribute("searchTXT", searchTXT);
		sqlHelper.destory();
		request.getRequestDispatcher("search.jsp").forward(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
