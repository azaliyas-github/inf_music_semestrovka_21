<#function notEmptyMacro macro>
    <#assign value><@macro/></#assign>
    <#return value?has_content>
</#function>
