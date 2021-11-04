package com.kh.elefont.websocket;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ChatClientsServlet
 */
@WebServlet("/chat/clients")
public class ChatClientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 업무로직
		Set<String> clients = ElefontWebsocket.clients.keySet();
		// 2. 응답처리
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(clients,response.getWriter());
	}

}
