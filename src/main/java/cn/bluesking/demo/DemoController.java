package cn.bluesking.demo;

import java.util.Map.Entry;

import cn.bluesking.annotation.Action;
import cn.bluesking.annotation.Controller;
import cn.bluesking.bean.Param;
import cn.bluesking.bean.View;

@Controller
public class DemoController {

	@Action(value = "get:/hello")
	public View demo(Param param) {
		System.out.println("demo方法");
		View view = new View("hello.jsp");
		view.addModel("message", "里好呀1111111！");
		return view;
	}
	
	@Action(value = "post:/hello1")
	public View demo1(Param param) {
		System.out.println("demo1方法");
		for(Entry<String, Object> entry : param.getParamMap().entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		View view = new View("hello.jsp");
		view.addModel("message", "里好呀！");
		return view;
	}
}
