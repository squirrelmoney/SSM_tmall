<%--
  订单列表
  功能：分页功能
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
    $(function(){
        $("button.orderPageCheckOrderItems").click(function(){
            var oid = $(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });

</script>

<title>订单管理</title>


<div class="workingArea">
	<h1 class="label label-info" >订单管理</h1>
	<br>
	<br>


	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover1  table-condensed">
			<thead>
			<tr class="success">
				<th>ID</th>
				<th>状态</th>
				<th>金额</th>
				<th width="100px">商品数量</th>
				<th width="100px">买家名称</th>
				<th>创建时间</th>
				<th>支付时间</th>
				<th>发货时间</th>
				<th>确认收货时间</th>
				<th width="120px">操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${ordersList}" var="o">
				<tr>
					<td>${o.oid}</td>
					<c:if test="${o.status=='waitPay'}">
						<td>待付款</td>
					</c:if>
					<c:if test="${o.status=='waitDelivery'}">
						<td>等待发货</td>
					</c:if>
					<c:if test="${o.status=='waitConfirm'}">
						<td>待收货</td>
					</c:if>
					<c:if test="${o.status=='waitReview'}">
						<td>待评价</td>
					</c:if>


					<td>￥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></td>
					<td align="center">${o.numberforone}</td>
					<td align="center">${o.user.name}</td>

					<td><fmt:formatDate value="${o.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${o.paydate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${o.deliverydate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${o.confirmdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

					<td>
						<button oid="${o.oid}" class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>

						<c:if test="${o.status=='waitDelivery'}">
							<a href="${pageContext.request.contextPath}/deliver?oid=${o.oid}">
								<button class="btn btn-primary btn-xs">发货</button>
							</a>
						</c:if>
					</td>
				</tr>
				<tr class="orderPageOrderItemTR"  oid=${o.oid}>
					<td colspan="10" align="center">

						<div  class="orderPageOrderItem">
							<table width="800px" align="center" class="orderPageOrderItemTable">
								<c:forEach items="${o.orderitems}" var="oi">
									<tr>
										<td align="left">
											<img width="40px" height="40px" src="${pageContext.request.contextPath}/${oi.product.productimage.path}">
										</td>

										<td>
											<a href="foreproduct?pid=${oi.product.pid}">
												<span>${oi.product.name}</span>
											</a>
										</td>
										<td align="right">

											<span class="text-muted">${oi.number}个</span>
										</td>
										<td align="right">

											<span class="text-muted">单价：￥${oi.product.promoteprice}</span>
										</td>

									</tr>

								</c:forEach>

							</table>
						</div>

					</td>
				</tr>
			</c:forEach>

			</tbody>
		</table>
	</div>
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
</div>
<%@include file="../include/admin/adminFooter.jsp"%>
