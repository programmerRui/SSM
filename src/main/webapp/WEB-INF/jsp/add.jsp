<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        #body {
            margin: 0px auto;
            width: 800px;
            height: 600px;
        }
    </style>
</head>
<body>
<div id="body">
    <div style="margin: 0px auto; width: 500px;">
        <form action="/add.do" id="imm" method="post">
            <table border="1px" style="text-align: center;">
                <tr>
                    <td>图书编号</td>
                    <td><input id="code" name="bookCode" onblur="checkCode();"><span
                            id="codeMessage"></span></td>
                </tr>
                <tr>
                    <td>图书名称</td>
                    <td><input name="bookName"></td>
                </tr>
                <tr>
                    <td>图书分类</td>
                    <td style="text-align: left;"><select name="bookType">
                        <c:forEach items="${bookTypes}" var="bookType">
                            <option value="${bookType.typeId }">${bookType.typeName}</option>

                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <td>作者</td>
                    <td><input name="bookAuthor"></td>
                </tr>
                <tr>
                    <td>出版社</td>
                    <td><input name="publishPress"></td>
                </tr>
                <tr>
                    <td>出版时间</td>
                    <td><input type="date" name="publishDate"></td>
                </tr>
                <tr>
                    <td>图片上传</td>
                    <td>
                        <img id="img" src="" width="120px" height="150px">
                        <input type="file" name="fileImage" onchange="uploadImage();">
                        <input type="hidden" name="bookPath" id="pic">
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="提交">
                        <a href="querybook.do"><input type="button" value="取消"></a></td>
                </tr>
            </table>
        </form>
    </div>


</div>
</body>
<
<script type="text/javascript" src="../../resource/js/jquery_2.1.4_baidu_min.js"></script>
<script type="text/javascript" src="../../resource/js/jquery-form.js"></script>
<script type="text/javascript">
    function uploadImage(){
        //编写ajax
        var obj={
            url:"imageupload.do",
            dataType:"json",
            type:"post",
            success:function(data){
                $("#img").attr("src",data.imagePath);
                $("#pic").attr("value",data.imagePath);
            }
        };
        //提交form
        $("#imm").ajaxSubmit(obj);
    }
</script>
</html>