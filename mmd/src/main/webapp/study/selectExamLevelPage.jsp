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
    <title>学习小测验</title>
</head>
<style>
    .form {
        background: rgba(0, 0, 0, .4);
        color: #ccc;
        width: 450px;
        margin: 168px auto;
        position: relative;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        border-radius: 4px
    }
    #questionDiv{
        display: none;
    }
</style>
<jsp:include page="/public/public.jsp"/>
    <% String path =request.getContextPath(); %>
<body>
<script >
    function startExam(){
        $("#examDiv").load("<%=path%>/study/baseExam",$("#levelForm").serialize(),function() {
            $("#page-container").hide();
            $("#questionDiv").show();
        });

    }

    function reloadQuestion(){
        var flag = window.confirm("放弃本次题目重新获取新的一批？");
        if(!flag){
            return;
        }
        $("#submitBtn").removeAttr("disabled");

        $.get("<%=path%>/study/clearExamQuestionSession",$("#reloadForm").serialize(),function(result) {
            $("#examDiv").load("<%=path%>/study/baseExam",$("#reloadForm").serialize(),function() {
                $("#page-container").hide();
                $("#questionDiv").show();
            });
        })

    }
    //提交答案
    function submitQuestion(){
        var tips = "以下题号没有作答：\n"
         var continueFlag = true;
        //判断是否有没有填写的
        var radios = $("#examDiv div[class=radio]");
        for(var i =0;i<radios.size();i++){
            var input = radios.eq(i).find(":checked");
            if(input.size()==0){
                //说明改题目没有做答 输出题号
                tips+=(i+1)+",";
                continueFlag =false;
            }
        }
        if(!continueFlag){
            alert(tips.substr(0,tips.length-1));
            return;
        }else{
            $("#submitBtn").attr("disabled","disabled");
            //获取正确答案 进入批改流程
            //将用户答案组成数组
            var answers = "";
            var answerOptions = $("#examDiv div[class=radio] input:checked");
            for(var i=0;i<answerOptions.size();i++){
                var answer = answerOptions.eq(i).val();
                answers+= answer+","
            }
            $("#examDiv div[class=radio] input").each(function(){
                $(this).attr("disabled","disabled");
            });
            $.ajax({
                type: "POST",
                url: "<%=path%>/study/showBaseExamAnswer",
                data: {
                    createType:$("#createType").val(),
                    aliasType:$("#aliasType").val(),
                    answers:answers.substr(0,answers.length-1)
                },
                dataType: "json",
                success: function(data){
                    //回调
                    var errorOptionSeq = data.errorOptionSeq;
                    var questionVOList = data.questionVOList;
                    for(var i=0;i<errorOptionSeq.length;i++){
                        var seq = errorOptionSeq[i];
                        var name = "#radio_" +seq;
                        $(name).append("<div style='color: red' class='col-md-12'>正确答案是："+ questionVOList[seq].options[questionVOList[seq].correctAnswerSeq]
                                        + "，你的答案是：" + questionVOList[seq].options[questionVOList[seq].answerSeq] +"</div>");
                    }
                    alert("你的得分是："+data.score);
                }
            });
            //TODO
        }
    }
</script>

<form id="levelForm">
<div id="page-container" class="fade in">
    <div class="form animated flipInX" data-pageload-addclass="animated flipInX">
        <div class="">
            <div class="panel panel-inverse" data-sortable-id="form-stuff-1">
                <div class="panel-heading">
                    <h4 class="panel-title">测试难度选择</h4>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label class=" control-label">假名类型：</label>

                        <div class="">
                            <label class="radio-inline">
                                <input name="aliasType" checked="" type="radio"  value="002001">
                                平假名
                            </label>
                            <label class="radio-inline">
                                <input name="aliasType" checked="" type="radio"  value="002002">
                                片假名
                            </label>
                            <label class="radio-inline">
                                <input name="aliasType" checked="" type="radio"  value="002003">
                                混合
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class=" control-label">题目类型：</label>
                        <div class="">
                            <label class="radio-inline">
                                <input name="createType" value="1" type="radio">
                                假名辩注音
                            </label>
                            <label class="radio-inline">
                                <input name="createType" value="2" type="radio">
                                注音辩假名
                            </label>
                            <label class="radio-inline">
                                <input name="createType" checked="" type="radio" value="3" >
                                混合
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <%--<label class="col-md-3 control-label">Submit</label>--%>
                        <div class="col-md-12">
                            <button type="button" class="btn btn-sm btn-block btn-success" onclick="startExam()">开始答题</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>
<div class="form-group" id="questionDiv">
    <div class="col-md-12" id="introduceDiv">
        <div class="panel-group" id="accordion">
            <div class="panel panel-inverse overflow-hidden">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true">
                            <i class="fa fa-plus-circle pull-right"></i>
                            题目
                        </a>
                    </h3>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" aria-expanded="true" style="">
                    <div id="examDiv" class="panel-body">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>