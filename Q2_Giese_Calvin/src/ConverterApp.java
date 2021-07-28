/**
 * Class Name: ConverterApp
 * 
 * Constructs a new ConverterApp that CLI that turns an infix expression into a postfix expression.
 * 
 * The main purpose of this class is to build a stack from the user's entry and follow the logic for converting
 * infix to postfix to produce the results. The program will continue to run until the user decides to exit and
 * prints whether the entry was valid or not as well as the results if the entry was valid.
 * 
 * @author Calvin Giese
 * @version version 1.0
 * @since July 27, 2021
 *
 */

public class ConverterApp {
	
	/**
	 * reader will allow for command line interactions between the user and the program.
	 */
	private KeyboardReader reader;

	/**
	 * Constructs the reader of object type KeyboardReader
	 */
	public ConverterApp() {
		reader = new KeyboardReader();
	}
	
	/**
	 * This method prompts the user to enter a mathematical expression in infix format and displays it in postfix format.
	 * Repeats as many times as the user wants, exiting when the user types 'EXIT'.
	 * 
	 */
	public void run() {
		
		// Beginning of user interface
		reader.display('\n' + "        Converter Program        " + '\n');
		
		// Integer used to switch on when the while loop should exit
		int exit = 0; 
		
		// Continues looping until user decides to exit
		while(exit == 0) {
			
			// Indicators used to trigger action
			String valid = "invalid"; // Summary statement result that only changes if a valid entry is input
			int counter = 0; // Counts valid character order and compares total to entry length to verify validity 
			String output = null; // Will replace string with converted expression if valid
			
			// Declares variables for later use
			char opTop;
			char opThis;
			int valTop = 0;
			int valThis = 0;
			
			// String buffer to be filled with the postfix results
			StringBuffer b1 = new StringBuffer();			
			
			// Prompts user to enter their mathematical expression to be converted
			reader.prompt('\n' + "Please enter an infix mathematical expression to convert or type 'EXIT' to end program: ");
			String entry = reader.getKeyboardInput();
			
			// Leaves if user types 'EXIT'
			if(entry.equals("EXIT")) {
				exit = 1;
				break;
			}
			
			// Checks that entry was a valid expression, with length of 3 or more and is an odd number
			else if (entry.length() > 2 && entry.length() % 2 == 1){
				Stack conStack = new Stack(entry.length());
				
				// Counts the operators to build a stack of the right size
				int opCounter = 0;
				for(int i = 0; i < entry.length(); i++) {
					if(i % 2 == 1) {
						opCounter++;
					}
				}
				
				// Operator stack
				Stack opStack = new Stack(opCounter);
				
				// Builds stack from back to front from the entry
				for(int i = entry.length() - 1; i > - 1; i--) {
					if(i % 2 == 0) {
						if((entry.charAt(i) > 64 && entry.charAt(i) < 123) || (entry.charAt(i) > 47 && entry.charAt(i) < 58)) {
							conStack.push(entry.charAt(i));
							counter++;
						}
					}
					else if(entry.charAt(i) == '+' || entry.charAt(i) == '-' || entry.charAt(i) == '*' || entry.charAt(i) == '/'){
						conStack.push(entry.charAt(i));
						counter++;
					}
				}
				
				// Adds character to output if its not an operator
				for(int i = 0; i < entry.length(); i++) {
					char newChar = conStack.pop();
					if(opValue(newChar) == 0) {
						b1.append(newChar);
					}
					
					// Uses infix-postfix logic to rearrange operands and operators to the correct order
					if(opValue(newChar) > 0) {
						opThis = newChar;
						if(opStack.isEmpty() == true) {
							opStack.push(opThis);
						}
						else {
							valThis = opValue(opThis);
							opTop = opStack.pop();
							valTop = opValue(opTop);
							if(valTop < valThis) {
								opStack.push(opTop);
								opStack.push(opThis);
							}
							else if(valTop >= valThis) {
								b1.append(opTop);
								opStack.push(opThis);
							}
						}
					}	
				}
				
				// Fills output with remaining operators
				while(opStack.isEmpty() == false) {
					b1.append(opStack.pop());
				}
			}
			// Converts output to string
			output = b1.toString();	
			
			// Displays whether the entry was valid or not
			reader.display('\n' + "The expression entered was " + valid + '\n');
			
			// Displays results if the expression entered was valid
			if(counter == entry.length()) {
				valid = "valid";
				reader.display('\n' + "The infix expression entered was: " + entry);
				reader.display('\n' + "The converted postfix expression is: " + output + '\n');
			}
		}
	}
	
	/**
	 * Method used to determine the precedence of the operator values and returns 0 for invalid operators
	 * 
	 * @param op is the operator character
	 * @return the precedence value of the character
	 */
	private int opValue(char op) {
		int val = 0;
		if(op == '+' || op == '-') {
			val = 1;
		}
		else if(op == '*' || op == '/') {
			val = 2;
		}
		return val;
	}
	
	public static void main(String[] args) {
		ConverterApp app = new ConverterApp();
		app.run();
	}
}