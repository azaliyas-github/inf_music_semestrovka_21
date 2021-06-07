package ru.itis.config;

import org.springframework.context.annotation.*;
import org.springframework.messaging.simp.config.*;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	private final String websocketsPrefix = "/chat";

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(websocketsPrefix + "/users");
		config.setApplicationDestinationPrefixes(websocketsPrefix);
		config.setUserDestinationPrefix(websocketsPrefix + "/users");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(websocketsPrefix).withSockJS();
	}
}
