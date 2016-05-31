package assignment4;

public class CenterNode extends ChainObject{

	protected Character character;
	public CenterNode(Character c) {
		character = c;
	}

	protected int matchCharacter(String target,int location, boolean bForceFind) {
		System.out.println(target.length());
		System.out.println(character+" "+location);
		if(location == (target.length())) return FAILURE;
			if(bForceFind) {
				if(character.compareTo('*') == 0) 
					return next.matchCharacter(target, location, true);
				else {
					for (int i = location; i < target.length(); i++){
						if(character.compareTo(target.charAt(i)) == 0)   
							return next.matchCharacter(target, i, false);
					}
				}
				// if success - findFirstIn(target, location + 1, false)
				// if failure - return -1
			}
			else if (character.compareTo('.') == 0)
				return next.matchCharacter(target, location + 1, false);
			else if(character.compareTo('*') == 0)
				return next.matchCharacter(target, location, true);
			else if (character.compareTo(target.charAt(location)) == 0)
					return next.matchCharacter(target, location + 1, false);
		return FAILURE;
	}
}
