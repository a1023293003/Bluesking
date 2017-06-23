package cn.bluesking.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 切面代理
 * 
 * @author 随心
 *
 */
public abstract class AspectProxy implements Proxy {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(AspectProxy.class);
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result = null;
		Class<?> cls = proxyChain.getTargetClass();
		Method method = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();
		
		begin();
		try {
			if(intercept(cls, method, params)) {
				before(cls, method, params);
				result = proxyChain.doProxyChain();
				after(cls, method, params);
			} else {
				result = proxyChain.doProxyChain();
			}
		} catch (Exception e) {
			_LOG.error("代理失败！", e);
			error(cls, method, params, e);
			throw e;
		} finally {
			end();
		}
		return result;
	}
	
	/**
	 * before之前
	 */
	public void begin() {
	}
	
	/**
	 * 判断方法是否进行前置后置增强
	 * @param cls
	 * @param method
	 * @param params
	 * @return
	 * @throws Throwable
	 */
	public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
		return true;
	}
	
	/**
	 * 前置增强
	 * @param cls
	 * @param method
	 * @param params
	 * @throws Throwable
	 */
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
	}

	/**
	 * 后置增强
	 * @param cls
	 * @param method
	 * @param params
	 * @throws Throwable
	 */
	public void after(Class<?> cls, Method method, Object[] params) throws Throwable {
	}
	
	/**
	 * 抛出增强
	 * @param cls
	 * @param method
	 * @param params
	 * @param e
	 */
	public void error(Class<?> cls, Method method, Object[] params, Throwable e) {
	}
	
	/**
	 * 返回之前
	 */
	public void end() {
	}
}
