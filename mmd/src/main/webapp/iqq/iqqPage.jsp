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
  <title>IQQ配置页面</title>
  <jsp:include page="/public/public.jsp"/>
</head>
<script src="js/iqq.js"></script>
<script>

  $(document).ready(function () {
    getQRCode();
  });

</script>
<body>
<div id="container">
  <div class="content" id="content">
    <!-- begin breadcrumb -->
    <ol class="breadcrumb pull-right">
      <li><a href="javascript:;">管理页面</a></li>
      <li class="active">IQQ配置</li>
    </ol>
    <!-- end breadcrumb -->
    <!-- begin page-header -->
    <h1 class="page-header">IQQ配置

    </h1>

    <div class="col-md-3 col-sm-6">
      <div class="widget widget-stats bg-blue">
        <div class="stats-icon"><i class="fa fa-chain-broken"></i></div>
        <div class="stats-info">
          <h4>二维码状态</h4>
          <p id="qrcode-status">获取中...</p>
        </div>
        <div class="stats-link">
          <a href="javascript:getQRCode();">刷新二维码 <i class="fa fa-arrow-circle-o-right"></i></a>
        </div>
      </div>
    </div>
    <div class="col-md-3 col-sm-6">
          <img id="qrcode" src="" style="width:165px;height: 165px">
    </div>
    <!-- end page-header -->
  </div>

</div>

</body>
</html>
