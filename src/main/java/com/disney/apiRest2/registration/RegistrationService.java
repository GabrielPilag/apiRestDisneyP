package com.disney.apiRest2.registration;

import java.time.LocalDateTime;

import javax.transaction.Transactional;


import org.springframework.stereotype.Service;

import com.disney.apiRest2.appUser.AppUser;
import com.disney.apiRest2.appUser.AppUserService;
import com.disney.apiRest2.email.EmailSender;
import com.disney.apiRest2.registration.token.ConfirmationToken;
import com.disney.apiRest2.registration.token.ConfirmationTokenService;
import com.disney.apiRest2.security.config.ApplicationUserRole;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	
	private final AppUserService appUserService;
	private final ConfirmationTokenService confirmationTokenService;
	

	
	public String register(RegistrationRequest request) {
		
		String token = appUserService
							.signUpUser(new AppUser(request.getFirstName(), request.getLastName(),request.getEmail(), request.getPassword(), ApplicationUserRole.USER));
		

		return token;
	}
	@Transactional
	public String confirmToken(String token) {
		
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()-> new IllegalStateException("token not found"));
		
		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}
		
		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		
		if(expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}
		
		confirmationTokenService.setConfirmedAt(token);
		appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
		return "confirmed";
		
	}
	
	
	
	

}
