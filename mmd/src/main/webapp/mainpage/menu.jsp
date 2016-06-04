<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/2/7
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单</title>
    <jsp:include page="/public/public.jsp"/>
</head>

<body>
<div id="container">
<!-- begin #page-container -->
<div id="page-container" >
    <!-- begin #sidebar -->
    <div id="sidebar" class="sidebar" style="padding-top: 0px;">
        <!-- begin sidebar scrollbar -->
        <div style="position: relative; overflow: hidden; width: auto; height: 100%;" class="slimScrollDiv">
            <div style="overflow: hidden; width: auto; height: 100%;" data-scrollbar="true" data-height="100%">
            <!-- begin sidebar user -->

            <ul class="nav">
                <li class="nav-profile">
                    <div class="image">
                        <a href="welcome" target="bodyframe"><img src="data:image/jpeg;base64,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" alt="" /></a>
                    </div>
                    <div class="info">
                        ${user_info.userName}
                        <small><a href="logout">注销</a> </small>
                    </div>
                </li>
            </ul>
            <!-- end sidebar user -->
            <!-- begin sidebar nav -->
            <ul class="nav">
                <li class="nav-header">导航</li>
                <li class="has-sub active">
                    <a href="javascript:;">
                        <b class="caret pull-right"></b>
                        <i class="fa fa-laptop"></i>
                        <span>软件</span>
                    </a>
                    <ul class="sub-menu">
                        <li><a href="introduce" target="bodyframe">简介</a></li>
                        <li><a href="download" target="bodyframe">下载地址</a></li>
                    </ul>
                </li>
                <li class="has-sub">
                    <a href="javascript:;">
                        <b class="caret pull-right"></b>
                        <i class="fa fa-heart-o"></i>
                        <span>分享</span>
                    </a>
                    <ul class="sub-menu">
                        <li><a href="imagesView" target="bodyframe">静画欣赏</a></li>
                    </ul>
                </li>

                <li class="has-sub">
                    <a href="javascript:;">
                        <b class="caret pull-right"></b>
                        <i class="fa fa-check-square-o"></i>
                        <span>审核</span>
                    </a>
                    <ul class="sub-menu">
                        <li><a href="myIntroduce" target="bodyframe">我的软件简介</a></li>
                        <li><a href="myIntroduceAjaxTable" target="bodyframe">我的软件简介AJAX</a></li>
                    </ul>
                </li>

                <li class="has-sub">
                    <a href="javascript:;">
                        <b class="caret pull-right"></b>
                        <i class="fa fa-star"></i>
                        <span>日语学习</span>
                    </a>
                    <ul class="sub-menu">
                        <li><a href="study/alphabet" target="bodyframe">五十音图</a></li>
                        <li><a href="study/pronunciation" target="bodyframe">读音练习</a></li>
                        <li><a href="study/selectExamLevel" target="bodyframe">小测试</a></li>

                    </ul>
                </li>
                <li class="has-sub">
                    <a href="javascript:;">
                        <b class="caret pull-right"></b>
                        <i class="fa fa-cogs"></i>
                        <span>IQQ配置</span>
                    </a>
                    <ul class="sub-menu">
                        <li><a href="iqq/showManagePage" target="bodyframe">管理页面</a></li>
                    </ul>
                </li>
                <li class="has-sub">
                    <a href="javascript:;">

                        <i class="fa fa-inbox"></i>
                        <span>联系站长</span>
                    </a>
                </li>
                <%--begin sidebar minify button--%>
                <li><a href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify" id="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
            </ul>
            <!-- end sidebar nav -->
            </div>
        <!-- end sidebar scrollbar -->
        </div>
    </div>
    <div class="sidebar-bg"></div>
    <!-- end #sidebar -->
    <!-- begin scroll to top btn -->
    <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
    <!-- end scroll to top btn -->
</div>
<!-- end page container -->
</div>
</body>
</html>
