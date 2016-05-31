package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Decorates the Iterator<String> to capitalize the strings
 */
public class CapitalizeStringsDecorator implements Iterator<String> {

	Iterator<String> capitalizeStringsIterator;

	public CapitalizeStringsDecorator(Iterator<String> capitalizeStringsIterator) {
		this.capitalizeStringsIterator = capitalizeStringsIterator;
	}

	@Override
	public boolean hasNext() {
		return capitalizeStringsIterator.hasNext();
	}

	@Override
	public String next() {
		if(!hasNext()) throw new NoSuchElementException();
		return capitalizeStringsIterator.next().toUpperCase();
	}

}
