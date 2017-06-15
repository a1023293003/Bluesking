package cn.bluesking.util;

/**
 * 字符串工具类
 * @author 随心
 *
 */
public final class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str != null) {
			str = str.trim();
		}
		return str == null || str.length() <= 0;
	}
	
	/**
	 * 判断字符串是否非空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
}
