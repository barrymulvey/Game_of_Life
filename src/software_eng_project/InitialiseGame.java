package software_eng_project;

import java.util.ArrayList;
import java.util.Scanner;

public class InitialiseGame {

	// constructor
	public InitialiseGame() {
	}

	// method to read in spaces which will make up the board
	public static ArrayList<Space> initialiseBoard(Utility utility) {
		ArrayList<Space> boardSpacesList = BoardReader.readBoard(utility);
		return boardSpacesList;
	}

	// method to initialise the players and their pawns
	public static ArrayList<Player> initialisePawns(ArrayList<CareerCards> careerCardList, Scanner keyboard, Utility utility) {
		//identifies the parameters in config.properties file
		String minPlayers = utility.getProperty("minPlayers");
		String maxPlayers = utility.getProperty("maxPlayers");
		// convert to integers
		int minPlayersInt = Integer.parseInt(minPlayers);
		int maxPlayersInt = Integer.parseInt(maxPlayers);
		
		// create list of pawn colours available
		ArrayList<String> carColour = new ArrayList<String>();
		carColour.add("Pink");
		carColour.add("Blue");
		carColour.add("Green");
		carColour.add("Yellow");
		
		// create original ArrayList of players 
		ArrayList<Player> first_listOfPlayers = new ArrayList<Player>();
		// create final ListOfPlayers
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		
		// Ascertain how many players there are and check for correct user input
		int numPlayers = 0;
		System.out.println("**** Welcome to the Game of Life! ****\n");
		numPlayers = ErrorCheck.rangeCheck("How many players? ("+minPlayers+"-"+maxPlayers+"): ", minPlayersInt, maxPlayersInt, numPlayers);
		
		// initialise each new player
		for (int i=1; i<=numPlayers; i++) {
			System.out.println("**** Player "+ i +" ****");
			Player player = new Player(); 
			Player player1 = new Player(); 		
			player1 = player.initialisePlayer(carColour, careerCardList, keyboard, utility);

			// remove colour chosen by player as each player must have a different colour
			String chosenColour = player1.getColour();
			carColour.remove(chosenColour);

			// add players as info is entered
			first_listOfPlayers.add(player1);
		}
		keyboard.nextLine();

		// arrange players in correct order (youngest goes first!)
		Player youngestPlayer = null;
		int playerListSize = first_listOfPlayers.size();

		while (playerListSize > 1) {
			int youngest = first_listOfPlayers.get(0).getAge();
			for(int y=0;y<first_listOfPlayers.size(); y++) {
				if (first_listOfPlayers.get(y).getAge() <= youngest) {
					youngest = first_listOfPlayers.get(y).getAge();
					youngestPlayer = first_listOfPlayers.get(y);
				}
			}
			listOfPlayers.add(youngestPlayer);
			first_listOfPlayers.remove(youngestPlayer);
			playerListSize = first_listOfPlayers.size();
		}
		// add last player
		listOfPlayers.add(first_listOfPlayers.get(0));

		// print out player details
		for (int x=0; x<listOfPlayers.size(); x++) {
			listOfPlayers.get(x).printDetails();
		}
		return listOfPlayers;
	}

	// initialise the houseCard deck- this is an ArrayList of HouseCard Objects
	public static ArrayList<HouseCards> initialiseHouseDeck() {
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String houseFileLocation = utility.getProperty("house_file");

		// call method to get list of house cards from text file
		ArrayList<HouseCards> houseList = new ArrayList<HouseCards>();
		houseList = HouseCards.getListOfCards(houseFileLocation);
		return houseList;
	}

	// method to create an ArrayList of CareerCards Objects 
	// NB used for college career cards and career cards
	public static ArrayList<CareerCards> initialiseCareerCardDeck(String fileName) {
		// house cards read in and sorted into array houseList, details printed to check if correct!

		//identifies were the file is located
		Utility utility = Utility.getInstance();
		String cardFileLocation = utility.getProperty(fileName);

		// call method to create ArrayList of CareerCards using location of text file storing details
		ArrayList<CareerCards> careerList = new ArrayList<CareerCards>();
		careerList = CareerCards.getListOfCards(cardFileLocation);
		return careerList;
	}

	// method to create and populate an ArrayList of ActionCards objects
	public static ArrayList<ActionCards> initialiseActionCardDeck() {
		//identifies were the file is located
		Utility utility = Utility.getInstance();
		String cardFileLocation = utility.getProperty("action_file");

		// call method to create ArrayList of ActionCards using location of text file storing details
		ArrayList<ActionCards> actionCardList = new ArrayList<ActionCards>();
		actionCardList = ActionCards.getListOfCards(cardFileLocation);
		return actionCardList;
	}
}