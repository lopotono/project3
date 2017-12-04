package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DuelM implements Mode {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

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
		
		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propriétés : "+"nombre d'essais : "+nombreEssaisM);
		
		System.out.println();
		if(properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < 4; i++) {
				System.out.print(code.get(i));
			}
		}
		
		ArrayList<Integer> propositionIA = null;
		// Lancement du jeu
		while (nombreEssaisM > 0) {

			// proposition user
			System.out.println("\nSaisir une proposition à 4 chiffres :");
			ArrayList<Integer> propositionUser = gamer1.getCode();
			logger.info("L'utilisateur a saisi sa proposition.");

			int bienPlace = 0;
			int present = 0;

			// Parcourir la proposition
			for (int i = 0; i < 4; i++) {
				int valueUser = propositionUser.get(i);
				if (codeUser.get(i) == valueUser) {
					bienPlace++;
				} else if (codeUser.contains(valueUser)) {
					present++;
				}
			}
			System.out.print("\nProposition user: ");
			for (int j = 0; j < 4; j++) {
				System.out.print(propositionUser.get(j));
			}
			System.out.print(" -> réponse : " + bienPlace + " bien placé(s) " + present + " présent(s) ");
			logger.info("Affichage de la proposition User.");
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
			logger.info("Affichage de la proposition IA.");
			System.out.println();

			if (bienPlace == 4) {
				System.out.println("\nBRAVO vous avez gagné !");
				logger.info("User a gagné");
				nombreEssaisM = 0;
			} else if (bienPlaceIA == 4) {
				System.out.println("\nBRAVO IA a gagné !");
				logger.info("IA a perdu");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;
			
			if (nombreEssaisM == 0) {
				System.out.print("PERDU !!!!");				
			}
		}
	}	
}
