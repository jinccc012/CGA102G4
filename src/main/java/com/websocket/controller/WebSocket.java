package com.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;
import com.google.gson.Gson;
import com.websocket.model.Role;
import com.websocket.model.empRole;
import com.websocket.model.memberRole;
import com.websocket.model.visitorRole;

@ServerEndpoint("/websocket/{param}")
public class WebSocket {
	// 在線上的成員
	private static Map<String, Set<Role>> group = new HashMap<String, Set<Role>>();
	Gson gson = new Gson();

	@OnOpen
	public void Onopen(@PathParam("param") String param, Session userSession) throws IOException {
		System.out.println("連線成功!");
		String id = param.substring(param.indexOf('.') + 1, param.length());
		if ("visitor.".equals(param)) {
			visitorRole visitor = new visitorRole(userSession);
			String a = "快加入FamilyRent吧!";
			String messsage = gson.toJson(a);
			visitor.session.getAsyncRemote().sendText(messsage);
			group.computeIfAbsent("visitors", k -> new HashSet<Role>()).add(visitor);
		} else if (param.startsWith("member.")) {
			memberRole member = new memberRole(id, userSession);
			List<String> hisotrymesage = JedisHandleMessgae.getHistoryMsg(id);
			String historyMsg = gson.toJson(hisotrymesage);
			member.session.getAsyncRemote().sendText(historyMsg);
			group.computeIfAbsent("members", k -> new HashSet<Role>()).add(member);

		} else if (param.startsWith("emp.")) {
			empRole emp = new empRole(id, userSession);
			group.computeIfAbsent("emps", k -> new HashSet<Role>()).add(emp);
		}

//			System.out.println("member" + group.get("members").size());
//			System.out.println("visitors" + group.get("visitors").size());
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {

		//userSession.getAsyncRemote().sendText(message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {

		for (Map.Entry<String, Set<Role>> entry : group.entrySet()) {
			Set<Role> value = entry.getValue();
			for (Role v : value) {
				if (v.session == userSession) {
					System.out.println(v.id + "以下線!!!");
					value.remove(v);
				}
			}
		}
	}

	// push message to group in online
	public void sentMessageToGroups(List groups, String message) {
		for (Map.Entry<String, Set<Role>> entry : group.entrySet()) {
			String group = entry.getKey();
			if (groups.contains(group)) {
				Set<Role> value = entry.getValue();
				for (Role v : value) {
					String json = gson.toJson(message);
					v.session.getAsyncRemote().sendText(json);
				}
			}
		}

	}

	// push message to partical person ID
	public void sentMessageToPerson(List<String> person, String message) {
		for (Map.Entry<String, Set<Role>> entry : group.entrySet()) {
			Set<Role> value = entry.getValue();
			for (Role v : value) {
				System.out.println(v.id);
				// 如果List
				if (person.contains(v.id)) {
					System.out.println("準備發送消息");
					String json = gson.toJson(message);
					v.session.getAsyncRemote().sendText(json);
					person.remove(v.id);
				}
			}
		}
		if (person.size() != 0) {
			System.out.println("離線先儲存訊息");
			person.forEach(n -> JedisHandleMessgae.saveMessage(n, message));
		}
	}

	// pub message to particle partical employee group exclude
	public void sentMessageToEMPST(String st_no,String message) {
		 EmployeeService empservice =new EmployeeService();
		 List<EmployeeVO> allByST = empservice.getAllByST(st_no);
			for (Map.Entry<String, Set<Role>> entry : group.entrySet()) {
					Set<Role> value = entry.getValue();
					for (Role v : value) {
						EmployeeVO empByPk = empservice.empByPk(v.id);
						if(allByST.contains(empByPk)) {
							System.out.println(empByPk.getEmp_name()+"針對發送消錫分店位置為"+empByPk.getSt_no());
						}							
						//離線先儲存訊息						
					}
				}
	}
	
}
