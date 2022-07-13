package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//存放每輛車所有的訂單日期
public class ReDay {
	private String car_no;// 存放車牌
	private List<List<Integer>> allday;// 存放日期
	//private List<List<Integer>> nextMonthday; //存放下個月日期
	private String car_model;
	private List<Integer> clean;
	private String store;
	private List<String> start_store;
	private List<String> end_store;
	private Integer status;

	public String getCar_no() {
		return car_no;
	}

	public void setCar_no(String car_no) {
		this.car_no = car_no;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public List<List<Integer>> getAllday() {
		return allday;
	}

	public void setAllday(List<List<Integer>> allday) {
		this.allday = allday;
	}

	public ReDay() {
		allday = new ArrayList<>();
		clean = new ArrayList<>();
		start_store = new ArrayList<>();
		end_store = new ArrayList<>();
		//nextMonthday = new ArrayList<>(); 
	}

	public void day(int start, int end, int sumMonth, int month, String startstore, String endstore,String rcaro_returnloc_actual,int rrend) {
		List<Integer> day = new ArrayList<>();
		int multiplyMonth = month * 2;
//		if (store.equals(startstore)) {
			if (multiplyMonth == sumMonth) {// 要是 訂單開始與結束都在同月份
				
				if(rrend != 0 && end < rrend) { //要是 實際還車時間 > 預計還車時間
					while (start <= rrend) {
						day.add(start);
						start++;
					}
				}else {
					while (start <= end) {
						day.add(start);
						start++;
					}
				}
				
//				while (start <= end) {
//					day.add(start);
//					start++;
//				}
				clean.add(end + 1);
				
				if(rcaro_returnloc_actual != null && !endstore.equals(rcaro_returnloc_actual)) { // 判斷 實際還車
					end_store.add(rcaro_returnloc_actual);
				}else {
					end_store.add(endstore);
				}
				//end_store.add(endstore);
				start_store.add(startstore);
			} else if (multiplyMonth - 1 == sumMonth) { // 要是 訂單開始在當月前一個月份
				
				if(rrend != 0 && end < rrend) { //要是 實際還車時間 > 預計還車時間
					for (int i = 1; i <= rrend; i++) {
						day.add(i);
					}
				}else {
					for (int i = 1; i <= end; i++) {
						day.add(i);
					}
				}
				
//				for (int i = 1; i <= end; i++) {
//					day.add(i);
//				}
				clean.add(end + 1);
				if(rcaro_returnloc_actual != null && !endstore.equals(rcaro_returnloc_actual)) {
					end_store.add(rcaro_returnloc_actual);
				}else {
					end_store.add(endstore);
				}
				//end_store.add(endstore);
				start_store.add(startstore);
			} else if (multiplyMonth + 1 == sumMonth) { // 要是 訂單結束在下一個月份
				int date = monthinDay(month);
				while (start <= date) {
					day.add(start);
					start++;
				}
				//end_store.add(endstore);
				start_store.add(startstore);
			} else if ((sumMonth == multiplyMonth - 2) && (end == monthinDay(month - 1))) { // 訂單開始與結束 都在上個月 且結束日為上個月底
				clean.add(1);
				if(rcaro_returnloc_actual != null && !endstore.equals(rcaro_returnloc_actual)) {
					end_store.add(rcaro_returnloc_actual);
				}else {
					end_store.add(endstore);
				}
				//end_store.add(endstore);
				start_store.add(startstore);
			}
			
			if(day.size() > 0 ) { //將空日期的擋掉
				allday.add(day);
			}
			
//		}

	}
	
	public void otherStoreCar(int start, int end, int endmonth, int month, String startstore, String endstore) {
		List<Integer> day = new ArrayList<>();
		//List<Integer> nextday = new ArrayList<>();
		
//		if (store.equals(startstore)) {
			if (endmonth == month) {// 要是 訂單結束在同月份
				
//				if(start > end) { //訂單開始時間在上個月
//					for (int i = 1; i <= end; i++) {
//						day.add(i);
//					}
//					clean.add(end + 1);
//					end_store.add(endstore);
//					start_store.add(startstore);
//				}else {
					while (start <= end) {
						day.add(start);
						start++;
					}
					clean.add(end + 1);
					end_store.add(endstore);
					start_store.add(startstore);
//				}

			} else if (endmonth > month) { //訂單結束在下一個月份
				int date = monthinDay(month);
				while (start <= date) {
					day.add(start);
					start++;
				}
				for (int i = 1; i <= end; i++) {
					//nextday.add(i);
					day.add(i);
				}
				end_store.add(endstore);
				start_store.add(startstore);
				clean.add(end + 1);
			} 
			
			if(day.size() > 0 ) { //將空日期的擋掉
				allday.add(day);
				//nextMonthday.add(nextday);
			}
			
//		}

	}

	public List<Integer> getClean() {
		return clean;
	}

	public void setClean(List<Integer> clean) {
		this.clean = clean;
	}

	public int monthinDay(int month) { // 取得指定月份 總天數
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		int maximum = calendar.get(Calendar.DATE);
//		System.out.println(maximum);
		return maximum;
	}

	public List<String> getStart_store() {
		return start_store;
	}

	public void setStart_store(List<String> start_store) {
		this.start_store = start_store;
	}

	public List<String> getEnd_store() {
		return end_store;
	}

	public void setEnd_store(List<String> end_store) {
		this.end_store = end_store;
	}

//	public List<List<Integer>> getNextMonthday() {
//		return nextMonthday;
//	}
//
//	public void setNextMonthday(List<List<Integer>> nextMonthday) {
//		this.nextMonthday = nextMonthday;
//	}

	@Override
	public String toString() {
		return "ReDay [car_no=" + car_no + ", allday=" + allday + ", car_model=" + car_model + ", clean=" + clean
				+ ", store=" + store + ", start_store=" + start_store + ", end_store=" + end_store + ", status="
				+ status + "]";
	}



}
