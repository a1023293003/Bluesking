package cn.bluesking.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象
 * 
 * @author 随心
 *
 */
public class View {

	/**
	 * 视图路径
	 */
	private String path;
	
	/**
	 * 模型数据
	 */
	private Map<String, Object> model;
	
	public View(String path) {
		this.path = path;
		this.model = new HashMap<String, Object>();
	}
	
	/**
	 * 更新模型数据
	 * @param key [String]参数名
	 * @param value [Object]参数值
	 * @return
	 */
	public View addModel(String key, Object value) {
		this.model.put(key, value);
		return this;
	}
	
	public String getPath() {
		return path;
	}
	
	public Map<String, Object> getModel() {
		return this.model;
	}
}
