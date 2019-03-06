package com.avalon.websocket.handler;

import com.avalon.websocket.service.BootstrapHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;



/**
 * 
 * @类名: BootstrapHandler
 * @功能描述:握手类
 * @类创建人: Evan
 * @类创建时间： 2016-1-13 下午04:07:05
 */
public class BootstrapHandler implements WebSocketHandler {

    @Autowired
    private BootstrapHandlerService bootstraphandlerservice;
    
    
    /**
     * 双工通讯 连接后 并且在这里心跳
     *
     * @param session
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
        // loggerNormal.info("连接成功!");
         bootstraphandlerservice.afterConnectionEstablished(session);
//        TextMessage textMessage;
//        try {
//            HttpHeaders headers = session.getHandshakeHeaders();
//            String userAgent = headers.get("user-agent").get(0);
//            logger.info("LOGIN : " + userAgent);
//           //构造回应的消息，每次连接成功后要回应消息！
//            //告诉客户端已经连接成功了！消息就在这里面构造
//            textMessage = new TextMessage("连接成功");
//            bootstraphandlerservice.afterConnectionEstablished(session);
//        } catch (Exception e) {
//            e.printStackTrace();
//            textMessage = new TextMessage("连接失败");
//        }
//           //这样就发送给客户端了~ 很简单！！
//        session.sendMessage(textMessage);
    }


    /**
     * 处理发送过来的消息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage message) throws Exception {
        
        //loggerNormal.info("接收消息"+message.toString());
//        StringBuffer sb = new StringBuffer((String) message.getPayload());
//        System.out.println(sb.toString());
        try {
         //如果连接成功！！这里面会不停的接收到心跳包！！ 怎么处理~看你的了！！！ 总之这个方法就是接受客户端发来消息的方法！！！

        // message.getPayload()得到的是客户端发来的消息，比如“你好啊！” 之类的。得到后转成String就能处理了！
            StringBuffer sb = new StringBuffer((String) message.getPayload());
         //这个是我自己写的一个处理业务逻辑。你可以实现自己的业务逻辑
            bootstraphandlerservice.handleMessage(session, sb);  
           
        } catch (Exception e) {
            e.printStackTrace();
           
        }
    }

    /**
     * 客户端 异常断开
     *
     * @param session
     * @param throwable
     * @throws Exception
     */
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        
        //loggerNormal.info(session.getId() + " - 异常断开连接");
        //所谓异常断开，例如：突然关闭HTML页面等等，总之不是用户正常关闭的！
         //这个也是我自己实现的 异常处理的业务逻辑，你可以自己写
        //bootstraphandlerservice.handleError(session, throwable);
    }

    /**
     * 连接已经断 开
     *
     * @param session
     * @param status
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
         
        //只要是断开连接！不管是异常断开，还是普通正常断开，一定会进入这个方法。
        String reason = status.getReason();
        if (reason == null) {
            reason = "客户端 按指令正常退出";
        }
        
        //loggerNormal.info(session.getId() + " - 已经主动关闭连接 - 关闭码 - " + status.getCode() + " - 缘由 -" + reason);

        //其实这里面封装了个session.close()释放了一些资源， 也是我自己实现的业务逻辑，你也可以自己写！
          bootstraphandlerservice.connectionClose(session);
        
        
        
    }

    /**
     * 握手成功 初始化操作在这里面进行
     *
     * @return
     */

    public boolean supportsPartialMessages() {
         //一旦HTTP认证成功 这个方法先被调用 如果返回true 则进行上面那么N多方法的流程。如果返回的是false就直接拦截掉了。不会调用上面那些方法了！！
         //就好像个构造器一样。这个是处理器 BootstrapHandler的构造器~
        return true;
    }
      

}
