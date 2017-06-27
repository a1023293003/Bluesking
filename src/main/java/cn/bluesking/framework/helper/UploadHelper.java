package cn.bluesking.framework.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bluesking.framework.bean.FileParam;
import cn.bluesking.framework.bean.FormParam;
import cn.bluesking.framework.bean.Param;
import cn.bluesking.framework.util.CollectionUtil;
import cn.bluesking.framework.util.FileUtil;
import cn.bluesking.framework.util.StreamUtil;
import cn.bluesking.framework.util.StringUtil;

/**
 * 文件上传助手类
 * 
 * @author 随心
 *
 */
public final class UploadHelper {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(UploadHelper.class);

	/**
	 * Apache Commons FileUpload提供的Servlet文件上传对象
	 */
	private static ServletFileUpload servletFileUpload;
	
	/**
	 * 初始化
	 * @param servletContext
	 */
	public static void init(ServletContext servletContext) {
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		servletFileUpload = new ServletFileUpload(
				new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
		int uploadLimit = ConfigHelper.getAppUploadLimit();
		// 单位MB
		if(uploadLimit != 0) {
			servletFileUpload.setFileSizeMax(uploadLimit * 1024 * 1024L);
		}
	}
	
	/**
	 * 判断请求是否为multipart类型
	 * @param request
	 * @return
	 */
	public static boolean isMultiPart(HttpServletRequest request) {
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/**
	 * 创建请求对象
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Param createParam(HttpServletRequest request) throws IOException {
		List<FormParam> formParamList = new ArrayList<FormParam>();
		List<FileParam> fileParamList = new ArrayList<FileParam>();
		try {
			Map<String, List<FileItem>> fileItemListMap = servletFileUpload.parseParameterMap(request);
			if(CollectionUtil.isNotEmpty(fileItemListMap)) {
				// 遍历每个参数名对应的FileItem列表
				for(Entry<String, List<FileItem>> fileItemListItem : fileItemListMap.entrySet()) {
					// 参数名
					String fieldName = fileItemListItem.getKey();
					List<FileItem> fileItemList = fileItemListItem.getValue();
					if(CollectionUtil.isNotEmpty(fileItemList)) {
						// 遍历FileItem列表
						for(FileItem fileItem :fileItemList) {
							if(fileItem.isFormField()) { // 普通参数
								// 参数值
								String fieldValue = fileItem.getString("UTF-8");
								formParamList.add(new FormParam(fieldName, fieldValue));
							} else { 					 // 文件参数
								String fileName = FileUtil.getRealFileName(
										new String(fileItem.getName().getBytes(), "UTF-8"));
								if(StringUtil.isNotEmpty(fileName)) {
									long fileSize = fileItem.getSize();
									String contentType = fileItem.getContentType();
									InputStream inputStream = fileItem.getInputStream();
									fileParamList.add(new FileParam(fileName, fieldName, 
											fileSize, contentType, inputStream));
								}
							}
						}
					}
				}
			}
		} catch (FileUploadException e) {
			
		}
		return new Param(formParamList, fileParamList);
	}
	
	/**
	 * 上传文件
	 * @param basePath [String]基础路径
	 * @param fileParam [FileParam]上传文件参数
	 */
	public static void uploadFile(String basePath, FileParam fileParam) {
		try {
			if(fileParam != null) {
				String filePath = basePath + fileParam.getFileName();
				FileUtil.createFile(filePath);
				InputStream inputStream = new BufferedInputStream(fileParam.getInputStream());
				OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
				StreamUtil.copyStream(inputStream, outputStream);
			}
		} catch (Exception e) {
			_LOG.error("上传文件失败！", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 批量上传文件
	 * @param basePath [String]基础路径
	 * @param fileParamList [List<FileParam>]上传文件参数列表
	 */
	public static void uploadFile(String basePath, List<FileParam> fileParamList) {
		try {
			if(CollectionUtil.isNotEmpty(fileParamList)) {
				for(FileParam fileParam : fileParamList) {
					uploadFile(basePath, fileParam);
				}
			}
		} catch (Exception e) {
			_LOG.error("上传文件失败！", e);
			throw new RuntimeException(e);
		}
	}
}
