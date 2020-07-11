package com.common.util;

public class Session {
	private static String token;
	public  static void setToken(String tk) {
		token = tk;
	}
	
	public static String getToken() {
		return token;
	}

}
