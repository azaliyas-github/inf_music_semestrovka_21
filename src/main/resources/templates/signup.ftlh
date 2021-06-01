<#include "base/layout.ftlh">
<#macro title>Confirmation</#macro>

<#macro base_head>
    <link rel="stylesheet" href="/layout/styles/signup.css">
</#macro>

<#macro base_body>
    <div id="container">
        <h1>Sign up</h1>

        <form>
            <input type="text" name="name" placeholder="Name">
            <input type="text" name="lastName" placeholder="Last name">
            <input type="email" name="email" placeholder="E-mail">
            <input type="password" name="pass" placeholder="Password">
            <a href="#">Sign up</a>
        </form>
    </div>
</#macro>

<@display_page/>