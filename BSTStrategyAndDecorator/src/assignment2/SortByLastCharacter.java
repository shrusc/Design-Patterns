package assignment2;

import java.util.Comparator;

/**
 * Class implementing strategy pattern. 
 * Defines the order of the strings based on the last character.
 */
public class SortByLastCharacter implements Comparator<String> {

	@Override
	public int compare(String arg0, String arg1) {
		String firstStringReverse = new StringBuilder(arg0).reverse().toString();
		String secondStringReverse = new StringBuilder(arg1).reverse().toString();
		return firstStringReverse.compareTo(secondStringReverse);

	}

}
