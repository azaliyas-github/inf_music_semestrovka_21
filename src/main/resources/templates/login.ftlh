<#include "base/layout.ftlh">
<#macro title>Confirmation</#macro>

<#macro base_head>
    <link rel="stylesheet" href="/layout/styles/login.css">
</#macro>

<#macro base_body>
    <div id="container">
        <h1>Log In</h1>

        <form>
            <input type="email" name="email" placeholder="E-mail">
            <input type="password" name="pass" placeholder="Password">
            <a href="#">Log in</a>
            <div id="remember-container">
                <input type="checkbox" id="checkbox-2-1" class="checkbox" checked="checked"/>
                <span id="remember">Remember me</span>
            </div>
        </form>
    </div>
</#macro>

<@display_page/>
