package cn.bluesking;

/**
 * 提供相关配置项常量
 * 
 * @author 随心
 *
 */
public interface ConfigConstant {

	/**
	 * 配置文件项目中的相对位置
	 */
	public final static String CONFIG_FILE = "bluesking.properties";
	
	/**
	 * JDBC连接驱动启动类全路径
	 */
	public final static String JDBC_DRIVER = "bluesking.framework.jdbc.driver";
	
	/**
	 * 数据库连接url
	 */
	public final static String JDBC_URL = "bluesking.framework.jdbc.url";

	/**
	 * 数据库用户名
	 */
	public final static String JDBC_USERNAME = "bluesking.framework.jdbc.username";
	
	/**
	 * 数据库密码
	 */
	public final static String JDBC_PASSWORD = "bluesking.framework.jdbc.password";
	
	/**
	 * 项目基础包名
	 */
	public final static String APP_BASE_PACKAGE = "bluesking.framework.app.base_package";

	/**
	 * 应用JSP文件路径
	 */
	public final static String APP_JSP_PATH = "bluesking.framework.app.jsp_path";
	
	/**
	 * 应用静态资源路径
	 */
	public final static String APP_ASSET_PATH = "bluesking.framework.app.asset_path";
	
	/**
	 * 上传文件的最大大小限制
	 */
	public final static String APP_UPLOAD_LIMIT = "bluesking.framework.app.upload_limit";
	
}
