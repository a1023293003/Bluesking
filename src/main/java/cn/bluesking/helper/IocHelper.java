package cn.bluesking.helper;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import cn.bluesking.annotation.Inject;
import cn.bluesking.util.ArrayUtil;
import cn.bluesking.util.CollectionUtil;
import cn.bluesking.util.ReflectionUtil;

/**
 * 依赖注入助手类
 * TODO 算法待优化
 * <pre>
 * 暴力遍历所有Bean类的所有属性，找到有注入标签的属性尝试注入。
 * </pre>
 * 
 * @author 随心
 *
 */
public final class IocHelper {

	static {
		// 获取Bean类与Bean实例之间的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if(CollectionUtil.isNotEmpty(beanMap)) {
			// 遍历Bean类
			for(Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				// Bean类关系键值对
				Class<?> beanClass = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				// 遍历当前Bean类的属性
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtil.isNotEmpty(beanFields)) {
					for(Field beanField : beanFields) {
						// 判断是否有注入注释
						if(beanField.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = beanField.getType();
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							// 如若找到注入Bean实例,尝试通过反射注入
							if(beanFieldInstance != null) {
								ReflectionUtil.setField(
										beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
