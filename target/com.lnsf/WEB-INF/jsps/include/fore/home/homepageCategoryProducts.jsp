<%--
分类下的产品：按照每种分类显示5个商品（标题、价格）的方式，显示分类下的产品（链接产品页）
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/4/19
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
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

<div class="homepageCategoryProducts">
	<c:forEach items="${category}" var="c" varStatus="stc">
		<c:if test="${stc.count<=categorycount}">
			<div class="eachHomepageCategoryProducts">
				<div class="left-mark"></div>
				<span class="categoryTitle">${c.name}</span>
				<br>
				<c:forEach items="${c.products}" var="p" varStatus="st">
					<c:if test="${st.count<=5}">
						<div class="productItem" >
							<a href="${pageContext.request.contextPath}/products/${p.pid}/details"><img width="100px" src="${pageContext.request.contextPath}/${p.productimage.path}"></a>
							<a class="productItemDescLink" href="${pageContext.request.contextPath}/products/${p.pid}/details">
								<span class="productItemDesc">[热销]
								${fn:substring(p.name, 0, 20)}
								</span>
						    </a>
							<span class="productPrice">
								<fmt:formatNumber type="number" value="${p.promoteprice}" minFractionDigits="2"/>
							</span>
						</div>
					</c:if>
				</c:forEach>
				<div style="clear:both"></div>
			</div>
		</c:if>
	</c:forEach>


	<img id="endpng" class="endpng" src="${pageContext.request.contextPath }/img/site/end.png">

</div>