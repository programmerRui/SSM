<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
        <form action="" id="imm" method="post">
            <table border="1px" style="text-align: center;">
                <tr>
                    <td>图书编号</td>
                    <td>${bookInfo.bookCode}</td>
                </tr>
                <tr>
                    <td>图书名称</td>
                    <td>${bookInfo.bookName}</td>
                </tr>
                <tr>
                    <td>图书分类</td>
                    <td >
                        <c:forEach items="${bookTypes}" var="bookType">
                            <c:if test="${bookType.typeId==bookInfo.bookType}">${bookType.typeName}</c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td>作者</td>
                    <td>${bookInfo.bookAuthor}</td>
                </tr>
                <tr>
                    <td>出版社</td>
                    <td>${bookInfo.publishPress}</td>
                </tr>
                <tr>
                    <td>是否借阅</td>
                    <td><c:if test="${bookInfo.isBorrow==1}">已借阅</c:if>
                        <c:if test="${bookInfo.isBorrow==0}">未借阅</c:if>
                    </td>
                </tr>
                <tr>
                    <td>出版时间</td>
                    <td><fmt:formatDate value="${bookInfo.publishDate}" pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td>图片上传</td>
                    <td>
                        <img id="img" src="${bookInfo.bookPath}" width="120px" height="150px">
                        <a href="download.do?bookPash=${bookInfo.bookPath}">下载</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
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
</html>