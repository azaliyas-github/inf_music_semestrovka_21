<#include "base/layout.ftlh">
<#import "spring.ftl" as spring/>
<#macro title>Confirmation</#macro>

<#macro base_head>
    <link rel="stylesheet" href="/layout/styles/login.css">
</#macro>

<#macro base_body>
    <div id="container">
        <h1>Log In</h1>
        <form method="post" action="/auth/login">
            <p></p>
            <input type="email" name="email" placeholder="E-mail">
            <input type="password" name="password" placeholder="Password">
            <input type="submit" value="Log in">
            <div id="remember-container">
                <input type="checkbox" name="rememberMe" id="checkbox-2-1" class="checkbox" checked="checked"/>
                <span id="remember">Remember me</span>
            </div>
        </form>
    </div>
</#macro>

<@display_page/>
