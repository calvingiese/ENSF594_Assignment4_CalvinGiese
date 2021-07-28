/**
 * Class Name: Stack
 * 
 * Used to build a stack of characters and allows adding or removing from the stack as well as checking if it's empty/full.
 * 
 * The main purpose of this class is to build a stack in the form of an array with the size that is passed to the class. 
 * A character can be pushed to the top of the stack and the top letter of the stack can be popped out. The class also allows
 * for checking if the stack is empty or full.
 * 
 * @author Calvin Giese
 * @version version 1.0
 * @since July 26, 2021
 *
 */

public class Stack {
	
	/**
	 * The largest size the stack can be filled to
	 */
	private int maxSize;
	
	/**
	 * The stack in array form that will be filled with characters
	 */
	private char[] stackValues;
	
	/**
	 * The top value in the stack
	 */
	private int top;
	
	/**
	 * Constructs the stack, setting the size, building an empty array and setting the top to -1 temporarily
	 * 
	 * @param s is the size of the stack
	 */
	public Stack(int s) {
		
		maxSize = s;
		stackValues = new char[maxSize];
		top = -1;
	}
	
	/**
	 * Method used to add a character to the stack
	 * 
	 * @param j is the character to add
	 */
	public void push(char j) {
		stackValues[++top] = j;
	}
	
	/**
	 * Method used to retrieve the top value in the stack
	 * 
	 * @return the top character
	 */
	public char pop() {
		return stackValues[top--];
	}
	
	/**
	 * Method used to determine if the stack is empty
	 * 
	 * @return true if empty and false if not
	 */
	public boolean isEmpty() {
		return(top == -1);
	}
	
	/**
	 * Method used to determine if the stack is full
	 * 
	 * @return true if full and false if not
	 */
	public boolean isFull() {
		return(top == maxSize - 1);
	}
	
	
}