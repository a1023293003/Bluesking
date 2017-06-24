package cn.bluesking.proxy;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理管理器
 * 
 * @author 随心
 *
 */
public class ProxyManager {

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(final Class<?> targetClass, final List<Proxy> proxyList) {
		return (T) Enhancer.create(targetClass, new MethodInterceptor() {
			@Override
			public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, 
					MethodProxy methodProxy) throws Throwable {
				// 拦截方法，然后调用代理链的执行方法
				return new ProxyChain(targetClass, targetObject, targetMethod, 
						methodProxy, methodParams, proxyList).doProxyChain();
			}
		});
	}
}
