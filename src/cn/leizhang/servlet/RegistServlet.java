package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.leizhang.service.UserService;

public class RegistServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String checkbox=request.getParameter("agree");
			
			UserService uss=new UserService();
			try {
				boolean isExist=uss.isUserExist(username);
				if(checkbox==null)
				{
					request.setAttribute("error", "noagree");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
				else if(isExist)
				{
				request.setAttribute("error", "usernameexist");
				request.getRequestDispatcher("register.jsp").forward(request, response);
					
				}
				else if(username==""||password==""||username==null||password==null)
				{
					request.setAttribute("error", "nameorpwempty");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
				else if(username==""||password==""||username==null||password==null)
				{
					request.setAttribute("error", "nameorpwempty");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
				else
				{	
					
					uss.saveUser(username,password);
					request.getRequestDispatcher("myaccount.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
