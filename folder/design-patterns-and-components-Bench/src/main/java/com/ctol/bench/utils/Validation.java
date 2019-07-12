package com.ctol.bench.utils;

public class Validation {

	private Validation() {

	}

	private static final String PW_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private static final String PHONE_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

	public static boolean checkPasswordRegex(String password) {
		return password.matches(PW_REGEX);
	}

	public static boolean checkPhoneRegex(String phone) {
		return phone.matches(PHONE_REGEX);
	}
}
