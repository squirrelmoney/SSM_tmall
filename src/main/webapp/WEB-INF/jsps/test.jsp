<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/22
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type"; content="multipart/form-data; charset=utf-8"/>

    <title>Insert title here</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery/jquery.form.js"></script>

    <script type="text/javascript">
        function fileOnchage(){
            var option = {
                type:"post",
                data:{pictureFile:'pictureFile'},
                dataType:"json",
                url:"${pageContext.request.contextPath }/uploadPic",
                success:function(data){
                    //String格式json，转json对象
                    alert(data['realPath']);
                    var path=data['realPath'];
                    $("#picImg").append("<img id='myImg'  src='"+path+"'/>");

                }
            }
            $("#fileForm").ajaxSubmit(option);
        }
    </script>
</head>
<body>
<form id="fileForm" method="post" enctype="multipart/form-data">
    <div id="picImg"></div>
    <input type="file" name="pictureFile" id="pictureFile" value="请选择"  onchange="fileOnchage()"/>
</form>
</body>
</html>