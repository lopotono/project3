package p3;

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
			System.out.println("\nIl faut saisir une combinaison à 4 chiffres : ");
			logger.error("L'utilisateur n'a pas saisi 4 chiffres.");
			char[] tab = str.toCharArray();
			code.clear();
			for (int i = 0; i < str.length(); i++) {
				code.add(Integer.parseInt(Character.toString(tab[i])));
			}
		} while (str.length() != 4);
		return code;
	}

	public ArrayList<Integer> getCodeM() {

		Parametres param = new Parametres();
		Properties properties = param.getProperties();
		
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
		
		Parametres param = new Parametres();
		Properties properties = param.getProperties();

		ArrayList<Integer> codeUser = new ArrayList<Integer>();
		
		int nombreCases = Integer.parseInt(properties.getProperty("nombreCases"));

		char[] tabResultUser = resultUser.toCharArray();

		for (int i = 0; i < nombreCases; i++) {
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