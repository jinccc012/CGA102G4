package controller;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websockets/{name}")
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//    private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("name") String userName,Session session){
    	sessionsMap.put(userName, session);
        this.session = session;
//        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("新連結加入!目前人數為" + getOnlineCount());
        System.out.println("新連結加入! 名子:" + userName);
        
//        try {
////        	Set<String> keySet = sessionsMap.keySet();
////        	List<String> list = keySet.stream().filter(e-> !e.equals(userName) ).collect(Collectors.toList());
////        	list.forEach(e-> {
////				try {
////					sendMessage(e, userName+"連線");
////				} catch (IOException e1) {
////					// TODO Auto-generated catch block
////					e1.printStackTrace();
////				}
////			});
//        	
//			sendMessage(userName, userName + "你好! " + "連線成功");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("name") String userName){
//        webSocketSet.remove(this);  //从set中删除
        sessionsMap.remove(userName);
        sessionsMap.keySet().forEach(e->System.out.println(e));
        subOnlineCount();           //在线数减1
        System.out.println("有一連線關閉！目前人數為" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("連自客戶端訊息:" + message);
        //群发消息
        Collection<Session> values = sessionsMap.values();
        for(Session item: values){
            try {
            	item.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("發生錯誤");
        error.printStackTrace();
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public boolean sendMessage(String name,String message) throws IOException{
    	
    	Session session2 = sessionsMap.get(name);
    	if(session2 == null) {
    		System.out.println("用戶不存在");
    		return false;
    	}else {
    		if(session2.isOpen()) {
    			session2.getBasicRemote().sendText(message);
    			return true;
    		}else {
    			return false;
    		}
    	}
        //this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}
