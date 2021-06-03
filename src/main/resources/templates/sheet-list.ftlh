<#function generateTooltipAttrIfNeed text maxLength>
    <#assign tooltipAttr>title="${text}"</#assign>
    <#return (text?length > maxLength)?then(tooltipAttr, "")>
</#function>

<#macro content sheets>
    <#if sheets??>
        <#list sheets as sheet>
            <div class="inner-shell">
                <a href="/api/pdf/${sheet.src}" download="">
                    <div class="product-card">
                        <div class="product-image">
                            <img src="/api/images/${sheet.previewSrc}" />
                        </div>
                        <div class="product-info">
                            <h5 class="sheetName"
                                    ${generateTooltipAttrIfNeed(sheet.title, 20)}>
                                ${sheet.title?truncate_w(20, "…")}
                            </h5>
                            <h6 class="sheetAuthor"
                                    ${generateTooltipAttrIfNeed(sheet.composerName, 27)}>
                                ${sheet.composerName?truncate_w(27, "…")}
                            </h6>
                            <#if sheet.instruments??>
                                <#assign instrumentsLine=sheet.instruments
                                    ?map(x -> x.getName())
                                    ?join(", ") />
                                <p class="instruments_list"
                                        ${generateTooltipAttrIfNeed(instrumentsLine, 25)}>
                                    ${instrumentsLine?truncate_w(25, "…")}
                                </p>
                            </#if>
                            <#if authorities?seq_contains("MODERATOR")>
                                <p>User id: ${sheet.createdByUserId}</p>
                            </#if>
                        </div>
                    </div>
                </a>
            </div>
        </#list>
    </#if>
</#macro>

<content>
    <@content sheets/>
</content>
