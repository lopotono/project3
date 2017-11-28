package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ChallengerM implements Mode {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public static void run(Properties properties) {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// générer une combinaison aléatoire
		ArrayList<Integer> code = gamer2.generateCode();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propriétés : "+"nombre d'essais : "+nombreEssaisM);
		
		if(properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < 4; i++) {
				System.out.print(code.get(i));
			}
		}

		while (nombreEssaisM > 0) {

			System.out.println("\nSaisir une combinaison à 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();
			logger.info("L'utilisateur a saisi une combinaison.");

			int bienPlace = 0;
			int present = 0;

			// vérification de la proposition
			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> réponse : ");

			// parcourir la proposition
			for (int i = 0; i < 4; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(bienPlace + " bien placé(s) " + present + " présent(s) ");
			logger.info("Affichage de la proposition User.");
			
			if (bienPlace == 4) {
				System.out.println("\nBRAVO c'est gagné !");
				logger.info("User a gagné.");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("\nPERDU !!!!" + " La combinaison de l'IA était : ");
				logger.info("User a perdu");
				logger.info("Affichage de la bonne combinaison.");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
