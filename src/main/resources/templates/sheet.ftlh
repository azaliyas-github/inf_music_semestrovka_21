<#include "base/layout.ftlh">

<#macro title>Моя тестовая страница</#macro>

<#macro body>
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
        <#if sheets??>
            <#list sheets as sheet>
                <div class="outer_shell">
                    <img href="${sheet.src}" class="" src="${sheet.previewSrc}">
                    <div class="text_block">
                        <div class="sheetName"><a href="${sheet.src}" download="">${sheet.title}</a></div>
                        <div class="sheetAuthor"><a>${sheet.composersName}</a></div>
                    </div>
                </div>
            </#list>
        </#if>
    </section>
</#macro>

<@display_page/>
