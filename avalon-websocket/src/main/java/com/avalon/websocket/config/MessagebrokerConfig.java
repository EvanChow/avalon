package com.avalon.websocket.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.avalon.websocket.config.MessageBrokerTaskScheduler;

@Configuration
@EnableWebSocketMessageBroker
public class MessagebrokerConfig extends AbstractWebSocketMessageBrokerConfigurer{

    public void configureMessageBroker(MessageBrokerRegistry config) {
        //消息主题配置
        TaskScheduler  taskScheduler=new MessageBrokerTaskScheduler();
        config.enableSimpleBroker("/topic/").setTaskScheduler(taskScheduler);
        
        //消息发送端配置
        config.setApplicationDestinationPrefixes("/app");
        
        //路径规则修改，默认为  / 
       // config.setPathMatcher(new AntPathMatcher("."));
       // config.enableStompBrokerRelay(destinationPrefixes);
        //config.enableSimpleBroker(destinationPrefixes);
    }
    
    /**
     * Websocket  URL
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //Websocket 地址配置,允许跨域请求
        registry.addEndpoint("/avalon/messagebroker").setAllowedOrigins("*").withSockJS();
    }

}
