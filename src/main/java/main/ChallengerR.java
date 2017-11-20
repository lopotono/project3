package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ChallengerR implements Mode {

	public void run() {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// g�n�rer la combinaison
		ArrayList<Integer> code = gamer2.generateCode();

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssais = Integer.parseInt(properties.getProperty("nombreEssais"));
		// TODO : R�cup�rer cette valeur dans un fichier de configuration.

		// Lancer le jeu
		while (nombreEssais > 0) {

			System.out.println("\nSaisir une combinaison � 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();

			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> r�ponse : ");

			// V�rifier la proposition
			String result = "";

			for (int i = 0; i < 4; i++) {
				if (code.get(i) == proposition.get(i)) {
					result += "=";
				} else if (code.get(i) < proposition.get(i)) {
					result += "-";
				} else
					result += "+";
			}
			System.out.println(result);
			// Afficher le retour pour la combinaison trouv�e
			if (result.equals("====")) {
				System.out.println("BRAVO c'est gagn� !");
				nombreEssais = 0;
			}
			nombreEssais--;

			if (nombreEssais == 0) {
				System.out.print("PERDU !!!!" + " La combinaison de l'IA �tait : ");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}
