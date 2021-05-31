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
                            <h5 class="sheetName">${sheet.title}</h5>
                            <h6 class="sheetAuthor">${sheet.composerName}</h6>
                            <#if sheet.instruments??>
                                <p class="instruments_list">
                                    ${sheet.instruments
                                    ?map(x -> x.getName())
                                    ?join(", ")
                                    ?left_pad(20)[0..*20]
                                    ?ensure_ends_with("…")}
                                </p>
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
