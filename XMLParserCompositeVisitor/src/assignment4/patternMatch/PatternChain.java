package assignment4.patternMatch;

/**
 * Abstract Class for the pattern object chain
 */
public abstract class PatternChain {

	protected PatternChain next;
	protected static final int FAILURE = -1;

	/**
	 * Abstract Method to see if there is a match for the 
	 * character in the location for the target string.
	 * @param target - target string
	 * @param location - check the char at this location
	 * @param isAsterix - if the previous char was a * 
	 * @returns - the current location passed on success
	 * 			  -1 on failure
	 */
	protected abstract int matchCharacter(String target,int location, boolean isAsterix);

	public PatternChain() {
		next = null;
	}

	protected void setNext(PatternChain next) {
		this.next = next;
	}
}
