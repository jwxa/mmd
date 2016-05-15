<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/6/21
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
</head>
<jsp:include page="/public/public.jsp"/>
<!-- ================== BEGIN BASE JS ================== -->
<%--进度条js--%>
<script src="theme/assets/plugins/pace/pace.min.js"></script>
<!-- ================== END BASE JS ================== -->
<style>
    .maintitle {
        background: none;
        margin: 20px 0px 0px;
        padding: 0px 5px;
        left: 120px;
        top: 45px;
        width: auto;
        height: auto;
        text-align: left;
        color: rgb(129, 81, 38);
        line-height: 50px;
        font-family: 微软雅黑;
        font-size: 36px;
        font-weight: normal;
    }
</style>
<body style="background-image:url(mainpage/welcome.jpg);background-size:cover ">
<div class="container">
    <div class="maintitle">欢迎来到MMD管理台</div>
</div>
</body>
</html>
