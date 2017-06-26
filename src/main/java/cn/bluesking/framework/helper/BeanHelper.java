package cn.bluesking.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.bluesking.framework.util.ReflectionUtil;

/**
 * Bean助手类
 * 
 * @author 随心
 *
 */
public final class BeanHelper {

	/**
	 * 定义Bean映射(用于存放Bean类与Bean实例的映射关系)
	 */
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static {
		Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
		for(Class<?> beanClass : beanClassSet) {
			Object obj = ReflectionUtil.newInstance(beanClass);
			BEAN_MAP.put(beanClass, obj);
		}
	}
	
	/**
	 * 获取Bean类与Bean实例之间的映射关系
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap() {
		return BEAN_MAP;
	}
	
	/**
	 * 获取Bean实例
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> beanClass) {
		if(!BEAN_MAP.containsKey(beanClass)) {
			throw new RuntimeException("无法通过" + beanClass + "找到对应的Bean！");
		}
		return (T) BEAN_MAP.get(beanClass);
	}
	
	/**
	 * 设置Bean实例
	 * @param cls
	 * @param obj
	 */
	public static void setBean(Class<?> cls, Object obj) {
		BEAN_MAP.put(cls, obj);
	}
	
}
