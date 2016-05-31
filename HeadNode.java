package assignment4;

public class HeadNode extends ChainObject {

	private int startLocation;
	private Character character;
	public HeadNode(Character c) {
		startLocation = -1;
		character = c;
	}

	public int find(String target){
		int x = matchCharacter(target,0,false);
		System.out.println(x); 
		return x;
	}

	@Override
	protected int matchCharacter(String target, int location, boolean bForceFind) {
		System.out.println("head"+ character+ location);
		if(location == (target.length() - 1)) return FAILURE;
		boolean found = false;
		for (int i = location; i < target.length(); i++) {
			if(character.compareTo(target.charAt(i)) == 0) { 
				startLocation = i;
				found = true;
				break;
			}
		}
		//check to see if there was another start character in target string
		System.out.println(found);
		if(!found) 
			startLocation = -1;
		else {
			System.out.println("in else");
			if(next.matchCharacter(target, startLocation+1, false) == -1){
				startLocation +=1;
				matchCharacter(target,startLocation,false);
			}
		}
		return startLocation;
	}

	/*public static void main(String args[]){
		HeadNode head = new HeadNode('c');
		CenterNode one = new CenterNode('*');
		CenterNode two = new CenterNode('*');
		CenterNode three = new CenterNode('t');
		TailNode tail = new TailNode(null);
		head.setNext(one);
		one.setNext(two);
		two.setNext(three);
		three.setNext(tail);
		tail.setNext(null);

		System.out.println(head.find("caucaat"));
	}*/

}
