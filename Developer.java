
package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.xml.DOMConfigurator;

public class Developer implements Mode {

	private static Logger logger = Logger.getLogger(Logger.class.getName());

	public void run() {

		DOMConfigurator.configure("D://workspace/fr.projet3/src/main/resources/log4j.xml");
		Logger logRoot = Logger.getRootLogger();
		ConsoleAppender co = new ConsoleAppender();
		co.setName("console");
		co.setLayout(new SimpleLayout());
		co.activateOptions();
		logRoot.addAppender(co);

		User gamer1 = new User();
		IA gamer2 = new IA();

		// générer la combinaison
		ArrayList<Integer> code = gamer2.generateCode();

		int essais = 0;
		logger.info("L'utilisateur doit saisir le nombre d'essais :");
		Scanner sc = new Scanner(System.in);
		essais = sc.nextInt();

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(new File("D://workspace/fr.projet3/src/main/resources/conf.properties"));
			out.write(essais);
			out.close();

			logger.info("Paramètre sauvegardé !");

		} catch (IOException e) {
			System.out.println("Erreur");
		}
		// Affichage de la solution au début du programme
		logger.info("La combinaison de l'IA est : ");
		for (int i = 0; i < 4; i++) {
			System.out.print(code.get(i));
		}
		// Lancer le jeu
		while (essais > 0) {

			System.out.println();
			logger.info("Le programme demande à l'utilisateur de saisir une combinaison à 4 chiffres.");
			System.out.println("\nSaisir une combinaison à 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();

			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> réponse : ");

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

			logger.info("Vérification de la proposition de l'utilisateur.");

			if (result.equals("====")) {
				logger.info("L'utilisateur a trouvé la bonne réponse.");
				essais = 0;
			}
			essais--;

			if (essais == 0) {
				logger.info("L'utilisateur n'a pas trouvé la bonne réponse.");
			}
		}
	}
}
