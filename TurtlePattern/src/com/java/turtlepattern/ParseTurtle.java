package com.java.turtlepattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseTurtle {
	private Scanner fileScanner;
    private String currentToken;
    
    	
	 /** Read next symbol/keyword.
     */
    String getNextToken() {
    	if(fileScanner.hasNext()){
            currentToken = fileScanner.next();
            return currentToken;
    	}
		return null;
    }
	
    /*
     * Parses the turtle program and returns a list of Turtle interpreter expression objects.
     */
	public ArrayList<TurtleExpression> parseProgram(String filePath) throws FileNotFoundException{		
		fileScanner =  new Scanner(new File(filePath));
		return parseStatementList();
	}
	
	/*
	 * Checks if the operand is a constant or variable and 
	 * returns a corresponding type of object
	 */
	public TurtleExpression getOperand(){
		String nextToken = getNextToken();
		if(nextToken.contains("$")){
			String variableName =  nextToken.substring(1);
			return new TurtleVariableExpression(variableName);
		}else{
			int value = Integer.parseInt(nextToken);
			return  new TurtleConstantExpression(value);
		}
	}
	
	/*
	 * Parses the program statement and return an object of TurtleExpression
	 */
	public TurtleExpression parseStatement(){
		
		if(currentToken.compareToIgnoreCase("turn") == 0){			
			return new TurtleTurnExpression(getOperand());				
		}else if(currentToken.compareToIgnoreCase("move") == 0){
			return new TurtleMoveExpression(getOperand());
		}else if(currentToken.compareToIgnoreCase("repeat") == 0){		
			//In case of repeat we need to create another list which is specific to that block
			// So we invoke parseStatementList() 
			return new TurtleRepeatExpression(getOperand(),parseStatementList());			
		}else if(currentToken.compareToIgnoreCase("penup") == 0){
			return new TurtlePenUpExpression();
		}else if(currentToken.compareToIgnoreCase("pendown") == 0){
			return new TurtlePenDownExpression();
		}else if(currentToken.contains("$")){
			String variableName =  currentToken.substring(1);
			getNextToken(); // Skip the '=' token
			int value = Integer.parseInt(getNextToken());
			return new TurtleAssignExpression(variableName, value);	
		}
		return null;
	}

	/*
	 * Iterates through the program to create a list of TurtleExpression objects.
	 */
	ArrayList<TurtleExpression> parseStatementList(){
		ArrayList<TurtleExpression> expressionList = new ArrayList<TurtleExpression>();
		String nextToken = getNextToken();
		while(nextToken != null){
			// Need this check to break the loop in case of repeat block
			 if((nextToken.compareToIgnoreCase("end") == 0))  
				 break;
				
			 expressionList.add(parseStatement());	         
			 nextToken = getNextToken();
		}
		return expressionList;            
    }
}

