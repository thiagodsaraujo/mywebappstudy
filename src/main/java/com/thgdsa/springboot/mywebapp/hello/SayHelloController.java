package com.thgdsa.springboot.mywebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	
	//"say-hello" => "Hello! What are you learning today?"
	

	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> My first HTML page - Changed </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("My first html page with body - Changed");
		sb.append("</body>");
		sb.append("</html>");	
		return sb.toString();
	
	}
	
	//
	// "say-hello-jsp" => sayHello.jsp
	///src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	///src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	///src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
	///src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}

}
