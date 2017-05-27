package cn.leizhang.utils;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import cn.leizhang.domain.Goods;
import cn.leizhang.service.GoodsService;

public class SolrUtils {
			//根据字段连接solr搜索引擎进行搜索，然后根据商品id查询数据库，返回商品集合
			public static ArrayList<Goods>  searchGoods(String searchfield)
			{
				String urlString="http://localhost:8080/solr/collection1";
				SolrServer solr = new HttpSolrServer(urlString); 
				SolrQuery query = new SolrQuery();
				ArrayList<Goods> al=new ArrayList<Goods>();
				GoodsService gs=new GoodsService();
				//设置查询条件,名称“q”是固定的且必须 的
				//搜索product_keywords域，product_keywords是复制域包括product_name和product_description
				
				//query.set("q", "detail:"+searchfield);
				// 请求查询
				try {
					query.set("q", "detail:"+searchfield+" OR breif:"+searchfield+" OR name:"+searchfield);
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
				
				return al;
				
			}
}
