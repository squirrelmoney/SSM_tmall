<%--
分类侧边栏的内容：当鼠标移动到某一个纵向分类连接的时候，显示这个分类下的推荐商品
展示产品名称时，限定一行最多有6个标题，但一个分类下有n（n可能大于6）个产品，所以在业务逻辑那一步，把分类下的所有产品先
以每6个一组存入List<product>中,然后再把此存入List<List<product>>
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/4/19
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script>
$(function(){
	$("div.productsAsideCategorys div.row a").each(function(){
		var v = Math.round(Math.random() *6);
		if(v == 1)
			$(this).css("color","#87CEFA");
	});
});

</script>
<c:forEach items="${category}" var="c">
	<div cid="${c.cid}" class="productsAsideCategorys">

		<c:forEach items="${c.productsList}" var="ps">
			<div class="row show1">
				<c:forEach items="${ps}" var="p">
					<%--<p>${p.subtitle}</p>--%>
					<c:if test="${!empty p.subtitle}">
						<a href="${pageContext.request.contextPath}/products/${p.pid}/details">
							<c:forEach items="${fn:split(p.subtitle, ' ')}" var="title" varStatus="st">
								<c:if test="${st.index==0}">
									${title}
								</c:if>
							</c:forEach>
						</a>
					</c:if>
				</c:forEach>
				<div class="seperator"></div>
			</div>		
		</c:forEach>
	</div>			
</c:forEach>
	
