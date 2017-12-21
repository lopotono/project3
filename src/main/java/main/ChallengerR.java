package p3;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ChallengerR implements Mode {

	private static Logger logger = Logger.getLogger(Logger.class);
	
	public void run(Properties properties) {
				
		User gamer1 = new User();
		IA gamer2 = new IA();
		
		// générer la combinaison
		ArrayList<Integer> code = gamer2.generateCode();
		
		int nombreEssais = Integer.parseInt(properties.getProperty("nombreEssais"));
		logger.info("Chargement des propriétés : " + "nombre d'essais : " + nombreEssais);
			
		if(properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < 4; i++) {
				System.out.print(code.get(i));
			}			
		} 						
		// Lancer le jeu
		while (nombreEssais > 0) {

			System.out.println("\nSaisir une combinaison à 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();
			logger.info("L'utilisateur a saisi une combinaison.");

			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> réponse : ");

			// Vérifier la proposition
			String result = "";

			for (int i = 0; i < 4; i++) {
				if (code.get(i) == proposition.get(i)) {
					result += "=";
				} else if (code.get(i) < proposition.get(i)) {
					result += "-";
				} else
					result += "+";
			}
			System.out.println(result);
			logger.info("Affichage de la proposition User.");
			// Afficher le retour pour la combinaison trouvée
			if (result.equals("====")) {
				System.out.println("BRAVO c'est gagné !");
				logger.info("L'utilisateur a trouvé la bonne réponse.");
				nombreEssais = 0;
			}
			nombreEssais--;

			if (nombreEssais == 0) {
				System.out.print("PERDU !!!!" + " La combinaison de l'IA était : ");
				logger.info("L'utilisateur a perdu.");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}		
}