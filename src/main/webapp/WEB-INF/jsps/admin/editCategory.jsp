<%--
  编辑分类列表
  功能：跨服务器文件上传
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



<title>编辑分类</title>

<%@include file="../../../js/jquery/AJAX/FileUpload.jsp"%>
<script>
    $(function(){

        $("#editForm").submit(function(){
            if(!checkEmpty("name","分类名称"))
                return false;

            return true;
        });
    });

</script>

<div class="workingArea">

	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/categories">所有分类</a></li>
		<li class="active">编辑分类</li>
	</ol>

	<div class="panel panel-warning editDiv">
		<div class="panel-heading">编辑分类</div>
		<div class="panel-body">
			<form method="post" id="fileForm" action="${pageContext.request.contextPath}/categories"  enctype="multipart/form-data">
				<table class="editTable">
					<tr>
						<td>id</td>
						<td><input type="text" readonly="readonly" name="cid" value="${c.cid}"></td>
					</tr>

					<tr>
						<td>分类名称</td>
						<td><input  id="name" name="name" value="${c.name}" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td>圖片</td>
						<td>
							<div width="180" height="80">
								<img id="myimg" name="myimg"  src="${pageContext.request.contextPath}/${c.cpiturepath}" width="180" height="80"/>
							</div>
						</td>
					</tr>
					<tr>
						<td>分类圖片</td>
						<td>
							<input type="file" name="pictureFile" accept="image/*" id="pictureFile" value=""  onchange="fileOnchage()"/>
							<input type="hidden" name="cpiturepath"  id="cpiturepath" value="" >
						</td>
					</tr>
					<tr class="submitTR">
						<td colspan="2" align="center">
							<button type="submit" class="btn btn-success">提 交</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>