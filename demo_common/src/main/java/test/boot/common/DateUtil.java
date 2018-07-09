/**
 * 
 */
package test.boot.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author Administrator
 *
 */
public class DateUtil {

	public static void main(String[] args) {
		// System.err.println(date2ChineseYear(new Date()));
		// System.err.println(LocalDateTime.now());
		// System.err.println(LocalDate.now());
		// System.err.println(LocalTime.now());
		// System.err.println(getFutureTime(1 * 60 * 60 * 1000));
		// System.err.println(getFutureTime2(1 * 60 * 60 * 1000));

	}

	/**
	 * 获取当前所在天是本月的第几天
	 * 
	 * @return
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 获取第几周(所在年的第几周)
	 * 
	 * @return
	 */
	public static int getWeek() {
		Calendar cal = Calendar.getInstance();
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** 获取当前年月日时间 */
	public static String date2ChineseYear(Date date) {
		String dateStr = sdf.format(date);

		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(5, 7);
		String day = dateStr.substring(8, 10);
		String time = dateStr.substring(10);

		return year + "年" + month + "月" + day + "日" + time;
	}

	/** 获取当前时间字符串,格式:yyyyMMddHHmmss */
	public static String getChineseDate() {
		return sdf2.format(new Date());
	}

	/**
	 * 获取Millisecond后的未来时间字符串
	 * 
	 * @param Millisecond
	 *            毫秒值
	 * @return
	 */
	public static String getFutureTime(long Millisecond) {
		long timeLose = System.currentTimeMillis() + Millisecond;
		return sdf2.format(new Date(timeLose));
	}

	/**
	 * 获取Millisecond后的未来时间
	 * 
	 * @param Millisecond
	 *            毫秒值
	 * 
	 * @return
	 */
	public static Date getFutureTime2(long Millisecond) {
		long timeLose = System.currentTimeMillis() + Millisecond;
		return new Date(timeLose);
	}

}
