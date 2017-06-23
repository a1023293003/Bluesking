package cn.bluesking.bean;

import java.util.Map;

import cn.bluesking.util.CaseUtil;

/**
 * 请求参数对象
 * 
 * @author 随心
 *
 */
public class Param {
	
	private Map<String, Object> paramMap;
	
	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	/**
	 * 获取所有字段信息
	 * @return
	 */
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	
	/**
	 * 根据参数名获取long类型参数值
	 * @param name [String]参数名
	 * @return
	 */
	public long getLong(String name) {
		return CaseUtil.caseLong(paramMap.get(name));
	}
	
	/**
	 * 根据参数名获取double类型数值
	 * @param name [String]参数名
	 * @return
	 */
	public double getDouble(String name) {
		return CaseUtil.caseDouble(paramMap.get(name));
	}
	
	/**
	 * 根据参数名获取int类型数值
	 * @param name [String]参数名
	 * @return
	 */
	public int getInt(String name) {
		return CaseUtil.caseInt(paramMap.get(name));
	}
	
	/**
	 * 根据参数名获取String类型数值
	 * @param name [String]参数名
	 * @return
	 */
	public String getString(String name) {
		return CaseUtil.caseString(paramMap.get(name));
	}
	
	/**
	 * 根据参数名获取boolean类型数值
	 * @param name [String]参数名
	 * @return
	 */
	public boolean getBoolean(String name) {
		return CaseUtil.caseBoolean(paramMap.get(name));
	}
}
