package com.disney.apiRest2.registration;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class RegistrationController {
	
	private final RegistrationService registrationService;


	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody RegistrationRequest request) {
		return new ResponseEntity<>(registrationService.register(request),HttpStatus.OK);
	}

	@GetMapping("/register/confirm")
	public ResponseEntity<String> confirm(@RequestParam("token") String token) {
		return new ResponseEntity<>(registrationService.confirmToken(token),HttpStatus.OK);
	}
	
}
