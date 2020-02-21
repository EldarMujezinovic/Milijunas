package com.omareldar.milijunas;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Jokeri {
	static Scanner input = new Scanner(System.in);
	static Random rand = new Random();

	public static void ponudiJokere() throws InputMismatchException {
		int odluka = 0;

		System.out.println("\nDa li zelite iskoristiti joker?\n1.DA\n2.NE\n");

		odluka = provjeraInputa(odluka, 1, 2);

		if (odluka == 1) {
			System.out.println("Lista jokera:\n1.Pola-Pola\n2.Pozovi prijatelja\n3.Pomoc publike\n");
			int izborJokera = 0;

			izborJokera = provjeraInputa(izborJokera, 1, 3);

			switch (izborJokera) {
			case 1:
				jokerPolaPola();
				break;
			case 2:
				jokerPozoviPrijatelja();
				break;
			case 3:
				jokerPomocPublike();
				break;
			}
		} else {
			System.out.println("Unesite svoj odgovor(A, B, C, D): ");
			return;
		}

	}

	public static void jokerPolaPola() {
		System.out.println("Odabrali ste joker 'Pola-Pola'.");

		String tacanOdgovor = Odgovori.tacniOdgovori.get(Pitanja.getIndex());
		Map<Character, String> odgovoriNakonJokera = new HashMap<>();

		Character kljucTacnogOdg = getKljucPrekoVrijednosti(Odgovori.ponudjeniOdgovori, tacanOdgovor);
		odgovoriNakonJokera.put(kljucTacnogOdg, tacanOdgovor);

		Character kljucNetacnogOdg = (char) (rand.nextInt((68 - 65) + 1) + 65);
		while (kljucNetacnogOdg == kljucTacnogOdg) {
			kljucNetacnogOdg = (char) (rand.nextInt((68 - 65) + 1) + 65);
		}
		odgovoriNakonJokera.put(kljucNetacnogOdg, Odgovori.ponudjeniOdgovori.get(kljucNetacnogOdg));
		
		System.out.println("Odgovori nakon jokera Pola-Pola su: ");
		for (Object objectName : odgovoriNakonJokera.keySet()) {
			System.out.print(objectName + ". ");
			System.out.println(odgovoriNakonJokera.get(objectName));

		}
		odgovoriNakonJokera.clear();
	}

	public static void jokerPozoviPrijatelja() {
		System.out.println("Odabrali ste joker 'Pozovi prijatelja'.");
		
		String tacanOdgovor = Odgovori.tacniOdgovori.get(Pitanja.getIndex());
		String[] prijatelji = new String[4];
		prijatelji[0] = "Safet";
		prijatelji[1] = "Ahmo";
		prijatelji[2] = "Bego";
		prijatelji[3] = "Reufik";
		
		char slovoTacnogOdgovora = getKljucPrekoVrijednosti(Odgovori.ponudjeniOdgovori, tacanOdgovor);
		char slovoRandOdgovora;
		int randPrijatelj = rand.nextInt(prijatelji.length);
		
		switch(randPrijatelj) {
		case 0:
			slovoRandOdgovora = (char) (rand.nextInt((68 - 65) + 1) + 65);
			System.out.println("Pozvali ste prijatelja "+ prijatelji[0]);
			System.out.println(prijatelji[0]+ " kaze da je tacan odgovor pod "+ slovoRandOdgovora);
			break;
		case 1:
			System.out.println("Pozvali ste prijatelja "+ prijatelji[1]);
			System.out.println(prijatelji[0]+ " kaze da je tacan odgovor pod "+ slovoTacnogOdgovora);
			break;
		case 2:
			slovoRandOdgovora = (char) (rand.nextInt((68 - 65) + 1) + 65);
			System.out.println("Pozvali ste prijatelja "+ prijatelji[2]);
			System.out.println(prijatelji[0]+ " kaze da je tacan odgovor pod "+ slovoRandOdgovora);
			break;
		case 3:
			System.out.println("Pozvali ste prijatelja "+ prijatelji[3]);
			System.out.println(prijatelji[0]+ " kaze da je tacan odgovor pod "+ slovoTacnogOdgovora);
			break;	
		}	
		
	}

	public static void jokerPomocPublike() {
			System.out.println("Pozvali ste joker 'Pomoc publike'.");
			char slovo = 'A';
			int ukupniPostotak = 100;
			int randomPostotak;
			for(int i = 0; i < 4; i++) {
				randomPostotak = rand.nextInt(ukupniPostotak);
				System.out.println("Odgovor pod "+slovo+": "+randomPostotak+"%");
				ukupniPostotak -= randomPostotak;
				slovo++;
			}
	}

	public static <T, E> T getKljucPrekoVrijednosti(Map<T, E> map, E value) { // ova metoda prima mapu i vrijednost
		for (Entry<T, E> entry : map.entrySet()) { 							// i vraca kljuc sa tom vrijednoscu u mapi
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	public static int provjeraInputa(int var, int p1, int p2) {
		boolean uslov = false;

		do {
			try {
				var = input.nextInt();

				if (var < p1 || var > p2) {
					throw new InputMismatchException();
				} else {
					uslov = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Pogresan unos. Pokusajte ponovo:");
				input.nextLine();
			}
		} while (uslov != true);

		return var;
	}
}
