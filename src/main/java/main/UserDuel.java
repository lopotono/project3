package main;

import java.util.ArrayList;

public class UserDuel {

	public ArrayList<Integer> getCode() {
		int valueUser = 0;
		ArrayList<Integer> codeUser = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {			
			if (codeUser.contains(valueUser)){
				codeUser.add(valueUser);
			}			
		}
		return codeUser;
	}
}