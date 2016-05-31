package assignment4.patternMatch;

/**
 * Class representing characters other than the wild character 
 */
public class PatternCharacter extends PatternChain {

	private Character character;

	public PatternCharacter(Character patternCharacter) {
		character = patternCharacter;
	}

	@Override
	protected int matchCharacter(String target, int location, boolean isAsterix) {
		if(!(location == (target.length()))) {
			//if the previous was a * wild character
			if(isAsterix) {
				for (int i = location; i < target.length(); i++){
					if(character.compareTo(target.charAt(i)) == 0)   
						return next.matchCharacter(target, i + 1, false);
				}
			}
			else if (character.compareTo(target.charAt(location)) == 0)
				return next.matchCharacter(target, location + 1, false);
		}	
		return FAILURE;
	}

}
