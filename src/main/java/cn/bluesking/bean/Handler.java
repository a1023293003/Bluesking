package cn.bluesking.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * 
 * @author 随心
 *
 */
public class Handler {

	/**
	 * Controller类
	 */
	private Class<?> controllerClass;
	
	/**
	 * Action方法
	 */
	private Method actionMethod;
	
	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}
	
	public Class<?> getControllerClass() {
		return controllerClass;
	}
	
	public Method getActionMethod() {
		return actionMethod;
	}
}