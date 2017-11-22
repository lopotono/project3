package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DuelR implements Mode {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public void run() {

		User gamer1 = new User();
		IA gamer2 = new IA();

		ArrayList<Integer> codeUser = gamer2.generateCode();

		System.out.println("\nSaisir la combinaison à faire deviner à IA : ");
		ArrayList<Integer> code = gamer1.getCode();
		logger.info("L'utilisateur a saisi une combinaison à faire deviner à IA.");

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
		logger.info("Chargement des propriétés : "+"nombre d'essais : "+nombreEssais);

		String developerMode = properties.getProperty("developerMode");
		if (developerMode.equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < 4; i++) {
				System.out.print(code.get(i));
			}			
		}

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
			logger.info("Affichage de la proposition User.");
			
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
			logger.info("Affichage de la proposition IA.");
			
			if (resultIA.equals("====")) {
				System.out.println("BRAVO IA a gagné !");
				logger.info("IA a gagné.");
				nombreEssais = 0;
			} else if (resultUser.equals("====")) {
				System.out.println("BRAVO vous avez gagné !");
				logger.info("User a gagné.");
				nombreEssais = 0;
			}
			nombreEssais--;
		}
	}
}
