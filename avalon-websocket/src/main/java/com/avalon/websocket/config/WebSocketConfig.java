package com.avalon.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.avalon.websocket.handler.BootstrapHandler;
import com.avalon.websocket.interceptor.Bootstrapnterceptor;

/**
 * WebSocket 配置类
 */
@Configuration //一定不能少
//@EnableWebMvc //影响默认配置
@EnableWebSocket //不能少
public class WebSocketConfig  extends WebMvcConfigurerAdapter implements WebSocketConfigurer  {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(bootstrapHandler(), "/avalon/websocket").addInterceptors(bootstrapnterceptor()).setAllowedOrigins("*");
        //registry.addHandler(bootstrapHandler(),"/avalon/websocket/**").setAllowedOrigins("http://192.168.5.122:8080");
        registry.addHandler(bootstrapHandler(),"/avalon/websocket/**").addInterceptors(bootstrapnterceptor()).setAllowedOrigins("*").withSockJS();
        
    }
    
    // Allow serving HTML files through the default Servlet    // 完全可以无视下面的代码 
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public BootstrapHandler bootstrapHandler(){
        return new BootstrapHandler();
    }
    @Bean
    public Bootstrapnterceptor bootstrapnterceptor(){
        return new Bootstrapnterceptor();
    }
    
    
   
    

}
