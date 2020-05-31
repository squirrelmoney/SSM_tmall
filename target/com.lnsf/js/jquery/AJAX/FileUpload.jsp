<%--
  Created by IntelliJ IDEA.
  User: money
  Date: 2020/3/31
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
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
                var realpath=data['realPath'];
                var relativePath=data['relativePath'];
                $("#myimg").attr('src',realpath);
                $("#cpiturepath").attr('value',relativePath);

            }
        }
        $("#fileForm").ajaxSubmit(option);
    }
    function fileOnchagetwo(){
        var option = {
            type:"post",
            data:{pictureFile:'pictureFile'},
            dataType:"json",
            url:"${pageContext.request.contextPath }/uploadPic",
            success:function(data){
                //String格式json，转json对象
                var realpath=data['realPath'];
                var relativePath=data['relativePath'];
                $("#myimgtwo").attr('src',realpath);
                $("#cpiturepathtwo").attr('value',relativePath);

            }
        }
        $("#fileFormtwo").ajaxSubmit(option);
    }
</script>