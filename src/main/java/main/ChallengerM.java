package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ChallengerM implements Mode {

	public void run() {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// g�n�rer une combinaison al�atoire
		ArrayList<Integer> code = gamer2.generateCode();

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		// D�terminer le nombre limit� d'essai

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		// TODO : R�cup�rer cette valeur dans un fichier de configuration.

		while (nombreEssaisM > 0) {

			System.out.println("\nSaisir une combinaison � 4 chiffres : ");
			ArrayList<Integer> proposition = gamer1.getCode();

			int bienPlace = 0;
			int present = 0;

			// v�rification de la proposition");
			System.out.print("Proposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			System.out.print(" -> r�ponse : ");

			// Etape 1 : parcourir la proposition
			for (int i = 0; i < 4; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(bienPlace + " bien plac�(s) " + present + " pr�sent(s) ");

			if (bienPlace == 4) {
				System.out.println("\nBRAVO c'est gagn� !");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("\nPERDU !!!!" + " La combinaison de l'IA �tait : ");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}
