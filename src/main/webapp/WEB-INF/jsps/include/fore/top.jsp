<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top ">
	<a href="${pageContext.request.contextPath }/home" methods="get">
		<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
		天猫首页
	</a>
		<span>喵，欢迎来天猫</span>
			<c:if test="${!empty user}">
			<a href="loginPage">${user.name}</a>
			<a href="${pageContext.request.contextPath }/signout">退出</a>
		  </c:if>

	<c:if test="${empty user}">
		<a href="${pageContext.request.contextPath }/signin" methods="get">请登录</a>
		<a href="${pageContext.request.contextPath }/signup" methods="get">免费注册</a>
	</c:if>


	<span class="pull-right">
			<a href="${pageContext.request.contextPath}/${user.uid}/orders">我的订单</a>
			<a href="${pageContext.request.contextPath }/${user.uid}/forecart">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
			购物车<strong>${cartTotalItemNumber}</strong>件</a>
		</span>


</nav>



