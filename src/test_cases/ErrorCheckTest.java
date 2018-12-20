package test_cases;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import software_eng_project.ErrorCheck;

class ErrorCheckTest {

	@Test
	void testRangeCheck() {
		String question = "Choose Number of Players (2-4)";
		int limitLower = 2;
		int limitUpper = 4;
		// initialise number of players to 0 before passing to rangeCheck method
		int numPlayers = 0;
		
		// Ask user how many players there are
		Scanner keyboard = new Scanner(System.in);
		int chosen = ErrorCheck.rangeCheck(question, limitLower, limitUpper, numPlayers);
				
		// choose user input to be 'result' for testing
		int result = 2;
		//int result = 3;
		//int result = 4;
		assertEquals("rangeCheck shoudl return an int with selected user input if within specified range", chosen, result);
		keyboard.close();
	}

}
