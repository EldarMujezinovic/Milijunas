package com.omareldar.milijunas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Odgovori {
	static ArrayList<String> tacniOdgovori = new ArrayList<>();
	static ArrayList<String> sporedniOdgovori = new ArrayList<>();
	static Map<Character, String> ponudjeniOdgovori = new TreeMap<Character, String>();
	static int bodovi = 0;
	static String v;

	public static void loading() throws FileNotFoundException {
		try {
		File file = new File("Odgovori.txt");
		File file2 = new File("SporedniOdgovori.txt");
		v = file.getName();

		Scanner read = new Scanner(file);
		Scanner read2 = new Scanner(file2);

		while (read.hasNextLine())
			tacniOdgovori.add(read.nextLine());

		while (read2.hasNextLine())
			sporedniOdgovori.add(read2.nextLine());

		read.close();
		read2.close();
		} 
		catch(FileNotFoundException e) {
			System.err.println("Ne postoji file - " + v);
			e.getStackTrace();
		}

	}

	public static void ponudiOdgovore() {

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
		
		check();
		ponudjeniOdgovori.clear();

	}

	public static void check() throws NullPointerException {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String tempOdgovor = input.nextLine();
		int uslov = -1;
		while (uslov != 0)
			if (tempOdgovor.length() != 1) {
				System.out.println("Molimo vas unesite jedan od ponudenih odgovora(A, B, C, D): ");
				tempOdgovor = input.nextLine();
			} else {
				uslov = 0;
			}
		
		char odgovor = tempOdgovor.charAt(0); 
		
		int uslov1 = -1;
		while (uslov1 != 0) {
			odgovor = tempOdgovor.charAt(0);
			odgovor = Character.toUpperCase(odgovor); 

			if (Character.isLetter(odgovor) && ponudjeniOdgovori.containsKey(odgovor)) {
				if (ponudjeniOdgovori.get(odgovor).equals(tacniOdgovori.get(Pitanja.getIndex()))) {
					bodovi++;
					System.out.println("Tacan\n");
					uslov1 = 0;
				} else {
					System.out.println("Netacan\n");
					uslov1 = 0;
				}
			} else {
				System.out.println("Molimo vas unesite jedan od ponudenih odgovora(A, B, C, D): ");
				tempOdgovor = input.nextLine();

			}
			;

		}
		;
	}

}
