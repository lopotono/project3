package p3;

import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DuelR implements Mode {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public void run(Properties properties) {

		User gamer1 = new User();
		IA gamer2 = new IA();

		ArrayList<Integer> codeUser = gamer2.generateCode();

		System.out.println("\nSaisir la combinaison � faire deviner � IA");
		ArrayList<Integer> code = gamer1.getCode();
		logger.info("L'utilisateur a saisi une combinaison � faire deviner � IA.");

		System.out.print("Votre combinaison : ");
		for (int i = 0; i < 4; i++) {
			System.out.print(code.get(i));
		}

		int nombreEssais = Integer.parseInt(properties.getProperty("nombreEssais"));
		logger.info("Chargement des propri�t�s : "+"nombre d'essais : "+nombreEssais);

		System.out.println();
		if(properties.get("developerMode").equals("true")) {
			System.out.print("La combinaison de IA est : ");
			for (int i = 0; i < 4; i++) {
				System.out.print(codeUser.get(i));
			}
		}

		ArrayList<Integer> propositionIA = null;
		String resultUser = null;
		String resultIA = null;

		// lancement du jeu
		while (nombreEssais > 0) {

			//System.out.println("\nSaisir une proposition :");
			ArrayList<Integer> propositionUserDuel = gamer1.getCode();

			System.out.print("\nProposition user : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(propositionUserDuel.get(j));
			}
			System.out.print(" -> r�ponse : ");

			// v�rification de la proposition user
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
			// v�rification de la proposition IA
			System.out.print("\nProposition IA : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(propositionIA.get(j));
			}
			System.out.print(" -> r�ponse : ");

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
				System.out.println("BRAVO IA a gagn� !");
				logger.info("IA a gagn�.");
				nombreEssais = 0;
			} else if (resultUser.equals("====")) {
				System.out.println("BRAVO vous avez gagn� !");
				logger.info("User a gagn�.");
				nombreEssais = 0;
			}
			nombreEssais--;
		}
	}	
}