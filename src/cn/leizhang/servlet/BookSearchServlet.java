package cn.leizhang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import cn.leizhang.domain.Goods;
import cn.leizhang.service.GoodsService;
import cn.leizhang.utils.SolrUtils;

public class BookSearchServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 		request.setCharacterEncoding("utf-8");
		 		String searchfield=request.getParameter("searchfield");
				//System.out.println("haha"+searchfield);
				ArrayList<Goods> al=new ArrayList<Goods>();
				if(searchfield!=null&&searchfield!="")
				{
					al=SolrUtils.searchGoods(searchfield);
					
				}
				request.setAttribute("GoodsList",al );
				request.getSession().setAttribute("searchField",searchfield );
				request.getRequestDispatcher("searchresult.jsp").forward(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
	}

}
