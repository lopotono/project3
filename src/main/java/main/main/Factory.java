package main;

import org.apache.log4j.Logger;

public class Factory {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public static Mode get(char game, char choice) {
		
		Mode mode = null;
		String choiceC = "Mode challenger";
		String choiceD = "Mode d�fenseur";
		String choiceDu = "Mode duel";

		if (game == '1' && choice == '1') {
			System.out.println("Vous avez choisi " + choiceC);
			System.out.println("Vous devez trouver la combinaison secr�te de l'ordinateur.");
			logger.info("L'utilisateur a choisi le mode challenger.");
			mode = new ChallengerR();
		} else if (game == '1' && choice == '2') {
			System.out.println("Vous avez choisi " + choiceD);
			System.out.println("C'est � l'ordinateur de trouver votre combinaison secr�te.");
			logger.info("L'utilisateur a choisi le mode d�fenseur.");
			mode = new DefenserR();
		} else if (game == '1' && choice == '3') {
			System.out.println("Vous avez choisi " + choiceDu);
			System.out.println("L'ordinateur et vous jouez tour � tour, le premier � trouver la combinaison secr�te de l'autre a gagn�.");
			logger.info("L'utilisateur a choisi le mode duel.");
			mode = new DuelR();
		} else if (game == '2' && choice == '1') {
			System.out.println("Vous avez choisi " + choiceC);
			System.out.println("Vous devez trouver la combinaison secr�te de l'ordinateur.");
			logger.info("L'utilisateur a choisi le mode challenger.");
			mode = new ChallengerM();
		} else if (game == '2' && choice == '2') {
			System.out.println("Vous avez choisi " + choiceD);
			System.out.println("C'est � l'ordinateur de trouver votre combinaison secr�te.");
			logger.info("L'utilisateur a choisi le mode d�fenseur.");
			mode = new DefenserM();
		} else if (game == '2' && choice == '3') {
			System.out.println("Vous avez choisi " + choiceDu);
			System.out.println("L'ordinateur et vous jouez tour � tour, le premier � trouver la combinaison secr�te de l'autre a gagn�.");
			logger.info("L'utilisateur a choisi le mode duel.");
			mode = new DuelM();
		} 
		return mode;
	}
}
