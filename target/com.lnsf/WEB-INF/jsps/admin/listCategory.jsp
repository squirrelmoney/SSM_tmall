<%--
 分类列表
 功能：分页
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<meta http-equiv="Content-Type"; content="multipart/form-data; charset=utf-8"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
<%@include file="../../../js/jquery/AJAX/FileUpload.jsp"%>
<script type="text/javascript">
    var uselessName;
    function checkname(){
        if ($("#name").val().length!=0) {
            var name = $("#name").val();
            var json = JSON.stringify({
                "cid": " ",
                "name": name,
                "cpiturepath": " "
            });
            $.ajax({
                type:'POST',/* 请求类型为post */
                url:'${pageContext.request.contextPath}/CheckCategoryname',/* 请求路径 */
                contentType:'application/json;charset=UTF-8',/* 向服务器提出的请求类型 */
                dataType: 'json',
                data:json,/* 请求的数据 */
                async:false,
                success: function(data) {/* 回调函数 */
                    if (data==null){
                        uselessName="";
                    }else{
                        alert("名字已存在，请更改");
                        uselessName=data['name'];
                    }

                },
                error:  function () {
                   uselessName="";
                }

            });
        }
        $("#fileForm").submit(function(){
            if(uselessName!=""){
                alert("名字已存在，请更改");
                return false;
            }
        });
    }

    $(function(){
        $("#fileForm").submit(function(){
            if(!checkEmpty("name","分类名称"))
                return false;
            if(!checkEmpty("pictureFile","分类图片"))
                return false;
            return true;
        });
    });
</script>
<title>分类管理</title>

<div class="workingArea">
    <h1 class="label label-info" >分类管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>分类名称</th>
                <th>属性管理</th>
                <th>产品管理</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="c">

                <tr>
                    <td>${c.cid}</td>
                    <td><img height="40px" src="${pageContext.request.contextPath}/${c.cpiturepath}"></td>
                    <td>${c.name}</td>

                    <td><a href="${pageContext.request.contextPath}/categories/${c.cid}/property"><span class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a href="${pageContext.request.contextPath}/products/${c.cid}"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                    <td><a href="${pageContext.request.contextPath}/categories/${c.cid}/edit"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" href="${pageContext.request.contextPath}/categories/${c.cid}/del" ><span class="glyphicon glyphicon-trash"></span></a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增分类</div>
        <div class="panel-body">
                <form id="fileForm" action="${pageContext.request.contextPath }/categories" method="post" enctype="multipart/form-data">
                <table class="addTable">
                    <td><input  id="cid" name="cid" type="hidden"></td>
                    <tr>
                        <td>分类名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control" onchange="checkname();"></td>
                    </tr>
                    <tr>
                        <td>圖片</td>
                        <td>
                            <div width="80" height="80">
                                <img id="myimg" name="myimg"  src="" width="80" height="80"/>
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

<%@include file="../include/admin/adminFooter.jsp"%>