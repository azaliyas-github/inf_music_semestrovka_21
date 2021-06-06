<#include "base/layout.ftlh">
<#import "sheet-list.ftlh" as sheet_list>
<#macro title>Sheets</#macro>

<#macro head>
    <link href="/layout/styles/sheets.css" rel="stylesheet" type="text/css" xmlns="http://www.w3.org/1999/html">
    <script src="/layout/scripts/sheets.js"></script>
</#macro>

<#macro body>
    <section>
        <form id="search-form" method="get" action="/sheets/search">
            <input class="query_string" type="text" name="query">
            <div class="select">
                <select name="instruments">
                    <#list instruments as instrument, _>
                        <option>${instrument}</option>
                    </#list>
                </select>
                <div class="select__arrow arrow-first"></div>
            </div>
            <button id="search-submit-button" type="submit">Submit</button>
        </form>
    </section>
    <section class="sec-add-form">
        <div id="add-form-shell">
            <h3>Add new sheet</h3>
            <@spring.bind "sheetForm"/>
            <form id="addForm" method="post" action="/add_sheet" enctype="multipart/form-data">
                <@spring.formInput "sheetForm.name", "id='name' placeholder='Name'
                class='query_string' required"/>
                <@spring.formInput "sheetForm.composer", "id='composer' required class='query_string'
                placeholder='Composer name'"/>
                <div class="select">
                    <div id="instruments">
                        <@spring.formSingleSelect "sheetForm.instruments", instruments,
                        "required id='instruments' autocomplete='on'"/>
                        <div class="select__arrow arrow-second"></div>
                    </div>
                </div>
                <div class="upload-btn-wrapper">
                    <button class="btn">Upload a file</button>
                    <@spring.formInput "sheetForm.file", "id='file' accept='.pdf'", "file"/>
                </div>
                    <button id="add-sheet-button" type="submit" form="addForm">Add</button>
            </form>
        </div>
    </section>
    <section class="products">
        <div id="sheets-output-block">
            <@sheet_list.content sheets/>
        </div>
    </section>
</#macro>

<@display_page/>
