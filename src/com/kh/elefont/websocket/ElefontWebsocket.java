package com.kh.elefont.websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;


@ServerEndpoint(value="/elefontWebsocket", configurator=ElefontWebsocketConfig.class)
public class ElefontWebsocket {
	
	/**
	 * 접속한 client를 userId로 관리
	 */
	public static Map<String, Session> clients = new HashMap<>();
	
	public static void logClients() {
		System.out.printf("현재 접속자 수 (%d) : %s%n",clients.size(),clients.keySet());
	}
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) throws IOException {
		// 사용자 userId - session을 clients map에 저장
		Map<String, Object> userProp = config.getUserProperties();
		String memberNo = (String) userProp.get("memberNo");
		
		clients.put(memberNo, session);
		
		
		// userId를 session의 userProperties 맵 객체에 저장(onClose에서 사용)
		Map<String, Object> sessionUserProp = session.getUserProperties();
		sessionUserProp.put("memberNo", memberNo);
		
		
		// 접속 알림
		String jsonMsg = msgToJson("welcome","("+memberNo+")","님이 입장했습니다.");
		onMessage(jsonMsg, session);
		
		logClients();
	}

	private String msgToJson(String type, String userId, String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("sender", userId);
		map.put("msg", msg);
		map.put("time", System.currentTimeMillis());
		
		String jsonMsg = new Gson().toJson(map);
		return jsonMsg;
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException {
		Map<String, Object> map = new Gson().fromJson(msg, HashMap.class);
		
		if("que".equals(map.get("type"))) {
			Session receiverSess = clients.get(map.get("receiver"));
			receiverSess.getBasicRemote().sendText(msg);
		}else {
			
			// 메세지 전송 중에 clients가 변경되어서는 안되므로 동기화블럭으로 접근을 제한함
			synchronized(clients) {
			
				
				Collection<Session> sessions = clients.values();
			
				for(Session sess : sessions) {
					// Basic인터페이스 구현체 - 커넥션 동기화보장, 읽기/쓰기 업무 처리
					Basic basic = sess.getBasicRemote();
					basic.sendText(msg);
				}
			}
		}
	}
	
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) throws IOException {
		// clients에서 사용자 제거
		Map<String, Object> userProp = session.getUserProperties();
		String memberNo = (String)userProp.get("memberNo");
		
		clients.remove(memberNo);
		
		// 다른 사용ㄹ자에게 알림
		String msg = msgToJson("goodbte",memberNo,"님이 나갔습니다.");
		onMessage(msg,session);
	}

}
