package com.omareldar.milijunas;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import com.omareldar.exceptions.IsValidNicknameException;
import com.omareldar.exceptions.IsValidUserException;
/**
 * Klasa Igra, extenda klasu Milijunas, sadrzi metodu za pocetak igre
 * @author Omar Corbic, Eldar Mujezinovic
 *
 */
public class Igra extends Milijunas {
	public static void zapocniIgru() throws FileNotFoundException, InputMismatchException, NullPointerException, IsValidUserException, IsValidNicknameException {

		if (userCreated) {

			if (!Igrac.jeIgrao(trenutniIgrac)) {
				System.out.println("Trenutni igrac: " + trenutniIgrac);
				for (int i = 0; i < 10; i++) {

					Pitanja.ucitajPitanje();
					Odgovori.ponudiOdgovore();

				}
				Igrac.povecajBrojBodovaIgracu(trenutniIgrac, Odgovori.bodovi);
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
	

}
