package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.leizhang.domain.User;
import cn.leizhang.service.UserService;


public class loginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			UserService userservice=new UserService();
			try {
				User user=userservice.check(username,password);
				if(user!=null)
				{
					
					HttpSession session=request.getSession();
					session.setAttribute("User", user);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else
				{
					request.setAttribute("error", "用户名或密码错误");
					request.getRequestDispatcher("myaccount.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
