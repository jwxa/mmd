<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/2/7
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="addIntroduceDivTag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="showIntroduceDivTag" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>MMD简介</title>
    <jsp:include page="/public/public.jsp"/>
    <link href="introduce/css/introduce.css" rel="stylesheet" />
    <script src="introduce/js/introduce.js"></script>
</head>
<body>
<div style="height: 77px;padding-top: 30px;padding-left: 20px">
    <a href="#" onclick="showQueryDiv();" class="btn btn-primary m-r-5">查询</a>
    <a href="#" onclick="showAddDiv();" class="btn btn-primary m-r-5">新增</a>
</div>

<addIntroduceDivTag:addIntroduceDivTag/>
<showIntroduceDivTag:showIntroduceDivTag/>
</body>
</html>
