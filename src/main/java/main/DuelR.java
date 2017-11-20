package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class DuelR implements Mode {

	public void run() {

		User gamer1 = new User();
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

		int nombreEssais = Integer.parseInt(properties.getProperty("nombreEssais"));
		// TODO : Récupérer cette valeur dans un fichier de configuration.
		// nombre total d'essais

		ArrayList<Integer> propositionIA = null;
		String resultUser = null;
		String resultIA = null;

		// lancement du jeu
		while (nombreEssais > 0) {

			System.out.println("\nSaisir une proposition :");
			ArrayList<Integer> propositionUserDuel = gamer1.getCode();

			System.out.print("\nProposition user : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(propositionUserDuel.get(j));
			}
			System.out.print(" -> réponse : ");

			// vérification de la proposition user
			resultUser = "";
			for (int i = 0; i < 4; i++) {
				if (codeUser.get(i) == propositionUserDuel.get(i)) {
					resultUser += "=";
				} else if (codeUser.get(i) < propositionUserDuel.get(i)) {
					resultUser += "-";
				} else
					resultUser += "+";
			}
			System.out.println(resultUser);

			propositionIA = gamer2.generateCode(propositionIA, resultIA);
			// vérification de la proposition IA
			System.out.print("\nProposition IA : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(propositionIA.get(j));
			}
			System.out.print(" -> réponse : ");

			resultIA = "";
			for (int i = 0; i < 4; i++) {
				if (code.get(i) == propositionIA.get(i)) {
					resultIA += "=";
				} else if (code.get(i) < propositionIA.get(i)) {
					resultIA += "-";
				} else
					resultIA += "+";
			}
			System.out.println(resultIA);

			if (resultIA.equals("====")) {
				System.out.println("BRAVO IA a gagné !");
				nombreEssais = 0;
			} else if (resultUser.equals("====")) {
				System.out.println("BRAVO vous avez gagné !");
				nombreEssais = 0;
			}
			nombreEssais--;
		}
	}
}
