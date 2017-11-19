package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class DefenserR implements Mode {

	public void run() {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// Etape 1 : saisir la combinaison
		System.out.println("\nSaisir une combinaison à 4 chiffres : ");

		ArrayList<Integer> code = gamer1.getCode();

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssais = Integer.parseInt(properties.getProperty("nombreEssais"));
		// TODO : Récupérer cette valeur dans un fichier de configuration.

		// Lancer le jeu
		ArrayList<Integer> proposition = null;
		String result = null;

		while (nombreEssais > 0) {

			// Etape 4 : Faire proposition
			proposition = gamer2.generateCode(proposition, result);

			// Etape 5 : Vérifier la proposition
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
			// Etape 6 : Afficher le retour (combinaison trouvé ou non")

			// System.out.println(result);
			if (result.equals("====")) {
				System.out.println("BRAVO c'est gagné !");
				nombreEssais = 0;
			}
			nombreEssais--;

			if (nombreEssais == 0) {
				System.out.print("PERDU !!!!" + " La combinaison de User était : ");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}
