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
	
		String game1 = "Recherche+/-";
		String game2 = "Mastermind";
		String choiceC = "Mode challenger";
		String choiceD = "Mode défenseur";
		String choiceDu = "Mode duel";
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

				if (game == '1') {
					System.out.print("Vous avez choisi le jeu " + game1 + "\n");
					logger.info("L'utilisateur a choisi le jeu " + game1 + "\n");
				} else if (game == '2') {
					System.out.println("Vous avez choisi le jeu " + game2 + "\n");
					logger.info("L'utilisateur a choisi le jeu " + game2 + "\n");
				} else if (game == '3') {
					System.out.println("Fin du jeu");
					logger.warn("Fin du jeu.");
				}break;

			} while (game != '1' && game != '2' && game != '3');

			if (game == '1' || game == '2') {
				do {
					choice = ' ';
					System.out.println("CHOISIR LE MODE DE JEU:---------");
					System.out.println("---> 1 : Mode challenger -------");
					System.out.println("---> 2 : Mode défenseur --------");
					System.out.println("---> 3 : Mode duel--------------");
					Scanner sc = new Scanner(System.in);
					choice = sc.next().charAt(0);

					if (choice != '1' && choice != '2' && choice != '3' && choice != '4')
						logger.error("Vous devez taper 1, 2 ou 3");

					Mode modeGame = Factory.get(game, choice);					
							
					if (choice == '1' && game == '1') {
						System.out.println("Vous avez choisi " + choiceC);
						System.out.println("Vous devez trouver la combinaison secrète de l'ordinateur.");
						logger.info("L'utilisateur a choisi le mode challenger.");
						modeGame = new ChallengerR();
						
					} else if (choice == '2' && game == '1') {
						System.out.println("Vous avez choisi " + choiceD);
						System.out.println("C'est à l'ordinateur de trouver votre combinaison secrète.");
						logger.info("L'utilisateur a choisi le mode défenseur.");
						modeGame = new DefenserR();	

					} else if (choice == '3' && game == '1') {
						System.out.println("Vous avez choisi " + choiceDu);
						System.out.println("L'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné.");
						logger.info("L'utilisateur a choisi le mode duel.");
						modeGame = new DuelR();

					} else if (choice == '1' && game == '2') {
						System.out.println("Vous avez choisi " + choiceC);
						System.out.println("Vous devez trouver la combinaison secrète de l'ordinateur.");
						logger.info("L'utilisateur a choisi le mode challenger.");
						modeGame = new ChallengerM();

					} else if (choice == '2' && game == '2') {
						System.out.println("Vous avez choisi " + choiceD);
						System.out.println("C'est à l'ordinateur de trouver votre combinaison secrète.");
						logger.info("L'utilisateur a choisi le mode défenseur.");
						modeGame = new DefenserM();

					} else if (choice == '3' && game == '2') {
						System.out.println("Vous avez choisi " + choiceDu);
						System.out.println("L'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné.");
						logger.info("L'utilisateur a choisi le mode duel.");
						modeGame = new DuelM();						
					} 
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
