package cn.bluesking.framework.demo;

import java.util.Map.Entry;

import cn.bluesking.framework.annotation.Action;
import cn.bluesking.framework.annotation.Controller;
import cn.bluesking.framework.bean.Param;
import cn.bluesking.framework.bean.View;

@Controller
public class DemoController {

	@Action(value = "get:/hello")
	public View demo() {
		System.out.println("demo方法");
		View view = new View("hello.jsp");
		view.addModel("message", "里好呀1111111！");
		return view;
	}
	
	@Action(value = "get:/hello1")
	public View demo1(Param param) {
		System.out.println("demo1方法");
		View view = new View("hello.jsp");
		view.addModel("message", "里好呀！");
		return view;
	}
}
