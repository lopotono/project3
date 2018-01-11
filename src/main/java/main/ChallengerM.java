package p3;

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

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		logger.info("Chargement des propri�t�s : " + "nombre d'essais : " + nombreEssaisM);

		int nombreChiffres = Integer.parseInt(properties.getProperty("nombreChiffres"));
		logger.info("Chargement des propri�t�s : " + "nombre de chiffres : " + nombreChiffres);

		if (properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < nombreChiffres; i++) {
				System.out.print(code.get(i));
			}
		}

		while (nombreEssaisM > 0) {
			
			//System.out.println("\nSaisir une combinaison � " + nombreChiffres + " chiffres :");
			ArrayList<Integer> proposition = gamer1.getCodeM();
			logger.info("L'utilisateur a saisi une combinaison.");
						
			int bienPlace = 0;
			int present = 0;
			// v�rification de la proposition
			System.out.print("Proposition : ");
			for (int j = 0; j < nombreChiffres; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> r�ponse : ");

			// parcourir la proposition
			for (int i = 0; i < nombreChiffres; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(bienPlace + " bien plac�(s) " + present + " pr�sent(s) ");
			logger.info("Affichage de la proposition User.");

			if (bienPlace == nombreChiffres) {
				System.out.println("\nBRAVO c'est gagn� !");
				logger.info("User a gagn�.");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("\nPERDU !!!!" + " La combinaison de l'IA �tait : ");
				logger.info("User a perdu");
				logger.info("Affichage de la bonne combinaison.");
				for (int i = 0; i < nombreChiffres; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}
