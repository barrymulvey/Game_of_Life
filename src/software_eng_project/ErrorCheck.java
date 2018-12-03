package software_eng_project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ErrorCheck {
	public static int rangeCheck(String question, int limitLower, int limitUpper, int toCheck) {
	// Check if user inputs are valid
		Scanner keyboard = new Scanner(System.in);
			while (true) {
				try {
					System.out.println(question);
					toCheck = keyboard.nextInt();
					if (toCheck>=limitLower && toCheck<=limitUpper) {
						break;
						//return toCheck;
					}
					else System.out.println("Invalid input - please try again!");
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid input - please try again!");
					keyboard.next();
				}
			}
			return toCheck;
	}
}
