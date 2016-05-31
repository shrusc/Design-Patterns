package assignment2;

import java.util.Comparator;

/**
 * Class implementing strategy pattern. 
 * Defines the order of the strings based on the 1st character.
 */
public class SortByFirstCharacter implements Comparator<String> {

	@Override
	public int compare(String arg0, String arg1) {
		return arg0.compareTo(arg1);

	}

}
