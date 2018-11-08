package software_eng_project;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class InitialiseGame {
	
	public InitialiseGame() {
		
	}
	
	public static ArrayList<Space> initialiseBoard() {
	ArrayList<Space> boardSpacesList = BoardReader.readBoard();
	for(int i=0;i<boardSpacesList.size();i++) {
		boardSpacesList.get(i).printSpace();
		System.out.println();
		}
	return boardSpacesList;
	}

	public static ArrayList<Player> initialisePawns() {
		// create list of pawn colours available
		ArrayList<String> carColour = new ArrayList<String>();
		carColour.add("Pink");
		carColour.add("Blue");
		carColour.add("Green");
		carColour.add("Yellow");
		//String userColour = null;
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		
		// Ascertain how many players there are
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many players? (2-4): ");
		int numPlayers = keyboard.nextInt();
		
		// initialise each new player
		for (int i=1; i<=numPlayers; i++) {
			System.out.println("**** Player "+ i +"****");
			Player player = new Player(); 
			Player player1 = new Player(); 		
			player1 = player.initialisePlayer(carColour);
			
			String chosenColour = player1.getColour();
			carColour.remove(chosenColour);
			
			listOfPlayers.add(player1);
			}
		
		// print out player details
		for (int x=0; x<listOfPlayers.size(); x++) {
			listOfPlayers.get(x).printDetails();
		}
	
		//keyboard.close();
		return listOfPlayers;
	}
	
	public static ArrayList<HouseCards> initialiseHouseDeck() {
		// house cards read in and sorted into array houseList, details printed to check if correct!
		
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String houseFileLocation = utility.getProperty("house_file");
		
		ArrayList<HouseCards> houseList = new ArrayList<HouseCards>();
		houseList = HouseCards.getListOfCards(houseFileLocation);
		for (int i=0;i<houseList.size();i++) {
			houseList.get(i).printCardDetails();
		} 
	return houseList;
	}

	// used for college career cards and career cards
	public static ArrayList<CareerCards> initialiseCareerCardDeck(String fileName) {
		// house cards read in and sorted into array houseList, details printed to check if correct!
		
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String cardFileLocation = utility.getProperty(fileName);
		
		ArrayList<CareerCards> careerList = new ArrayList<CareerCards>();
		careerList = CareerCards.getListOfCards(cardFileLocation);
		for (int i=0;i<careerList.size();i++) {
			careerList.get(i).printCardDetails();
		} 
	return careerList;
	}
	
	public static ArrayList<String> initialiseActionCardDeck() {
		ArrayList<String> actionCardList = new ArrayList<String>();
		actionCardList = ActionCards.getListOfCards();
	return actionCardList;
	}
	
}