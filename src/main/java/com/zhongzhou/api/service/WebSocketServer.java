package com.zhongzhou.api.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author fengtao.xue
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {
    static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session WebSocketsession;
    //当前发消息的人员userId
    private String userId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String param, Session WebSocketsession, EndpointConfig config) {
        userId = param;
        //log.info("authKey:{}",authKey);
        this.WebSocketsession = WebSocketsession;
        //防止重复替换
        webSocketSet.put(param + "-" + System.currentTimeMillis(), this);//加入map中
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        logger.info("有连接加入，当前连接数为：{}", cnt);
//        sendMessage(this.WebSocketsession, "连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (!userId.equals("")) {
            webSocketSet.remove(userId);//从set中删除
            int cnt = OnlineCount.decrementAndGet();
            logger.info("有连接关闭，当前连接数为：{}", cnt);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("来自客户端的消息：{}", message);
        sendMessage(session, "收到消息，消息内容：" + message);
    }

    /**
     * @param session 会话
     * @param error   异常
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param message 消息
     */
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
            //session.getBasicRemote().sendText(String.format("%s",message));
        } catch (IOException e) {
            logger.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message 消息
     * @throws IOException
     */
    public void broadCastInfo(String message) {
        for (String key : webSocketSet.keySet()) {
            Session session = webSocketSet.get(key).WebSocketsession;
            if (session != null && session.isOpen() && !userId.equals(key)) {
                sendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     *
     * @param message 消息
     * @throws IOException
     */
    public void sendToUser(String userId, String message) {
        WebSocketServer webSocketServer = webSocketSet.get(userId);
        if (webSocketServer != null && webSocketServer.WebSocketsession.isOpen()) {
            sendMessage(webSocketServer.WebSocketsession, message);
        } else {
            logger.warn("当前用户不在线：{}", userId);
        }
    }

    /**
     * 指定区域发送消息
     *
     * @param regionId 区域id
     * @param message  消息
     */
    public void sendRegion(String regionId, String message) {
        for (String key : webSocketSet.keySet()) {
            if (key.contains(regionId + "-")) {
                Session session = webSocketSet.get(key).WebSocketsession;
                if (session != null && session.isOpen() && !userId.equals(key)) {
                    sendMessage(session, message);
                }
            }
        }
    }

    public static void main(String[] args) {
        String value = "abc";
        System.out.println(value.contains("b"));
    }

}
