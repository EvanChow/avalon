package com.avalon.websocket.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;



@Controller
public class WebSocketController {  
    
    /**
     * 
     * @方法名: addNum
     * @功能描述: 广播消息
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2016-5-25 下午05:32:40
     */
    
    @MessageMapping("/all")
    @SendTo("/topic/showResult")
    public Map<String,String> SendToAll(Map<String,String> input,Principal principal) throws Exception {
        System.out.println("广播推送，所有用户都收得到:"+input); 
        return input;
    }
    
    @MessageMapping("/one")
    @SendToUser(value ="/topic/greetings",broadcast = false)
    public Map<String,String> SendToUser(Map<String,String> input,Principal principal) throws Exception {
        
        System.out.println("精准推送，只推送到" + principal.getName()+"，msg:"+input); 
        return input;
    }
    
   
    @SubscribeMapping("/topic/greetings")
    public String sub(Principal principal) {
        System.out.println(principal.getName()+"用户订阅了我。。。");
        return new String("感谢你订阅了我。。。");
    }
    
    @SubscribeMapping("/topic/showResult")
    public String showResult(Principal principal) {
        System.out.println(principal.getName()+"用户订阅了我。。。");
        return new String("感谢你订阅了我。。。");
    }
    
    
}
