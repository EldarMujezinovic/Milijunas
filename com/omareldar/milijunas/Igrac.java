package com.omareldar.milijunas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Igrac {

	static boolean isValid = false;
	static ArrayList<Igrac> igraci = new ArrayList<>();
	static ArrayList<String> imenaIgraca = new ArrayList<>();

	private String nickname;
	private int brojBodova;
	private int rank;
	private boolean igrao;

	public Igrac() {

	}

	public Igrac(String ime, int bodovi, int rang) {
		nickname = ime;
		brojBodova = bodovi;
		rank = rang;
		igrao = false;
		igraci.add(this);
	}

	public static boolean isValidNick(String nick) {

		if (nick.length() >= 3 && nick.length() <= 10) {
			return true;
		} else {
			return false;

		}
	}

	public static boolean userIsValid(String name) {
		for (int i = 0; i < igraci.size(); i++) {
			if (igraci.get(i).nickname.equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static void ispisIgraca() {
		if (!imenaIgraca.isEmpty())
			System.out.println("Ucesnici Milijunasa: \n" + imenaIgraca.toString());
		else
			System.out.println("Nema prijavljenih igraca.");
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
			int brojac = 1;
			System.out.println("Rank lista: \n| Nickname | Bodovi |");
			for (Entry<String, Integer> item : list) {
				System.out.println(brojac + ". " + item.getKey() + "  \t" + item.getValue());
				brojac++;
			}
		} else {
			System.out.println("Nema prijavljenih igraca.");
		}

	}



	public static void increaseUserScore(String name, int amount) {
		for (int i = 0; i < igraci.size(); i++) {
			if (igraci.get(i).nickname.equals(name)) {
				igraci.get(i).setBrojBodova(amount);
			}
		}
	}

	public static void printUserDetails() {
		if (!igraci.isEmpty()) {
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

}
