
package main;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

	public ArrayList<Integer> getCode() {

		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();

		char[] tab = str.toCharArray();
		ArrayList<Integer> code = new ArrayList<Integer>();
		code.add(Integer.parseInt(Character.toString(tab[0])));
		code.add(Integer.parseInt(Character.toString(tab[1])));
		code.add(Integer.parseInt(Character.toString(tab[2])));
		code.add(Integer.parseInt(Character.toString(tab[3])));

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
