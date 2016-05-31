package com.java.turtlepattern;

import java.awt.geom.Point2D;

/* Turtle class implementing the basic turtle commands * 
 */
public class Turtle {
	/*
	 * Private data variables to store the location, direction 
	 * and penup state of turtle.
	 */
	private Point2D turtleLocation;
	private int turtleDirection;
	private boolean penIsUp;
	private final static double PI = 3.141592654;
	
	public Turtle(){
		penIsUp = false; //Start with turtle pen down
		turtleDirection = 0;		
		turtleLocation = new Point2D.Double(0,0);
	}
	
	public void penUp(){
		penIsUp = false;
	}
	
	public void penDown(){
		penIsUp = true;				
	}
	
	public boolean isPenUp(){
		return penIsUp;
	}
	
	public void turn(int degree){
		/*
		 * Increment the degree as per turtle movement.
		 * Check to make sure the value does not go beyond 360
		 * or below 0 degree.
		 */
		turtleDirection += degree;
		
		while(turtleDirection > 360){
			turtleDirection -= 360;
		}
		while(turtleDirection < 0){
			turtleDirection += 360;
		}
		System.out.println("Degrees: "+turtleDirection);
	}
	
	public void move(int distance){
		/*
		 * Calculate the new location of the turtle by moving along the specified
		 * direction and for a distance provided by user.
		 */
		double radians = PI*turtleDirection/180;
		double deltaY  = Math.sin(radians) * distance;
		double deltaX  = Math.cos(radians) * distance;  
		
		double newX    = turtleLocation.getX() + deltaX;
		double newY    = turtleLocation.getY() + deltaY;
	
		turtleLocation.setLocation(newX, newY);
	}
	
	public int direction(){
		return turtleDirection;
	}
	
	public Point2D location(){
		return turtleLocation;		
	}
	
}
