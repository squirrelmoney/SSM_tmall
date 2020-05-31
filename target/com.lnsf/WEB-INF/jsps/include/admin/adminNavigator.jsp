<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="navitagorDiv">
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<img style="margin-left:10px;margin-right:0px" class="pull-left" src="${pageContext.request.contextPath}/img/site/tmallbuy.png" height="45px">
		<a class="navbar-brand" href="#nowhere">天猫后台</a>
		
		<a class="navbar-brand" href="${pageContext.request.contextPath}/categories">分类管理</a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/users">用户管理</a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/orders">订单管理</a>
	</nav>
</div>