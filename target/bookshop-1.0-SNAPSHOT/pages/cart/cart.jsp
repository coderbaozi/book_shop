<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<!--静态包含base标签，css样式，JQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				//给删除操作绑定单击事件
				$("a.deleteItem").click(function () {
					return confirm("你确定要删除 "+$(this).parent().parent().find("td:first").text()+" 吗？")
				});
				$("a.clearAll").click(function () {
					return confirm("你确定要清空购物车吗？")
				});
				// 给输入框绑定失去焦点事件
				$(".updateCount").change(function () {
					var name=$(this).parent().parent().find("td:first").text();
					var count=this.value;
					var id=$(this).attr("bookId");
					if(confirm("你确定要将 "+name+" 修改为"+count+"吗？")){
						//发起请求，给服务器保存修改
						location.href="${pageContext.getAttribute("basePath")}cartServlet?action=updateItem&count="+count+"&id="+id;
					}else{
						//defaultValue是表单项Dom对象的属性，它表示默认的value属性值。
						this.value=this.defaultValue;
					}
				});
			});
		</script>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
<%--			如果购物车为空给提示--%>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">抱歉您还没有添加任何商品到购物车！</a></td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 80px" value="${entry.value.count}" bookId="${entry.value.id}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

			
		</table>
<%--		如果购物车中没有商品则不需要输出这些信息--%>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a class="clearAll" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		</c:if>
	
	</div>


	<!--静态包含页脚内容-->
	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>