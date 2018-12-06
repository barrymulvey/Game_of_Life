package software_eng_project;

import java.util.ArrayList;
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

	
	public static int containsIntCheck(String question, ArrayList<Integer> integerList, int toCheck) {
		// Check user inputs, throw exception if not valid
		// Keep looping until they enter an integer chosen from the available list

		// open scanner
		Scanner keyboard = new Scanner(System.in);

		// continue to execute try-catch until correct input entered by user
		while (true) {
			try {
				System.out.println(question);
				toCheck = keyboard.nextInt();
				if (integerList.contains(toCheck)) {
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

	public static String startsWithCheck(String question, ArrayList<String> listOfStrings, String toCheck) {
		// Check user inputs, throw exception if not valid
		// Keep looping until user enters String with first letter matching a first letter of an entry from the available list

		// open scanner
		Scanner keyboard = new Scanner(System.in);

		// continue to execute try-catch until correct input entered by user
		while (true) {
			try {
				System.out.println(question);
				toCheck = keyboard.next();
				for (String s: listOfStrings) {
					if (s.toUpperCase().startsWith(toCheck.toUpperCase())) {
						return toCheck.toUpperCase();
					}
				}
				System.out.println("Invalid input - please try again!");
			}

			catch (InputMismatchException e) {
				System.out.println("Invalid input - please try again!");
				keyboard.next();
			}
		}
	}

	public static String pathCheck(String question, String lifeDecision) {
		// Check user inputs, throw exception if not valid
		// Keep looping until they enter an integer within the specified range

		// open scanner
		Scanner keyboard = new Scanner(System.in);

		// continue to execute try-catch until correct input entered by user
		while (true) {
			try {
				System.out.println(question);
				lifeDecision = keyboard.next();
				if(lifeDecision.equalsIgnoreCase("L")||lifeDecision.equalsIgnoreCase("College")||lifeDecision.equalsIgnoreCase("R") || lifeDecision.equalsIgnoreCase("Career")) {
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
		return lifeDecision;
	}
}