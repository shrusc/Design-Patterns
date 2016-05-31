package assignment4.patternMatch;

/**
 * Class which creates a pattern matcher chain
 */
public class Match {

	private PatternChain start = null;
	private static final int FAILURE = -1;

	public Match(String pattern) {
		PatternChainFactory chainFactory = new PatternChainFactory();
		//Initialize the start of the chain 
		PatternChain currentObject = chainFactory.getChainObject(pattern.charAt(0));
		start = currentObject;
		//Make the chain for the rest of the pattern
		for(int i =1; i<pattern.length(); i++) {
			PatternChain nextObject = chainFactory.getChainObject(pattern.charAt(i));
			currentObject.setNext(nextObject);
			currentObject = nextObject;
		}
		//Ending the chain with the TailObject. 
		currentObject.setNext(new PatternEnd());
	}

	/**
	 * Method which finds the startIndex of the 
	 * 1st match of the pattern 
	 */
	public int findFirstIn(String target) {
		for(int i=0; i<target.length(); i++){
			int matchFound = start.matchCharacter(target, i, false);
			if(matchFound != -1)
				return i;
		}
		return FAILURE;
	}

}
