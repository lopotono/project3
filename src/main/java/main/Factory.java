package main;

public class Factory {

	public static Mode get(char game, char choice) {

		Mode mode = null;

		if (game == '1' && choice == '1') {
			mode = new ChallengerR();
		} else if (game == '1' && choice == '2') {
			mode = new DefenserR();
		} else if (game == '1' && choice == '3') {
			mode = new DuelR();
		} else if (game == '2' && choice == '1') {
			mode = new ChallengerM();
		} else if (game == '2' && choice == '2') {
			mode = new DefenserM();
		} else if (game == '2' && choice == '3') {
			mode = new DuelM();
		} 
		return mode;
	}
}
