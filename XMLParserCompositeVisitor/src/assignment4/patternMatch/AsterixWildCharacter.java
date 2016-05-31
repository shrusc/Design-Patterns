package assignment4.patternMatch;

/**
 * Class representing the * wild character
 */

public class AsterixWildCharacter extends PatternChain {

	@Override
	protected int matchCharacter(String target, int location, boolean isAsterix) {
		if(location == (target.length())) 
			return FAILURE;
		else
			return next.matchCharacter(target, location, true);
	}

}
