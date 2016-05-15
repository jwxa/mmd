<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/2/17
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>我的软件简介</title>
    <jsp:include page="/public/public.jsp"/>
    <link href="theme/assets/plugins/DataTables/css/data-table.css" rel="stylesheet">
    <script src="theme/assets/plugins/DataTables/js/jquery.dataTables.js"></script>
    <%--<script src="theme/assets/js/table-manage-default.demo.min.js"></script>--%>
    <script type="text/javascript" src="introduce/js/myIntroduce.js"></script>
    <style>
        td a:hover {
            font-size: 12px;
            color: #337ab7;
            text-decoration: none;
            cursor: pointer;
        }
        .fa{
            padding-top: 4px
        }
    </style>
</head>
<body>
<div id="content" class="content">
    <!-- begin breadcrumb -->
    <ol class="breadcrumb pull-right">
        <li><a href="javascript:;">审核</a></li>
        <li class="active">我的软件简介</li>
    </ol>
    <!-- end breadcrumb -->
    <!-- begin page-header -->
    <h1 class="page-header">我的软件简介
        <%--<small>header small text goes here...</small>--%>
    </h1>
    <!-- end page-header -->

    <!-- begin row -->
    <div class="row">
        <!-- begin col-12 -->
        <div class="col-md-12 ui-sortable">
            <!-- begin panel -->
            <div class="panel panel-inverse">
                <div class="panel-heading">
                    <div class="panel-heading-btn">
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default"
                           data-click="panel-expand"><i class="fa fa-expand"> </i></a>
                        <%--<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success"--%>
                           <%--data-click="panel-reload"><i class="fa fa-repeat" style="padding-top: 4px"></i></a>--%>
                        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                           data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                        <%--<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger"--%>
                           <%--data-click="panel-remove"><i class="fa fa-times" style="padding-top: 4px"></i></a>--%>
                    </div>
                    <h4 class="panel-title">列表</h4>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="overflow-x: hidden">
                        <div class="dataTables_wrapper no-footer" id="data-table_wrapper">
                            <table aria-describedby="data-table_info" role="grid" id="data-table"
                                   class="table table-striped table-bordered dataTable no-footer" style="font-size: 12px">
                                <thead>
                                <tr role="row">
                                    <th
                                            style="width: 20%;" colspan="1" rowspan="1" aria-controls="data-table"
                                            tabindex="0" class="sorting">标题
                                    </th>
                                    <th style="width: 20%;"
                                        colspan="1" rowspan="1" aria-controls="data-table" tabindex="0" class="sorting">
                                        作者
                                    </th>
                                    <th
                                            style="width: 20%;" colspan="1" rowspan="1" aria-controls="data-table"
                                            tabindex="0" class="sorting">状态
                                    </th>
                                    <th
                                            style="width: 20%;" colspan="1" rowspan="1" aria-controls="data-table"
                                            tabindex="0" class="sorting">创建时间
                                    </th>
                                    <th style="width: 20%;"
                                        colspan="1" rowspan="1" aria-controls="data-table" tabindex="0" class="sorting">
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${introduceVOList}" var="introduceVO" varStatus="status">
                                    <c:if test="${status.count%2==0}">
                                        <tr role="row" class="gradeA odd">
                                    </c:if>
                                    <c:if test="${status.count%2!=0}">
                                        <tr role="row" class="gradeA even">
                                    </c:if>
                                    <td>${introduceVO.title}</td>
                                    <td>${introduceVO.author}</td>
                                    <td>${introduceVO.statusName}</td>
                                    <td>
                                        <fmt:parseDate value="${introduceVO.createTime}"
                                                       pattern="yyyy-MM-dd HH:mm:ss.SSS" var="createTime"/>
                                        <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>
                                        <a><i class="fa fa-edit">&nbsp;</i>修改</a>&nbsp;&nbsp;
                                        <a href="#" onclick="submitToAudit('${introduceVO.uuid}')"><i class="fa fa-file">&nbsp;</i>提交</a>&nbsp;&nbsp;
                                        <a><i class="fa fa-trash-o">&nbsp;</i>删除</a>
                                    </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
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
</div>
</body>
</html>
