package software_eng_project;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testTakeExtraTurn() {
		// initialise player to have name, age, initial balance, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		Player player2 = new Player("Barry", 23, 100, "green", "Career");
		Player player3 = new Player("Paddy", 24, 100, "pink", "College");

		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		listOfPlayers.add(player3);
		
		// x=0 implies it is player1's turn (they are stored in element 1 of the listOfPlayers ArrayList)
		int x = 0;
		int result = player1.takeExtraTurn(listOfPlayers, x);
		
		int chosen = 2;
		// should return x = x-1. If x-x=0. It should return x = listOfPlayers.size
		assertEquals("takeExtraTurn should return x=2 when player 1 needs another turn", chosen, result);
		
	}

	@Test 
	void testGetMaritalStatus() {		
		// initialise player to have name, age, an initial balance, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		String result = player1.getMaritalStatus();
		String chosen = "Single";
		assertEquals("getMarriedStatus should return a String 'Single' ", chosen, result);
	}
	
	@Test 
	void testGetName() {		
		// initialise player to have name, age, an initial balance, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		String result = player1.getName();
		String chosen = "Brid";
		assertEquals("getName should return a String 'Brid' ", chosen, result);
	}
	
	@Test 
	void testGetColour() {		
		// initialise player to have name, age, an initial balance, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		String result = player1.getColour();
		String chosen = "blue";
		assertEquals("getColour should return a String 'blue' ", chosen, result);
	}
	
	@Test 
	void testGetBalance() {		
		// initialise player to have name, age, an initial balance, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100.0, "blue", "College");
		double result = player1.getBalance();
		double chosen = 100.0;
		
		// set delta to 0.01
		assertEquals("getBalance should return a double, 100", chosen, result, 0.01);
	}
	
	@Test 
	void testGetAge() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		int result = player1.getAge();
		int chosen = 22;
		assertEquals("getAge should return an int 22", chosen, result);
	}
	
	@Test 
	void testAddChildren() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		int chosen = 2;
		int result = player1.addChildren(chosen);
		assertEquals("addChildren should return an int 2", chosen, result);
	}
	
	void testGetNumChildren() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		int chosen = 0;
		int result = player1.getNumChildren();
		assertEquals("getNumChildren should return an int 0", chosen, result);
	}
	
	void testGetNumLoans() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		int chosen = 0;
		int result = player1.getNumLoans();
		assertEquals("getNumLoans should return an int 0", chosen, result);
	}
	
	void testGetPath() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		String chosen = "College";
		String result = player1.getPath();
		assertEquals("getPath should return a String, 'College'", chosen, result);
	}
	
	void testGetCareer() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		// assign career 'Student' to player1
		player1.getStudentCard();
		
		String chosen = "Student";
		String result = player1.getCareer();
		assertEquals("getCareer should return a String, 'Student' ", chosen, result);
	}
	
	void testGetSalary() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		// assign career 'Student' to player1
		player1.getStudentCard();
		
		int chosen = 0;
		int result = player1.getSalary();
		assertEquals("getSalary should return an int 0", chosen, result);
	}
	
	void testGetBonus() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		// assign career 'Student' to player1
		player1.getStudentCard();
		
		int chosen = 0;
		int result = player1.getBonus();
		assertEquals("getBonus should return an int 0", chosen, result);
	}
	
	void testCurrentSpace() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		// assign career 'Student' to player1
		player1.getStudentCard();
		
		String chosen = "0";
		String result = player1.getCurrentSpace();
		
		// player hasn't moved yet so current space should be 0
		assertEquals("getCurrentSpace should return a String '0' ", chosen, result);
	}
	
	void testGetNumActionCards() {		
		// initialise player to have name, age, a colour for car pawn and to choose their path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		// assign career 'Student' to player1
		player1.getStudentCard();
		
		int chosen = 0;
		int result = player1.getNumActionCards();
		assertEquals("getNumActionCards should return an int 0", chosen, result);
	}
	
}
