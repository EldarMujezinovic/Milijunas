package com.omareldar.milijunas;

@SuppressWarnings("serial")
public class IsValidNicknameException extends Exception{

	public IsValidNicknameException(String name) {
		super("Nickname mora sadrzati samo slova (najmanje 3, a najvise 10)!");
	}
}
