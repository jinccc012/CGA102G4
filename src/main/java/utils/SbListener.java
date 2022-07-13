package utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.scar.model.ScarService;
import com.scar.model.ScarVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/******************************************************************
 * Application Lifecycle Listener implementation class SbListener *
 ******************************************************************/
@WebListener
public class SbListener implements ServletContextListener {
	Timer timer;

	public void contextInitialized(ServletContextEvent sce) {
		new Thread(new Runnable() {
			public void run() {
				JedisPool pool = JedisUtil.getJedisPool();
				Jedis jedis = pool.getResource();// 同時開啟監聽註冊
				// 如果未開啟 幫忙開啟
				String parameter = "notify-keyspace-events";
				List<String> notify = jedis.configGet(parameter);
				if (notify.get(1).equals("")) {
					jedis.configSet(parameter, "Ex");
				}
				jedis.psubscribe(new Comsumer(), "__keyevent@*__:expired");
				jedis.close();
			}
		}).start();
		System.out.println("SbListener--" + "過期頻道已註冊");
		timer = new Timer();
		System.out.println("SbListener--" + "監聽器已啟動");
		Calendar starttime = new GregorianCalendar(2022, Calendar.JULY, 13, 9, 0, 0);
		timer.scheduleAtFixedRate(new SbidTask(), starttime.getTime(), 1 * 60 * 1000); // 判斷上架時間
		System.out.println("SbListener--" + "已經新增任務");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
		System.out.println("監聽器已銷毀");
	}
}

/**********
 * 任務一 *
 **********/
class SbidTask extends TimerTask {

	private List<ScarVO> allScar;
	private ScarService scarservice = new ScarService();

	public void run() {
		Timestamp now = new Timestamp(scheduledExecutionTime());
		System.out.println("SbListener--" + "任務開始: " + now);
		Date date = new Date(this.scheduledExecutionTime());
		// 下次執行時間
		System.out.println("SbListener--" + "下次執行: " + date.getTime());
		allScar = scarservice.readyToLunched();// Serach read to lunch(0) scar

		for (ScarVO scarvo : allScar) {
			System.out.println("SbListener--" + scarvo.getScar_no() + "未上架，車輛狀態為:" + scarvo.getScar_status());
			Timestamp startdate = scarvo.getScar_startime();
			Timestamp enddate = scarvo.getScar_endtime();
			String scar_no = scarvo.getScar_no();
			// 實行上下架時間判斷
			if (now.equals(startdate) || (now.after(startdate) && now.before(enddate))) {
				scarservice.launched(scar_no);
			}
			if (now.equals(enddate) || now.after(enddate)) {
				scarservice.discontinued(scar_no);
			}
		}
		
		// 每60秒執行一次新增
		System.out.println(scheduledExecutionTime());
		if ((scheduledExecutionTime() % (1*60*1000)) == 0) {
			System.out.println("SbListener--" + "redis新增中古車執行");
			scarservice.saveScarInRedis();
		}
		
	}
}
