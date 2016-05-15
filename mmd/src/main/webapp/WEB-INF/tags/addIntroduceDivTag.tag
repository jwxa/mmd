<%@ tag pageEncoding="UTF-8" %>
<%@ tag body-content="empty" %>
<%@ tag dynamic-attributes="tagAttrs" %>
<div id="addDiv">
    <div class="panel panel-inverse" data-sortable-id="form-stuff-1">
        <div class="panel-heading">
            <div class="panel-heading-btn">
                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reset" data-original-title="" title=""><i class="fa fa-repeat" style="padding-top: 4px"></i></a>
                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger"  data-click="panel-close" data-original-title="" title=""><i class="fa fa-times" style="padding-top: 4px"></i></a>
            </div>
            <h4 class="panel-title">新增简介</h4>
        </div>
        <div class="panel-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="col-md-3 control-label">标题</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" placeholder="在此输入标题，最大不超过50个字符" maxlength="50" id="addIntroduceTitle">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">操作人</label>
                    <div class="col-md-9">
                        <input type="text" class="form-control" placeholder="${user_info.userName}" disabled="">N
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">内容</label>
                    <div class="col-md-9">
                        <textarea class="form-control" placeholder="在此输入你要介绍的软件的内容" rows="10" id="addIntroduceArticle"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-9">
                        <button type="button" class="btn btn-sm btn-success" onclick="submitIntroduce()">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
