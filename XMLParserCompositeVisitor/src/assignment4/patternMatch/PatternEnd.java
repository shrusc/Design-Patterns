package assignment4.patternMatch;

/**
 * Class representing the tail of the pattern chain 
 */
public class PatternEnd extends PatternChain {

	@Override
	protected int matchCharacter(String target, int location, boolean bForceFind) {
		//if the control reaches tail its a success, so just return location
		return location;
	}

}
