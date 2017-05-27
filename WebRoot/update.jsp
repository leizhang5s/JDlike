<%@ page language="java"
	import="java.util.*,cn.leizhang.domain.User,javax.servlet.http.HttpSession"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	
	User user=(User)request.getSession().getAttribute("User");
	
	boolean isLogin = false;
	if (user != null) {
		isLogin = true;
	}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/content.css">
<link rel="stylesheet" href="css/footer.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/myaccount.css">
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="content">
		<div id="content-left">
			<div class="gray">
				<img src="images/bullet1.gif">我的账户
			</div>
			<%
				if (!isLogin) {
			%>
			  <div id="loginlogin">
                <form action="login" method="post">
                <%
 						if (request.getAttribute("error")!= null) {
 							
 									out.println("<div style='font-size:15px;margin:3px 0 3px 1px;color:red;'>用户名或密码错误!</div>");
							
						}

					%>
                    <div class="login_title">登入你的账户</div>
                    <div class="row1">
                        <label>用户名:</label>&nbsp;
                        <input name="username">
                    </div>
                    <div class="row2">
                        <label>密&nbsp;&nbsp;码:</label>&nbsp;
                        <input type="password" name="password">
                    </div>
                    <div class="row3">
                        <input type="checkbox" name="remember">记住我</input>
                    </div>
                    <div class="row4">
                        <input type="submit" value="登陆">
                    </div>
                </form>
            </div>
			<%
				} else {
			%>
			 <div id="myaccount">
                <div class="item">
                    <div class="box_top"></div>
                    <div class="box_center">
                    <form action="<%="updateuser?id="+user.getUserid()%>" method="post">
                        <table>
                            <tr>
                                <td class="left">我的账号:</td>
                                <td>
                                    <span class="username"><%=user.getUsername() %></span>
                                </td>
                            </tr>
                            <tr>
                                <td class="left">真实姓名:</td>
                                <td><input type="text" name="truename" value="<%=user.getTruename()%>"></td>
                            </tr>
                            <tr>
                                <td class="left">电子邮件:</td>
                                <td><input type="text" name="email" value="<%=user.getEmail()%>"></td>
                            </tr>
                            <tr>
                                <td class="left">电话号码:</td>
                                <td><input type="text" name="phone" value="<%=user.getPhone()%>"></td>
                            </tr>
                            <tr>
                                <td class="left">用户住址:</td>
                                <td><input type="text" name="address" value="<%=user.getAddress_()%>"></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="imgtd">
                                    <hr>我就是传说中的分割线
                                    <hr>
                                </td>
                            </tr>
                            <tr>
                                <td>用户级别:</td>
                                <td>RANK 1</td>
                            </tr>
                             <tr >
                             		
                                <td ><input type="submit" value="提交"></td>
                               
                            </tr>
                        </table>
                        </form>
                    </div>
                    <div class="box_bottom"></div>
                </div>

            </div>
            <img src="images/bookstore.png" class="touming">
			<%
				}
			%>
		</div>
		<jsp:include page="content-right.jsp"></jsp:include>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>
