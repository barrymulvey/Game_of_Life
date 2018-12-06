package software_eng_project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class HouseCardsTest {

	@Test
	void testGetName() {

		// initialise house card deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		String cardName = "Ranch";
		String result = houseCardList.get(0).getName();

		assertEquals("getName output should be equal to name of card", cardName, result);
	}

	@Test
	void testGetValue1() {
		// initialise house card deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		int chosen = 600;
		int result = houseCardList.get(0).getValue1();

		assertEquals("getValue1 should return the first integer value on a line of the text file", chosen, result);
	}

	@Test
	void testGetValue2() {

		// initialise house card deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		int chosen = 600;
		int result = houseCardList.get(0).getValue1();

		assertEquals("getValue2 should return the second integer value on a line of the text file", chosen, result);
	}

	@Test
	void testGetValue3() {
		// initialise house card deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		int chosen = 750;
		int result = houseCardList.get(0).getValue3();

		assertEquals("getValue3 should return the third integer value on a line of the text file", chosen, result);
	}

	@Test
	void testSellHouse1() {
		// test sellHouse method when player has houses

		// create instance of Utility to read in properties from config.properties file
		Utility utility = Utility.getInstance();
		
		// open scanner
		Scanner keyboard = new Scanner(System.in);
		
		// initialise house card deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		// initialise college career card deck
		ArrayList<CareerCards> collegeCareerCardList = new ArrayList<CareerCards>();
		collegeCareerCardList = InitialiseGame.initialiseCareerCardDeck("college_careers_file");

		// define car colours
		ArrayList<String> carColour = new ArrayList<String>();
		carColour.add("green");
		carColour.add("blue");
		
		// initialise players 
		Player player1 = new Player();
		player1 = player1.initialisePlayer(carColour, collegeCareerCardList, keyboard, utility);
		Player player2 = new Player();
		player2 = player2.initialisePlayer(carColour, collegeCareerCardList, keyboard, utility);
		
		// create list of defined players
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);

		// ask player1 to buy a house
		HouseCards.buyHouse(houseCardList, player1);

		boolean result = HouseCards.sellHouse(houseCardList, player1, listOfPlayers, keyboard);

		// expect output to be false when player has HouseCard objects
		assertFalse(result);
	}
	
	@Test
	void testSellHouse2() {
		// test sellHouse method when player has no houses

		Scanner keyboard = new Scanner(System.in);
		
		// initialise house card deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		// initialise player to have name Brid, age 22, wallet balance of 100K, car colour blue and a college life path
		Player player1 = new Player("Brid", 22, 100, "blue", "College");
		Player player2 = new Player("Barry", 22, 100, "blue", "Career");
		
		// create list of defined players
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers.add(player1);
		listOfPlayers.add(player2);

		boolean result = HouseCards.sellHouse(houseCardList, player1, listOfPlayers, keyboard);

		// expect output to be true when player has no HouseCards objects
		assertTrue(result);
	}
}
