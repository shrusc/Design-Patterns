package com.java.test.turtlepattern;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.java.turtlepattern.ParseTurtle;
import com.java.turtlepattern.TurtleCommand;
import com.java.turtlepattern.TurtleContext;
import com.java.turtlepattern.TurtleExpression;
import com.java.turtlepattern.TurtleInterpreterVisitor;
import com.java.turtlepattern.TurtleMoveVisitor;

public class TestTurtlePattern {
	private ParseTurtle parser;
	private ArrayList<ArrayList<TurtleExpression>> expressionList;
	// Create array objects with expected result
	private double[] xCoordValueList = {22.990381052151754,-17.32050809102244,72.32050807263676,-2.1410161220547783};
	private double[] yCoordValueList = {27.500000000888125,62.32050806703323,27.320508078240294,-24.95190529157248};
	private int[] degreeList = {30,90,0,240};
	private int[] distanceList = {45,85,85,165};
	private TurtleContext[] context = new TurtleContext[4];

	public TestTurtlePattern() {
		parser = new ParseTurtle();
		expressionList = new ArrayList<ArrayList<TurtleExpression>>();		
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		for(int index = 0; index < 4; index++){
			context[index] = new TurtleContext();
		}
		expressionList.add(parser.parseProgram("C:/files/turtle.txt"));
		expressionList.add(parser.parseProgram("C:/files/turtle_repeat.txt"));
		expressionList.add(parser.parseProgram("C:/files/turtle_variable_repeat.txt"));
		expressionList.add(parser.parseProgram("C:/files/turtle_nested_repeat.txt"));		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/*
	 * Test the interpreter by obtaining the list of expression objects 
	 * and evaluating them. 
	 */
	@Test
	public void testInterpreter(){		
		
		
		for(int listIndex = 0; listIndex < expressionList.size(); listIndex++){
			for(int index = 0; index < expressionList.get(listIndex).size(); index++){
				expressionList.get(listIndex).get(index).evaluate(context[listIndex]);
			}
			
			Point2D location = context[listIndex].getTurtle().location();
			
			//Compare the final location and direction with expected location
			assertEquals(location.getX(), xCoordValueList[listIndex],0.1);
			assertEquals(location.getY(), yCoordValueList[listIndex], 0.1);
			assertEquals(context[listIndex].getTurtle().direction(), degreeList[listIndex]);
		}		
		
	}
	
	/*
	 * Test the visitor and command pattern by obtaining the list of
	 * command objects by visiting interpreter list
	 * and executing them. 
	 */
	@Test
	public void testVisitor(){
		TurtleExpression turtleExpression;
		TurtleInterpreterVisitor[] visitor = new TurtleInterpreterVisitor[4];
		LinkedList<TurtleCommand> turtleCommandList;
		ListIterator<TurtleCommand> turtleIterator;
		
		for(int index = 0; index < 4; index++){
			context[index] = new TurtleContext();
			visitor[index] = new TurtleInterpreterVisitor(context[index]);	
		}
		
		for(int listIndex = 0; listIndex < expressionList.size(); listIndex++){
			for(int index = 0; index < expressionList.get(listIndex).size(); index++){
				turtleExpression = expressionList.get(listIndex).get(index);
				turtleExpression.accept(visitor[listIndex]);
			}
			turtleCommandList = (LinkedList<TurtleCommand>) visitor[listIndex].getCommandList();
			turtleIterator    = turtleCommandList.listIterator();
			
			while(turtleIterator.hasNext()){
				turtleIterator.next().execute();
			}
			
			Point2D location = context[listIndex].getTurtle().location();
			
			assertEquals(location.getX(), xCoordValueList[listIndex],0.1);
			assertEquals(location.getY(), yCoordValueList[listIndex], 0.1);
			assertEquals(context[listIndex].getTurtle().direction(), degreeList[listIndex]);
		}
				
	}
	

	@Test
	public void testCommandUndo(){

		TurtleExpression turtleExpression;
		TurtleInterpreterVisitor[] visitor = new TurtleInterpreterVisitor[4];
		LinkedList<TurtleCommand> turtleCommandList;
		ListIterator<TurtleCommand> turtleIterator;
		TurtleCommand currentCommand;
		int counter = 3;
		
		for(int index = 0; index < 4; index++){
			context[index] = new TurtleContext();
			visitor[index] = new TurtleInterpreterVisitor(context[index]);	
		}
		
		for(int listIndex = 0; listIndex < expressionList.size(); listIndex++){
			for(int index = 0; index < expressionList.get(listIndex).size(); index++){
				turtleExpression = expressionList.get(listIndex).get(index);
				turtleExpression.accept(visitor[listIndex]);
			}
			turtleCommandList = (LinkedList<TurtleCommand>) visitor[listIndex].getCommandList();
			turtleIterator    = turtleCommandList.listIterator();
			
			//Counter to stop the execution in middle.
			while(counter > 0){
				turtleIterator.next().execute();
				counter--;
			}
			
			currentCommand = turtleIterator.next();
			currentCommand.execute();
			
			//Save the location and direction after execute 
			Point2D location = context[listIndex].getTurtle().location();
			int degree = context[listIndex].getTurtle().direction();
			
			//Undo the  command
			currentCommand.undo();	
			//Redo the command
			currentCommand.execute();
			
			//Get the details after an undo and a redo
			Point2D locationAfterUndo = context[listIndex].getTurtle().location();
			int degreeAfterUndo = context[listIndex].getTurtle().direction();
			
			//Compare the two values.
			assertEquals(location.getX(), locationAfterUndo.getX(),0.1);
			assertEquals(location.getY(), locationAfterUndo.getY(), 0.1);
			assertEquals(degree, degreeAfterUndo);
		}
	}
	
	/*
	 * Test the move visitor by obtaining the list of
	 * command objects by visiting interpreter list
	 * and computing the distance moved by turtle. 
	 */
	@Test
	public void testDistanceMoved(){
		TurtleExpression turtleExpression;
		TurtleMoveVisitor[] visitor = new TurtleMoveVisitor[4];
		
		for(int index = 0; index < 4; index++){
			visitor[index] = new TurtleMoveVisitor(context[index]);	
		}
		
		for(int listIndex = 0; listIndex < expressionList.size(); listIndex++){
			for(int index = 0; index < expressionList.get(listIndex).size(); index++){
				turtleExpression = expressionList.get(listIndex).get(index);
				turtleExpression.accept(visitor[listIndex]);
			}
			
			assertEquals(visitor[listIndex].getDistanceMoved(), distanceList[listIndex]);
		}
		
	}
	


}
