package com.java.turtlepattern;

import java.util.Hashtable;

/*
 * Context class to store the common data which will be
 * used among the interpreter objects for evaluation
 */
public class TurtleContext {
	/*
	 * Has a hashtable to store the values
	 * and a turtle object which is used by all interpreter objects 
	 * to evaluate.
	 */
	private Hashtable<String,Integer> values;
	private Turtle turtle;
	
	public TurtleContext() {
		values = new Hashtable<String,Integer>();
		turtle = new Turtle();
	}

	public int getValue(String variableName) {  
		return values.get(variableName); 
	} 
	
	public void setValue(String variableName, int value) { 
		values.put(variableName, value); 
	}
	
	public void removeValue(String variableName){
		values.remove(variableName);
	}
	
	public Turtle getTurtle() {
		return turtle;
	} 

}
