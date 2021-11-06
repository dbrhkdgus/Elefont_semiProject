package com.kh.elefont.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.kh.elefont.member.model.vo.Member;

public class ElefontWebsocketConfig extends Configurator{

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// HttpSession의 속성 userId 가져오기
		HttpSession session = (HttpSession) request.getHttpSession();
		String memberNo = (String)session.getAttribute("memberNo");
		
		
		// config의 userProperties맵에 저장
		Map<String, Object> userProp = sec.getUserProperties();
		userProp.put("memberNo", memberNo);
		
		
	}

}
