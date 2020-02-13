package com.omareldar.milijunas;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Milijunas {
	static String trenutniIgrac = " ";
	static boolean userCreated = false;
	static Scanner input = new Scanner(System.in);
	static int userChoose;

	public static void main(String[] args)
			throws FileNotFoundException, InputMismatchException, IsValidUserException, IsValidNicknameException {

		Pitanja.loading();
		Odgovori.loading();

		System.out.println("********DOBRODOSLI U MILIJUNAS!********\n");
		meni();

	}

	public static void meni()
			throws FileNotFoundException, InputMismatchException, IsValidUserException, IsValidNicknameException {

		System.out.println("\nOdaberite zeljenu opciju:\n1.Novi igrac\n2.Zapocni igru "
				+ "\n3.Rang lista\n4.Ispis igraca\n5.Detalji igraca\n6.Promijeni igraca\n7.Izlaz\n");

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
				System.out.println("Wrong input, try again!");
			}
			input.nextLine();
		} while (i != 0);
		switch (userChoose) {

		case 1:
			kreirajIgraca();
			meni();
			break;

		case 2:
			zapocniIgru();
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
			Igrac.printUserDetails();
			meni();
			break;

		case 6:
			promijeniIgraca();
			meni();
			break;

		case 7:
			System.out.println("********HVALA NA IGRI********");
			System.exit(0);
			break;

		}

	}

	public static void kreirajIgraca() throws InputMismatchException, IsValidUserException, IsValidNicknameException {

		System.out.println("Unesite nickname: ");
		String nickname = input.nextLine();
		boolean isLetter = false;
		while (isLetter)
			for (int i = 0; i < nickname.length(); i++) {
				if (Character.isLetter(nickname.charAt(i))) {
					isLetter = true;
				} else {
					isLetter = false;
					nickname = input.nextLine();
					break;
				}
			}

		int brojBodova = 0;
		int rank = 0;

		do {
			try {
				if (Igrac.isValidNick(nickname)) {

					if (!Igrac.userIsValid(nickname)) {
						@SuppressWarnings("unused")
						Igrac user = new Igrac(nickname, brojBodova, rank);
						Igrac.imenaIgraca.add(nickname);
						trenutniIgrac = nickname;
						userCreated = true;
						break;
					} else {
						throw new IsValidUserException(nickname);
					}
				} else {
					throw new IsValidNicknameException(nickname);
				}
			} catch (IsValidNicknameException e) {
				System.out.println(e.getMessage());
				nickname = input.next();
			} catch (IsValidUserException e) {
				System.out.println(e.getMessage());
				nickname = input.next();
			}

		} while (true);

	}

	public static void zapocniIgru() throws FileNotFoundException {
		if (userCreated) {

			if (!Igrac.jeIgrao(trenutniIgrac)) {
				System.out.println("Trenutni igrac: " + trenutniIgrac);
				for (int i = 0; i < Pitanja.pitanja.size(); i++) {

					Pitanja.ucitajPitanje();
					Odgovori.ponudiOdgovore();

				}
				Igrac.increaseUserScore(trenutniIgrac, Odgovori.bodovi);
				Odgovori.bodovi = 0;
			} else {
				System.out.println("Nije dozvoljeno igrati vise od jednom na istom profilu!");
			}
		} else {
			System.out.println("Nema kreiranih igraca.");
		}
		Igrac.igracJeIgrao(trenutniIgrac);
		Pitanja.postavljenaPitanja.clear();

	}

	public static void promijeniIgraca() {
		if (Igrac.igraci.size() <= 1) {
			System.out.println("Broj kreiranih igraca mora biti veci od 1.");
		} else {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			int rbIgraca = 0;

			System.out.println(
					"Trenutni igrac je: " + Milijunas.trenutniIgrac + "\nOdaberite igraca s kojim zelite igrati: ");

			for (String ime : Igrac.imenaIgraca) {
				if (Milijunas.trenutniIgrac.equals(ime)) {
					System.out.println(Igrac.imenaIgraca.indexOf(ime) + 1 + ". " + "Trenutni igrac: " + ime);
					continue;
				}
				System.out.println(Igrac.imenaIgraca.indexOf(ime) + 1 + ". " + ime);

			}

			int i = -1;
			do {

				try {
					rbIgraca = input.nextInt();
					if (rbIgraca <= 0 || rbIgraca > Igrac.imenaIgraca.size()) {
						System.out.println("Unesite broj u razmaku od 1 do " + Igrac.imenaIgraca.size());
					} else if (rbIgraca == Igrac.imenaIgraca.indexOf(Milijunas.trenutniIgrac) + 1) {
						System.out.println("Ne mozete ponovo unijeti trenutnog igraca. Molimo unesite ponovo: ");
					} else {
						i = 0;
					}
				} catch (InputMismatchException e) {
					System.out.println("Pogresan unos pokusajte ponovo.");
				}
				input.nextLine();

			} while (i != 0);
			Milijunas.trenutniIgrac = Igrac.imenaIgraca.get(rbIgraca - 1);

		}
	}
}
