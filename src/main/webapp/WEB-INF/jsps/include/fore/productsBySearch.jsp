<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="searchProducts">

	<c:forEach items="${ps}" var="p">
	<div class="productUnit" price="${p.promoteprice}">
		<a href="${pageContext.request.contextPath}/products/${p.pid}/details">
			<img class="productImage" src="${pageContext.request.contextPath}/${p.productimage.path}">
		</a>
		<span class="productPrice">¥<fmt:formatNumber type="number" value="${p.productimage.id}" minFractionDigits="2"/></span>
		<a class="productLink" href="foreproduct?pid=${p.pid}">
				${fn:substring(p.name, 0, 50)}
		</a>

		<a class="tmallLink" href="foreproduct?pid=${p.pid}">天猫专卖</a>

		<div class="show1 productInfo">
			<span class="monthDeal ">月成交 <span class="productDealNumber">${p.salecount}笔</span></span>
			<span class="productReview">评价<span class="productReviewNumber">${p.reviewcount}</span></span>
			<span class="wangwang"><img src="img/site/wangwang.png"></span>
		</div>

	</div>
	</c:forEach>
	<c:if test="${empty ps}">
	<div class="noMatch">没有满足条件的产品<div>
		</c:if>

		<div style="clear:both"></div>
	</div>
	</div>