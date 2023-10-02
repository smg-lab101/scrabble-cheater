package scrabbleCheaterDeluxe;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * The Dictionary class takes a textFile, stores its words in a hashtable
 * data structure and provides several statistical methods for evaluation.
 * 
 * @author smg-lab101, vivigl, TahroO
 * @version 2023.10.01 2.0 by smg-lab101
 */

public class Dictionary {
	protected LinkedList<String>[] hashtable;
	int wordCountinHashtable = 0;
	int listCount = 0;
	static int size = 1553; // tested number for ideal size for words.txt;
	protected BufferedReader bufferedReader;

	@SuppressWarnings("unchecked")
	public Dictionary(String allWordsFile) throws IOException {
		hashtable = new LinkedList[size];
		createDictionaryTable(allWordsFile);
	}

	private void createDictionaryTable(String allWordsFile) throws IOException {
		bufferedReader = new BufferedReader(new FileReader(allWordsFile));
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {

				insertWordtoHashtable(line);
				wordCountinHashtable++;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void insertWordtoHashtable(String line) {

		int index = getIndexWithHashFunction(normalize(line));
		if (hashtable[index] == null) {
			hashtable[index] = new LinkedList<>();
			listCount++;
		}
		hashtable[index].add(line);
	}

	public static String normalize(String word) {
		String normalized = word.toLowerCase();
		char[] characters = normalized.toCharArray();
		Arrays.sort(characters);
		return new String(characters);
	}

	public static int getIndexWithHashFunction(String string) {
		int hash = string.hashCode();

		int index = Math.abs(hash) % size;
		return index;
	}

	public boolean contains(String word) {
		int index = getIndexWithHashFunction(normalize(word));
		LinkedList<String> bucket = hashtable[index];
		return bucket.contains(word);
	}
	
	/////THE FOLLOWING METHODS ARE PURELY STATISTICAL AND ARE NOT USED FOR THE SCRABBLE CHEATER

	public void totalCollisionsInHashtable() {
		int total = 0;
		int i = 0;
		while (i < size) {
			if (hashtable[i] != null) {
				LinkedList<String> tmpList = hashtable[i];
				int mySize = tmpList.size();
				//System.out.println("" + mySize + " entries in List " + i);
				total = total + mySize - 1;
				i++;
			}
		}
		System.out.println("Total collisions in hashtable: " + total);
	}

	protected String[] lookUp(String word) {
		int count = 0;
		String[] scrabbleCheatList;
		int index = getIndexWithHashFunction(normalize(word));
		LinkedList<String> myLinkedlist = hashtable[index];
		
		if (myLinkedlist != null) {
			scrabbleCheatList = new String[myLinkedlist.size()];
			for (String entry : myLinkedlist) {
				if (isPermutation(word, entry)) {
					System.out.println("I Found: " + entry);
					scrabbleCheatList[count] = entry;
					count++;
				}
			}
			return scrabbleCheatList;
		} else {
			System.out.println("Nothing found for " + word);
		}
		return null;
	}
	
	public int longestCollsionChain() {
		int max = 0;
		for (LinkedList<String> entry : hashtable) {
			int mySize = entry.size();
			if (mySize > max) {
				max = mySize;
			}
		}
		return max;
	}
	
	protected boolean isPermutation(String wordOne, String wordTwo) {
		return (normalize(wordOne).equals(normalize(wordTwo)));
	}
	
	protected static String generateRandomString(int length) {
		String randomString = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = (char) random.nextInt(65, 91);
			randomString += c;
		}
		System.out.println("Random String is: " + randomString);
		return randomString;
	}

}
