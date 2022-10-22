package com.thgdsa.springboot.mywebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	//GET, POST
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String goToLoginPage() {
		return "login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String goToWelcomePage(@RequestParam String name, 
			@RequestParam String password, ModelMap model) {
		//Authentication
		//name - tita
		//password - 123
		if (authenticationService.authenticate(name, password)) {
			model.put("errorMessage", "Login realizado com Sucesso!");
			model.put("name", name);
//			model.put("password", password);
			return "welcome";
		}
		model.put("errorMessage", "Usuário ou senhas incorretas!");
		return "login";
		
	}
}
