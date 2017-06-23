package cn.bluesking;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bluesking.bean.Data;
import cn.bluesking.bean.Handler;
import cn.bluesking.bean.Param;
import cn.bluesking.bean.View;
import cn.bluesking.helper.BeanHelper;
import cn.bluesking.helper.ConfigHelper;
import cn.bluesking.helper.ControllerHelper;
import cn.bluesking.util.ArrayUtil;
import cn.bluesking.util.CodecUtil;
import cn.bluesking.util.JsonUtil;
import cn.bluesking.util.ReflectionUtil;
import cn.bluesking.util.StreamUtil;
import cn.bluesking.util.StringUtil;

/**
 * 请求转发器
 * 
 * @author 随心
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

	/**
	 * slf4j日志配置
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(DispatcherServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		_LOG.info("初始化DispatcherServlet");
		// 初始化Helper类
		HelperLoad.init();
		// 获取ServletContext对象(用于注册Servlet)
		ServletContext servletContext = config.getServletContext();
		// 注册JSP的Servlet
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
		// 注册静态资源默认Servlet
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求方法和请求路径
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		System.out.println(requestMethod + "  " + requestPath);
		// 获取Action容器
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler != null) {
			// 获取Controller类及其实例
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			// 获取请求参数对象
			Map<String, Object> paramMap = new HashMap<String, Object>();
			request.setCharacterEncoding("UTF-8");
			Enumeration<String> paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			// getParamter读取不到数据时从请求中读取数据
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			System.out.println("body = " + body);
			if(StringUtil.isNotEmpty(body)) {
				String[] params = body.split("&");
				if(ArrayUtil.isNotEmpty(params)) {
					for(String param : params) {
						String[] array = param.split("=");
						if(ArrayUtil.isNotEmpty(array) && array.length == 2) {
							paramMap.put(array[0], array[1]);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			// 调用Action方法
			Method actionMethod = handler.getActionMethod();
			Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			// 处理Action返回值
			if(result instanceof View) {
				View view = (View) result;
				String path = view.getPath();
				if(StringUtil.isNotEmpty(path)) {
					// TODO 跳转自由度太差
					if(path.startsWith("/")) {
						response.sendRedirect(request.getContextPath() + path);
					} else {
						Map<String, Object> model = view.getModel();
						for(Entry<String, Object> entry : model.entrySet()) {
							request.setAttribute(entry.getKey(), entry.getValue());
						}
						request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
							.forward(request, response);
					}
				}
			} else if(result instanceof Data) {
				Data data = (Data) result;
				Object model = data.getModel();
				if(model != null) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					String json = JsonUtil.toJson(model);
					out.println(json);
					out.flush();
					out.close();
				}
			}
		}
	}
}
