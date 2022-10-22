package com.thgdsa.springboot.mywebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
//	http://localhost:8080/login?name=Thiago
	//Model
	@RequestMapping("login")
	public String goToLoginPage(@RequestParam String name, ModelMap model) {
		model.put("name", name);
		logger.debug("Request param is {}", name);
		logger.info("I Want this printed at info level");
		logger.warn("I Want this printed at warn level");
		
		System.out.println("Request param is " + name); // SOMENTE PARA TESTE
		
		return "login";
	}
}
