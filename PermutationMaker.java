package scrabbleCheaterDeluxe;

import java.util.ArrayList;
import java.util.List;

/**
 * The permutation generator generates all possible permutations of an input
 * string. Determines all the Strings that are substrings in the sense that they
 * only contain letters from the given String, with multiples only up to the
 * number of multiples available.
 * 
 * @author smg-lab101, vivigl, TahroO
 * @version 2023.10.01 2.0 by smg-lab101
 */

public class PermutationMaker {

	private String inputString;

	public PermutationMaker(String inputString) {
		this.inputString = inputString;
	}

	public List<String> generatePermutationStrings() {
		List<String> permutationStrings = new ArrayList<>();
		generatePermutationStringsHelper("", inputString, permutationStrings);
		return permutationStrings;
	}

	private void generatePermutationStringsHelper(String prefix, String remaining, List<String> permutations) {
		if (!prefix.isEmpty()) {
			permutations.add(prefix);
		}
		if (remaining.isEmpty()) {
			return;
		}
		for (int i = 0; i < remaining.length(); i++) {
			char currentChar = remaining.charAt(i);
			String newPrefix = prefix + currentChar;

			String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);

			generatePermutationStringsHelper(newPrefix, newRemaining, permutations);
		}
	}
}