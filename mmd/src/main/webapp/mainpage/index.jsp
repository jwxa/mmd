<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8"/>
    <title>mikumiku管理台</title>
    <jsp:include page="/public/public.jsp"/>
    <script type="text/javascript">
        $(document).ready(function () {
//            var divWidth = 220;
            var randomNum = Math.random();
            //判断是否在iframe中加载
            if (self != top) {
                top.window.navigate("index");
                return;
            }
            $.get("menu?t=" + randomNum, function (data) { //初始將a.html include div#iframe
                $("#menu").html(data);
                var html = $("#container").html();
                $("#menu").html(html);
                //绑定li标签 active样式
                $("#menu ul .has-sub").live("click", function () {
                    $("#menu ul .has-sub").removeClass("active");
                    $(this).addClass("active");
                });
            });
            $("#sidebar-minify").live("click",function(e) {
                e.preventDefault();
                var t = "page-sidebar-minified";
                var n = "#page-container";
                if ($(n).hasClass(t)) {
                    $(n).removeClass(t);
                    $("#body").removeClass(t);
                    if ($(n).hasClass("page-sidebar-fixed")) {
                        generateSlimScroll($('#sidebar [data-scrollbar="true"]'));
                    }
                } else {
                    $(n).addClass(t);
                    $("#body").addClass(t);
                    if ($(n).hasClass("page-sidebar-fixed")) {
                        $('#sidebar [data-scrollbar="true"]').slimScroll({
                            destroy: true
                        });
                        $('#sidebar [data-scrollbar="true"]').removeAttr("style");
                    }
                    $("#sidebar [data-scrollbar=true]").trigger("mouseover");
                }
                $(window).trigger("resize")
            })
        });
    </script>
</head>
<style>
    .page-sidebar-minified{
        padding-left: 61px !important;
    }
</style>
<body>
<div id="menu" style=""></div>
<div id="body" class="frame-content" >
    <iframe id="bodyframe" src="welcome" name="bodyframe" frameborder="0" width="100%" height="100%"/>
</div>


</body>
</html>
