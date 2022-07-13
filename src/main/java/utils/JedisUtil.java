package utils;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
	// Singleton單例模式
	private static JedisPool pool = null;

	private JedisUtil() {
	}

	public static JedisPool getJedisPool() {
		// double lock
		if (pool == null) {//如果剛好兩個請求
			synchronized(JedisUtil.class) {
				if (pool == null) {//如果遲空的 //如果第二個竟然 就不要在初始化池子
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxTotal(50);//可以設大一點沒關西
					config.setMaxIdle(10);
					config.setMaxWaitMillis(10000);
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}

}
