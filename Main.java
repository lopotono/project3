
package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {

	private static Logger logger = Logger.getLogger(Logger.class.getName());

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMConfigurator.configure("D://workspace/fr.projet3/src/main/resources/log4j.xml");
		Logger logRoot = Logger.getRootLogger();
		ConsoleAppender co = new ConsoleAppender();
		co.setName("console");
		co.setLayout(new SimpleLayout());
		co.activateOptions();
		logRoot.addAppender(co);

		Properties properties = new Properties();
		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			logger.error("Erreur de chargement du fichier de configuration !");
		}

		System.out.println("JEU RECHERCHE+/-");
		System.out.print("Nombre de cases de la combinaison secrète : ");
		System.out.println(properties.getProperty("nombreCases", "defaultnombreCases"));
		System.out.print("Nombre d'essais : ");
		System.out.println(properties.getProperty("nombreEssais", "defaultnombreEssais"));
		System.out.println();
		System.out.println("JEU MASTERMIND");
		System.out.print("Nombre d'essais : ");
		System.out.println(properties.getProperty("nombreEssaisM", "defaultnombreEssais"));
		System.out.print("Nombre de chiffres utilisables : ");
		System.out.println(properties.getProperty("nombreChiffres", "defaultnombreChiffres"));

		System.out.println();

		String game1 = "Recherche+/-";
		String game2 = "Mastermind";
		String choiceC = "Mode challenger";
		String choiceD = "Mode défenseur";
		String choiceDu = "Mode duel";
		String choiceDev = "Mode développeur";
		char result = ' ', game = ' ', choice = ' ';

		logger.info("Lancement des jeux");
		System.out.println();

		do {
			do {
				game = ' ';
				System.out.println("------------CHOISIR LE JEU------------");
				System.out.println("---->Taper 1 pour jouer à Recherche+/-");
				System.out.println("---->Taper 2 pour jouer à Mastermind");
				System.out.println("---->Taper 3 pour quitter\n");
				Scanner sc = new Scanner(System.in);
				game = sc.next().charAt(0);

				if (game != '1' && game != '2' && game != '3')
					logger.error("Vous devez taper 1, 2 ou 3");

				if (game == '1')
					logger.info("Vous avez choisi le jeu " + game1 + "\n");
				else if (game == '2')
					logger.info("Vous avez choisi le jeu " + game2 + "\n");
				else if (game == '3')
					logger.warn("Fin du jeu.");
				break;

			} while (game != '1' && game != '2' && game != '3');

			if (game == '1' || game == '2') {
				do {
					choice = ' ';
					System.out.println("CHOISIR LE MODE DE JEU:---------");
					System.out.println("---> 1 : Mode challenger -------");
					System.out.println("---> 2 : Mode défenseur --------");
					System.out.println("---> 3 : Mode duel--------------");
					System.out.println("---> 4 : Mode développeur-------");
					Scanner sc = new Scanner(System.in);
					choice = sc.next().charAt(0);

					if (choice != '1' && choice != '2' && choice != '3' && choice != '4')
						logger.error("Vous devez taper 1, 2, 3 ou 4");

					Mode modeGame = Factory.get(game, choice);

					if (choice == '1' && game == '1') {
						logger.info("Vous avez choisi " + choiceC);
						logger.info("Vous devez trouver la combinaison secrète de l'ordinateur.");
						modeGame = new ChallengerR();

					} else if (choice == '2' && game == '1') {
						logger.info("Vous avez choisi " + choiceD);
						logger.info("C'est à l'ordinateur de trouver votre combinaison secrète.");
						modeGame = new DefenserR();

					} else if (choice == '3' && game == '1') {
						logger.info("Vous avez choisi " + choiceDu);
						logger.info(
								"L'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné.");
						modeGame = new DuelR();

					} else if (choice == '1' && game == '2') {
						logger.info("Vous avez choisi " + choiceC);
						logger.info("Vous devez trouver la combinaison secrète de l'ordinateur.");
						modeGame = new ChallengerM();

					} else if (choice == '2' && game == '2') {
						logger.info("Vous avez choisi " + choiceD);
						logger.info("C'est à l'ordinateur de trouver votre combinaison secrète.");
						modeGame = new DefenserM();

					} else if (choice == '3' && game == '2') {
						logger.info("Vous avez choisi " + choiceDu);
						logger.info(
								"L'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné.");
						modeGame = new DuelM();

					} else if (choice == '4' && game == '1') {
						logger.info("Choix du mode : " + choiceDev);
						modeGame = new Developer();
					}

					modeGame.run();

					do {
						System.out.println();
						logger.info("Voulez-vous rejouer ? (o/n)");
						Scanner ret = new Scanner(System.in);
						result = ret.nextLine().charAt(0);
					} while (result != 'o' && result != 'n');

				} while (result == 'o');
			}
		} while (game != '3');

		System.out.println("A bientôt !");
	}
}
