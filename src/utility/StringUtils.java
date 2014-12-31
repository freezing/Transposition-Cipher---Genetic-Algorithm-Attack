package utility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	static Random r = new Random();
	static char[] alphabet;
	static {
		alphabet = new char[Character.getNumericValue('Z') - Character.getNumericValue('A') + 1];
		for (char letter = 'A'; letter != 'Z'; letter++) {
			alphabet[Character.getNumericValue(letter) - Character.getNumericValue('A')] = letter;
		}
		alphabet[Character.getNumericValue('Z') - Character.getNumericValue('A')] = 'Z';
	}
	
	public static char nextRandomChar() {
		return alphabet[r.nextInt(alphabet.length)];
	}
	
	public static void main(String[] args) {
		
	}

	public static List<String> extractNGrams(String plainText, int ngramLength) {
		List<String> ngrams = new LinkedList<String>();
		
		Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
		Matcher matcher = pattern.matcher(plainText);
		
		List<String> tokens = new ArrayList<String>();
		while (matcher.find()) {
			int beginIndex = matcher.start();
			int endIndex = matcher.end();
			tokens.add(plainText.substring(beginIndex, endIndex));
		}
		
		for (int i = 0; i < tokens.size() - ngramLength + 1; i++) {
			StringBuilder ngramString = new StringBuilder();
			for (int j = 0; j < ngramLength; j++) {
				ngramString.append(tokens.get(i + j));
				if (j < ngramLength - 1) {
					ngramString.append(" ");
				}
			}
			ngrams.add(ngramString.toString());
		}
		
		return ngrams;
	}
}
