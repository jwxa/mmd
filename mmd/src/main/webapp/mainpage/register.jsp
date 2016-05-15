<%--
  Created by IntelliJ IDEA.
  User: Jwxa
  Date: 2015/2/8
  Time: 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <jsp:include page="/public/public.jsp"/>
</head>
<body>
<div class="panel-body panel-form">
    <form class="form-horizontal form-bordered" data-parsley-validate="true" name="demo-form" novalidate="">
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="fullname">Full Name * :</label>
            <div class="col-md-6 col-sm-6">
                <input class="form-control" type="text" id="fullname" name="fullname" placeholder="Required" data-parsley-required="true" data-parsley-id="2272"><ul class="parsley-errors-list" id="parsley-id-2272"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="email">Email * :</label>
            <div class="col-md-6 col-sm-6">
                <input class="form-control" type="text" id="email" name="email" data-parsley-type="email" placeholder="Email" data-parsley-required="true" data-parsley-id="1820"><ul class="parsley-errors-list" id="parsley-id-1820"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="website">Website :</label>
            <div class="col-md-6 col-sm-6">
                <input class="form-control" type="url" id="website" name="website" data-parsley-type="url" placeholder="url" data-parsley-id="2741"><ul class="parsley-errors-list" id="parsley-id-2741"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="message">Message (20 chars min, 200 max) :</label>
            <div class="col-md-6 col-sm-6">
                <textarea class="form-control" id="message" name="message" rows="4" data-parsley-range="[20,200]" placeholder="Range from 20 - 200" data-parsley-id="5052"></textarea><ul class="parsley-errors-list" id="parsley-id-5052"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="message">Digits :</label>
            <div class="col-md-6 col-sm-6">
                <input class="form-control" type="text" id="digits" name="digits" data-parsley-type="digits" placeholder="Digits" data-parsley-id="0742"><ul class="parsley-errors-list" id="parsley-id-0742"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="message">Number :</label>
            <div class="col-md-6 col-sm-6">
                <input class="form-control" type="text" id="number" name="number" data-parsley-type="number" placeholder="Number" data-parsley-id="6523"><ul class="parsley-errors-list" id="parsley-id-6523"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4" for="message">Phone :</label>
            <div class="col-md-6 col-sm-6">
                <input class="form-control" type="text" id="data-phone" data-parsley-type="number" placeholder="(XXX) XXXX XXX" data-parsley-id="5879"><ul class="parsley-errors-list" id="parsley-id-5879"></ul>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-4 col-sm-4"></label>
            <div class="col-md-6 col-sm-6">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </form>
</div>
<script>

    $(document).ready(function() {
        App.init();
    });

</script>
</body>
</html>
