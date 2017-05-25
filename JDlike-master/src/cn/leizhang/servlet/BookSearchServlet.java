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

public class BookSearchServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		String searchfield=request.getParameter("searchfield");
		String urlString="http://localhost:8080/solr/collection1";
		SolrServer solr = new HttpSolrServer(urlString); 
		// 查询对象
				SolrQuery query = new SolrQuery();
				
				ArrayList<Goods> al=new ArrayList<Goods>();
				GoodsService gs=new GoodsService();
				//设置查询条件,名称“q”是固定的且必须 的
				//搜索product_keywords域，product_keywords是复制域包括product_name和product_description
				query.set("q", "detail:"+searchfield+" OR breif:"+searchfield+" OR name:"+searchfield);
				//query.set("q", "detail:"+searchfield);
				// 请求查询
				try {
					QueryResponse response1 = solr.query(query);
					SolrDocumentList docs = response1.getResults();
					for (SolrDocument doc : docs) {
						String id = (String) doc.getFieldValue("id");
						
						try {
							al.add(gs.getGoodsById(id));
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//System.out.println(id);
					}
					
				} catch (SolrServerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.setAttribute("GoodsList",al );
				request.getRequestDispatcher("searchresult.jsp").forward(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
	}

}
