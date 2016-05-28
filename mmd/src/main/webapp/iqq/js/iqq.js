var int;
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
                if(resultStr=="登录成功"){
                    clearInterval(int);
                    beginPollMsg();
                }
            }, error: function (e) {
                //请求出错处理
                alert("提交失败，请联系管理员");
                console.log(e);
                return;
            }
        });
    };
    int = setInterval(checkFun,1000);
}

function beginPollMsg(){
    $.ajax({
        url: "beginPollMsg",
        type: "post",
        dataType: "json",
        data: {
        },
        success: function (result) {
            var flag = result.flag;
            if(flag){
                $("#qrcode-status").text("开始获取信息");
            }
        }, error: function (e) {
            //请求出错处理
            alert("提交失败，请联系管理员");
            console.log(e);
            return;
        }
    });
}