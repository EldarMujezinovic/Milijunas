package com.omareldar.milijunas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import com.omareldar.exceptions.IsValidNicknameException;
import com.omareldar.exceptions.IsValidUserException;

/**
 * Klasa koja ucitava fajl sa odgovorima i posjeduje podatke i funkcije vezane
 * za odgovore
 * 
 * @author Omar Corbic, Eldar Mujezinovic
 *
 */
public class Odgovori {
	static ArrayList<String> tacniOdgovori = new ArrayList<>();
	static ArrayList<String> sporedniOdgovori = new ArrayList<>();
	static Map<Character, String> ponudjeniOdgovori = new TreeMap<Character, String>();
	static int bodovi = 0;
	static String v;
	static String tempOdgovor;
	static Scanner input = new Scanner(System.in);

	/*
	 * Metoda koja ucitava fajlove - pitanja i odgovori
	 */
	public static void loading() throws FileNotFoundException {
		try {
			File file = new File("Odgovori.txt");
			File file2 = new File("SporedniOdgovori.txt");

			Scanner read = new Scanner(file);
			Scanner read2 = new Scanner(file2);

			while (read.hasNextLine())
				tacniOdgovori.add(read.nextLine());

			while (read2.hasNextLine())
				sporedniOdgovori.add(read2.nextLine());

			read.close();
			read2.close();
		} catch (FileNotFoundException e) {
			System.err.println("Ne postoji file");
			e.getStackTrace();
		}

	}

	public static void ponudiOdgovore() throws InputMismatchException, NullPointerException, FileNotFoundException,
			IsValidUserException, IsValidNicknameException {

		Random rand = new Random();
		ArrayList<Integer> ponudjeniIndexi = new ArrayList<>();

		Character c = ' ';

		int br = Pitanja.getIndex();
		int indexOdgovora = 0;

		int max = (br + 1) * 3;
		int min = ((br + 1) * 3) - 2;

		c = (char) (rand.nextInt((68 - 65) + 1) + 65);
		indexOdgovora = rand.nextInt((max - min) + 1) + min;

		while (ponudjeniOdgovori.size() != 3) {

			c = (char) (rand.nextInt((68 - 65) + 1) + 65);
			indexOdgovora = rand.nextInt((max - min) + 1) + min;

			if (!ponudjeniIndexi.contains(indexOdgovora) && !ponudjeniOdgovori.containsKey(c)) {

				ponudjeniOdgovori.put(c, sporedniOdgovori.get(indexOdgovora));
				ponudjeniIndexi.add(indexOdgovora);

			} else {
				while (ponudjeniIndexi.contains(indexOdgovora)) {
					indexOdgovora = rand.nextInt((max - min) + 1) + min;
				}
				while (ponudjeniOdgovori.containsKey(c)) {
					c = (char) (rand.nextInt((68 - 65) + 1) + 65);

				}
				ponudjeniOdgovori.put(c, sporedniOdgovori.get(indexOdgovora));
				ponudjeniIndexi.add(indexOdgovora);

			}

		}
		while (ponudjeniOdgovori.size() != 4) {
			c = (char) (rand.nextInt((68 - 65) + 1) + 65);

			if (!ponudjeniOdgovori.containsKey(c)) {
				ponudjeniOdgovori.put(c, tacniOdgovori.get(br));
			} else {
				while (ponudjeniOdgovori.containsKey(c)) {
					c = (char) (rand.nextInt((68 - 65) + 1) + 65);

				}
				ponudjeniOdgovori.put(c, tacniOdgovori.get(br));
			}
		}
		for (Object objectName : ponudjeniOdgovori.keySet()) {

			System.out.print(objectName + ". ");
			System.out.println(ponudjeniOdgovori.get(objectName));

		}
		System.out.println("--------------");
		System.out.println("J. Pozovi Jokera");
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println("X. Prekid igre");
		System.out.println("--------------");

		unesi_I_ProvjeriOdgovor();
		ponudjeniOdgovori.clear();

	}

	/*
	 * Metoda koja prima odgovor kao unos od korisnika Provjerava ispravnost unosa
	 * korisnika Ova metoda implementira i metodu provjeriOdgovor
	 */

	public static void unesi_I_ProvjeriOdgovor() throws NullPointerException, InputMismatchException,
			FileNotFoundException, IsValidUserException, IsValidNicknameException {

		char odgovor = provjeraInputa();
		switch(odgovor) {
		case 'X':
			prekidIgre();
			break;
		case 'J':
			Jokeri.ponudiJokere();
			odgovor = provjeraInputa();
			break;
		}
		provjeriOdgovor(odgovor);

	};

