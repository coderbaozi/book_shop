<%--
  Created by IntelliJ IDEA.
  User: QZY
  Date: 2022/6/4
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页条--%>
<div id="page_nav">
    <%--如果是第一页没有首页和上一页--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <c:choose>
        <%--情况一、如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
        <c:when test="${requestScope.page.pageTotal<=5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
        </c:when>
        <%--情况二、总页码大于5的情况，--%>
        <c:when test="${requestScope.page.pageTotal>5}">
            <c:choose>
                <%--小情况1、当前页码是前三个--%>
                <c:when test="${requestScope.page.pageNo<=3}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                </c:when>
                <%--小情况2、页码为后面三个--%>
                <c:when test="${requestScope.page.pageNo>=requestScope.page.pageTotal-2}">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                </c:when>
                <%--小情况3，页码为中间，当前页面-2 到当前页码+2--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>

                </c:otherwise>
            </c:choose>

        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>
    <%--如果到了最后一页，则没有下一页和末页--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="serchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            //跳到指定的页码，
            $("#serchPageBtn").click(function () {
                var pageNo=$("#pn_input").val();
                var pageTotal=${requestScope.page.pageTotal}
                //javascript语言提供了一个location地址栏对象
                //它的一个属性href，可以获取浏览器地址栏中的地址
                //href属性，可读，可写，赋值就相当于页面条状
                if(pageNo>0&&pageNo<=pageTotal){
                    location.href="${pageContext.getAttribute("basePath")}${requestScope.page.url}&pageNo="+pageNo;
                }else{
                    alert("请输入正确的页码！");
                }
            });
        });
    </script>
</div>
<%--分页条结尾--%>
