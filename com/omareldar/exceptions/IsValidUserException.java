package com.omareldar.exceptions;

@SuppressWarnings("serial")
public class IsValidUserException extends Exception{
	
	public IsValidUserException(String name) {
		super("Igrac sa nickom: " + name + " vec postoji. Pokusajte ponovo: ");
	}
}
