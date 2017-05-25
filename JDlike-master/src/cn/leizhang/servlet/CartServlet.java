package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;


import cn.leizhang.domain.ShoppingCart;

import cn.leizhang.service.ShoppingCartService;

public class CartServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		   String id=request.getParameter("id");
		   String action="default";
		   if( request.getParameter("action")!=null)
		   {
			    action=request.getParameter("action");
		   }
		   String cookievalue="null";
		  // System.out.println(action);
		   ShoppingCartService scs=new ShoppingCartService();
		 // Cookie newcookie=new Cookie("shoppingcart",null);
		   
		  // response.addCookie(newcookie);
			//response.addCookie(cookie);
			ShoppingCart sc=new ShoppingCart();
			Cookie [] cookies =request.getCookies();
			Cookie cookie=scs.cookieSearch(cookies);
			//Cookie cookies1=null;
			
			//System.out.println(cookie.getValue());
			
			if(cookie!=null)
			{
				
			try {
				if(action.equals("default"))
				{
					 cookievalue = Base64.encodeBase64String(scs.addShoppingCart(cookie,id).getBytes());
					 cookie.setValue(cookievalue);
					response.addCookie(cookie);
					response.sendRedirect("index.jsp");
				}
				else if(action.equals("sub"))
				{
					cookievalue = Base64.encodeBase64String(scs.subShoppingCart(cookie,id).getBytes());
					 cookie.setValue(cookievalue);
						response.addCookie(cookie);
						response.sendRedirect("cart.jsp");
					
				}
				else{
					 cookievalue = Base64.encodeBase64String(scs.addShoppingCart(cookie,id).getBytes());
					 cookie.setValue(cookievalue);
						response.addCookie(cookie);
						response.sendRedirect("cart.jsp");
				}
				
				
			} catch (SQLException e) {
			
			e.printStackTrace();
			}
				
		}
		else{
				Cookie newcookie=new Cookie("shoppingcart","initial");
				String cookievalue2=null;
				try {
					
						cookievalue2 =Base64.encodeBase64String(scs.addShoppingCart(newcookie,id).getBytes());
						//System.out.println(cookievalue2);
						newcookie.setValue(cookievalue2);
						newcookie.setMaxAge(60*60*24*6);//设置cookie过期时间为6天
						response.addCookie(newcookie);
						response.sendRedirect("index.jsp");
				} catch (SQLException e) {
					
					e.printStackTrace();
				};
				 
					
			
			
		}
			
			
			
			
		 
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			doGet(request,response);
	}

}
