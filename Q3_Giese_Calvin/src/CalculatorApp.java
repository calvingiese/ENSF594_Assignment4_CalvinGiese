/**
 * Class Name: ConverterApp
 * 
 * Constructs a new StackApp that CLI that tests whether a user enters a valid bracket or not.
 * 
 * The main purpose of this class is to build a stack from the user's entry and test whether or not it matches the brackets
 * defined in the program (). There can be nested brackets but it must be a series of opening brackets followed by the same
 * number of closing brackets to be valid.
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
		
		// Builds stack of alternating characters and operators, continuing to run until the user opts to exit
		int exit = 0; // Integer used to switch on when the while loop should exit

		
		while(exit == 0) {
			
			// Prompts user to enter their mathematical expression to be converted
			reader.prompt('\n' + "Please enter an postfix mathematical expression to calculate or type 'EXIT' to end program: ");
			String entry = reader.getKeyboardInput();
			
			StringBuffer b1 = new StringBuffer();
			String valid = "invalid";
			int opCounter = 0;
			String output = null;
			
			// Leaves if user types 'EXIT'
			if(entry.equals("EXIT")) {
				exit = 1;
				break;
			}
			
			// 
			else if(entry.length() > 2){
				
				Stack calcStack = new Stack(entry.length());
				for(int i = entry.length() - 1; i > - 1; i--) {
					calcStack.push(entry.charAt(i));
				}
				
				for(int i = entry.length() - 1; i > - 1; i--) {
					if(opValue(entry.charAt(i)) > 0) {
						opCounter++;
					}
				}
				
				if(entry.length() / 2 == opCounter) {
					valid = "valid";
				}
				
				Stack newStack = new Stack(entry.length());
				
				for(int i = 0; i < entry.length(); i++) {
					
					if(entry.charAt(i) > 47 && entry.charAt(i) < 58) {
						newStack.push(entry.charAt(i));
					}
					else {
						int firstOp = newStack.pop() - 48;
						int secondOp = newStack.pop() - 48;
						int res = calc(firstOp, secondOp, entry.charAt(i));
						String stringResult = res + "";
						b1.append(stringResult);
						//newStack.push(res);
						System.out.println(firstOp);
						System.out.println(secondOp);
						System.out.println(entry.charAt(i));
					}
				}
				
				output = b1.toString();
				
								
			}
				
			
			// 
			reader.display('\n' + "The expression entered was " + valid + '\n');
			
			// Displays results if the expression entered was valid
			if(valid == "valid") {
				reader.display('\n' + "The infix expression entered was: " + entry);
				reader.display('\n' + "The converted postfix expression is: " + output + '\n');
			}
		}
	}
	
	private int calc(int a, int b, char c) {
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
		CalculatorApp app = new CalculatorApp();
		app.run();
	}
}