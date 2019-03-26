<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="../../resource/js/jquery_2.1.4_baidu_min.js"></script>
    <script type="text/javascript">
        function doPage(pageno) {
            //.给表单的当前也那么赋值
            $("#pageno").val(pageno);
            //提交查询的表单
            $("#query").submit();
        }
        function changecheckBox(check) {
            var input = $("input[name='bookid']");
            if (check) {
                $(input).each(function (i) {
                    this.setAttribute("checked","checked");
//this.setAttribute("checked","true");
                })
            } else {
                $(input).each(function (i) {
                    this.removeAttribute("checked");
                })
            }
        }
        function deletebyId() {
            if(confirm("你确定要删除这条信息？")==true){
                return true;
            }else{
                return false;
            }
        }
        function deleteAll() {
            if(confirm("你确定要删除这条信息?")==true){
                $("#delete").submit();
            }else{
                alert("我不叫艾斯");
            }
        }
    </script>

</head>
<body>
<div style="height: 600px; width: 800px; margin: 0px auto;">


    <form action="querybook.do" method="post" id="query">
        图书分类<select name="bookTypeid">
        <option value="0">全部</option>
        <c:forEach items="${bookTypes}" var="type">
            <option value="${type.typeId}"
                    <c:if test="${type.typeId==bookTypeid}">selected="selected"</c:if>>${type.typeName}</option>
        </c:forEach>
    </select> &nbsp; 图书名称<input name="bookname" value="${bookname}">
        &nbsp;是否借阅 <select name="isborrow">
        <option value="-1">请选择</option>
        <option value="0"
                <c:if test="${borrow==0}">selected="selected" </c:if>>未借阅</option>
        <option value="1"
                <c:if test="${borrow==1}">selected="selected" </c:if>>已借阅</option>
    </select> <input type="hidden" name="now" id="pageno"> <input
            type="submit" value="查询">
    </form>



    <a href="toadd.do">添加</a> &nbsp;
    <button onclick="deleteAll();">批量删除</button>
    <form id="delete" action="deletebyIds.do" method="post">
        <input type="hidden" name="choose" value="7">
        <table>
            <tr>
                <td><input type="checkbox"
                           onclick="changecheckBox(this.checked);"></td>
                <td>图书编号</td>
                <td>图书分类</td>
                <td>图书名称</td>
                <td>作者</td>
                <td>出版社</td>
                <td>操作</td>
                <td>详情</td>
                <td>删除</td>
                <td>修改</td>
            </tr>
            <c:forEach items="${pageInfo.list}" var="book">
                <tr>
                    <td><input type="checkbox" name="bookid"
                               value="${book.bookId}"></td>
                    <td>${book.bookCode }</td>
                    <td>${book.bookTypes.typeName}</td>
                    <td>${book.bookName }</td>
                    <td>${book.bookAuthor }</td>
                    <td>${book.publishPress }</td>
                    <td><c:if test="${book.isBorrow ==0 }">未借阅</c:if> <c:if
                            test="${book.isBorrow ==1 }">已借阅</c:if></td>
                    <td><a href="detailed.do?bookId=${book.bookId}">详情</a></td>
                    <td><a href="deletebyId.do?bookId=${book.bookId}" onclick="return deletebyId()">删除</a></td>
                    <td><a href="toupdate.do?bookId=${book.bookId}">修改</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="9"><a href="javascript:doPage(1);">首页</a>&nbsp;&nbsp;
                    <a href="javascript:doPage(${pageInfo.pageNum-1});">上一页</a>&nbsp;&nbsp;
                    <a href="javascript:doPage(${pageInfo.pageNum+1});">下一页</a>&nbsp;&nbsp;
                    <a href="javascript:doPage(${pageInfo.pages});">末页</a>&nbsp;&nbsp;
                    ${pageInfo.pageNum}/${pageInfo.pages}页</td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>