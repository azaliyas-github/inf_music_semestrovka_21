<#macro links>
	<link href="/layout/styles/chat.css" rel="stylesheet" type="text/css">
	<script src="/layout/scripts/chat.js"></script>
</#macro>

<#macro content>
	<div class="chat-window">
		<aside>
			<#if chat_users??>
				<ul>
					<#list chat_users as user>
						<li class="recipient-selector">
							<input class="user-id" value="${user.id}">
							<img src="/api/images/${user.photoFileName}">
							<div>
								<h2>${user.fullName}</h2>
							</div>
						</li>
               </#list>
				</ul>
         </#if>
		</aside>
		<main>
			<header>
				<img id="selected-recipient-photo" basesrc="/api/images/" src="">
				<div>
					<h2 id="selected-recipient-name"></h2>
				</div>
			</header>
			<ul id="chat">
				<li class="you">
					<div class="entete">
						<span class="status green"></span>
						<h2>Vincent</h2>
						<h3>10:12AM, Today</h3>
					</div>
					<div class="triangle"></div>
					<div class="message">
						Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
					</div>
				</li>
				<li class="me">
					<div class="entete">
						<h3>10:12AM, Today</h3>
						<h2>Vincent</h2>
						<span class="status blue"></span>
					</div>
					<div class="triangle"></div>
					<div class="message">
						Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
					</div>
				</li>
				<li class="me">
					<div class="entete">
						<h3>10:12AM, Today</h3>
						<h2>Vincent</h2>
						<span class="status blue"></span>
					</div>
					<div class="triangle"></div>
					<div class="message">
						OK
					</div>
				</li>
				<li class="you">
					<div class="entete">
						<span class="status green"></span>
						<h2>Vincent</h2>
						<h3>10:12AM, Today</h3>
					</div>
					<div class="triangle"></div>
					<div class="message">
						Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
					</div>
				</li>
				<li class="me">
					<div class="entete">
						<h3>10:12AM, Today</h3>
						<h2>Vincent</h2>
						<span class="status blue"></span>
					</div>
					<div class="triangle"></div>
					<div class="message">
						Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor.
					</div>
				</li>
				<li class="me">
					<div class="entete">
						<h3>10:12AM, Today</h3>
						<h2>Vincent</h2>
						<span class="status blue"></span>
					</div>
					<div class="triangle"></div>
					<div class="message">
						OK
					</div>
				</li>
			</ul>
			<footer>
				<textarea placeholder="Type your message"></textarea>
				<img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_picture.png" alt="">
				<img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/1940306/ico_file.png" alt="">
				<a href="#">Send</a>
			</footer>
		</main>
	</div>
</#macro>
