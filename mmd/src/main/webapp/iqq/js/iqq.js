function getQRCode(){
    var r = Math.random();
    $("#qrcode").attr("src",
        "getQRCode?r="+r
    );

    var checkFun = function(){
        $.ajax({
            url: "checkQRCode",
            type: "post",
            dataType: "json",
            data: {
            },
            success: function (result) {
                var resultStr = result.resultStr;
                $("#qrcode-status").text(resultStr);
            }, error: function (e) {
                //请求出错处理
                alert("提交失败，请联系管理员");
                console.log(e);
                return;
            }
        });
    };
    setTimeout(checkFun,1000);
}