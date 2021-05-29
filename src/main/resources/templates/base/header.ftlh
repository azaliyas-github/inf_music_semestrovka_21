<#import "common.ftlh" as common>
<#import "menu.ftlh" as menu>

<#macro links>
    <link href="/layout/styles/header.css" rel="stylesheet" type="text/css" media="all">
</#macro>

<#macro content title custom_header>
    <div class="bgded overlay" style="background-image:url('/images/backgrounds/01.png');">
        <div class="wrapper row1">
        <@menu.content/>
        </div>
        <#if common.notEmptyMacro(custom_header)>
    <@custom_header/>
        <#else>
        <div id="breadcrumb" class="hoc clear">
            <h6 class="heading"><@title/></h6>
        </div>
        </#if>
    </div>
</#macro>
