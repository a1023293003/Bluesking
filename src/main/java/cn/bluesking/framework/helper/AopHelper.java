package cn.bluesking.framework.helper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bluesking.framework.annotation.Aspect;
import cn.bluesking.framework.annotation.Service;
import cn.bluesking.framework.annotation.Transaction;
import cn.bluesking.framework.proxy.Proxy;
import cn.bluesking.framework.proxy.ProxyManager;

/**
 * 切面编程助手类
 * 
 * @author 随心
 *
 */
public final class AopHelper {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(AopHelper.class);
	
	/**
	 * 初始化AOP框架
	 */
	static {
		try {
			Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
			for(Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
				Class<?> targetClass = targetEntry.getKey();
				List<Proxy> proxyList = targetEntry.getValue();
				Object proxy = ProxyManager.createProxy(targetClass, proxyList);
				BeanHelper.setBean(targetClass, proxy);
			}
		} catch (Exception e) {
			_LOG.error("AOP初始化失败！", e);
		}
	}
	
	/**
	 * 获取切面注解中注解类的Class类集合
	 * @param aspect
	 * @return
	 * @throws Exception
	 */
	private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Exception {
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		Class<? extends Annotation> annotation = aspect.value();
		if(annotation != null && !annotation.equals(Aspect.class)) {
			targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
		}
		return targetClassSet;
	}
	
	/**
	 * 获取切面代理类和其代理目标类集合的映射关系
	 * @return
	 * @throws Exception
	 */
	private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
		Map<Class<?>, Set<Class<?>>> proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
		addAspectProxy(proxyMap);
		addTransactionProxy(proxyMap);
		return proxyMap;
	}
	
	/**
	 * 添加普通切面代理类和目标类映射关系
	 * @param proxyMap
	 * @throws Exception
	 */
	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
		Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(Aspect.class);
		for(Class<?> proxyClass : proxyClassSet) {
			if(proxyClass.isAnnotationPresent(Aspect.class)) {
				Aspect aspect = proxyClass.getAnnotation(Aspect.class);
				Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
				proxyMap.put(proxyClass, targetClassSet);
			}
		}
	}
	
	/**
	 * 添加事务切面代理类和业务逻辑类映射关系
	 * @param proxyMap
	 * @throws Exception
	 */
	private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap) throws Exception {
		Set<Class<?>> serviceClassSet = ClassHelper.getClassSetByAnnotation(Service.class);
		proxyMap.put(Transaction.class, serviceClassSet);
		
	}
	
	/**
	 * 获取目标类与代理对象列表之间的映射关系
	 * @param proxyMap
	 * @return
	 * @throws Exception
	 */
	private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap)
			throws Exception {
		Map<Class<?>, List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
		for(Entry<Class<?>, Set<Class<?>>> proxyEntry : proxyMap.entrySet()) {
			Class<?> proxyClass = proxyEntry.getKey();
			Set<Class<?>> targetClassSet = proxyEntry.getValue();
			for(Class<?> targetClass : targetClassSet) {
				Proxy proxy = (Proxy) proxyClass.newInstance();
				if(targetMap.containsKey(targetClass)) {
					targetMap.get(targetClass).add(proxy);
				} else {
					List<Proxy> proxyList = new ArrayList<Proxy>();
					proxyList.add(proxy);
					targetMap.put(targetClass, proxyList);
				}
			}
		}
		return targetMap;
	}
}
