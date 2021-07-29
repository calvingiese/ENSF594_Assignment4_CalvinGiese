/**
 * Class Name: ConverterApp
 * 
 * Constructs a new CalculatorApp CLI that uses determines the correct output for mathematical expressions in postfix format.
 * 
 * The main purpose of this class is to build a stack from the user's entry and perform the calculations using postfix logic
 * and then print the results for the user to see.
 * 
 * @author Calvin Giese
 * @version version 1.0
 * @since July 27, 2021
 *
 */

public class CalculatorApp {
	
	/**
	 * reader will allow for command line interactions between the user and the program.
	 */
	private KeyboardReader reader;

	/**
	 * Constructs the reader of object type KeyboardReader
	 */
	public CalculatorApp() {
		reader = new KeyboardReader();
	}
	
	/**
	 * This method prompts the user to enter a mathematical expression in infix format and displays it in postfix format.
	 * 
	 */
	public void run() {
		
		// Beginning of user interface
		reader.display('\n' + "        Postfix Calculator Program        " + '\n');
		
		int exit = 0; // Integer used to switch on when the while loop should exit
		
		// Continues to run program until the user opts to exit
		while(exit == 0) {
			
			// Prompts user to enter their mathematical expression to be converted (postfix format)
			reader.prompt('\n' + "Please enter an postfix mathematical expression to calculate or type 'EXIT' to end program: ");
			String entry = reader.getKeyboardInput();
			
			// Declares variables and indicators to be used later
			String valid = "invalid";
			String output = null;
			int opCounter = 0;
			
			// Leaves if user types 'EXIT'
			if(entry.equals("EXIT")) {
				exit = 1;
				break;
			}
			
			// Applies algorithm to entries with at least 3 characters
			else if(entry.length() > 2){
				
				// Builds stack with and adds the user's entry to it, one by one starting from the last character
				int length = entry.length();
				Stack calcStack = new Stack(length);
				for(int i = length - 1; i > - 1; i--) {
					String character = entry.charAt(i) + "";
					calcStack.push(character);
					if(opValue(character) > 0) {
						opCounter++;
					}
				}
				
				// Valid expression if the operator count fits the entry length
				if(length / 2 == opCounter) {
					valid = "valid";
				}
				
				// Builds new stack that will be used to contain the results
				Stack newStack = new Stack(length);
	
				// Iterates through the first stack and pops out the elements, pushes them if they're numbers and calculates if it's an operator
				for(int i = 0; i < length; i++) {
					
					String currentEntry = calcStack.pop();
					
					if(opValue(currentEntry) < 1) {
						if(Integer.parseInt(currentEntry) > -1) {
							newStack.push(currentEntry);
						}
					}
					else if(opValue(currentEntry) > 0 && newStack.isEmpty() != true) {
						int secondVal = Integer.parseInt(newStack.pop());
						int firstVal = Integer.parseInt(newStack.pop());
						int res = calc(firstVal, secondVal, currentEntry);
						String result = res + "";
						newStack.push(result);
					}				
				}
				
				// Fills output string with the resulting calculation in the stack once all operations have been completed
				output = newStack.pop();
				
			// Displays whether or not the entry was valid
			reader.display('\n' + "The expression entered was " + valid + '\n');
			
			// Displays results if the expression entered was valid
			if(valid == "valid") {
				reader.display('\n' + "The infix expression entered was: " + entry);
				reader.display('\n' + "The converted postfix expression is: " + output + '\n');
				}
			}
		}
	}
	
	/**
	 * Method used to perform operations on two integers. Converts the operator from string to char and then calculates.
	 * 
	 * @param a is the first operand
	 * @param b is the second operand
	 * @param s is the operator in string format
	 * @return the result of the calculation or -1 if an invalid operator was passed
	 */
	private int calc(int a, int b, String s) {
		char c = s.charAt(0);
		int result = 0;
		if(c == '+') {
			result = a + b;
		}
		else if(c == '-') {
			result = a - b;
		}
		else if(c == '*') {
			result = a * b;
		}
		else if(c == '/') {
			result = a / b;
		}
		else {
			result = -1;
		}
		return result;
	}
	
	/**
	 * Method used to determine the precedence of the operator. In this program, it is simply used to check if a character is an operator.
	 * 
	 * @param s is the operator in string format
	 * @return the precedence value of the operator
	 */
	private int opValue(String s) {
		if(s.length() > 1) {
			return 0;
		}
		else {
			char op = s.charAt(0);
			int val = 0;
			if(op == '+' || op == '-') {
				val = 1;
			}
			else if(op == '*' || op == '/') {
				val = 2;
			}
			return val;
		}
	}
	
	public static void main(String[] args) {
		CalculatorApp app = new CalculatorApp();
		app.run();
	}
}