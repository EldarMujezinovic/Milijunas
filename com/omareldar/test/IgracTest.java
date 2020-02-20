package com.omareldar.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.omareldar.milijunas.Igrac;
@SuppressWarnings("static-access")
class IgracTest {

	Igrac igrac;

	@BeforeEach
	void setUp() throws Exception {
		igrac = new Igrac();
	}

	@Test
	public void shouldReturnTrueIfNickIsValid() {
		igrac.setNickname("Eldar");
		String nick = igrac.getNickname();
		assertTrue(igrac.isValidNick(nick));
	}
	
	@Test
	public void shouldReturnFalseIfNickIsInvalid() {
		igrac.setNickname("3o");
		String nick = igrac.getNickname();
		assertFalse(igrac.isValidNick(nick));
	}
	
	@Test
	public void shouldReturnTrueIFUserIsValid() {
	
	}

}
