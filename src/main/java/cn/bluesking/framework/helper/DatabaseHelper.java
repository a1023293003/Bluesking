package cn.bluesking.framework.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库操作助手类
 * 
 * @author 随心
 *
 */
public final class DatabaseHelper {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(DatabaseHelper.class);
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<Connection>();
	
	static {
		DRIVER = ConfigHelper.getJdbcDriver();
		URL = ConfigHelper.getJdbcUrl();
		USERNAME = ConfigHelper.getJdbcUserName();
		PASSWORD = ConfigHelper.getJdbcPassword();
		
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			_LOG.error("无法加载jdbc驱动！", e);
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception e) {
				_LOG.error("获取数据库连接失败！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 */
	public static void closeConnection() {
		Connection conn = CONNECTION_HOLDER.get();
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				_LOG.error("关闭数据库连接失败！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	/**
	 * 开启事务
	 */
	public static void beginTransaction() {
		Connection conn = getConnection();
		if(conn != null) {
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				_LOG.error("开启事务失败！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection conn = getConnection();
		if(conn != null) {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				_LOG.error("提交事务失败", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		Connection conn = getConnection();
		if(conn != null) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e) {
				_LOG.error("回滚事务失败！", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}
	}
	
	// TODO 
	public static DataSource getDataSource() {
		return null;
	}
	
	public static String query(String sql, Object... params) {
		return null;
	}
	
	public static Set<String> querySet(String sql, Object... params) {
		return null;
	}
}
