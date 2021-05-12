<#import "/spring.ftl" as spring>
<#import "header.ftlh" as header>

<#macro main_lang>ru</#macro>
<#macro base_html>
<html lang="<@main_lang/>">
</#macro>

<#macro title>Страница</#macro>

<#macro base_title>
    <title><@title/></title>
</#macro>

<#macro head></#macro>

<#macro base_head>
    <meta charset="UTF-8">
<@base_title/>
<@header.links/>
<@head/>
</#macro>

<#macro body></#macro>

<#macro base_body>
<@header.content/>
<@body/>
</#macro>

<#macro display_page>
<!DOCTYPE html>
<@base_html/>
<#--noinspection HtmlRequiredTitleElement-->
<head>
    <@base_head/>
</head>
<body>
    <@base_body/>
</body>
</html>
</#macro>
