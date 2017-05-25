<%@ page language="java" import="cn.leizhang.service.GoodsService" %>
<%@ page language="java" import="cn.leizhang.domain.Goods" %>
<%@ page language="java" import="cn.leizhang.service.ShoppingCartService" %>
<%@ page language="java" import="net.sf.json.JSONObject" %>
<%@ page language="java" import="cn.leizhang.domain.ShoppingCart" %>
<%@ page language="java" import="org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.HashMap" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%
	GoodsService gs=new GoodsService();
	ArrayList<Goods> arr=gs.getGoods();
	ShoppingCartService scs=new ShoppingCartService();
	//由于本身 Cookie只能接收String类型，故现在利用json将对象序列化，然后存入cookie；
 	Cookie [] cookies=request.getCookies();
	Cookie cookie=scs.cookieSearch(cookies);
 	
	float total_money=0;
	int total_goods=0;
	ArrayList<Goods> al=null;
	Map<String,Integer> hsm=null;
 	if(cookie!=null)
 	{
 		JSONObject scc=JSONObject.fromObject(new String(Base64.decodeBase64(cookie.getValue())));
 		ShoppingCart shopcart= (ShoppingCart)JSONObject.toBean(scc,ShoppingCart.class);
 		total_money=shopcart.getTotal_Money();
 		total_goods=shopcart.getTotal_Goods();
 		hsm=shopcart.getGoodsList();
 		 al=scs.getGoods(hsm);
 		 
 		
 	}
	
	//int sum = 0;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/content.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/cart.css">
<!-- <script type="text/javascript"> -->
<!-- // 	function calc_total(td1) { -->
<!-- // 		var tbody = td1.parentNode.parentNode; -->
<!-- // 		var trs = tbody.children; -->
<!-- // 		var total_num = 0; -->
<!-- // 		var total_price = 0; -->
<!-- // 		for ( var i = 1; i <= trs.length - 3; i++) { -->
<!-- // 			total_price += parseInt(trs[i].lastElementChild.innerHTML); -->
<!-- // 			total_num += parseInt(trs[i].lastElementChild.previousElementSibling.children[1].innerHTML); -->
<!-- // 		} -->
<!-- // 		var td_total_num = document.getElementById("total_num"); -->
<!-- // 		var td_total_price = document.getElementById("total_price"); -->
<!-- // 		td_total_num.innerHTML = total_num + "本"; -->
<!-- // 		td_total_price.innerHTML = total_price + "￥"; -->
<!-- // 	} -->

<!-- // 	function add(a) { -->
<!-- // 		var num = parseInt(a.nextElementSibling.innerHTML); //数量 -->
<!-- // 		var td1 = a.parentNode; -->
<!-- // 		var td_p = td1.previousElementSibling; //单价 -->
<!-- // 		var td_n = td1.nextElementSibling; //总价 -->
<!-- // 		var unitprice = td_p.innerHTML; -->
<!-- // 		var unitprice_num = parseInt(unitprice); -->
<!-- // 		td_n.innerHTML = unitprice_num * (num + 1) + "￥"; -->
<!-- // 		a.nextElementSibling.innerHTML = num + 1; //数量单元格 -->
<!-- // 		calc_total(td1); -->
<!-- // 	} -->

<!-- // 	function sub(a) { -->
<!-- // 		var num = parseInt(a.previousElementSibling.innerHTML); -->
<!-- // 		if (parseInt(a.previousElementSibling.innerHTML) - 1 == 0) { -->
<!-- // 			var p = a.parentNode.parentNode; -->
<!-- // 			p.remove(); -->
<!-- // 		} -->
<!-- // 		var td1 = a.parentNode; -->
<!-- // 		var td_p = td1.previousElementSibling; -->
<!-- // 		var td_n = td1.nextElementSibling; -->
<!-- // 		var unitprice = td_p.innerHTML; -->
<!-- // 		var unitprice_num = parseInt(unitprice); -->
<!-- // 		td_n.innerHTML = unitprice_num * (num - 1) + "￥"; -->
<!-- // 		a.previousElementSibling.innerHTML = num - 1; -->
<!-- // 		calc_total(td1); -->
<!-- // 	} -->
<!-- // </script> -->
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="content">
		<div id="content-left">
			<div class="gray">
				<img src="images/bullet1.gif">我的购物车
			</div>
			<div id="cartcart">
				<table class="cart_table">
					<tbody>
						<tr class="cart_title">
							<td>图书封面</td>
							<td>书名</td>
							<td>单价</td>
							<td>数量</td>
							<td>总价</td>
						</tr>
						<%
						//遍历Map集合
						
							
							if(al!=null)
							{
							for (int i = 0; i < al.size(); i++) {
						%>
						<tr>
							<td><a
								href="ShowDetail?id=<%=al.get(i).getGoodsid()%>"> <img
									src=<%="images/banner/" + al.get(i).getPhoto()%> alt=""
									title="" border="0" class="cart_thumb">
							</a></td>
							<td><%=al.get(i).getGoodsname()%></td>
							<td><%=al.get(i).getGoodsprice()%>￥</td>
							<td><a href="Cart?action=add&id=<%=al.get(i).getGoodsid()%>">+</a> <span><%=hsm.get(al.get(i).getGoodsid())%></span>
								<a href="Cart?action=sub&id=<%=al.get(i).getGoodsid()%>"">-</a></td>
							<td><%=hsm.get(al.get(i).getGoodsid())*al.get(i).getGoodsprice()%>￥</td>
						</tr>
						<%
								
							}
						}
						%>
						<tr>
							<td colspan="4" class="cart_total"><span class="red">总数量:</span>
							</td>
							<td id="total_num"><%=total_goods %> 本</td>
						</tr>
						<tr>
							<td colspan="4" class="cart_total"><span class="red">总共:</span>
							</td>
							<td id="total_price"><%=total_money%>￥</td>
						</tr>
					</tbody>
				</table>
				<a href="books.jsp" class="continue">&lt; 继续</a> <a href="#"
					class="checkout">结算&gt;</a>
			</div>
		</div>
		<jsp:include page="content-right.jsp"></jsp:include>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
