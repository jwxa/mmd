
    <thead>
    <tr>
        <th>#</th>
        <th>#</th>
        <th>#</th>
        <th>#</th>
        <th>#</th>
        <th>#</th>
    </tr>
    </thead>
        <tbody>
        <#if examMap??&&examMap?size gt 0>
            <#list examMap?keys as key>
            <tr>
                <#assign colMap=examMap[key]/>
                <#if colMap??&&colMap?size gt 0>
                <td>#</td>
                    <#list colMap?keys as key>
                        <#assign shownWords=colMap[key].shownList/>
                        <#assign unshownWords=colMap[key].unshownList/>
                        <td title="<#list unshownWords as unshownWord>${unshownWord.value} </#list>">
                        <#list shownWords as shownWord>
                        ${shownWord.value}&nbsp;
                        </#list>
                        </td>
                    </#list>
                </#if>
            </tr>
            </#list>
        </#if>
        </tbody>
