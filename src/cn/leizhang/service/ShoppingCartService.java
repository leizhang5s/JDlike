package cn.leizhang.service;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.Cookie;



import net.sf.json.JSONObject;



import cn.leizhang.dao.GoodsDao;
import cn.leizhang.domain.Goods;
import cn.leizhang.domain.ShoppingCart;
import org.apache.commons.codec.binary.Base64;
public class ShoppingCartService {

	public String addShoppingCart(Cookie cookie,String id) throws SQLException {
			GoodsService gs=new GoodsService();
			Goods goods=gs.getGoodsById(id);
		//将cookie字符串值转成对象
		if(!cookie.getValue().equals("initial"))
		{
		byte [] by=Base64.decodeBase64(cookie.getValue().getBytes());
		
		JSONObject scc=JSONObject.fromObject(new String(by));
		ShoppingCart  shopcart= (ShoppingCart)JSONObject.toBean(scc,ShoppingCart.class);
			//读取
			HashMap<String,Integer> hsm=shopcart.getGoodsList();
			boolean bl=hsm.containsKey(id);
			if(bl)
			{
				hsm.put(id,hsm.get(id)+1);
				shopcart.setGoodsList(hsm);
				
			}
			else
			{
				hsm.put(id, 1);
				shopcart.setGoodsList(hsm);
			}
			//将对象转成json字符串
			shopcart.setTotal_Goods(shopcart.getTotal_Goods()+1);
			shopcart.setTotal_Money(shopcart.getTotal_Money()+goods.getGoodsprice());
			String str=JSONObject.fromObject(shopcart).toString();
			return str;
		}
			else
			{
				ShoppingCart  shopcart =new ShoppingCart();
				HashMap<String,Integer> hsm=new HashMap<String,Integer>();
				hsm.put(id, 1);
				shopcart.setGoodsList(hsm);
				shopcart.setTotal_Goods(1);
				shopcart.setTotal_Money(goods.getGoodsprice());
				//将对象转成json字符串
				//shopcart.setTotal_Goods(shopcart.getTotal_Goods()+1);
				//shopcart.setTotal_Money(shopcart.getTotal_Money()+goods.getGoodsprice());
				String str=JSONObject.fromObject(shopcart).toString();
				return str;
			
			}		
	}
		//判断购物车是否存在？也就是购物车的cookie是否存在
		public Cookie cookieSearch(Cookie [] cookie)
		{
			if(cookie!=null)
			{
				for(Cookie i: cookie)
				{
					if(i.getName().equals("shoppingcart"))
					{
						return i;
						
					}
				}
			}
			
			
			return null;
		}
		
		public ArrayList<Goods> getGoods(Map<String,Integer> hsm) throws SQLException
		{
			
			ArrayList<Goods> al=new ArrayList<Goods>();
			Iterator it=hsm.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry<String, Integer> entry=(Map.Entry<String, Integer>)it.next();
				String key=entry.getKey();
				GoodsService gs=new GoodsService();
				 Goods goods=gs.getGoodsById(key);
				 al.add(goods);
			}
			return al;
			
			
		}
		public String subShoppingCart(Cookie cookie, String id) throws SQLException {
			
			GoodsService gs=new GoodsService();
			Goods goods=gs.getGoodsById(id);
		//将cookie字符串值转成对象
		
		byte [] by=Base64.decodeBase64(cookie.getValue().getBytes());
		
		JSONObject scc=JSONObject.fromObject(new String(by));
		ShoppingCart  shopcart= (ShoppingCart)JSONObject.toBean(scc,ShoppingCart.class);
			//读取
			HashMap<String,Integer> hsm=shopcart.getGoodsList();
			if(hsm.get(id)>1)
			hsm.put(id, hsm.get(id)-1);
			else hsm.remove(id);
			shopcart.setGoodsList(hsm);
		
			//将对象转成json字符串
			shopcart.setTotal_Goods(shopcart.getTotal_Goods()-1);
			shopcart.setTotal_Money(shopcart.getTotal_Money()-goods.getGoodsprice());
			String str=JSONObject.fromObject(shopcart).toString();
			return str;
		}
	public String subShoppingCartById(Cookie cookie, String id) throws SQLException {
			GoodsService gs=new GoodsService();
			Goods goods=gs.getGoodsById(id);
		//将cookie字符串值转成对象
		
		byte [] by=Base64.decodeBase64(cookie.getValue().getBytes());
		
		JSONObject scc=JSONObject.fromObject(new String(by));
		ShoppingCart  shopcart= (ShoppingCart)JSONObject.toBean(scc,ShoppingCart.class);
			//读取
			HashMap<String,Integer> hsm=shopcart.getGoodsList();
			int num=hsm.get(id);
			float money=hsm.get(id)*goods.getGoodsprice();
			hsm.remove(id);
			shopcart.setGoodsList(hsm);
		
			//将对象转成json字符串
			shopcart.setTotal_Goods(shopcart.getTotal_Goods()-num);
			shopcart.setTotal_Money(shopcart.getTotal_Money()-money);
			String str=JSONObject.fromObject(shopcart).toString();
			return str;
			
		}
			
		}
	
	
	
		

