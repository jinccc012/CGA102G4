package com.websocket.model;

import javax.websocket.Session;

public class Role {
	
	public String id;
	public Session session;
	public String role;//member visit
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public Session getSession() {
//		return session;
//	}
//	public void setSession(Session session) {
//		this.session = session;
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", session=" + session + ", role=" + role + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj )
			return true;
		if (obj != null && getClass() == obj.getClass()) {

			if (obj instanceof Role) {   //可以不用寫，因為class 已經確認 避免轉型失敗
				Role r = (Role) obj;
				// 選擇該類別的必要成員變數(實體變數)來加以判斷是否有相等(相同)
				if (id.equals(r.id)) {
					return true;
				}
			}
	}
		return false;
}
	
	
}

