<#include "base/layout.ftlh">
<#macro title>Confirmation error</#macro>

<#macro head>
    <link rel="stylesheet" href="/layout/styles/email-confirmation-error.css">
</#macro>

<#macro body>
    <h1 id="error" class="c-r-f">Error!</h1>
    <p id="reg" class="c-r-f">Can't complete registration.</p>
    <br/>
    <p id="reg" class="c-r-f"><a href="/">Click</a> to get new confirmation mail.</p>
    <button id="resend-mail-button">Resend mail</button>
</#macro>

<@display_page/>