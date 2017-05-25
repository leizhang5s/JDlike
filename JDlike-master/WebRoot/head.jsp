<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="cn.leizhang.domain.User"%>
<%@ page language="java" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<img src="images/logo.gif">
		<%
			boolean isLogin = false;
			User user = (User) request.getSession().getAttribute("User");
			if (user != null)
				isLogin = true;
		%>
		<%
			if (isLogin) {
		%>
		<div class="welcome">
			<a href="myaccount.jsp">欢迎你，<%=user.getUsername()%></a><span >&nbsp;&nbsp;角色:<font color="blue"><%=user.getGrade() %></font></span>&nbsp;&nbsp;<a
				href="LogOut">注销</a>
		</div>
		<%
			} else {
		%>
		<div id="login">
			<form action="login" method="post">
				<div>
					账号：<input type="text" name="username"><br /> 密码：<input
						type="password" name="password">
				</div>
				<div id="denglu">
					<input type="submit" value="登陆">
				</div>
			</form>

		</div>
		<%
			}
		%>
		<div id="menu">
			<ul>
				<li><a href="index.jsp"
					<%if (request.getRequestURI().contains("index.jsp")) {%>
					class="selected" <%}%>>主页</a>
				</li>
				<li><a href="about.jsp"
					<%if (request.getRequestURI().contains("about.jsp")) {%>
					class="selected" <%}%>>关于</a>
				</li>
				<li><a href="books.jsp"
					<%if (request.getRequestURI().contains("books.jsp")) {%>
					class="selected" <%}%>>书籍</a>
				</li>
				<li><a href="special.jsp"
					<%if (request.getRequestURI().contains("special.jsp")) {%>
					class="selected" <%}%>>特别推荐</a>
				</li>
				<li><a href="myaccount.jsp"
					<%if (request.getRequestURI().contains("myaccount.jsp")) {%>
					class="selected" <%}%>>我的账号</a>
				</li>
				<li><a href="register.jsp"
					<%if (request.getRequestURI().contains("register.jsp")) {%>
					class="selected" <%}%>>注册</a>
				</li>
				<li><a href="contact.jsp"
					<% if(request.getRequestURI().contains("contact.jsp")){ %>
					class="selected" <% } %>>留言</a>
				</li>
			</ul>
		</div>
	</div>

</body>
</html>