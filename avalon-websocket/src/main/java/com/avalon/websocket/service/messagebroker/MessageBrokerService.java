package com.avalon.websocket.service.messagebroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageBrokerService {
    
    @Autowired
    private SimpMessagingTemplate template;
    
    /**
     * 
     * @方法名: broadCast
     * @功能描述: 广播
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2017-12-25 上午11:06:47
     */
    public void SendAll(String topic,Object msg){
        template.convertAndSend(topic, msg);
       
    }
    
    /**
     * 
     * @方法名: SendToUser
     * @功能描述: 一对一发送，发送特定的客户端
     * @参数：@param
     * @返回：@return
     * @创建人: Evan
     * @创建时间： 2017-12-25 上午11:08:25
     */
    public void SendToUser(String uid,String topic,Object msg){
        template.convertAndSendToUser(uid, topic, msg);
    }
    
    
}
