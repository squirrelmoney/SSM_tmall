<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<script>
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});

</script>


<nav>
  <ul class="pagination">
    <li <c:if test="${page.currentPage==1}">class="disabled"</c:if>>
      <a  href="?start=0" aria-label="Previous" >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    <li <c:if test="${page.currentPage==1}">class="disabled"</c:if>>
      <a  href="?start=${page.currentPage-1}" aria-label="Previous" >
        <span aria-hidden="true">&lsaquo;</span>
      </a>
    </li>    

  <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">

		    <li <c:if test="${status.index*page.pageNumber==page.dbIndex}">class="disabled"</c:if>>
		    	<a  
		    	href="?start=${status.count}"
		    	<c:if test="${status.index*page.pageNumber==page.dbIndex}">class="current"</c:if>
		    	>${status.count}</a>
		    </li>
		
    </c:forEach>

    <li <c:if test="${page.currentPage==page.totalPage}">class="disabled"</c:if>>
      <a href="?start=${page.currentPage+1}" aria-label="Next">
        <span aria-hidden="true">&rsaquo;</span>
      </a>
    </li>
    <li <c:if test="${page.currentPage==page.totalPage}">class="disabled"</c:if>>
      <a href="?start=${page.totalPage}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
