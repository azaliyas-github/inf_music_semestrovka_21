<#macro links>
    <link href="/layout/styles/base/auth-section.css" rel="stylesheet" type="text/css">
    <script src="/layout/scripts/base/auth-section.js"></script>
</#macro>

<#macro content>
    <div class="auth-section">
        <div id="sign-up-container" class="auth-form">
            <form action="#">
                <h1>Create Account</h1>
                <input name="name" type="text" placeholder="Name" />
                <input name="lastName" type="text" placeholder="Last name" />
                <input name="email" type="email" placeholder="Email" />
                <input name="password" type="password" placeholder="Password" />
                <button>Sign Up</button>
            </form>
        </div>
        <div id="sign-in-container" class="auth-form">
            <form action="#">
                <h1>Sign in</h1>
                <input name="email" type="email" placeholder="Email" />
                <input name="password" type="password" placeholder="Password" />
                <label class="remember-me-checkbox">
                    <input name="rememberMe" type="checkbox">Remember me
                </label>
                <button>Sign In</button>
            </form>
        </div>
        <div class="outer-hello">
            <div class="inner-hello">
                <div class="hello-panel left-panel">
                    <h1>Welcome Back!</h1>
                    <p>To keep connected with us please login with your personal info</p>
                    <button class="ghost" id="show-signIn">Sign In</button>
                </div>
                <div class="hello-panel right-panel">
                    <h1>Hello, Friend!</h1>
                    <p>Enter your personal details and start journey with us</p>
                    <button class="ghost" id="show-signUp">Sign Up</button>
                </div>
            </div>
        </div>
    </div>
</#macro>
