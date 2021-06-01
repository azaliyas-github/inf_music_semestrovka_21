<#include "base/layout.ftlh">
<#import "spring.ftl" as spring/>
<#macro title>Confirmation</#macro>

<#macro base_head>
    <link rel="stylesheet" href="/layout/styles/signup.css">
    <style>
		.error {
            text-align: center;
			color: red;
		}
    </style>
</#macro>

<#macro base_body>
    <div id="container">
        <h1>Sign up</h1>
        <@spring.bind "registrationForm"/>
        <form method="post" action="/auth/signup">
            <@spring.formInput "registrationForm.name" "placeholder='Name'", "text"/>
            <@spring.showErrors "<br>" "error"/>
            <@spring.formInput "registrationForm.lastName" "placeholder='Lastname'", "text"/>
            <@spring.showErrors "<br>" "error"/>
            <@spring.formInput "registrationForm.email" "placeholder='Email'", "email"/>
            <@spring.showErrors "<br>" "error"/>
            <@spring.formInput "registrationForm.password" "placeholder='Password'", "password"/>
            <@spring.showErrors "<br>" "error"/>
            <input type="submit" value="Sign up">
        </form>
    </div>
</#macro>

<@display_page/>