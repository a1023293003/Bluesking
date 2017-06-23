package cn.bluesking.helper;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import cn.bluesking.annotation.Controller;
import cn.bluesking.annotation.Service;
import cn.bluesking.util.ClassUtil;

/**
 * 类操作助手类
 * 
 * @author 随心
 *
 */
public final class ClassHelper {
	
	/**
	 * 定义类集合(用于存放所加载的类)
	 */
	private static final Set<Class<?>> CLASS_SET;
	
	static {
		String basePackage = ConfigHelper.getAppBasePackage();
		CLASS_SET = ClassUtil.getClassSet(basePackage);
	}
	
	/**
	 * 获取应用包名下所有类
	 * @return
	 */
	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}
	
	/**
	 * 获取应用包名下某父类(或接口)的所有子类(或实现类)
	 * @param superClass
	 * @return
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET) {
			if(superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下带有某注解的所有类
	 * @param clazz [Class<? extends Annotation>]指定注解类的class
	 * @return
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass) {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for(Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(annotationClass)) {
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	/**
	 * 获取应用包名下所有Service类
	 * @return
	 */
	public static Set<Class<?>> getServiceClassSet() {
		return getClassSetByAnnotation(Service.class);
	}
	
	/**
	 * 获取应用报名下所有Controller类
	 * @return
	 */
	public static Set<Class<?>> getControllerClassSet() {
		return getClassSetByAnnotation(Controller.class);
	}
	
	/**
	 * 获取应用包名下所有bean类(包括:Service、Controller等)
	 * @return
	 */
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		classSet.addAll(getServiceClassSet());
		classSet.addAll(getControllerClassSet());
		return classSet;
	}
}
