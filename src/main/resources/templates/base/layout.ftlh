<#import "/spring.ftl" as spring>
<#import "header.ftlh" as header>
<#import "main.ftlh" as main>
<#import "footer.ftlh" as footer>
<#import "auth-section.ftlh" as auth_section>
<#import "modal-window.ftlh" as modal_window>

<#macro main_lang>ru</#macro>
<#macro base_html>
<html lang="<@main_lang/>">
</#macro>

<#macro title>Page</#macro>
<#macro base_title>
    <title><@title/> | Conservatorio di Musica Vincenzo Bellini</title>
</#macro>

<#macro head></#macro>
<#macro base_head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link href="/layout/styles/base/layout.css" rel="stylesheet" type="text/css" media="all">
<@base_title/>
<@base_scripts/>
<@header.links/>
<@footer.links/>
<@auth_section.links/>
<@modal_window.links/>
<@head/>
</#macro>

<#macro custom_header></#macro>
<#macro custom_main></#macro>
<#macro body></#macro>
<#macro base_body>
<@header.content title custom_header/>
<@main.content body custom_main/>
<@footer.content/>
<@auth_section.content/>
<@modal_window.content/>
</#macro>

<#macro scripts></#macro>
<#macro base_scripts>
<script src="/layout/scripts/base/jquery-3.6.0.min.js"></script>
<script src="/layout/scripts/base/mobilemenu.min.js"></script>
<@scripts/>
</#macro>

<#macro display_page>
<!DOCTYPE html>
<@base_html/>
<#--noinspection HtmlRequiredTitleElement-->
<head>
    <@base_head/>
</head>
<body id="top">
    <@base_body/>
</body>
</html>
</#macro>
