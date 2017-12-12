package main;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DefenserM implements Mode {

	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

		UserM gamer1 = new UserM();
		IA gamer2 = new IA();

		int nombreChiffres = Integer.parseInt(properties.getProperty("nombreChiffres"));
		logger.info("Chargement des propri�t�s : " + "nombre de chiffres : " + nombreChiffres);

		// Saisir une combinaison
		System.out.println("\nSaisir une combinaison � " + nombreChiffres + " chiffres :");
		logger.info("L'utilisateur a saisi une combinaison.");

		ArrayList<Integer> code = gamer1.getCode();

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propri�t�s : " + "nombre d'essais : " + nombreEssaisM);

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
			// V�rification de la proposition
			for (int i = 0; i < nombreChiffres; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(" -> r�ponse : " + bienPlace + " bien plac�(s) " + present + " pr�sent(s) ");
			logger.info("Affichage de la proposition IA.");
			System.out.println();

			if (bienPlace == nombreChiffres) {
				System.out.println("\nBRAVO c'est gagn� !");
				logger.info("IA a gagn�.");
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