package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.leizhang.domain.User;
import cn.leizhang.service.UserService;

public class updateuserServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
		    String id=request.getParameter("id");
			String truename=request.getParameter("truename");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String phone=request.getParameter("phone");
			UserService us=new UserService();
			User user;
			try {
				
				us.updateUser(id,truename,email,address,phone);
				user = us.getUserById(id);
				//System.out.println(user.getAddress_());
				request.getSession().setAttribute("User", user);
				request.getRequestDispatcher("myaccount.jsp").forward(request, response);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				
			
			
	}	

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		
	}

}
