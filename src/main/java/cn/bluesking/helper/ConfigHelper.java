package cn.bluesking.helper;

import cn.bluesking.ConfigConstant;
import cn.bluesking.util.ConfigParser;
import cn.bluesking.util.PropsUtil;

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
	
}
