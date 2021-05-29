<#import "/spring.ftl" as spring>
<#import "header.ftlh" as header>
<#import "footer.ftlh" as footer>

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
    <link href="/layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<@base_title/>
<@header.links/>
<@footer.links/>
<@head/>
</#macro>

<#macro custom_header></#macro>
<#macro body></#macro>
<#macro base_body>
<@header.content title custom_header/>
<#if custom_main??>
<@custom_main/>
<#else>
<div class="wrapper row3">
    <main class="hoc container clear">
        <div class="content">
        <@body/>
        </div>
        <div class="clear"></div>
    </main>
</div>
</#if>
<@footer.content/>
<a id="backtotop" href="#top"><i class="fas fa-chevron-up"></i></a>
</#macro>

<#macro scripts></#macro>
<#macro base_scripts>
<script src="/layout/scripts/jquery.min.js"></script>
<script src="/layout/scripts/jquery.backtotop.js"></script>
<script src="/layout/scripts/jquery.mobilemenu.js"></script>
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
    <@base_scripts/>
</body>
</html>
</#macro>