	/**
	 * Metoda koja provjerava da li je odgovor tacan ili ne Dodaje bodove ako je
	 * tacan Ova metoda implementira metodu konacanOdgovor
	 * 
	 * @throws InputMismatchException
	 * @throws NullPointerException
	 * @throws IsValidUserException
	 * @throws IsValidNicknameException
	 * @throws FileNotFoundException
	 */

	public static void provjeriOdgovor(char odgovor) throws InputMismatchException, NullPointerException,
			IsValidUserException, IsValidNicknameException, FileNotFoundException {
		int uslov1 = -1;
		int unosKonacanOdgovor = 0;
		boolean notExecuted = true;
		@SuppressWarnings({ "resource", "unused" })
		Scanner unosKorisnika = new Scanner(System.in);
		while (uslov1 != 0) {
			if (notExecuted) {
				System.out.println("Da li je to vas konacan odgovor: ");
				System.out.println("1. DA");
				System.out.println("2. NE");
				unosKonacanOdgovor = Jokeri.provjeraInputa(unosKonacanOdgovor, 1, 2);
			}

			if (Character.isLetter(odgovor) && ponudjeniOdgovori.containsKey(odgovor)) {
				if (konacanOdgovor(unosKonacanOdgovor)) {
					notExecuted = true;
					if (ponudjeniOdgovori.get(odgovor).equals(tacniOdgovori.get(Pitanja.getIndex()))) {
						bodovi+=10;
						System.out.println("Tacan odgovor\n");
						Igrac.azuriranjeRanka();
						uslov1 = 0;
					} else {
						System.out.println("Netacan odgovor\n");
						uslov1 = 0;
					}
				} else {
					System.out.println("Pokusajte ponovo(A, B, C, D): ");
					notExecuted = false;
					unosKonacanOdgovor = 1;
					odgovor = provjeraInputa();
				}
			} else {
				System.out.println("Molimo vas unesite jedan od ponudenih odgovora(A, B, C, D): ");
				odgovor = provjeraInputa();
			}

		}

	}

	/**
	 * Metoda koja prekida igru, poslije izvrsavanja ove metode desava se sljedece:
	 * - korisniku se dodavaju bodovi koje je osvojio - korisnik ne moze ponovo
	 * igrati - poziva se metoda "meni"
	 * 
	 * @throws InputMismatchException
	 * @throws FileNotFoundException
	 * @throws IsValidUserException
	 * @throws IsValidNicknameException
	 */
	public static void prekidIgre()
			throws InputMismatchException, FileNotFoundException, IsValidUserException, IsValidNicknameException {
		Igrac.povecajBrojBodovaIgracu(Milijunas.trenutniIgrac, Odgovori.bodovi);
		Odgovori.bodovi = 0;
		Igrac.igracJeIgrao(Milijunas.trenutniIgrac);
		Pitanja.postavljenaPitanja.clear();
		Odgovori.ponudjeniOdgovori.clear();
		Milijunas.meni();
	}

	/**
	 * Metoda koja vraca true ako je unos 1, false ako je 2 ili neki drugi broj.
	 * 
	 * @param unos
	 * @return true ako je unos 1, false ako je unos 2 ili neki drugi broj.
	 */

	public static boolean konacanOdgovor(int unos) {
		if (unos == 1) {
			return true;
		} else if (unos == 2) {
			return false;
		} else {
			return false;
		}
	}

	public static char provjeraInputa() throws FileNotFoundException, IsValidUserException, IsValidNicknameException {
		@SuppressWarnings("resource")
		Scanner input1 = new Scanner(System.in);
		boolean uslov = true;
		char karakter = ' ';
		do {
			try {
				String 	unos = input1.nextLine(); // A
				if (unos.length() != 1) {
					throw new InputMismatchException();
				}
				karakter = unos.charAt(0); // a
				karakter = Character.toUpperCase(karakter);
				
				switch (karakter) {

				case 'A':
					uslov = false;
					break;

				case 'B':
					uslov = false;
					break;

				case 'C':
					uslov = false;
					break;

				case 'D':
					uslov = false;
					break;

				case 'J':
					uslov = false;
					break;

				case 'X':
					uslov = false;
					break;
					
					default:
						throw new InputMismatchException();
				}

			} catch (InputMismatchException e) {
				System.out.println("Pogresan unos. Unesite ponovo:");
			}
		} while (uslov);
		return karakter;
	}
}