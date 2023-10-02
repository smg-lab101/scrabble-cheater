package scrabbleCheaterDeluxe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;

/**
 * The ScrabbleCheaterDeluxe is a program that takes the input of a 
 * user and generates possible legal solutions for the game using a 
 * list with valid English words handled by the dictionary class. 
 * The class furthermore uses a permutation maker to generate all 
 * permutations possible with the input.
 * 
 * @author smg-lab101, vivigl, TahroO
 * @version 2023.10.01 2.0 by smg-lab101
 */

public class ScrabbleCheater {

	protected static ArrayList<String> twoLetter = new ArrayList<>();
	protected static ArrayList<String> threeLetter = new ArrayList<>();
	protected static ArrayList<String> fourLetter = new ArrayList<>();
	protected static ArrayList<String> fiveLetter = new ArrayList<>();
	protected static ArrayList<String> sixLetter = new ArrayList<>();
	protected static ArrayList<String> sevenLetter = new ArrayList<>();

	protected static ArrayList<ArrayList<String>> combinations = new ArrayList<>(
			Arrays.asList(twoLetter, threeLetter, fourLetter, fiveLetter, sixLetter, sevenLetter));

	protected static Dictionary dictionary;
	protected static PermutationMaker permutationMaker;
	protected static String inputString;
	protected static List<String> permutations;

	public static void main(String[] args) throws IOException {
		dictionary = new Dictionary("res/words.txt");

		ScrabbleCheater scrabbleCheater = new ScrabbleCheater();
		String inputString = scrabbleCheater.getInput();

		permutationMaker = new PermutationMaker(inputString);
		permutations = permutationMaker.generatePermutationStrings();

		generateAnswer(permutations);

	}

	private static void generateAnswer(List<String> substrings) {
		sortPermutationsByLenght();
		printNumberOfSolutions();
		printSortedSolutions();
	}

	private static void printSortedSolutions() {
		for (ArrayList<String> arrayList : combinations) {
			if (!arrayList.isEmpty()) {
				int wordLength = arrayList.get(0).length();

				System.out.println("");
				System.out.println("YOUR " + wordLength + " LETTER OPTIONS (" + arrayList.size() + "):");
			}
			for (String string : arrayList) {
				System.out.print(string + " *** ");
			}

			System.out.println("");
		}
		
	}

	private static void printNumberOfSolutions() {
		int numberOfOptions = 0;

		for (ArrayList<String> arrayList : combinations) {
			numberOfOptions += arrayList.size();
		}
		
		System.out.println("");
		System.out.println("Total matching words found: " + numberOfOptions);
		System.out.println("");
		
	}

	private static void sortPermutationsByLenght() {
		for (String substring : permutations) {
			if (dictionary.contains(substring)) {

				int length = substring.length();

				switch (length) {
				case 2:
					if (!twoLetter.contains(substring))
						twoLetter.add(substring);
					break;
				case 3:
					if (!threeLetter.contains(substring))
						threeLetter.add(substring);
					break;
				case 4:
					if (!fourLetter.contains(substring))
						fourLetter.add(substring);
					break;
				case 5:
					if (!fiveLetter.contains(substring))
						fiveLetter.add(substring);
					break;
				case 6:
					if (!sixLetter.contains(substring))
						sixLetter.add(substring);
					break;
				case 7:
					if (!sevenLetter.contains(substring))
						sevenLetter.add(substring);
					break;
				default:
					return;
				}
			}
		}
	}

	private String getInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to the Scrabble Cheater! No blanks allowed here! ");
		System.out.print("Enter the letters you are cheating with: ");
		String str = sc.next().toUpperCase();

		System.out.println("Solutions are being generated for " + str);
		sc.close();
		return str;
	}
}