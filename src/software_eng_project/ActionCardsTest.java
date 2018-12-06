package software_eng_project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class ActionCardsTest {

	@Test
	void testGetName() {

		// initialise action card deck
		ArrayList<ActionCards> actionCardList = new ArrayList<ActionCards>();
		actionCardList = InitialiseGame.initialiseActionCardDeck();

		String cardName = "Career Change";
		String result = actionCardList.get(0).getName();

		assertEquals("getName output should be equal to name of card", cardName, result);
	}

	@Test
	void testGetValue1() {
		// initialise action card deck
		ArrayList<ActionCards> actionCardList = new ArrayList<ActionCards>();
		actionCardList = InitialiseGame.initialiseActionCardDeck();

		int chosen = 0;
		int result = actionCardList.get(0).getValue1();

		assertEquals("getValue1 should return the first integer value on a line of the text file", chosen, result);
	}

	@Test
	public void testPlayersPay_1Player() {
		// This test checks result of playersPay method when there are not enough players to play "PlayersPay"

		// create instance of utility
		Utility utility = Utility.getInstance();
		// initialise player to have name Brid, age 22, wallet balance of 100K, car colour blue and a college life path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");

		ArrayList<Player> listOfPlayers1 = new ArrayList<Player>();
		listOfPlayers1.add(player1);

		boolean result = ActionCards.playersPay(player1, listOfPlayers1, utility);
		assertTrue(result);
	}

	@Test
	public void testPlayersPay_2Players() {
		// This test checks result of playersPay method when there are enough players to play "PlayersPay"

		// create instance of Utility
		Utility utility = Utility.getInstance();
		
		// initialise player to have name Brid, age 22, wallet balance of 100K, car colour blue and a college life path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		// initialise player to have name Barry, age 22, wallet balance of 100K, car colour green and a career life path
		Player player2 = new Player("Barry", 22, 100, "green", "Career");

		ArrayList<Player> listOfPlayers2 = new ArrayList<Player>();
		listOfPlayers2.add(player1);
		listOfPlayers2.add(player2);

		boolean result = ActionCards.playersPay(player1, listOfPlayers2, utility);
		assertFalse(result);
	}

	@Test
	public void testCareerChange() {
		// This test checks result of careerChange method when current player is a student/has a career

		// create new instance of Utility 
		Utility utility = Utility.getInstance();
		
		// Open keyboard to receive user input
		Scanner keyboard = new Scanner(System.in);

		// initialise college career card deck
		ArrayList<CareerCards> collegeCareerCardList = new ArrayList<CareerCards>();
		collegeCareerCardList = InitialiseGame.initialiseCareerCardDeck("college_careers_file");

		// define car colours
		ArrayList<String> carColour = new ArrayList<String>();
		carColour.add("green");
		carColour.add("blue");

		// initialise player 
		Player player1 = new Player();
		player1 = player1.initialisePlayer(carColour, collegeCareerCardList, keyboard, utility);

		boolean result = ActionCards.careerChange(player1, collegeCareerCardList);

		// output flag will be false if all executing correctly

		// expected to fail here (flag = true) if user chooses player to be a student as player has no career yet!
		//assertTrue(result);

		// expected to pass here (flag = false) if user chooses player to have a career
		assertFalse(result);
	}
}