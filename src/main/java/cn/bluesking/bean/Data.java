package cn.bluesking.bean;

/**
 * 返回数据对象
 * 
 * @author 随心
 *
 */
public class Data {

	/**
	 * 模型数据
	 */
	private Object model;
	
	public Data(Object model) {
		this.model = model;
	}
	
	public Object getModel() {
		return model;
	}
}
