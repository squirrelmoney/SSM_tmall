<%--
获取分类下的所有产品
功能：分页
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/4/19
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<c:if test="${empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>
	
<div class="categoryProducts">
	<c:forEach items="${c.products}" var="p" varStatus="stc">
		<c:if test="${stc.count<=categorycount}">
		<div class="productUnit" price="${p.promoteprice}">
			<div class="productUnitFrame">
				<a href="foreproduct?pid=${p.pid}">
					<img class="productImage" src="${pageContext.request.contextPath}/${p.productimage.path}">
				</a>
				<span class="productPrice">¥<fmt:formatNumber type="number" value="${p.promoteprice}" minFractionDigits="2"/></span>
				<a class="productLink" href="foreproduct?pid=${p.pid}">
				 ${fn:substring(p.name, 0, 50)}
				</a>
				
				<a  class="tmallLink" href="foreproduct?pid=${p.pid}">天猫专卖</a>
	
				<div class="show1 productInfo">
					<span class="monthDeal ">月成交 <span class="productDealNumber">${p.salecount}笔</span></span>
					<span class="productReview">评价<span class="productReviewNumber">${p.reviewcount}</span></span>
					<span class="wangwang">
					<a class="wangwanglink" href="#nowhere">
						<img src="img/site/wangwang.png">
					</a>
					
					</span>
				</div>
			</div>
		</div>
		</c:if>
	</c:forEach>
		<div style="clear:both"></div>
</div>