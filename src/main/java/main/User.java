package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class User {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	public ArrayList<Integer> getCode() {

		Scanner scanner = new Scanner(System.in);
		String str = null;

		ArrayList<Integer> code = new ArrayList<Integer>();
		do {
			str = scanner.nextLine();
			System.out.println("\nSaisir une combinaison à 4 chiffres : ");
			logger.error("L'utilisateur n'a saisi 4 chiffres.");
			char[] tab = str.toCharArray();
			code.clear();
			for (int i = 0; i < str.length(); i++) {
				code.add(Integer.parseInt(Character.toString(tab[i])));
			}
		} while (str.length() != 4);
		return code;
	}

	public ArrayList<Integer> getCodeM() {

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		Scanner scanner = new Scanner(System.in);
		int nombreChiffres = Integer.parseInt(properties.getProperty("nombreChiffres"));

		ArrayList<Integer> code = new ArrayList<Integer>();
		String str = null;
		do {
			str = scanner.nextLine();
			System.out.println("\nSaisir une combinaison à " + nombreChiffres + " chiffres :");
			logger.error("L'utilisateur n'a pas saisi le nombre de chiffres demandé : "+nombreChiffres+" chiffres.");
			char[] tab = str.toCharArray();
			code.clear();
			for (int i = 0; i < str.length(); i++) {
				code.add(Integer.parseInt(Character.toString(tab[i])));
			}
		} while (str.length() != nombreChiffres);
		return code;
	}

	public ArrayList<Integer> getCode(ArrayList<Integer> previousCode, String resultUser) {
		if (previousCode == null && resultUser == null) {
			return getCode();
		}

		ArrayList<Integer> codeUser = new ArrayList<Integer>();

		char[] tabResultUser = resultUser.toCharArray();

		for (int i = 0; i < 4; i++) {
			if (tabResultUser[i] == '=') {
				codeUser.add(previousCode.get(i));
			} else if (tabResultUser[i] == '-') {
				int value = previousCode.get(i) - 1;
				codeUser.add(value);
			} else if (tabResultUser[i] == '+') {
				int value = previousCode.get(i) + 1;
				codeUser.add(value);
			}
		}
		return codeUser;
	}
}
