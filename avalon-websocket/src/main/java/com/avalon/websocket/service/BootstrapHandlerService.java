package com.avalon.websocket.service;


import com.avalon.websocket.service.session.WebSocketSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class BootstrapHandlerService{
	
    @Autowired
    private WebSocketSessionService sessionService;
    
    

    public void afterConnectionEstablished(WebSocketSession session){
        sessionService.onLine(session);
    }
    
    
	public void handleMessage(WebSocketSession session,StringBuffer sb){
	   // loggerNormal.info("接收消息"+sb);
	    sessionService.sendAllMessage(sb.toString());
	}
	
	public void handleError(WebSocketSession session, Throwable throwable){	
		//loggerNormal.info("异常结束");
		sessionService.offLine(session);
	}
	
	public void connectionClose(WebSocketSession session){
		//loggerNormal.info("连接关闭");
		//loggerNormal.info("清空 onlines sessionID"+session.getId());
		sessionService.offLine(session);
	}
	
	//一旦HTTP认证成功 这个方法先被调用 如果返回true 则进行上面那么N多方法的流程。如果返回的是false就直接拦截掉了。不会调用上面那些方法了！！
    //就好像个构造器一样。这个是处理器 BootstrapHandler的构造器~
	public boolean supportsPartialMessages() {
        
       return true;
   }
	
	
}
