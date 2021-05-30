<#include "base/layout.ftlh">
<#import "sheet-list.ftlh" as sheet_list>
<#macro title>Моя тестовая страница</#macro>

<#macro head>
    <link href="/layout/styles/sheets.css" rel="stylesheet" type="text/css">
    <script src="/layout/scripts/sheets.js"></script>
</#macro>

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
    </section>
    <section>
        <div id="sheets-output-block">
            <@sheet_list.content sheets/>
        </div>
    </section>
    <section>
        <form id="search-form" method="get" action="/sheets/search">
            <select multiple name="instruments">
                <#list instruments as instrument, _>
                    <option>${instrument}</option>
                </#list>
            </select>
            <input type="text" name="query">
            <button type="submit">Тык</button>
        </form>
    </section>
</#macro>

<@display_page/>
