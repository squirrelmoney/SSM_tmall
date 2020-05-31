<%--
注册功能
功能：ajax查找后台是否有同名，实现主键限制
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/15
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js" type="text/javascript"></script>

<script>
	var uselessName;
	function checkname(){
		if ($("#name").val().length!=0) {
			var username = $("#name").val();
			var json = JSON.stringify({
				"uid": " ",
				"name": username,
				"passWord": " "
			});
			$.ajax({
				type:'POST',/* 请求类型为post */
				url:'${pageContext.request.contextPath}/CheckUsername',/* 请求路径 */
				contentType:'application/json;charset=UTF-8',/* 向服务器提出的请求类型 */
				dataType: 'json',
				data:json,/* 请求的数据 */
				async:false,
				success: function(data) {/* 回调函数 */
					$("span.errorMessage").html("名字已存在，请更改");
					$("div.registerErrorMessageDiv").css("visibility", "visible");
					uselessName=data['name'];
				},
				error:  function () {
					$("span.errorMessage").html("名字可用");
					$("div.registerErrorMessageDiv").css("visibility", "visible");
					uselessName="";
				}

			});
		}
		$(".registerForm").submit(function(){
			if(uselessName!=""){
				$("span.errorMessage").html("请更改名字");
				$("div.registerErrorMessageDiv").css("visibility","visible");
				return false;
			}
		});
	}

    $(function(){
		<c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div.registerErrorMessageDiv").css("visibility","visible");
        </c:if>/*显示上个页面或者操作带来的错误信息*/
        $(".registerForm").submit(function(){
            if(0==$("#name").val().length){
                $("span.errorMessage").html("请输入用户名");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }

            if(0==$("#password").val().length){
                $("span.errorMessage").html("请输入密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }

            if(0==$("#repeatpassword").val().length){
                $("span.errorMessage").html("请输入重复密码");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }
            if($("#password").val() !=$("#repeatpassword").val()){
                $("span.errorMessage").html("重复密码不一致");
                $("div.registerErrorMessageDiv").css("visibility","visible");
                return false;
            }

            return true;
        });

    })


function checkPassword() {
	var judge = /^[a-zA-Z0-9]{6,12}$/;
	if ($("#password").val().length!=0&&!judge.test($("#password").val())){
		$("span.errorMessage").html("请输入不少于六位数包括字母、数字的密码");
		$("div.registerErrorMessageDiv").css("visibility","visible");
		$(".registerForm").submit(function() {
			return false;
		});
		}
}

</script>



<form method="post" action="${pageContext.request.contextPath}/signup" class="registerForm">


	<div class="registerDiv">
		<div class="registerErrorMessageDiv">
			<div class="alert alert-danger" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
				<span class="errorMessage"></span>
			</div>
		</div>


		<table class="registerTable" align="center">
			<tr>
				<td  class="registerTip registerTableLeftTD">设置会员名</td>
				<td></td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">登陆名</td>
				<td  class="registerTableRightTD"><input id="name" name="name" placeholder="会员名一旦设置成功，无法修改" onchange="checkname()"> </td>
			</tr>
			<tr>
				<td  class="registerTip registerTableLeftTD">设置登陆密码</td>
				<td  class="registerTableRightTD">登陆时验证，保护账号信息</td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">登陆密码</td>
				<td class="registerTableRightTD"><input id="password" name="password" type="password" onchange="checkPassword()"  placeholder="设置你的登陆密码" > </td>
			</tr>
			<tr>
				<td class="registerTableLeftTD">密码确认</td>
				<td class="registerTableRightTD"><input id="repeatpassword" type="password"   placeholder="请再次输入你的密码" > </td>
			</tr>

			<tr>
				<td colspan="2" class="registerButtonTD">
					<button type="submit" >提   交</button>
				</td>
			</tr>
		</table>
	</div>
</form>