package com.thgdsa.springboot.mywebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	
	public boolean authenticate(String username, String password) {
		
		boolean isValidUserName = username.equalsIgnoreCase("tita");
		boolean isValidPassword = password.equalsIgnoreCase("123");
		
		
		return isValidPassword && isValidUserName;
	}

}
