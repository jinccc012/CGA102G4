package com.websocket.model;

import java.util.UUID;

import javax.websocket.Session;

public class visitorRole extends Role {

	public String getId() {
		return id;
	}

	public visitorRole(Session session) {
		id = "1";
		super.session =session;
		role = "vivstor";
	
	}


	@Override
	public String toString() {
		return "visitorRole [id=" + id + ", session=" + session + ", role=" + role + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj )
			return true;
		if (obj != null && getClass() == obj.getClass()) {

			if (obj instanceof visitorRole) {   //可以不用寫，因為class 已經確認 避免轉型失敗
				visitorRole v = (visitorRole) obj;
				// 選擇該類別的必要成員變數(實體變數)來加以判斷是否有相等(相同)
				if (id.equals(v.id)) {
					return true;
				}
			}
	}
		return false;
}
}
