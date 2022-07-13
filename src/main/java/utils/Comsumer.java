package utils;

import com.scar.model.ScarService;
import com.successful_bid.model.Successful_BidService;
import com.successful_bid.model.Successful_BidVO;

import redis.clients.jedis.JedisPubSub;

public class Comsumer extends JedisPubSub {
	Successful_BidService sbservice = new Successful_BidService();
	ScarService scarservice =new ScarService();
	//JSP 執行順序   el    js  html 
	@Override
	public void onPMessage(String pattern, String channel, String message) {
		// TODO Auto-generated method stub
		// super.onPMessage(pattern, channel, message);
		
		System.out.println(message.toString());
		if(message.startsWith("sborder")) {
			String[] split = message.split(":");
			Integer sb_no = Integer.valueOf(split[1]);
			Successful_BidVO vo = sbservice.getOneSbNo(sb_no);
			//check 
			if (vo.getSb_non_paying() == 0) {
				scarservice.updateStatus(3, vo.getScar_no());
				System.out.println("流標");
				//早第二位
			} else {
				// 中古車改已賣出
				scarservice.updateStatus(4, vo.getScar_no());
				System.out.println("已賣出");
			}
			
		}
		System.out.println("已結束");
		
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// TODO Auto-generated method stub
		System.out.println(pattern);
		System.out.println(subscribedChannels);
	
	}

}
