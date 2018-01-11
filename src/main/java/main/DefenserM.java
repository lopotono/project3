package p3;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DefenserM implements Mode {

	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

		User gamer1 = new User();
		IA gamer2 = new IA();

		int nombreChiffres = Integer.parseInt(properties.getProperty("nombreChiffres"));
		logger.info("Chargement des propriétés : " + "nombre de chiffres : " + nombreChiffres);

		// Saisir une combinaison
		//System.out.println("\nSaisir une combinaison à " + nombreChiffres + " chiffres :");
		logger.info("L'utilisateur a saisi une combinaison.");

		ArrayList<Integer> code = gamer1.getCodeM();

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propriétés : " + "nombre d'essais : " + nombreEssaisM);

		ArrayList<Integer> proposition = null;

		// Lancement du jeu
		while (nombreEssaisM > 0) {

			int bienPlace = 0;
			int present = 0;

			proposition = gamer2.generateCode();

			System.out.print("\nProposition : ");
			for (int j = 0; j < nombreChiffres; j++) {
				System.out.print(proposition.get(j));
			}
			// Vérification de la proposition
			for (int i = 0; i < nombreChiffres; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(" -> réponse : " + bienPlace + " bien placé(s) " + present + " présent(s) ");
			logger.info("Affichage de la proposition IA.");
			System.out.println();

			if (bienPlace == nombreChiffres) {
				System.out.println("\nBRAVO c'est gagné !");
				logger.info("IA a gagné.");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("PERDU !!!!");
				logger.info("IA a perdu.");
			}
		}
	}
}