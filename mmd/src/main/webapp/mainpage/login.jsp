<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/2/8
  Time: 2:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
    <jsp:include page="/public/public.jsp"/>
    <% String path = request.getContextPath(); %>
    <script src="<%=path%>/theme/assets/js/login-v2.demo.min.js"></script>
</head>
<script>
    if(self!=top){
        top.location.href= self.location.href;
    }
</script>
<body>
<div class="login login-v2 animated flipInX" data-pageload-addclass="animated flipInX" style="z-index: 99">
    <!-- begin brand -->
    <div class="login-header">
        <div class="brand">
            <span class="logo"></span> 登录
        </div>
        <div class="icon">
            <i class="fa fa-sign-in"></i>
        </div>
    </div>
    <!-- end brand -->
    <div class="login-content">
        <form action="<%=path%>/login" method="POST" class="margin-bottom-0">
            <div class="form-group m-b-20">
                <input type="text" class="form-control input-lg" placeholder="用户名" name="loginName" value="${login_name}">
            </div>
            <div class="form-group m-b-20">
                <input type="password" class="form-control input-lg" placeholder="密码" name="loginPwd" value="${login_pwd}">
            </div>
            <%--<div class="checkbox m-b-20">--%>
                <%--<label>--%>
                    <%--<input type="checkbox"> 记住密码--%>
                <%--</label>--%>
            <%--</div>--%>
            <div class="login-buttons">
                <button type="submit" class="btn btn-success btn-block btn-lg">登录</button>
            </div>
            <div class="m-t-20">
                没有账号？点击 <a href="toRegister">这里</a> 注册
                <c:if test="${err_msg!=null}">
                    <br><font color="#dc143c">${err_msg}</font>
                </c:if>
            </div>
        </form>
    </div>
</div>
<div class="login-cover">
    <div class="login-cover-image"><img src="<%=path%>/theme/assets/img/login-bg/bg-6.jpg" data-id="login-cover-image"></div>
    <div class="login-cover-bg"></div>
</div>
<script>
    $(document).ready(function() {
        App.init();
        LoginV2.init();
        document.getElementsByTagName('body')[0].style.overflow='hidden';
    });
</script>
</body>
</html>
