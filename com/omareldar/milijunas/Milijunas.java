package com.omareldar.milijunas;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.omareldar.exceptions.IsValidNicknameException;
import com.omareldar.exceptions.IsValidUserException;

/**
 * PROJEKAT MILIJUNAS Main klasa
 * 
 * @author Omar Corbic, Eldar Mujezinovic
 *
 */
public class Milijunas {
	static String trenutniIgrac;
	static boolean userCreated = false;
	static Scanner input = new Scanner(System.in);
	static int userChoose;

	public static void wait(double n) {
		try {
			Thread.sleep((long) (n * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void slowPrint(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				System.out.print(s.charAt(i));
				wait(0.07);
			} else {
				System.out.print(s.charAt(i));
				wait(0.02);
			}
		}
	}

	/**
	 * Main metoda u kojoj se poziva meni.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 * @throws IsValidUserException
	 * @throws IsValidNicknameException
	 */
	public static void main(String[] args)
			throws FileNotFoundException, InputMismatchException, IsValidUserException, IsValidNicknameException {

	slowPrint("********DOBRODOSLI U MILIJUNAS!********\n");
		meni();

	}

	/**
	 * Metoda koja provjera unos korisnika
	 */
	public static void meniUnos() {
		int i = -1;
		do {
			try {
				userChoose = input.nextInt();
				if (userChoose < 1 || userChoose > 7) {
					throw new InputMismatchException();
				} else {
					i = 0;
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogresan unos, pokusajte ponov(Unesite opciju u razmaku od 1 do 7!");
			}
			input.nextLine();
		} while (i != 0);
	}

	/**
	 * Metoda koja nudi razlicite opcije korisniku
	 * 
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 * @throws IsValidUserException
	 * @throws IsValidNicknameException
	 */
	public static void meni()
			throws FileNotFoundException, InputMismatchException, IsValidUserException, IsValidNicknameException {

		System.out.println("\nOdaberite zeljenu opciju:\n1.Novi igrac\n2.Zapocni igru "
				+ "\n3.Rang lista\n4.Ispis igraca\n5.Detalji igraca\n6.Promijeni igraca\n7.Izlaz\n");
		meniUnos();

		switch (userChoose) {

		case 1:
			kreirajIgraca();
			meni();
			break;

		case 2:
			Igra.zapocniIgru();
			meni();
			break;
		case 3:
			Igrac.rankLista();
			meni();
			break;

		case 4:
			Igrac.ispisIgraca();
			meni();
			break;

		case 5:
			Igrac.ispisiDetaljeIgraca();
			meni();
			break;

		case 6:
			Igrac.promijeniIgraca();
			meni();
			break;

		case 7:
			slowPrint("********HVALA NA IGRANJU********");
			System.exit(0);
			break;

		}

	}

	/**
	 * Metoda koja kreira igraca Provjerava: - Da li je validan nickname - Da li je
	 * validan igrac - Unos korisnika
	 * 
	 * @throws InputMismatchException
	 * @throws IsValidUserException
	 * @throws IsValidNicknameException
	 */
	public static void kreirajIgraca() throws InputMismatchException, IsValidUserException, IsValidNicknameException {
		Igrac user = new Igrac();
		int brojBodova = 0;
		int rank = 0;
		System.out.println("Unesite nickname: ");
		String nickname = user.getImeIgraca();
		user = new Igrac(nickname, brojBodova, rank);
		trenutniIgrac = nickname;
		userCreated = true;

	}


}
