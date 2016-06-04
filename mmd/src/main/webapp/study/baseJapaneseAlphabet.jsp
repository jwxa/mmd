<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/6/22
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>五十音图</title>
</head>
<jsp:include page="/public/public.jsp"/>

<style>
    .fa {
        margin-top: 4px;
    }
</style>
<body class="">
<div class="" id="">
    <div class="content" id="content">
        <ol class="breadcrumb pull-right">
            <li><a href="javascript:;">日语学习</a></li>
            <li class="active">五十音图</li>
        </ol>
        <h1 class="page-header">五十音图
        </h1>
        <!-- begin row -->
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12 ui-sortable">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                            <a style="" title="" data-original-title="" href="javascript:;"
                               class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i
                                    class="fa fa-expand"></i></a>
                            <%--<a title="" data-original-title="" href="javascript:;"--%>
                            <%--class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i--%>
                            <%--class="fa fa-repeat"></i></a>--%>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                               data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                            <%--<a title="" data-original-title="" href="javascript:;"--%>
                            <%--class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i--%>
                            <%--class="fa fa-times"></i></a>--%>
                        </div>
                        <h4 class="panel-title">平假名</h4>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>あ段</th>
                                    <th>い段</th>
                                    <th>う段</th>
                                    <th>え段</th>
                                    <th>お段</th>
                                </tr>
                                </thead>
                                <c:forEach var="s" begin="0" end="${fn:length(hiraganaList)-1}"  step="5">
                                    <tbody>
                                    <tr>
                                        <th>${hiraganaList[s].aliasName}行</th>
                                        <td>${hiraganaList[s].aliasName} &nbsp;${hiraganaList[s].pronounce}</td>
                                        <td>${hiraganaList[s+1].aliasName} &nbsp;${hiraganaList[s+1].pronounce}</td>
                                        <td>${hiraganaList[s+2].aliasName} &nbsp;${hiraganaList[s+2].pronounce}</td>
                                        <td>${hiraganaList[s+3].aliasName} &nbsp;${hiraganaList[s+3].pronounce}</td>
                                        <td>${hiraganaList[s+4].aliasName} &nbsp;${hiraganaList[s+4].pronounce}</td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- end panel -->
            </div>
            <!-- end col-12 -->
        </div>
        <!-- end row -->
        <!-- begin row -->
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12 ui-sortable">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-7">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                            <a style="" title="" data-original-title="" href="javascript:;"
                               class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i
                                    class="fa fa-expand"></i></a>
                            <%--<a title="" data-original-title="" href="javascript:;"--%>
                            <%--class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i--%>
                            <%--class="fa fa-repeat"></i></a>--%>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                               data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                            <%--<a title="" data-original-title="" href="javascript:;"--%>
                            <%--class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i--%>
                            <%--class="fa fa-times"></i></a>--%>
                        </div>
                        <h4 class="panel-title">片假名</h4>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>あ段</th>
                                    <th>い段</th>
                                    <th>う段</th>
                                    <th>え段</th>
                                    <th>お段</th>
                                </tr>
                                </thead>
                                <c:forEach var="s" begin="0" end="${fn:length(kataganaList)-1}"  step="5">
                                    <tbody>
                                    <tr>
                                        <th>${hiraganaList[s].aliasName}行</th>
                                        <td>${kataganaList[s].aliasName} &nbsp;${kataganaList[s].pronounce}</td>
                                        <td>${kataganaList[s+1].aliasName} &nbsp;${kataganaList[s+1].pronounce}</td>
                                        <td>${kataganaList[s+2].aliasName} &nbsp;${kataganaList[s+2].pronounce}</td>
                                        <td>${kataganaList[s+3].aliasName} &nbsp;${kataganaList[s+3].pronounce}</td>
                                        <td>${kataganaList[s+4].aliasName} &nbsp;${kataganaList[s+4].pronounce}</td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- end panel -->
            </div>
            <!-- end col-12 -->
        </div>
        <!-- end row -->

    </div>
    <!-- end #content -->


</div>
<!-- end page container -->

<script>
    $(document).ready(function () {
        App.init();
    });
</script>


</body>
</html>
