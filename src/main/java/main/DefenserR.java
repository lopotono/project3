package main;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DefenserR implements Mode {

	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// Saisir la combinaison
		System.out.println("\nSaisir une combinaison à 4 chiffres : ");
		logger.info("L'utilisateur a saisi une combinaison.");

		ArrayList<Integer> code = gamer1.getCode();

		int nombreEssais = Integer.parseInt(properties.getProperty("nombreEssais"));
		logger.info("Chargement des propriétés : " + "nombre d'essais : " + nombreEssais);

		// Lancer le jeu
		ArrayList<Integer> proposition = null;
		String result = null;

		while (nombreEssais > 0) {

			// Faire une proposition
			proposition = gamer2.generateCode(proposition, result);

			// Vérifier la proposition
			result = "";
			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> réponse : ");

			for (int i = 0; i < 4; i++) {
				if (code.get(i) == proposition.get(i)) {
					result += "=";
				} else if (code.get(i) < proposition.get(i)) {
					result += "-";
				} else
					result += "+";
			}
			System.out.println(result);
			logger.info("Affichage de la proposition IA.");
			// Afficher le retour (combinaison trouvé ou non)

			if (result.equals("====")) {
				System.out.println("BRAVO c'est gagné !");
				logger.info("IA a gagné.");
				nombreEssais = 0;
			}
			nombreEssais--;

			if (nombreEssais == 0) {
				System.out.print("PERDU !!!!");
				logger.info("IA a perdu.");
			}
		}
	}
}
