<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/4/10
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>模仿天猫官网</title>
<div class="categoryPictureInProductPageDiv">
	<img class="categoryPictureInProductPage" src="${pageContext.request.contextPath}/${c.cpiturepath}">
</div>

<div class="productPageDiv">

	<%@include file="imgAndInfo.jsp" %>

	<%@include file="productReview.jsp" %>

	<%@include file="productDetail.jsp" %>
	

</div>