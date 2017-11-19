
package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class DuelM implements Mode {

	public void run() {

		UserDuel gamer1 = new UserDuel();
		IA gamer2 = new IA();

		ArrayList<Integer> codeUser = gamer2.generateCode();

		System.out.println("\nSaisir la combinaison à faire deviner à IA : ");
		ArrayList<Integer> code = gamer1.getCode();

		System.out.print("Votre combinaison : ");
		for (int i = 0; i < 4; i++) {
			System.out.print(code.get(i));
		}

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		// TODO : Récupérer cette valeur dans un fichier de configuration.
		// nombre d'essais
		ArrayList<Integer> propositionIA = null;
		// Lancement du jeu
		while (nombreEssaisM > 0) {

			// proposition user
			System.out.println("\nSaisir une proposition à 4 chiffres :");
			ArrayList<Integer> propositionUser = gamer1.getCode();

			int bienPlace = 0;
			int present = 0;

			System.out.print("\nProposition user: ");
			for (int j = 0; j < 4; j++) {
				System.out.print(propositionUser.get(j));
			}
			System.out.print(" -> réponse : " + bienPlace + " bien placé(s) " + present + " présent(s) ");

			// Parcourir la proposition
			for (int i = 0; i < 4; i++) {
				int valueUser = propositionUser.get(i);
				if (codeUser.get(i) == valueUser) {
					bienPlace++;
				} else if (codeUser.contains(valueUser)) {
					present++;
				}
			}
			System.out.println();

			int bienPlaceIA = 0;
			int presentIA = 0;

			propositionIA = gamer2.generateCode();
			// Vérification de la proposition
			// parcourir la proposition
			for (int j = 0; j < 4; j++) {
				int value = propositionIA.get(j);
				if (code.get(j) == value) {
					bienPlaceIA++;
				} else if (code.contains(value)) {
					presentIA++;
				}
			}
			System.out.print("\nProposition IA: ");
			for (int i = 0; i < 4; i++) {
				System.out.print(propositionIA.get(i));
			}

			System.out.print(" -> réponse : " + bienPlaceIA + " bien placé(s) " + presentIA + " présent(s) ");
			System.out.println();

			if (bienPlace == 4) {
				System.out.println("\nBRAVO vous avez gagné !");
				nombreEssaisM = 0;
			} else if (bienPlaceIA == 4) {
				System.out.println("\nBRAVO IA a gagné !");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;
		}
	}
}
