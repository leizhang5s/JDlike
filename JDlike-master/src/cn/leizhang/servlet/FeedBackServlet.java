package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.leizhang.domain.FeedBack;
import cn.leizhang.service.GoodsService;

public class FeedBackServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name=(String) request.getParameter("name");
		String email=(String) request.getParameter("email");
		String subject=(String) request.getParameter("subject");
		String message=(String) request.getParameter("message");
		String ip=(String) request.getParameter("ip");
		GoodsService gs=new GoodsService();
		FeedBack fb=new FeedBack();
		fb.setEmail(email);
		fb.setIp(ip);
		fb.setMsg(message);
		fb.setName(name);
		fb.setSubject(subject);
				try {
					gs.saveFeedBack(fb);
					request.getRequestDispatcher("contact.jsp").forward(request, response);
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
