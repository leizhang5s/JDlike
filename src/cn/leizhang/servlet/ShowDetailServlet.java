package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.leizhang.domain.Goods;
import cn.leizhang.service.GoodsService;

public class ShowDetailServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String goodsId=request.getParameter("id");
			
			GoodsService gs=new GoodsService();
			Goods goods;
			try {
				goods = gs.getGoodsById(goodsId);
				request.setAttribute("Goods", goods);
				request.getRequestDispatcher("details.jsp").forward(request, response);
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
