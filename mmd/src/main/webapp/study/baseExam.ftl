<div class="container">
<#list questionVOList as question>
    <div class="col-md-12" style="margin-top: 15px;">
        <label><b>第${question_index+1}题.</b>&nbsp;&nbsp;${question.question}<br></label>
    </div>
    <div class="radio" id="radio_${question_index}" >
        <div class="col-md-12" >
            <label>
                <input name="optionsRadios_${question_index}" value="0" type="radio">
            ${question.options[0]}&nbsp;&nbsp;&nbsp;&nbsp;
            </label>
            <label>
                <input name="optionsRadios_${question_index}" value="1" type="radio">
            ${question.options[1]}&nbsp;&nbsp;&nbsp;&nbsp;
            </label>
            <label>
                <input name="optionsRadios_${question_index}" value="2" type="radio">
            ${question.options[2]}&nbsp;&nbsp;&nbsp;&nbsp;
            </label>
            <label>
                <input name="optionsRadios_${question_index}" value="3" type="radio">
            ${question.options[3]}&nbsp;&nbsp;&nbsp;&nbsp;
            </label>
        </div>
    <#--<br>-->
    <#--<label>-->
    <#--答案：${question.options[question.correctAnswerSeq]}-->
    <#--</label>-->
    </div>
</#list>
</div>
<div class="form-group col-md-6" style="padding-left: 45px; padding-top: 10px;">
    <form id="reloadForm">
        <div class="col-md-1">
            <button type="button" onclick="submitQuestion()" class="btn btn-sm btn-success" id="submitBtn">提交评分</button>
        </div>
        <div class="col-md-1">
            <button type="button" onclick="reloadQuestion()" class="btn btn-sm btn-success">换题目</button>
        </div>
        <input type="hidden" name="createType" id="createType" value="${createType}">
        <input type="hidden" name="aliasType" id="aliasType" value="${aliasType}">
    </form>
</div>
