package test.boot.common;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public class AppUtil {
	public static void main(String[] args) {
		System.err.println(createNnoncestr());
		System.err.println(createTimestamp());
	}

	/** 雪花算法 */
	private static final IdGen idGen = IdGen.get();

	/**
	 * 参数Md5加密
	 * 
	 * @param inStr
	 * @return
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		byte[] md5Bytes = md5.digest(inStr.getBytes());

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	/** 生产随机accessToken */
	public static String buildAccessToken() {
		return string2MD5(UUID.randomUUID().toString() + LocalDateTime.now()).toLowerCase();
	}

	/** 生产随机secret */
	public static String buildSecret() {
		return string2MD5(UUID.randomUUID().toString() + LocalDateTime.now()).toLowerCase();
	}

	/** 生产全局唯一id */
	public static long getId() {
		return idGen.nextId();
	}

	/** 生产随机noncestr */
	public static String createNnoncestr() {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 16; ++i) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}

		return sb.toString();
	}

	/** 生产时间戳 */
	public static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}
