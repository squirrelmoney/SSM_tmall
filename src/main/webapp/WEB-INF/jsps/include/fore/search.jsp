<%--
搜索功能：根据关键字搜索
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>

<a href="${contextPath}">
	<img id="logo" src="${pageContext.request.contextPath}/img/site/logo.gif" class="logo">
</a>

<form action="${pageContext.request.contextPath}/search" method="post" >
	<div class="searchDiv">
		<input name="keyword" type="text" value="${param.keyword}" placeholder="时尚男鞋  太阳镜 ">
		<%--这是用EL表达式取request对象值，相当于request.getParameter("参数");从上一个jsp传过来的参数--%>
		<button  type="submit" class="searchButton">搜索</button>
		<div class="searchBelow">
			<c:forEach items="${category}" var="c" varStatus="st">
				<c:if test="${st.count>=5 and st.count<=8}">
						<span>
							<a href="${pageContext.request.contextPath}/categories/${c.cid}/products">
									${c.name}
							</a>
							<c:if test="${st.count!=8}">
								<span>|</span>
							</c:if>
						</span>
				</c:if>
			</c:forEach>
		</div>
	</div>
</form>

