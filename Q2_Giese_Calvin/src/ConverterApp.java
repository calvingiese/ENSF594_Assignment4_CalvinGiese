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
 * @since July 26, 2021
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
	 * 
	 */
	public void run() {
		
		// Beginning of user interface
		reader.display('\n' + "        Converter Program        " + '\n');
		
		// Builds stack of alternating characters and operators, continuing to run until the user opts to exit
		int exit = 0; // Integer used to switch on when the while loop should exit
		while(exit == 0) {
			
			// Indicators used to trigger action
			String valid = "invalid"; // Summary statement result that only changes if a valid entry is input
			int counter = 0; // Counts valid character order and compares total to entry length to verify validity 
			String output = null; // Will replace string with converted expression if valid
			
			char opTop;
			char opThis;
			int valTop = 0;
			int valThis = 0;
			
			StringBuffer b1 = new StringBuffer();			
			
			// Prompts user to enter their mathematical expression to be converted
			reader.prompt('\n' + "Please enter an infix mathematical expression to convert or type 'EXIT' to end program: ");
			String entry = reader.getKeyboardInput();
			
			// Leaves if user types 'EXIT'
			if(entry.equals("EXIT")) {
				exit = 1;
				break;
			}
			
			// Checks that entry was a valid expression and builds new stack from back to front
			else if (entry.length() > 2 && entry.length() % 2 == 1){
				Stack conStack = new Stack(entry.length());
				Stack opStack = new Stack(entry.length() - 3);
				for(int i = entry.length() - 1; i > - 1; i--) {
					if(i % 2 == 0) {
						if(entry.charAt(i) > 64 && entry.charAt(i) < 123) {
							conStack.push(entry.charAt(i));
							counter++;
						}
					}
					else if(entry.charAt(i) == '+' || entry.charAt(i) == '-' || entry.charAt(i) == '*' || entry.charAt(i) == '/'){
						conStack.push(entry.charAt(i));
						counter++;
					}
				}
				
				for(int i = 0; i < entry.length(); i++) {
					char newChar = conStack.pop();
					if(opValue(newChar) == 0) {
						b1.append(newChar);
					}
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
				while(opStack.isEmpty() == false) {
					b1.append(opStack.pop());
				}
			}
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