<!doctype html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Моя тестовая страница</title>
</head>
<body>
<section>
    <form id="addForm" method="post" action="/add_sheet" enctype="multipart/form-data">
        <select required name="instruments" multiple autocomplete="on">
            <option>Guitar</option>
            <option>Piano</option>
            <option>Vocal</option>
            <option>Violin</option>
            <option>Drums</option>
            <option>Music history</option>
        </select>
        <input type="text" name="sheetName" placeholder="Title">
        <input type="text" name="composersName" placeholder="Composer`s name">
        <p><input type="file" name="pdf"
                  multiple accept="application/pdf">
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
</body>
</html>