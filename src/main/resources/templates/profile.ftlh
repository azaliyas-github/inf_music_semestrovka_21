<#include "base/layout.ftlh">
<#macro title>Profile</#macro>

<#macro head>
    <link href="/layout/styles/profile.css" rel="stylesheet" type="text/css">
</#macro>

<#macro custom_header>
    <div id="profile_header" class="hoc clear">
        <div class="inner_profile_header">
            <div id="outer_img_shell"></div>
            <div class="image-cropper">
                <#if profile??>
                    <img id="profile_image" src="/api/images/${profile.photoFileName}">
                </#if>
            </div>
            <#if user??>
                <p id="name_and_lastName">${user.name} ${user.lastName}</p>
            </#if>
        </div>
        <div id="just_some_part" class="hoc"></div>
    </div>
</#macro>

<#macro body>
    <#if user??>
        <div id="personal_inf_outer_block" class="content">
            <div id="personal_inf_inner_block" class="personal_inf_block_border">
                <p id="p_i_str">Personal information</p>
            </div>
            <div class="personal_inf_block_style personal_inf_block_border">
                <p class="thin_font"><i class="fa-envelope fa-fw fas"></i>Email</p>
                <p class="thick_font">${user.email}</p>
            </div>
        </div>
    </#if>
</#macro>

<@display_page/>
