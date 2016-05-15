<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/2/23
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>静画欣赏</title>
    <jsp:include page="/public/public.jsp"/>
    <link href="theme/assets/plugins/isotope/isotope.css" rel="stylesheet">
    <script src="theme/assets/plugins/isotope/jquery.isotope.min.js"></script>
    <link href="theme/assets/plugins/lightbox/css/lightbox.css" rel="stylesheet">
    <script src="theme/assets/plugins/lightbox/js/lightbox-2.6.min.js"></script>
    <script src="theme/assets/js/gallery.demo.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            App.init();
            Gallery.init();
        });
    </script>
    <style type="text/css">

    </style>
</head>
<body>


<div id="content" class="content" style="margin-left: 0px">
    <!-- begin breadcrumb -->
    <ol class="breadcrumb pull-right">
        <li><a href="javascript:;">分享</a></li>
        <li class="active">静画欣赏</li>
    </ol>
    <!-- end breadcrumb -->
    <!-- begin page-header -->
    <h1 class="page-header">图库
        <%--<small>header small text goes here...</small>--%>
    </h1>
    <!-- end page-header -->

    <div id="masonry" class="container-fluid">
    <div style="position: relative; overflow: hidden; height: 1047px;" id="gallery" class="gallery isotope">
      <c:forEach begin="1" end="50" var="s" step="1">
          <div style="position: absolute; left: 0px; top: 0px; transform: translate(0px, 0px);" class="image gallery-group-1 isotope-item">
              <div class="image-inner">
                  <a href="pic/%28${s}%29.jpg " data-lightbox="gallery-group-1">
                      <img src="pic/%28${s}%29.jpg" alt="">
                  </a>
              </div>
          </div>
      </c:forEach>

    </div>
    </div>
</div>
</body>
</html>
