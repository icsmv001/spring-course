package com.springcourse.constant;

public class SecurityConstants {
	//Variaveis base para spring security
	// o token dever experiar apos 45 dias de sua criacao.
	public static final int JWT_EXP_DAYS = 45;
	public static final String API_KEY = "spring-course";
	public static final String JWT_PROVIDER = "Bearer";
	public static final String JWT_ROLE_KEY = "role";
	public static final String JWT_INVALID_MSG = "invalide JWT Token";
	
	

}
