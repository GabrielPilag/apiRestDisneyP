package com.disney.apiRest2.security.config;

import com.google.common.collect.Sets;

import static com.disney.apiRest2.security.config.ApplicationUserPermission.APPLICATION_WRITE;
import static com.disney.apiRest2.security.config.ApplicationUserPermission.APPlICATION_READ;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {

	USER(Sets.newHashSet(APPlICATION_READ)),
	ADMIN(Sets.newHashSet(APPlICATION_READ, APPLICATION_WRITE));
	
	
	private final Set<ApplicationUserPermission> permissions;

	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}
	
	
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}



	public Set<SimpleGrantedAuthority> getGrantedAuthority(){
		
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
			.collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return permissions;
		
	}
	
	
	
}
