package software_eng_project;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	//make board and deck
	public static Deck makeCards(Scanner keyboard) {	

		// Initialise house deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		// initialise career card deck
		ArrayList<CareerCards> careerCardList = new ArrayList<CareerCards>();
		careerCardList = InitialiseGame.initialiseCareerCardDeck("careers_file");

		// initialise college career card deck
		ArrayList<CareerCards> collegeCareerCardList = new ArrayList<CareerCards>();
		collegeCareerCardList = InitialiseGame.initialiseCareerCardDeck("college_careers_file");

		// initialise action card deck
		ArrayList<ActionCards> actionCardList = new ArrayList<ActionCards>();
		actionCardList = InitialiseGame.initialiseActionCardDeck("action_file");
		
		// create a deck of all cards in game
		Deck cardDeck = new Deck(actionCardList, careerCardList, collegeCareerCardList, houseCardList);
		
		return cardDeck;
	}

	public static ArrayList<Space> makeBoard() {
		// Read in spaces from text file and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard();
		return boardSpacesList;
	}

	public static ArrayList<Player> makePlayers(ArrayList<CareerCards> careerCardDeck, Scanner keyboard) {
		// create list of players and initialise
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers = InitialiseGame.initialisePawns(careerCardDeck, keyboard);
		return listOfPlayers;
	}

	public static void playGame(Deck cardDecks, ArrayList<Space> boardSpacesList, ArrayList<Player> listOfPlayers, Scanner keyboard) {		
		
		// initialise retired players list, this will be populated by the playersPlay method
		ArrayList<Player> retiredList = new ArrayList<Player>();
		ArrayList<Player> retiredPlayerList = new ArrayList<Player>();
		
		// Create a new spinner
		Spinner spinner = new Spinner();
		
		// play the game until all players reach retirement
		// decks = house, career, college, action
		retiredPlayerList = PlayersPlay.playersPlay(listOfPlayers, retiredList, spinner, keyboard, boardSpacesList, cardDecks.getCollegeCareerCardDeck(), cardDecks.getHouseCardDeck(), cardDecks.getActionCardDeck());
		
		// retire the players, calculate assets and declare the winner
		PlayersPlay.endOfGame(retiredPlayerList);
	}
}
