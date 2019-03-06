package com.avalon.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 
 * @类名: Bootstrapnterceptor
 * @功能描述:拦截器
 * @类创建人: Evan
 * @类创建时间： 2016-1-13 下午04:07:26
 */
public class Bootstrapnterceptor implements HandshakeInterceptor {

    /**
     * 握手前
     *
     * @param request
     * @param response
     * @param webSocketHandler
     * @param stringObjectMap
     * @return
     * @throws Exception
     */
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> stringObjectMap) throws Exception {
        
    	System.out.println("握手前");
    	return true;
    }

    /**
     * 握手成功后
     *
     * @param request
     * @param response
     * @param handler
     * @param e
     */
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception e) {
    	System.out.println("握手成功后");
    }
}
