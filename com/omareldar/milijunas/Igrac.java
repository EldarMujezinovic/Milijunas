package com.omareldar.milijunas;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.omareldar.exceptions.IsValidNicknameException;
import com.omareldar.exceptions.IsValidUserException;

import java.util.Scanner;

/**
 * Klasa Igrac sadrzi podatke i funkcije Igraca
 * 
 * @author Omar Corbic, Eldar Mujezinovic
 *
 */
public class Igrac {

	static boolean isValid = false;
	static ArrayList<Igrac> igraci = new ArrayList<>();
	static ArrayList<String> imenaIgraca = new ArrayList<>();
	static Scanner input = new Scanner(System.in);

	private String nickname;
	private int brojBodova;
	private int rank;
	private boolean igrao;
	private boolean igraoJokerPolaPola = false;
	private boolean igraoJokerPozoviPrijatelja = false;
	private boolean igraoJokerPomocPublike = false;
	static int brojPrijavljenihIgraca;



	/**
	 * Default konstruktor
	 */
	public Igrac() {
	}

	/**
	 * Konstruise i inicijalizira Igraca, dodjeljuje mu nickname, bodove i rank
	 * objekat dodjeljuje u niz objekata "igraci" nickname dodjeljuje u niz
	 * Stringova "imenaIgraca"
	 * 
	 * @param ime
	 * @param bodovi
	 * @param rang
	 */
	public Igrac(String ime, int bodovi, int rang) {

		nickname = ime;
		brojBodova = bodovi;
		rank = rang;
		igrao = false;
		igraci.add(this);
		imenaIgraca.add(nickname);
		brojPrijavljenihIgraca++;

	}

	public static void promijeniIgraca() throws FileNotFoundException, IsValidUserException, IsValidNicknameException {
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
			System.out.println("0. Nazad");

			int i = -1;
			do {

				try {
					rbIgraca = input.nextInt();
					if (rbIgraca <= -1 || rbIgraca > Igrac.imenaIgraca.size()) {
						System.out.println("Unesite broj u razmaku od 1 do " + Igrac.imenaIgraca.size());
					} else if (rbIgraca == Igrac.imenaIgraca.indexOf(Milijunas.trenutniIgrac) + 1) {
						System.out.println("Ne mozete ponovo unijeti trenutnog igraca. Molimo unesite ponovo: ");
					} else if (rbIgraca == 0) {
						Milijunas.meni();
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

	public static boolean isValidNick(String nick) {

		if (nick.length() >= 3 && nick.length() <= 10) {
			return true;
		} else {
			return false;

		}
	}

	public static boolean isValidUser(String name) {

		for (int i = 0; i < igraci.size(); i++) {
			if (igraci.get(i).nickname.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public String getImeIgraca() {
		String name = input.nextLine();
		nickname = name;
		try {
			if (Igrac.isValidNick(name)) {

				if (!Igrac.isValidUser(name)) {
					return nickname;
				} else {
					throw new IsValidUserException(name);
				}
			} else {
				throw new IsValidNicknameException(name);
			}
		} catch (IsValidNicknameException e) {
			System.out.println(e.getMessage());
			getImeIgraca();
		} catch (IsValidUserException e) {
			System.out.println(e.getMessage());
			getImeIgraca();

		}
		return nickname;

	}

	public static void ispisIgraca() {
		if (!imenaIgraca.isEmpty()) {
			System.out.println("Broj prijavljenih igraca: " + brojPrijavljenihIgraca);
			System.out.println("Ucesnici Milijunasa: \n" + imenaIgraca.toString());
		} else {
			System.out.println("Nema prijavljenih igraca.");
		}
	}

	public static void rankLista() {
		if (!igraci.isEmpty()) {
			Map<String, Integer> scoreMap = new HashMap<>();
			for (int i = 0; i < igraci.size(); i++) {
				scoreMap.put(igraci.get(i).nickname, igraci.get(i).brojBodova);
			}

			List<Entry<String, Integer>> list = new LinkedList<>(scoreMap.entrySet());
			Collections.sort(list, new Comparator<Entry<String, Integer>>() {

				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return -o1.getValue().compareTo(o2.getValue());
				}

			});
			@SuppressWarnings("unused")
			int brojac = 1;
			System.out.println("Rank lista: \n| Nickname | Bodovi |");
			for (@SuppressWarnings("unused")
			Entry<String, Integer> item : list) {
				System.out.println(brojac + ". " + item.getKey() + "  \t" + item.getValue());
				brojac++;
			}
		} else {
			System.out.println("Nema prijavljenih igraca.");
		}

	}

	public static void povecajBrojBodovaIgracu(String name, int amount) {
		for (int i = 0; i < igraci.size(); i++) {
			if (igraci.get(i).nickname.equals(name)) {
				igraci.get(i).setBrojBodova(amount);
			}
		}
	}

	public static void ispisiDetaljeIgraca() {
		if (!igraci.isEmpty()) {
			azuriranjeRanka();

			for (int i = 0; i < igraci.size(); i++) {
				System.out.println(igraci.get(i).toString());
			}
		} else {
			System.out.println("Nema prijavljenih igraca.");
		}
		;
	}

	public static boolean jeIgrao(String name) {
		for (int i = 0; i < igraci.size(); i++) {
			if (igraci.get(i).nickname.equals(name)) {
				return igraci.get(i).isIgrao();
			}
		}
		return false;

	}

	public static void igracJeIgrao(String name) {
		for (int i = 0; i < igraci.size(); i++) {
			if (igraci.get(i).nickname.equals(name)) {
				igraci.get(i).setIgrao(true);
			}
		}
	}

	public static void azuriranjeRanka() {
		
		int brojIgracaSaViseBodova = 0;
		Igrac trenutniIgrac;
		for (int i = 0; i < igraci.size(); i++) {
			trenutniIgrac = igraci.get(i);

			for (int j = 0; j < igraci.size(); j++) {
				if (igraci.get(j).nickname.equals(trenutniIgrac.nickname)) {
					continue;
				} else if (trenutniIgrac.getBrojBodova() < igraci.get(j).getBrojBodova()) {
					brojIgracaSaViseBodova++;
				}
			}
			
			trenutniIgrac.setRank(1 + brojIgracaSaViseBodova);
			brojIgracaSaViseBodova = 0;
		}

	}
	
	
	
	@Override
	public String toString() {
		return "Igrac - Nickname = " + nickname + ", Bodovi = " + brojBodova + ", Rank = " + rank;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getBrojBodova() {
		return brojBodova;
	}

	public void setBrojBodova(int brojBodova) {
		this.brojBodova = brojBodova;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isIgrao() {
		return igrao;
	}

	public void setIgrao(boolean igrao) {
		this.igrao = igrao;
	}
	public boolean isIgraoJokerPolaPola() {
		return igraoJokerPolaPola;
	}

	public void setIgraoJokerPolaPola(boolean igraoJokerPolaPola) {
		this.igraoJokerPolaPola = igraoJokerPolaPola;
	}

	public boolean isIgraoJokerPozoviPrijatelja() {
		return igraoJokerPozoviPrijatelja;
	}

	public void setIgraoJokerPozoviPrijatelja(boolean igraoJokerPozoviPrijatelja) {
		this.igraoJokerPozoviPrijatelja = igraoJokerPozoviPrijatelja;
	}

	public boolean isIgraoJokerPomocPublike() {
		return igraoJokerPomocPublike;
	}

	public void setIgraoJokerPomocPublike(boolean igraoJokerPomocPublike) {
		this.igraoJokerPomocPublike = igraoJokerPomocPublike;
	}
}
