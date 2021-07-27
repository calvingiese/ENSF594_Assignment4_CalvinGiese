/**
 * Class Name: StackApp
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

public class StackApp {
	
	/**
	 * reader will allow for command line interactions between the user and the program.
	 */
	private KeyboardReader reader;

	/**
	 * Constructs the reader of object type KeyboardReader
	 */
	public StackApp() {
		reader = new KeyboardReader();
	}
	
	/**
	 * This method prompts the user to enter a string and returns whether the entry was a valid set of brackets. The method
	 * will continue until the user decides to type 'EXIT' which will terminate the program. A summary will be returned at
	 * the end whether the user entered a valid set of brackets or not. 
	 * 
	 */
	public void run() {
		
		// Beginning of user interface
		reader.display('\n' + "        Delimiter Program        " + '\n');
		
		// Continues to run program until user decides to exit by typing EXIT
		int exit = 0;
		String valid = "invalid";
		while(exit == 0) {
			reader.prompt('\n' + "Please enter a string to test or type 'EXIT' to end program: ");
			String entry = reader.getKeyboardInput();
			if(entry.equals("EXIT")) {
				exit = 1;
				break;
			}
			
			// Builds stack from user entry, length must be an even number or it will automatically be invalid
			else if(entry.length() % 2 == 0) {	
				Stack newStack = new Stack(entry.length());
				for(int i = 0; i < entry.length(); i++){
					newStack.push(entry.charAt(i));
				}
				
				// Counts the number of closing brackets in the first half of the stack
				int counter = 0;
				for(int i = 0; i < entry.length() / 2; i++) {
					char newChar = newStack.pop();
					if(newChar == ')') {
						counter++;
					}
					else {
						counter = -1;
					}
				}
				
				// Counts the number of open brackets in the second half of the stack
				for(int i = entry.length(); i > entry.length() / 2; i--) {
					char newChar = newStack.pop();
					if(newChar == '(') {
						counter--;
					}
					else {
						counter = -1;
					}
				}
				
				// If string was a sequence of open brackets followed by the same amount of closing brackets, entry is valid
				if(counter == 0) {
					valid = "valid";
				}
			}		
			
			// Displays whether or not the entry was valid or not
			reader.display('\n' + "The entry " + "'" + entry + "'" + " is " + valid + '\n');
		}
	}
	
	public static void main(String[] args) {
		StackApp app = new StackApp();
		app.run();
	}
}
