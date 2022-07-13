package com.websocket.controller;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import utils.JedisUtil;

public class JedisHandleMessgae {
	private static JedisPool pool = JedisUtil.getJedisPool();
		/*
		 *拿出來就刪除 
		 *
		 * */
	public static List<String> getHistoryMsg(String receiverid) {
		Jedis jedis = pool.getResource();
		List<String> historymessage = jedis.lrange(receiverid, 0, -1);
		//清空數據
		jedis.ltrim(receiverid, 1, 0);
		return historymessage;
	}
	
	public static void saveMessage(String receiverid,String message) {
		Jedis jedis = pool.getResource();
		jedis.rpush(receiverid, message);
		jedis.close();
	
	}
	
//	public static void main(String []args) {
//		JedisHandleMessgae test = new JedisHandleMessgae();
//		test.saveMessage("B123456789", "你出價過的商品目前最高價為1");
//		test.saveMessage("B123456789", "你出價過的商品目前最高價為2");
//		test.saveMessage("B123456789", "你出價過的商品目前最高價為3");
//		test.saveMessage("B123456789", "你出價過的商品目前最高價為4");
//
//	}
//	
	

}
