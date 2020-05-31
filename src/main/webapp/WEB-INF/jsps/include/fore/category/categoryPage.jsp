<%--
根据分类，查找分类下的所有产品
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/4/19
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<title>模仿天猫官网-${c.name}</title>	
<div id="category">
	<div class="categoryPageDiv">
		<img src="${pageContext.request.contextPath}/${c.cpiturepath}">
		<%@include file="sortBar.jsp"%>
		<%@include file="productsByCategory.jsp"%>
	</div>

</div>