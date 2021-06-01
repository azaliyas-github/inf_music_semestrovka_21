<#macro links>
    <link href="/layout/styles/base/menu.css" rel="stylesheet" type="text/css" media="all">
</#macro>

<#macro content>
    <header id="header" class="hoc clear">
        <div id="logo" class="fl_left">
            <h1>
                <a href="/">
                    <p id="upper_logo_part"> Conservatorio di Musica</p>
                    <p id="lower_logo_part">Vincenzo Bellini</p>
                </a>
            </h1>
        </div>
        <nav id="mainav" class="fl_right">
            <ul class="clear">
                <li><a href="/">Home</a></li>
                <li class="active"><a href="/sheets" class="drop">Sheets</a>
                    <ul>
                        <li><a href="/sheets?instruments=Guitar">Guitar</a></li>
                        <li><a href="/sheets?instruments=Piano">Piano</a></li>
                        <li><a href="/sheets?instruments=Violin">Violin</a></li>
                        <li><a href="/sheets?instruments=Vocal">Vocal</a></li>
                        <li><a href="/sheets?instruments=Drums">Drums</a></li>
                    </ul>
                </li>
                <li><a class="drop" href="#">About our school</a>
                    <ul>
                        <li><a class="drop" href="#">Courses</a>
                            <ul>
                                <li><a href="/courses/guitar">Guitar</a></li>
                                <li><a href="/courses/piano">Piano</a></li>
                                <li><a href="/courses/violin">Violin</a></li>
                                <li><a href="/courses/vocal">Vocal</a></li>
                                <li><a href="/courses/drums">Drums</a></li>
                            </ul>
                        </li>
                        <li><a href="/prices">Prices</a></li>
                        <li><a href="/contact_information">Contact information</a></li>
                    </ul>
                </li>
                <li>
                    <#if authentication.getPrincipal() == "anonymousUser">
                    <a href="#auth-window" class="show-modal-window prevent-default-onclick">
                        Sign in
                    </a>
                    <#else>
                    <a href="/profile">Profile</a>
                    </#if>
                </li>
            </ul>
        </nav>
    </header>
</#macro>
