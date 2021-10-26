package com.disney.apiRest2.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("/welcome")
	public String getWelcome() {
		return "welcome";
	}

	
}
