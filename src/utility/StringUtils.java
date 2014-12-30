package utility;

import java.util.Random;

public class StringUtils {
	static Random r = new Random();
	static char[] alphabet;
	static {
		alphabet = new char[Character.getNumericValue('Z') - Character.getNumericValue('A') + 1];
		for (char letter = 'A'; letter != 'Z'; letter++) {
			alphabet[Character.getNumericValue(letter) - Character.getNumericValue('A')] = letter;
		}
		alphabet[Character.getNumericValue('Z') - Character.getNumericValue('A')] = 'Z';
		
		for (int i = 0; i < alphabet.length; i++) {
			System.out.println(alphabet[i]);
		}
	}
	
	public static char nextRandomChar() {
		return alphabet[r.nextInt(alphabet.length)];
	}
	
	public static void main(String[] args) {
		
	}
}
