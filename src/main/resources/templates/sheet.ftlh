<#include "base/layout.ftlh">
<#macro title>Моя тестовая страница</#macro>

<#macro head>
    <link rel="stylesheet" href="sheet.css">
</#macro>

<#macro body>
    <main>
        <section>
            <@spring.bind "sheetForm"/>
            <form id="addForm" method="post" action="/add_sheet" enctype="multipart/form-data">
                <@spring.formMultiSelect "sheetForm.instruments", instruments,
                "required autocomplete='on'"/>
                <@spring.formInput "sheetForm.name", "id='sheet_name' placeholder='Name' required"/>
                <@spring.formInput "sheetForm.composer", "id='sheet_composer' required
             placeholder='Composer name'"/>
                <p><@spring.formInput "sheetForm.file", "id='file' accept='.pdf'", "file"/>
                    <button type="submit" form="addForm">Добавить</button></p>
            </form>
        </section>
        <section>
            <div id="sheets-output-block">
                <#if sheets??>
                    <#list sheets as sheet>
                        <a href="/pdf/${sheet.src}" download="" class="product box
                        main_flex__nowrap flex_jcontent_center flex_align_items_center">
                            <div class="img_product">
                                <img src="/images/${sheet.previewSrc}">
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
            </div>
        </section>
    </main>

</#macro>

<@display_page/>
