package com.disney.apiRest2.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.disney.apiRest2.appUser.AppUserService;
import com.disney.apiRest2.jwt.JwtTokenVerifier;
import com.disney.apiRest2.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//anotacion para que se puedan utilizar las anotaciones para autenticaciones
@AllArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	private final AppUserService appUserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Autenticacion basica, con lista blanca para que no pida loggin en index... y demas
		
		http
//			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//Cross-site request forgery o falsificación de petición en sitios cruzados
//			.and()
			.csrf().disable()//solo para pruebas deshabilito csrf
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterAt(new JwtUsernameAndPasswordAuthenticationFilter("/auth/login",authenticationManager()),UsernamePasswordAuthenticationFilter.class)//Agrego filtros
			.addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers("/","index","/css/", "/js/*").permitAll()
			.antMatchers("/auth/**").permitAll()
			.anyRequest()
			.authenticated(); 
		 
	}
	
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(appUserService);
		return provider;
	}


}
