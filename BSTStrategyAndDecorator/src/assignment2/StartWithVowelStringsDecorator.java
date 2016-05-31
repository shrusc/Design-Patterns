package assignment2;

import java.util.*;

/**
 * Decorates the Iterator<String> to get the strings that start with a vowel.
 */
public class StartWithVowelStringsDecorator implements Iterator<String> {

	Iterator<String> getVowelStringsIterator;
	String nextString;
	boolean hasNextObject;

	public StartWithVowelStringsDecorator(Iterator<String> getVowelsStringIterator) {
		this.getVowelStringsIterator = getVowelsStringIterator;
		nextString = null;
		hasNextObject = false;
	}

	/**
	 * Set nextString to the next string starting with a vowel. 
	 * If there are no more objects satisfying the condition 
	 * then return false.Otherwise, return true.
	 * @return true if next element present else false
	 */
	private boolean setNextObject() {
		while(getVowelStringsIterator.hasNext()){
			String string = getVowelStringsIterator.next();
			char letter = string.toString().charAt(0);
			if(letter == 'A' || letter == 'E' || letter == 'I' ||
					letter == 'O' || letter == 'U' || letter == 'a' ||
					letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
				nextString = string;
				hasNextObject = true;
				return true;
			}
		}
		return false;
	}	 

	@Override
	public boolean hasNext() {
		if (hasNextObject == true) {
			return true;
		} 
		else {
			return setNextObject();
		}	
	}

	@Override
	public String next() {
		if (!hasNextObject  && !setNextObject()) {
			throw new NoSuchElementException();
		}
		hasNextObject = false;
		return nextString;
	}

}
