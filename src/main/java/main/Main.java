package main;

import java.util.Properties;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {

	private static Logger logger = Logger.getLogger(Logger.class);

	public static void main(String[] args) {

		DOMConfigurator.configure("D://workspace/fr.projet3/src/main/resources/log4j.xml");

		new FileLog();
		Parametres param = new Parametres();
		Properties properties = param.getProperties();	
		
		if(args.length > 0) {
			if(args[0].equals(true))
			{
				properties.setProperty("DevelopperMode", "true");
			}
		}
				
		String game1 = "Recherche+/-";
		String game2 = "Mastermind";

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
					logger.error("L'utilisateur n'a pas saisi 1, 2, ou 3");

				if (game == '1') {
					System.out.print("Vous avez choisi le jeu " + game1 + "\n");
					logger.info("L'utilisateur a choisi le jeu " + game1 + "\n");
				} else if (game == '2') {
					System.out.println("Vous avez choisi le jeu " + game2 + "\n");
					logger.info("L'utilisateur a choisi le jeu " + game2 + "\n");								
				} else if (game == '3') {
					System.out.println("Fin du jeu");
					logger.warn("Fin du jeu.");
				} break;

			} while (game != '1' && game != '2' && game != '3');

			if (game == '1' || game == '2') {
				do {
					do {
						choice = ' ';
						System.out.println("CHOISIR LE MODE DE JEU:---------");
						System.out.println("---> 1 : Mode challenger -------");
						System.out.println("---> 2 : Mode défenseur --------");
						System.out.println("---> 3 : Mode duel--------------");
						Scanner sc = new Scanner(System.in);
						choice = sc.next().charAt(0);

						if (choice != '1' && choice != '2' && choice != '3')
							logger.error("L'utilisateur n'a pas saisi 1, 2 ou 3");

					} while (choice != '1' && choice != '2' && choice != '3');

					Mode modeGame = Factory.get(game, choice);

					modeGame.run(properties);
					
					do {
						System.out.println();
						System.out.println("Voulez-vous rejouer ? (o/n)");
						Scanner ret = new Scanner(System.in);
						result = ret.nextLine().charAt(0);
					} while (result != 'o' && result != 'n');

				} while (result == 'o');
			}
		} while (game != '3');

		System.out.println("A bientôt !");
	}
}
