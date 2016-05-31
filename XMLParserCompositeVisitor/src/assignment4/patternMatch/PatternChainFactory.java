package assignment4.patternMatch;

/**
 * Factory Class which returns the appropriate pattern chain object
 */
public class PatternChainFactory {

	//method to get object for type of patternCharacter
	public PatternChain getChainObject(Character patternCharacter){	
		if(patternCharacter.compareTo('*') == 0)
			return new AsterixWildCharacter();
		else if(patternCharacter.compareTo('.') == 0)
			return new DotWildCharacter();
		else 
			return new PatternCharacter(patternCharacter);
	}
}