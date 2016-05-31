package com.java.turtlepattern;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * An iterator class to modify the command object list at run-time when repeat command is 
 * encountered.
 */


public class TurtleIterator implements ListIterator<TurtleCommand> {	

	private TurtleCommand 		           nextCommandObject;
	private boolean 			           hasNextObject; 
	private TurtleList<TurtleCommand>            repeatList;
	private TurtleList<TurtleCommand>			   commandList;
	private int 						   previousIndex;
	private int 						   nextIndex;
	
	/*
	 * Constructor to create the iterator.
	 * Take the command list as input.
	 */
	public TurtleIterator(TurtleList<TurtleCommand> commandList){
		this.commandList             =  commandList;
		nextCommandObject 			 = null;
		hasNextObject    			 = false;
		nextIndex 					 =  0;
		previousIndex				 =  0;
	}
	


	@Override
	public void add(TurtleCommand e) {
		commandList.add(previousIndex, e);		
	}

	private boolean setNextObject() {
		if(commandList.size() > nextIndex){			
			nextCommandObject = commandList.get(nextIndex);
			previousIndex = nextIndex;
			nextIndex++;
		
			hasNextObject = true;
		}
	
		return hasNextObject;
	}	 
	
	@Override
	public boolean hasNext() {
		if (hasNextObject == true) {
			return true;
		} else {
			return setNextObject();
		}	
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}

	@Override
	public TurtleCommand next() {
		
		if (hasNextObject == false) {
			if (setNextObject() == false) {
				throw new NoSuchElementException();
			}
        }
		
		/*
		 * If the next command object is of type TurtleRepeatCommand
		 * then call the visitor to get the list of commands in that block.
		 * Then replace that list at the place of repeat command.
		 */
		if(nextCommandObject instanceof TurtleRepeatCommand){
			repeatList = ((TurtleRepeatCommand) nextCommandObject).getCommandList();
			/* remove the repeat command. To facilitate in step by step execution
			 */
			remove(); 
			
			
			for(int index = 0; index < repeatList.size(); index++){
				add(repeatList.get(index));
				previousIndex++;
			}
			
			//Adjust the next and previous index
			nextIndex -= 1;
			previousIndex = nextIndex-1;
			setNextObject();
		}
		
        hasNextObject = false;
        
        return nextCommandObject;	
	}

	@Override
	public int nextIndex() {		
		return nextIndex;
	}

	@Override
	public TurtleCommand previous() {
		return commandList.get(previousIndex);
	}

	@Override
	public int previousIndex() {
		return previousIndex;
	}

	@Override
	public void remove() {
		commandList.remove(previousIndex);		
	}

	@Override
	public void set(TurtleCommand e) {
	}

}
