package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ChallengerM implements Mode {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// g�n�rer une combinaison al�atoire
		ArrayList<Integer> code = gamer2.generateCode();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propri�t�s : "+"nombre d'essais : "+nombreEssaisM);
		
		if(properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < 4; i++) {
				System.out.print(code.get(i));
			}
		}

		while (nombreEssaisM > 0) {

			System.out.println("\nSaisir une combinaison � 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();
			logger.info("L'utilisateur a saisi une combinaison.");

			int bienPlace = 0;
			int present = 0;

			// v�rification de la proposition
			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> r�ponse : ");

			// parcourir la proposition
			for (int i = 0; i < 4; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(bienPlace + " bien plac�(s) " + present + " pr�sent(s) ");
			logger.info("Affichage de la proposition User.");
			
			if (bienPlace == 4) {
				System.out.println("\nBRAVO c'est gagn� !");
				logger.info("User a gagn�.");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("\nPERDU !!!!" + " La combinaison de l'IA �tait : ");
				logger.info("User a perdu");
				logger.info("Affichage de la bonne combinaison.");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}	
}
