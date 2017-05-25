<%@page import="cn.leizhang.domain.Goods"%>
<%@page import="cn.leizhang.service.GoodsService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	GoodsService gs=new GoodsService();
	//首先定义一页可以放多少条记录
	int pagesize=2;
	//设置起始页面为1
	int currentpage=1;
	ArrayList<Goods> arr=gs.getBetweenGoods(1, 1*2);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/content.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/books.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="content">
		<div id="content-left">
			<div id="books">
				<div class="title">
					<a href="index.jsp">主页</a>>> 所有书籍
				</div>
				<div class="gray">
					<img src="images/bullet1.gif">全部书籍
				</div>
				<% 
				for(int i=0;i<arr.size();i++)
				{
				%>
				<div class="book">
					<div class="title"><%=arr.get(i).getGoodsname()%></div>
					<div class="book-box">
						<a href=<%="ShowDetail?id="+arr.get(i).getGoodsid()%>> <img
							src=<%="images/banner/"+arr.get(i).getPhoto()  %>>
						</a>
					</div>
				</div>
				<%} %>
				<div class="pagination">
					
				

					
				</div>
			</div>
		</div>
		<jsp:include page="content-right.jsp"></jsp:include>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
