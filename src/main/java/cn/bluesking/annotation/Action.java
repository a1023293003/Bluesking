package cn.bluesking.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action方法注释
 * 
 * @author 随心
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
	
	/**
	 * Action接收的url
	 * @return
	 */
	String[] urlPatterns() default {};
	
	/**
	 * Action接收的url
	 * @return
	 */
	String[] value() default {};
	
	/**
	 * 请求方式
	 * @return
	 */
	String[] method() default {"get", "post"};
	
}
