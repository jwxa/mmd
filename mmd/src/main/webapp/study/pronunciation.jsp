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
<% String path =request.getContextPath(); %>
<script>
    function startExam(){

        $.ajax({
            url : "<%=path%>/study/randomAlphabet",//这个就是请求地址对应sAjaxSource
            data : $("#levelForm").serialize(),
            type : 'post',
            dataType : 'json',
            async : true,
            success : function(result) {
                if(result.flag){
                    $("#alphabetTable").empty();
                    $("#alphabetTable").append(result.tableStr);
                }else{
                    alert("加载题目失败，请稍后再试");
                }

            },
            error : function(msg) {
                console.log(msg);
            }
        });

    }
</script>
<body class="">
<div class="" id="mainDiv">
    <div class="content" id="content">
        <ol class="breadcrumb pull-right">
            <li><a href="javascript:;">日语学习</a></li>
            <li class="active">读音练习</li>
        </ol>
        <h1 class="page-header">读音练习
        </h1>
        <!-- begin row -->
        <div class="row">
            <!-- begin col-12 -->
            <div class="col-md-12 ui-sortable">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-1">
                    <div class="panel-heading">
                        <h4 class="panel-title">模式选择</h4>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <form id="levelForm">
                                    <div class="form-group">
                                        <label class=" control-label">表格显示：</label>

                                        <div class="">
                                            <label class="radio-inline">
                                                <input name="aliasType" type="radio"  value="004001">
                                                平假名
                                            </label>
                                            <label class="radio-inline">
                                                <input name="aliasType"  type="radio"  value="004002">
                                                片假名
                                            </label>
                                            <label class="radio-inline">
                                                <input name="aliasType" checked="" type="radio"  value="004003">
                                                平假名&片假名
                                            </label>
                                            <label class="radio-inline">
                                                <input name="aliasType"  type="radio"  value="004004">
                                                发音
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class=" control-label">打乱范围：</label>
                                        <div class="">
                                            <label class="radio-inline">
                                                <input name="createType" value="003001" type="radio">
                                                按行
                                            </label>
                                            <label class="radio-inline">
                                                <input name="createType" value="003002" type="radio">
                                                按列
                                            </label>
                                            <label class="radio-inline">
                                                <input name="createType" checked="" type="radio" value="003003" >
                                                全表
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <%--<label class="col-md-3 control-label">Submit</label>--%>
                                        <div class="col-md-1">
                                            <button type="button" class="btn btn-sm btn-block btn-success" onclick="startExam()">开始练习</button>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- end panel -->
            </div>
            <!-- end col-12 -->
        </div>
        <!-- end row -->
        <!-- begin row -->
        <div id="alphabetRow" class="row">
            <!-- begin col-12 -->
            <div class="col-md-12 ui-sortable">
                <!-- begin panel -->
                <div class="panel panel-inverse" data-sortable-id="table-basic-2">
                    <div class="panel-heading">
                        <div class="panel-heading-btn">
                            <a style="" title="" data-original-title="" href="javascript:;"
                               class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i
                                    class="fa fa-expand"></i></a>
                            <a title="" data-original-title="" href="javascript:;"
                            class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i
                            class="fa fa-repeat"></i></a>
                            <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning"
                               data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                            <%--<a title="" data-original-title="" href="javascript:;"--%>
                            <%--class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i--%>
                            <%--class="fa fa-times"></i></a>--%>
                        </div>
                        <h4 class="panel-title">五十音图</h4>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table id="alphabetTable" class="table table-bordered">
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
                                        <td>${hiraganaList[s].aliasName} &nbsp;${kataganaList[s].aliasName}&nbsp;&nbsp;${hiraganaList[s].pronounce}</td>
                                        <td>${hiraganaList[s+1].aliasName} &nbsp;${kataganaList[s+1].aliasName}&nbsp;&nbsp;${hiraganaList[s+1].pronounce}</td>
                                        <td>${hiraganaList[s+2].aliasName} &nbsp;${kataganaList[s+2].aliasName}&nbsp;&nbsp;${hiraganaList[s+2].pronounce}</td>
                                        <td>${hiraganaList[s+3].aliasName} &nbsp;${kataganaList[s+3].aliasName}&nbsp;&nbsp;${hiraganaList[s+3].pronounce}</td>
                                        <td>${hiraganaList[s+4].aliasName} &nbsp;${kataganaList[s+4].aliasName}&nbsp;&nbsp;${hiraganaList[s+4].pronounce}</td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                                    <%--<th colspan="6">鼠标悬停即可看到读音提示</th>--%>

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
        var alphabetText = $("#alphabetTable").html();
        $("[data-click=panel-reload]").unbind("click");
        $("[data-click=panel-reload]").click(function (e) {
            $("#alphabetTable").empty();
            $("#alphabetTable").append(alphabetText);
            $("#alphabetRow .panel-title").text("五十音图");
        });
    });
</script>


</body>
</html>
