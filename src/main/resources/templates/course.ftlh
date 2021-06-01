<#include "base/layout.ftlh">
<#if course??>
    <#macro title>${course.name}</#macro>

    <#macro head>
        <link rel="stylesheet" href="/layout/styles/course.css">
    </#macro>

    <#macro body>
            <div id="main-pic">
                <img src="/images/${course.photoFileName}">
            </div>
            <div id="text-part">
                ${course.descriptionHtml?no_esc}
            </div>
    </#macro>

</#if>

<@display_page/>
