<#import "common.ftlh" as common>

<#macro content body custom_main>
    <#if common.notEmptyMacro(custom_main)>
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
</#macro>
