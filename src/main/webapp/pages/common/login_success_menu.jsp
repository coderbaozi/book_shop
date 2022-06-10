<%--
  Created by IntelliJ IDEA.
  User: QZY
  Date: 2022/5/29
  Time: 20:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
    <span>欢迎<span class="um_span">${sessionScope.user.username==null?"您":sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
    </div>
</body>
</html>
