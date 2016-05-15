function showAddDiv() {
    $("#introduceDiv").hide();
    $("#addDiv").show();
}

$(document).ready(function () {
    App.init();

    $("[data-click=panel-close]").hover(function () {
        $(this).tooltip({title: "关闭", placement: "bottom", trigger: "hover", container: "body"});
        $(this).tooltip("show")
    });
    $("[data-click=panel-close]").click(function (e) {
        $("#addDiv").hide();
        $("#introduceDiv").show();
    });
    $("[data-click=panel-reset]").hover(function () {
        $(this).tooltip({title: "重置", placement: "bottom", trigger: "hover", container: "body"});
        $(this).tooltip("show")
    });
    $("[data-click=panel-reset]").click(function (e) {
        e.preventDefault();
        var t = $(this).closest(".panel");
        if (!$(t).hasClass("panel-loading")) {
            var n = $(t).find(".panel-body");
            var r = '<div class="panel-loader"><span class="spinner-small"></span></div>';
            $(t).addClass("panel-loading");
            $(n).prepend(r);
            setTimeout(function () {
                $(t).removeClass("panel-loading");
                $(t).find(".panel-loader").remove()
            }, 2e3)
        }
    });

});

/**
 * 提交新的软件介绍文章(未审核)
 */
function submitIntroduce() {
    //验证表单
    if ($.trim($("#addIntroduceTitle").val()) == "") {
        alert("标题不能为空！");
        $("#addIntroduceTitle").focus();
        return;
    }
    if ($.trim($("#addIntroduceArticle").val()) == "") {
        alert("文章内容不能为空！");
        $("#addIntroduceArticle").focus();
        return;
    }
    $.ajax({
        url: "submitIntroduce.do",
        type: "post",
        dataType: "json",
        data: {
            title: $("#addIntroduceTitle").val(),
            body: $("#addIntroduceArticle").val()
        },
        success: function (result) {
            var resultFlag = result.resultFlag;
            if (resultFlag == "success") {
                var jmpUrlFlag = window.confirm("提交成功！是否跳转到审核申请页面？");
                if (jmpUrlFlag) {
                    window.navigate("/myIntroduce.do");
                } else {
                    return;
                }
            } else {
                alert(result.resultMsg);
            }
        }, error: function () {
            //请求出错处理
            alert("提交失败，请联系管理员");
            return;
        }

    });
}

