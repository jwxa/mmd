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
    <script type="text/javascript">
        var oTable ;
        function dataTableTest(){
            oTable = $("#data-table").dataTable({
                "language": {
                    "lengthMenu": '每页 <select>' +
                    '<option value="5">5</option>' +
                    '<option value="10">10</option>' +
                    '<option value="50">50</option>' +
                    '<option value="-1">显示所有</option>' +
                    '</select> 条',
                    "zeroRecords": "没有匹配结果",
                    "info": "_START_-_END_条，共 _TOTAL_ 条",
                    "search": "搜索",
                    "emptyTable": "表格中没有数据",
                    "infoFiltered": " - 从_MAX_条结果中过滤",
                    "infoEmpty": "0-0条，共0条",
                    "oPaginate": {
                        "sFirst":    "首页",
                        "sPrevious": "上页",
                        "sNext":     "下页",
                        "sLast":     "末页"
                    }
                },
                "bDestroy":true,
                "bPaginate":true,// 分页按钮
                "bFilter":false,// 搜索栏
                "bLengthChange":true,// 每行显示记录数
                "bSort" : false,// 排序
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认
                "bProcessing": false, // 是否显示取数据时的那个等待提示
                "bServerSide": true,//这个用来指明是通过服务端来取数据
                "sAjaxSource": "myIntroduceAjax",//这个是请求的地址
                "fnServerData": retrieveData, // 获取数据的处理函数
                "oTableTools": {
                    "aButtons": [ "copy", "print" ]  //"copy", "csv", "xls", "pdf", "print" 自定义
                }
            });
        }

        // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
        function retrieveData( sSource,aoData, fnCallback) {
            var test = $("#test").val();
            $.ajax({
                url : sSource,//这个就是请求地址对应sAjaxSource
                data : {
                    "aoData":JSON.stringify(aoData),
                    "test":test
                },//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type : 'post',
                dataType : 'json',
                async : false,
                success : function(result) {
                    fnCallback(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                    $(".row").show();
                },
                error : function(msg) {
                }
            });
        }
    </script>

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
    <input id="test"/>
    <input type="button" onclick="dataTableTest()" value="测试"/>
    <!-- begin row -->
    <div class="row" style="display: none;">
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
