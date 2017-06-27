package cn.bluesking.framework.bean;

/**
 * 封装表单参数
 * 
 * @author 随心
 *
 */
public class FormParam {

	/**
	 * 参数名
	 */
	private String fieldName;
	
	/**
	 * 参数值
	 */
	private Object fieldValue;

	public FormParam(String fieldName, Object fieldValue) {
		super();
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
	
}
