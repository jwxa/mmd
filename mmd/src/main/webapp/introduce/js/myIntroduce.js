$(document).ready(function () {
    App.init();
    $('#data-table').dataTable({
        "language": {
            "lengthMenu": '每页 <select>' +
            '<option value="10">10</option>' +
            '<option value="20">20</option>' +
            '<option value="50">50</option>' +
            '<option value="-1">显示所有</option>' +
            '</select> 条',
            //"lengthMenu": '<div class="dropdown pull-left">' +
            //'每页 <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">' +
            //'10' +
            //'<span class="caret"></span>' +
            //' </button>' +
            //'<ul class="dropdown-menu dropdown-menu-left" role="menu" aria-labelledby="dropdownMenu1">' +
            //'<li> <a href="#" role="menuitem" value="10">10</a></li>' +
            //'<li> <a href="#" role="menuitem" value="20">20</a></li>' +
            //'<li> <a href="#" role="menuitem" value="50">50</a></li>' +
            //'<li> <a href="#" role="menuitem" value="-1">显示所有</a></li>' +
            //' </ul> 条</div>',
            "zeroRecords": "没有匹配结果",
            "info": "_START_-_END_条，共 _TOTAL_ 条",
            "search": "搜索",
            "emptyTable": "表格中没有数据",
            "infoFiltered": " - 从_MAX_条结果中过滤",
            "infoEmpty": "0-0条，共0条",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        }
    });
});
function submitToAudit(uuid){
    $.ajax({
        url: "submitToAuditIntroduce",
        type: "post",
        dataType: "json",
        data: {
            uuid:uuid
        },
        success: function (result) {
            var resultFlag = result.resultFlag;
            if (resultFlag == "success") {

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