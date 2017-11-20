package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class DefenserM implements Mode {

	public void run() {

		User gamer1 = new User();
		IA gamer2 = new IA();

		// Saisir une combinaison
		System.out.println("\nSaisir une combinaison � 4 chiffres : ");

		ArrayList<Integer> code = gamer1.getCode();

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		int nombreEssaisM = Integer.parseInt(properties.getProperty("nombreEssaisM"));
		// TODO : R�cup�rer cette valeur dans un fichier de configuration.
		ArrayList<Integer> proposition = null;

		// Lancement du jeu
		while (nombreEssaisM > 0) {

			int bienPlace = 0;
			int present = 0;

			proposition = gamer2.generateCode();

			System.out.print("\nProposition : ");
			for (int j = 0; j < 4; j++) {
				System.out.print(proposition.get(j));
			}
			// V�rification de la proposition
			for (int i = 0; i < 4; i++) {
				int value = proposition.get(i);
				if (code.get(i) == value) {
					bienPlace++;
				} else if (code.contains(value)) {
					present++;
				}
			}
			System.out.print(" -> r�ponse : " + bienPlace + " bien plac�(s) " + present + " pr�sent(s) ");
			System.out.println();

			if (bienPlace == 4) {
				System.out.println("\nBRAVO c'est gagn� !");
				nombreEssaisM = 0;
			}
			nombreEssaisM--;

			if (nombreEssaisM == 0) {
				System.out.print("PERDU !!!!" + " La combinaison de User �tait : ");
				for (int i = 0; i < 4; i++) {
					System.out.print(code.get(i));
				}
			}
		}
	}
}