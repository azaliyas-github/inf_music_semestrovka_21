<#macro content sheets>
    <#if sheets??>
        <#list sheets as sheet>
            <a href="/pdf/${sheet.src}" download="" class="product box
                            main_flex__nowrap flex_jcontent_center flex_align_items_center">
                <div class="img_product">
                    <img src="/images/${sheet.previewSrc}" />
                </div>
                <h2 class="sheetName">${sheet.title}</h2>
                <p class="sheetAuthor">${sheet.composerName}</p>
                <#if sheet.instruments??>
                    <p class="instruments_list">
                        ${sheet.instruments
                        ?map(x -> x.getName())
                        ?join(", ")
                        ?left_pad(20)[0..*20]
                        ?ensure_ends_with("…")}
                    </p>
                </#if>
            </a>
        </#list>
    </#if>
</#macro>

<content>
    <@content sheets/>
</content>
