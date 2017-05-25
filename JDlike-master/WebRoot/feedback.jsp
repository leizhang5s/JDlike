<%@page import="cn.leizhang.domain.FeedBack"%>
<%@page import="cn.leizhang.service.GoodsService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	GoodsService gs = new GoodsService();
	ArrayList<FeedBack> arr = gs.getFeedBacks();
%>
<html>
<head>
<link rel="stylesheet" href="css/feedback.css">
</head>
<body>
	<div id="feedback">
		<%
			for (int i = 0; i < arr.size(); i++) {
		%>
		<div class="item">
			<div class="box_top"></div>
			<div class="box_center">
				<div class="orange"></div>
				<p class="details">
					<font color=blue>
						<%=arr.get(i).getIp()%>
						
						
						
					</font><br><%= arr.get(i).getSubject()%><br>
					<%=arr.get(i).getMsg()%>
				</p>
			</div>
			<div class="box_bottom"></div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>