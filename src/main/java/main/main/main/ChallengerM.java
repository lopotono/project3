package main;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ChallengerM implements Mode {

	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

		UserM gamer1 = new UserM();
		IA gamer2 = new IA();

		// générer une combinaison aléatoire
		ArrayList<Integer> code = gamer2.generateCode();
		
		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propriétés : " + "nombre d'essais : " + nombreEssaisM);

		int nombreChiffres = Integer.parseInt(properties.getProperty("nombreChiffres"));
		logger.info("Chargement des propriétés : " + "nombre de chiffres : " + nombreChiffres);

		if (properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < nombreChiffres; i++) {
				System.out.print(code.get(i));
			}
		}

		while (nombreEssaisM > 0) {
						
			System.out.println("\nSaisir une combinaison à " + nombreChiffres + " chiffres :");
			ArrayList<Integer> proposition = gamer1.getCode();			
			logger.info("L'utilisateur a saisi une combinaison.");
												
			int bienPlace = 0;
			int present = 0;
			// vérification de la proposition
			System.out.print("Proposition : ");
			for (int j = 0; j < nombreChiffres; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> réponse : ");

			// parcourir la proposition
			for (int i = 0; i < nombreChiffres; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(bienPlace + " bien placé(s) " + present + " présent(s) ");
			logger.info("Affichage de la proposition User.");

			if (bienPlace == nombreChiffres) {
				System.out.println("\nBRAVO c'est gagné !");
				logger.info("User a gagné.");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("\nPERDU !!!!" + " La combinaison de l'IA était : ");
				logger.info("User a perdu");
				logger.info("Affichage de la bonne combinaison.");
				for (int i = 0; i < nombreChiffres; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}