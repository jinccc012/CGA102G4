package com.websocket.model;

import javax.websocket.Session;

public class memberRole extends Role{

	public memberRole(String meb_no,Session session) {
		id = meb_no;
		super.session =session;
		role = "member";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "memberRole [id=" + id + ", session=" + session + ", role=" + role + "]";
	}
	
	
}
