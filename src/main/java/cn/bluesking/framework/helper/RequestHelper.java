package cn.bluesking.framework.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.bluesking.framework.bean.FormParam;
import cn.bluesking.framework.bean.Param;
import cn.bluesking.framework.util.ArrayUtil;
import cn.bluesking.framework.util.CodecUtil;
import cn.bluesking.framework.util.StreamUtil;
import cn.bluesking.framework.util.StringUtil;

/**
 * 请求助手类
 * 
 * @author 随心
 *
 */
public final class RequestHelper {

	/**
	 * 创建请求对象
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Param createParam(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		formParamList.addAll(parseParameterNames(request));
		formParamList.addAll(parseInputStream(request));
		return new Param(formParamList);
	}
	
	/**
	 * 获取Parameter中的参数
	 * @param request
	 * @return
	 */
	private static List<FormParam> parseParameterNames(HttpServletRequest request) {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String fieldName = paramNames.nextElement();
			String[] fieldValues = request.getParameterValues(fieldName);
			if(ArrayUtil.isNotEmpty(fieldValues)) {
				Object fieldValue;
				if(fieldValues.length == 1) {
					fieldValue = fieldValues[0];
				} else {
					StringBuilder sBuilder = new StringBuilder(fieldValues[0]);
					for(int i = 1; i < fieldValues.length; i ++) {
						sBuilder.append(StringUtil.SEPARATOR + fieldValues[i]);
					}
					fieldValue = sBuilder.toString();
				}
				formParamList.add(new FormParam(fieldName, fieldValue));
			}
		}
		return formParamList;
	}
	
	/**
	 * 获取InputStream中的参数
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private static List<FormParam> parseInputStream(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
		if(StringUtil.isNotEmpty(body)) {
			String[] keyValues = body.split("&");
			if(ArrayUtil.isNotEmpty(keyValues)) {
				for(String keyValue : keyValues) {
					String[] array = keyValue.split("=");
					if(ArrayUtil.isNotEmpty(array) && array.length == 2) {
						String fieldName = array[0];
						String fieldValue = array[1];
						formParamList.add(new FormParam(fieldName, fieldValue));
					}
				}
			}
		}
		return formParamList;
	}
}
