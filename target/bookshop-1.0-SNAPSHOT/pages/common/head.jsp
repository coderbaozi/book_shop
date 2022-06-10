<%--
  Created by IntelliJ IDEA.
  User: QZY
  Date: 2022/5/29
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath=request.getScheme()//得到协议http
            +"://"
            +request.getServerName()//得到服务器的ip
            +":"
            +request.getServerPort()//得到服务器端口号
            +request.getContextPath()//工程路径
            +"/";
    pageContext.setAttribute("basePath",basePath);
%>
<!--写base标签永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
