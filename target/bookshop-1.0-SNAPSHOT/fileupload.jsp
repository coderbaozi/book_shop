<%--
  Created by IntelliJ IDEA.
  User: QZY
  Date: 2022/5/28
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form enctype="multipart/form-data" action="http://localhost:8080/book_shop/fileupload" method="post">
    请输入用户名：<input type="text" name="username"><br>
    上传文件<input type="file" name="photo"><br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>
