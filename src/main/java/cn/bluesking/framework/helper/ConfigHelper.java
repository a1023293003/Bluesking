package cn.bluesking.framework.helper;

import cn.bluesking.framework.ConfigConstant;
import cn.bluesking.framework.util.CaseUtil;
import cn.bluesking.framework.util.ConfigParser;
import cn.bluesking.framework.util.PropsUtil;
import cn.bluesking.framework.util.StringUtil;

/**
 * 属性文件助手类
 * 
 * @author 随心
 *
 */
public final class ConfigHelper {
	
	/**
	 * 配置文件
	 */
	private static final ConfigParser CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE); 
	
	/**
	 * 获取jdbc驱动
	 * @return
	 */
	public static String getJdbcDriver() {
		return CONFIG_PROPS.getValue(ConfigConstant.JDBC_DRIVER);
	}
	
	/**
	 * 获取数据库连接url
	 * @return
	 */
	public static String getJdbcUrl() {
		return CONFIG_PROPS.getValue(ConfigConstant.JDBC_URL);
	}
	
	/**
	 * 获取数据库用户名
	 * @return
	 */
	public static String getJdbcUserName() {
		return CONFIG_PROPS.getValue(ConfigConstant.JDBC_USERNAME);
	}
	
	/**
	 * 获取数据库密码
	 * @return
	 */
	public static String getJdbcPassword() {
		return CONFIG_PROPS.getValue(ConfigConstant.JDBC_PASSWORD);
	}
	
	/**
	 * 获取应用基础包名
	 * 
	 * @return
	 */
	public static String getAppBasePackage() {
		return CONFIG_PROPS.getValue(ConfigConstant.APP_BASE_PACKAGE);
	}
	
	/**
	 * 获取应用JSP路径
	 * 
	 * @return
	 */
	public static String getAppJspPath() {
		return CONFIG_PROPS.getValue(ConfigConstant.APP_JSP_PATH);
	}
	
	/**
	 * 获取应用静态资源路径
	 * 
	 * @return
	 */
	public static String getAppAssetPath() {
		return CONFIG_PROPS.getValue(ConfigConstant.APP_ASSET_PATH);
	}
	
	/**
	 * 获取应用上传文件最大限制,默认10MB
	 * 
	 * @return
	 */
	public static int getAppUploadLimit() {
		return CaseUtil.caseInt(CONFIG_PROPS.getValue(ConfigConstant.APP_UPLOAD_LIMIT), 10);
	}
	
	/**
	 * 获取配置文件中指定参数值
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return CaseUtil.caseString(CONFIG_PROPS.getValue(key));
	}
	
	/**
	 * 获取配置文件中指定参数值
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		return CaseUtil.caseBoolean(CONFIG_PROPS.getValue(key));
	}
	
	/**
	 * 获取配置文件中指定参数值
	 * @param key
	 * @return
	 */
	public static double getDouble(String key) {
		return CaseUtil.caseDouble(CONFIG_PROPS.getValue(key));
	}
	
	/**
	 * 获取配置文件中指定参数值
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return CaseUtil.caseInt(CONFIG_PROPS.getValue(key));
	}
	
	/**
	 * 获取配置文件中指定参数值
	 * @param key
	 * @return
	 */
	public static long getLong(String key) {
		return CaseUtil.caseLong(CONFIG_PROPS.getValue(key));
	}
}
