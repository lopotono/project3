package main;

import java.util.ArrayList;
import java.util.Random;

public class IA {

	public ArrayList<Integer> generateCode() {

		Random r = new Random();

		ArrayList<Integer> code = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			int value;
			do {
				value = r.nextInt(10);
			} while (code.contains(value));
			code.add(value);
		}
		return code;
	}

	public ArrayList<Integer> generateCode(ArrayList<Integer> previousCode, String result) {

		if (previousCode == null && result == null) {
			return generateCode();
		}
		ArrayList<Integer> code = new ArrayList<Integer>();

		char[] tabResult = result.toCharArray();

		for (int i = 0; i < 4; i++) {
			Random rand = new Random();
			if (tabResult[i] == '=') {
				code.add(previousCode.get(i));
			} else if (tabResult[i] == '-') {
				// Entre 0 et previousCode.get(i) - 1
				int low = 0;
				int high = previousCode.get(i);
				int value = rand.nextInt(high - low) + low;
				code.add(value);

			} else if (tabResult[i] == '+') {
				// Entre previousCode.get(i) + 1 et 0
				int low = previousCode.get(i) + 1;
				int high = 10;
				int value = rand.nextInt(high - low) + low;
				code.add(value);
			}
		}
		return code;
	}
}
