package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class UserM {

	public ArrayList<Integer> getCode() {

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur");
		}

		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		int nombreChiffres = Integer.parseInt(properties.getProperty("nombreChiffres"));

		do {
			char[] tab = str.toCharArray();

			ArrayList<Integer> code = new ArrayList<Integer>();
			for (int i = 0; i < nombreChiffres; i++) {
				code.add(Integer.parseInt(Character.toString(tab[i])));
			}
			return code;
		} while (str.length() != nombreChiffres);
	}
}
