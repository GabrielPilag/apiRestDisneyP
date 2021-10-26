package com.disney.apiRest2.security.config;

public enum ApplicationUserPermission {

	APPlICATION_READ("application:read"),
	APPLICATION_WRITE("application:write");

	
	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
	
	
}
