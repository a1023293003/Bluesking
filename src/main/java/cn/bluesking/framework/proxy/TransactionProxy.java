package cn.bluesking.framework.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bluesking.framework.annotation.Transaction;
import cn.bluesking.framework.helper.DatabaseHelper;

/**
 * 事务代理
 * 
 * @author 随心
 *
 */
public class TransactionProxy implements Proxy {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(TransactionProxy.class);

	/**
	 * 标记，保证每个线程事务控制相关逻辑只执行一次
	 */
	private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return false;
		};
	};
	
	@Override
	public Object doProxy(ProxyChain proxyChain) throws Throwable {
		Object result;
		boolean flag = FLAG_HOLDER.get();
		Method method = proxyChain.getTargetMethod();
		if(!flag && method.isAnnotationPresent(Transaction.class)) {
			FLAG_HOLDER.set(true);
			try {
				DatabaseHelper.beginTransaction();
				_LOG.debug("开启事务...");
				result = proxyChain.doProxyChain();
				DatabaseHelper.commitTransaction();
				_LOG.debug("提交事务...");
			} catch (Exception e) {
				DatabaseHelper.rollbackTransaction();
				_LOG.debug("回滚事务...", e);
				throw e;
			} finally {
				FLAG_HOLDER.remove();
			}
		} else {
			result = proxyChain.doProxyChain();
		}
		return result;
	}

}
