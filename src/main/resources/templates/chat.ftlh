<#macro links>
	<link href="/layout/styles/chat.css" rel="stylesheet" type="text/css">
	<script src="/layout/scripts/base/sockjs-1.5.1.min.js"></script>
	<script src="/layout/scripts/base/stomp-4.0.8.min.js"></script>
	<script src="/layout/scripts/chat.js"></script>
</#macro>

<#macro content current_user>
	<script>
		const isModerator = ${authorities?seq_contains("MODERATOR")?c};
		const currentUserId = ${current_user.id};
	</script>
	<#assign recipientSelectedClass>
		 ${authorities?seq_contains("MODERATOR")?then("", " recipient-selected")}
	</#assign>
	<div class="chat-window not-connected${recipientSelectedClass}">
		<#if authorities?seq_contains("MODERATOR")>
			<aside>
				<ul class="users">
					<li class="recipient-selector prototype">
						<img class="profile-photo" src="">
						<div>
							<h2 class="user-name"></h2>
						</div>
					</li>
				</ul>
			</aside>
		</#if>
		<main>
			<header>
				<img id="selected-recipient-photo" src="">
				<div>
					<h2 id="selected-recipient-name"></h2>
				</div>
			</header>
			<ul id="chat">
				<li class="prototype">
					<div class="entete">
						<h2 class="author-name"></h2>
						<h3 class="message-datetime"></h3>
					</div>
					<div class="triangle"></div>
					<div class="message"></div>
				</li>
			</ul>
			<footer>
				<textarea class="message-content" placeholder="Type your message"></textarea>
				<a class="send-button" href="#">Send</a>
			</footer>
		</main>
	</div>
</#macro>
