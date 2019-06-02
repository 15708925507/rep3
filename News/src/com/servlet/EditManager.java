package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Manager;
import com.sql.SqlHelper;
import com.tool.Convert;

/**
 * Servlet implementation class EditManager
 */
@WebServlet({ "/EditManager", "/admin/editManager" })
public class EditManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String manager_id =request.getParameter("manager_id"); //取得管理员id值
		String manager_name =request.getParameter("manager_name");//取得管理员名字值
		Manager manager=new Manager();
		manager.setManager_id(manager_id);
		manager.setManager_name(Convert.ConvertToUtf8(manager_name));
		if(manager.getManager_id()==null ||"".equals(manager.getManager_id())||
				   manager.getManager_name()==null ||"".equals(manager.getManager_name())
				   
						
		){
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.print("    请把信息输入完整。<a href=\"managerList.jsp\">返回重新输入</a>");
			
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			
		}else{
			SqlHelper sqlHelper=new SqlHelper();
			boolean flag=sqlHelper.updateManagerName(manager);
			sqlHelper.destory();
			
		    response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE></TITLE></HEAD>");
			out.println("  <BODY>");
			out.println(flag?"修改成功":"修改失败");
			
			out.print("<a href=\"managerList.jsp\">返回管理列表</a>");
			
			
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			
		}
		
	}

}
