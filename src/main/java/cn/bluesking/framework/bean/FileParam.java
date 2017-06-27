package cn.bluesking.framework.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 * 
 * @author 随心
 *
 */
public class FileParam {

	/**
	 * 文件名
	 */
	private String fileName;
	
	/**
	 * 参数名
	 */
	private String fieldName;
	
	/**
	 * 文件大小
	 */
	private long fileSize;
	
	/**
	 * 请求头参数,用于截取文件类型
	 */
	private String contentType;
	
	/**
	 * 上传文件字节输入流
	 */
	private InputStream inputStream;

	public FileParam(String fileName, String fieldName, long fileSize, String contentType, InputStream inputStream) {
		this.fileName = fileName;
		this.fieldName = fieldName;
		this.fileSize = fileSize;
		this.contentType = contentType;
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	
}
