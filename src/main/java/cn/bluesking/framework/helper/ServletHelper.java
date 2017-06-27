package cn.bluesking.framework.helper;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet助手类
 * 
 * @author 随心
 *
 */
public final class ServletHelper {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(ServletHelper.class);

	/**
	 * 使每个线程独自拥有一个ServletHelper实例
	 */
	private static final ThreadLocal<ServletHelper> SERVLET_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * 私有构造,单例化ServletHelper
	 * @param request
	 * @param response
	 */
	private ServletHelper(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	/**
	 * 初始化
	 * @param request
	 * @param response
	 */
	public static void init(HttpServletRequest request, HttpServletResponse response) {
		SERVLET_HOLDER.set(new ServletHelper(request, response));
	}
	
	/**
	 * 销毁
	 */
	public static void destroy() {
		SERVLET_HOLDER.remove();
	}
	
	/**
	 * 获取Request对象
	 * @return
	 */
	private static HttpServletRequest getRequest() {
		return SERVLET_HOLDER.get().request;
	}
	
	/**
	 * 获取Response对象
	 * @return
	 */
	private static HttpServletResponse getResponse() {
		return SERVLET_HOLDER.get().response;
	}
	
	/**
	 * 获取Session对象
	 * @return
	 */
	private static HttpSession getSession() {
		return getRequest().getSession();
	}
	
	/**
	 * 获取ServletContext对象
	 * @return
	 */
	private static ServletContext getServletContext() {
		return getRequest().getServletContext();
	}
	
	/**
	 * 将属性放入Request中
	 * @param key
	 * @param value
	 */
	public static void setRequestAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
	
	/**
	 * 从Request中获取属性
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getRequestAttribute(String key){
		return (T) getRequest().getAttribute(key);
	}
	
	/**
	 * 从Request中移除属性
	 * @param key
	 */
	public static void removeRequestAttribute(String key) {
		getRequest().removeAttribute(key);
	}
	
	/**
	 * 发送重定向响应
	 * @param location
	 */
	public static void sendRedirect(String location) {
		try {
			getResponse().sendRedirect(getRequest().getContextPath() + location);
		} catch (Exception e) {
			_LOG.error("重定向失败！", e);
		}
	}
	
	/**
	 * 发送转发响应
	 * @param location
	 */
	public static void forward(String location) {
		try {
			getRequest().getRequestDispatcher(location).forward(getRequest(), getResponse());
		} catch (Exception e) {
			_LOG.error("跳转失败！", e);
		}
	}
	
	/**
	 * 将属性放入Request中
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(String key, Object value) {
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 从Request中获取属性
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(String key){
		return (T) getSession().getAttribute(key);
	}
	
	/**
	 * 从Request中移除属性
	 * @param key
	 */
	public static void removeSessionAttribute(String key) {
		getSession().removeAttribute(key);
	}
	
	/**
	 * 打印内容到当前页面
	 * @param str
	 */
	public static void printToResponse(String str) {
		try {
			getResponse().getWriter().println(str);
		} catch (IOException e) {
			_LOG.error("输出内容到浏览器失败！", e);
		}
	}
	
}
