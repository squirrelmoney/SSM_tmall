<%--
  单个产品的图片列表
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
<%@include file="../../../js/jquery/AJAX/FileUpload.jsp"%>
<script>
    $(function(){
        $(".addFormSingle").submit(function(){
            if(checkEmpty("filepathSingle","图片文件")){
                $("#filepathSingle").value("");
                return true;
            }
            return false;
        });
        $(".addFormDetail").submit(function(){
            if(checkEmpty("filepathDetail","图片文件"))
                return true;
            return false;
        });
    });

</script>

<title>产品图片管理</title>


<div class="workingArea">
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/categories">所有分类</a></li>
		<li><a href="${pageContext.request.contextPath}/products/${c.cid}">${c.name}</a></li>
		<li class="active">${p.name}</li>
		<li class="active">产品图片管理</li>
	</ol>

	<table class="addPictureTable" align="center">
		<tr>
			<td class="addPictureTableTD">
				<div>
					<div class="panel panel-warning addPictureDiv">
						<div class="panel-heading">新增产品<b class="text-primary"> 单个 </b>图片</div>
						<div class="panel-body">
							<form method="post" class="addFormSingle" id="fileForm" action="${pageContext.request.contextPath}/products/productimage" enctype="multipart/form-data">
								<table class="addTable">
									<tr>
										<td>请选择本地图片 尺寸400X400 为佳</td>
									</tr>
									<tr>
										<td>
											<div width="150" height="60">
												<img id="myimg" name="myimg"  src="" width="150" height="60"/>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<input id="filepathSingle" type="file" name="pictureFile" onchange="fileOnchage();"/>
											<input type="hidden" name="path"  id="cpiturepath" value="" >
										</td>
									</tr>
									<tr class="submitTR">
										<td align="center">
											<input type="hidden" name="type" value="type_single" />
											<input type="hidden" name="pid" value="${p.pid}" />
											<input type="hidden" name="cid" value="${c.cid}">
											<button type="submit" class="btn btn-success">提 交</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover  table-condensed">
						<thead>
						<tr class="success">
							<th>ID</th>
							<th>产品单个图片缩略图</th>
							<th>删除</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${imgsingle}" var="pi"  begin="0" end="50" step="1" >
							<tr>
								<td>${pi.id}</td>
								<td>
									<a title="点击查看原图" href="${pageContext.request.contextPath}/${pi.path}"><img height="50px" src="${pageContext.request.contextPath}/${pi.path}"></a>
								</td>
								<td><a deleteLink="true"
									   href="${pageContext.request.contextPath}/products/${c.cid}/${pi.pid}/productimage/${pi.pid}"><span
										class=" 	glyphicon glyphicon-trash"></span></a></td>

							</tr>

						</c:forEach>
						</tbody>
					</table>

				</div>
			</td>
			<td class="addPictureTableTD">
				<div>

					<div class="panel panel-warning addPictureDiv">
						<div class="panel-heading">新增产品<b class="text-primary"> 详情 </b>图片</div>
						<div class="panel-body">
							<form method="post" id="fileFormtwo" class="addFormDetail" action="${pageContext.request.contextPath}/products/productimage" enctype="multipart/form-data">
								<table class="addTable">
									<tr>
										<td>请选择本地图片 宽度790  为佳</td>
									</tr>
									<tr>
										<td>
											<div width="150" height="60">
												<img id="myimgtwo" name="myimg"  src="" width="150" height="60"/>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<input id="filepathDetail"  type="file" name="pictureFile" onchange="fileOnchagetwo()"/>
											<input type="hidden" name="path"  id="cpiturepathtwo" value="" >
										</td>
									</tr>
									<tr class="submitTR">
										<td align="center">
											<input type="hidden" name="type" value="type_detail" />
											<input type="hidden" name="pid" value="${p.pid}" />
											<input type="hidden" name="cid" value="${c.cid}">
											<button type="submit" class="btn btn-success">提 交</button>
										</td>
									</tr>
								</table>
							</form>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover  table-condensed">
						<thead>
						<tr class="success">
							<th>ID</th>
							<th>产品详情图片缩略图</th>
							<th>删除</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${imgdetail}" var="pi" begin="0" end="50" step="1">
							<tr>
								<td>${pi.id}</td>
								<td>
									<a title="点击查看原图" href="${pageContext.request.contextPath}/${pi.path}"><img height="50px" src="${pageContext.request.contextPath}/${pi.path}"></a>
								</td>
								<td><a deleteLink="true"
									   href="${pageContext.request.contextPath}/products/${c.cid}/${pi.pid}/productimage/${pi.id}"><span
										class=" 	glyphicon glyphicon-trash"></span></a></td>

							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</table>





</div>

<%@include file="../include/admin/adminFooter.jsp"%>
