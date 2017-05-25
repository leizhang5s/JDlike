<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="cn.leizhang.service.GoodsService" %>
<%@ page language="java" import="cn.leizhang.domain.Goods" %>
<%@ page language="java" import="cn.leizhang.service.ShoppingCartService" %>
<%@ page language="java" import="net.sf.json.JSONObject" %>
<%@ page language="java" import="cn.leizhang.domain.ShoppingCart" %>
<%@ page language="java" import="org.apache.commons.codec.binary.Base64"%>

<%
	GoodsService gs=new GoodsService();
	ArrayList<Goods> arr=gs.getGoods();
	ShoppingCartService scs=new ShoppingCartService();
	//由于本身 Cookie只能接收String类型，故现在利用json将对象序列化，然后存入cookie；
 	Cookie [] cookies=request.getCookies();
	Cookie cookie=scs.cookieSearch(cookies);
 	
	float total_money=0;
	int total_goods=0;
 	if(cookie!=null)
 	{
 		JSONObject scc=JSONObject.fromObject(new String(Base64.decodeBase64(cookie.getValue())));
 		ShoppingCart shopcart= (ShoppingCart)JSONObject.toBean(scc,ShoppingCart.class);
 		total_money=shopcart.getTotal_Money();
 		total_goods=shopcart.getTotal_Goods();
 	}
 %>
<div id="content-right">
	<div id="cart">
		<img src="images/cart.gif">我的购物车
	</div>
	<div id="total">
		
		<%=total_goods %>x 物品 <span class="red">| 总共: <%=total_money %>￥
		</span>
	</div>
	<div id="view">
		<a href="cart.jsp" class="red">查看购物车</a>
	</div>
	<div class="middle">
		<img src="images/border.gif">
	</div>
	<div class="middle" id="banner">
		<form action="BookSearch" method="post">
		<input name="searchfield" type="text">
		<input type="submit" value="搜索">
		</form>
	</div>
	<div class="middle">
		<img src="images/border.gif">
	</div>
	<br />
	<div id="bottom" class="gray">
		<div id="left">
			<img src="images/bullet4.gif">促销:
			<% for(int i=0;i<2;i++) { %>
			<div class="promotebook">
				<div class="title"></div>
				<div class="promotebook-box">
					<a href=<%="ShowDetail?id="+arr.get(i).getGoodsid() %>>
						<span class="promote"> <img src="images/promo_icon.gif">
					</span> <img src="images/banner/<%= arr.get(i).getPhoto()%>">
					</a>
				</div>
			</div>
			<% }%>
		</div>
	</div>
	<div id="right">
		<img src="images/bullet3.gif">种类:
		<table>
			<tr>
				<td><a href="##">C++</a></td>
			</tr>
			<tr>
				<td><a href="##">JAVA</a></td>
			</tr>
			<tr>
				<td><a href="##">JAVASCRIPT</a></td>
			</tr>
			<tr>
				<td><a href="##">ASP .NET</a></td>
			</tr>
			<tr>
				<td><a href="##">AJAX</a></td>
			</tr>
		</table>
		<div>
			<img src="images/bullet6.gif">新用户:
			<table class="flag">
				
				<tr>
					<td></td>
				</tr>
				
			</table>
		</div>
	</div>
</div>