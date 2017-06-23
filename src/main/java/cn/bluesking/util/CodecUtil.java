package cn.bluesking.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编码与解码操作工具类
 * 
 * @author 随心
 *
 */
public final class CodecUtil {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(CodecUtil.class);
	
	/**
	 * 将URL编码
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source) {
		String target;
		try {
			target = URLEncoder.encode(source, "UTF-8");
		} catch (Exception e) {
			_LOG.error("url编码失败！", e);
			throw new RuntimeException(e);
		}
		return target;
	}
	
	/**
	 * 将URL解码
	 * @param source
	 * @return
	 */
	public static String decodeURL(String source) {
		String target;
		try {
			target = URLDecoder.decode(source, "UTF-8");
		} catch (Exception e) {
			_LOG.error("url解码失败！", e);
			throw new RuntimeException(e);
		}
		return target;
	}

}
