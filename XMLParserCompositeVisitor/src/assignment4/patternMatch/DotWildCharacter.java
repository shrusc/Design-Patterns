package assignment4.patternMatch;

/**
 * Class representing the . wild character
 */
public class DotWildCharacter extends PatternChain {

	@Override
	protected int matchCharacter(String target, int location, boolean isAsterix) {
		if(location == (target.length())) 
			return FAILURE;
		else
			return next.matchCharacter(target, location + 1, isAsterix);
	}

}
