package com.disney.apiRest2.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;




public class JwtUsernameAndPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter{ //Las clases filter son simplemente clases que proveen algun tipo de validacion antes de llegar a la API 
	
	private final AuthenticationManager authenticationManager;
	
	public JwtUsernameAndPasswordAuthenticationFilter(String defaultFilterProcessesUrl,
		AuthenticationManager authenticationManager) {
	super(defaultFilterProcessesUrl);
	this.authenticationManager = authenticationManager;
}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) //este metodo verifica las credenciales es decir, el usuario y la contraseña
			throws AuthenticationException {

		try {
			UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override//Este metodo es invocado luego de que la autenticacion fue exitosa
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//Dentro de este metodo vamos a crear un token y enviarlo al cliente

		String key = "securesecuresecuresecuresecuresecuresecure";//Asegurarse que la palabra sea segura para generar la key
		String token = Jwts.builder()
				.setSubject(authResult.getName())
				.claim("authorities", authResult.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				.signWith(Keys.hmacShaKeyFor(key.getBytes()))
				.compact();

		response.addHeader("Authorization","Bearer " + token);
	} 

}
