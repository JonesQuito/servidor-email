package com.roboquito.email.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servidor-email")
public class LoginController {
	
	@GetMapping()
	public String login() {
		return "login";
	}

}
