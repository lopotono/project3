
package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ChallengerM implements Mode {

	public void run() {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// générer une combinaison aléatoire
		ArrayList<Integer> code = gamer2.generateCode();

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		// Déterminer le nombre limité d'essai

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		// TODO : Récupérer cette valeur dans un fichier de configuration.

		while (nombreEssaisM > 0) {

			System.out.println("\nSaisir une combinaison à 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();

			int bienPlace = 0;
			int present = 0;

			// vérification de la proposition");
			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> réponse : ");

			// Etape 1 : parcourir la proposition
			for (int i = 0; i < 4; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(bienPlace + " bien placé(s) " + present + " présent(s) ");

			if (bienPlace == 4) {
				System.out.println("\nBRAVO c'est gagné !");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("\nPERDU !!!!" + " La combinaison de l'IA était : ");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}
