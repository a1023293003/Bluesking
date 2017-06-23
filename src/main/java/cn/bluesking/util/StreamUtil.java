package cn.bluesking.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流操作工具类
 * 
 * @author 随心
 *
 */
public final class StreamUtil {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(StreamUtil.class);
	
	/**
	 * 从输入流中获取字符串
	 * @param is [InputStream]输入流
	 * @return
	 */
	public static String getString(InputStream is) {
		StringBuilder sBuilder = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine()) != null) {
				sBuilder.append(line);
			}
			reader.close();
		} catch (Exception e) {
			_LOG.error("获取字符串出错！", e);
			throw new RuntimeException(e);
		}
		return sBuilder.toString();
	}

}
