package software_eng_project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ErrorCheck {
	
	public static int rangeCheck(String question, int limitLower, int limitUpper, int toCheck) {
	// Check user inputs, throw exception if not valid
	// Keep looping until they enter an integer within the specified range
		
		// open scanner
		Scanner keyboard = new Scanner(System.in);
		
			// continue to execute try-catch until correct input entered by user
			while (true) {
				try {
					System.out.println(question);
					toCheck = keyboard.nextInt();
					if (toCheck>=limitLower && toCheck<=limitUpper) {
						break;
					}
					else System.out.println("Invalid input - please try again!");
				}
				
				// throw exception if input is out of specified range or not an integer
				catch (InputMismatchException e) {
					System.out.println("Invalid input - please try again!");
					keyboard.next();
				}
			}
			return toCheck;
	}
}