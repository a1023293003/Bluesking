package cn.bluesking.framework;

import cn.bluesking.framework.helper.AopHelper;
import cn.bluesking.framework.helper.BeanHelper;
import cn.bluesking.framework.helper.ClassHelper;
import cn.bluesking.framework.helper.ControllerHelper;
import cn.bluesking.framework.helper.IocHelper;
import cn.bluesking.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 * 
 * @author 随心
 *
 */
public class HelperLoad {

	/**
	 * 加载Helper类
	 * 
	 * <p>AopHelper要在BeanHelper之后初始化,因为AopHelper要将代理对象注入到BeanMap中.</p>
	 * <p>AopHelper要在IocHelper之前初始化,首先通过AopHelper获取代理对象，然后才通过IocHelper依赖注入.</p>
	 */
	public static void init() {
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		for(Class<?> clazz : classList) {
			ClassUtil.loadClass(clazz.getName());
		}
	}
}
