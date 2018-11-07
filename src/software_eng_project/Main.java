package software_eng_project;

import software_eng_project.Player;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import software_eng_project.HouseCards;

public class Main {

	public static void main(String[] args) {
		// initialise players
		
		//ArrayList<String> carColour = new ArrayList<String>();
		//carColour.add("Pink");
		//carColour.add("Blue");
		//carColour.add("Green");
		//carColour.add("Yellow");
		//String userColour = null;
		//ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		

		/*System.out.println("How many players? (2-4): ");
		int numPlayers = keyboard.nextInt();
		
		for (int i=1; i<=numPlayers; i++) {
			Player player = new Player(); 
			Player player1 = new Player(); 		
			player1 = player.initialisePlayer(carColour);
			
			String chosenColour = player1.getColour();
			carColour.remove(chosenColour);
			
			listOfPlayers.add(player1);
			}
		
		for (int x=0; x<listOfPlayers.size(); x++) {
			listOfPlayers.get(x).printDetails();
		}

		// HOUSE CARDS
		// house cards read in and sorted into array houseList, details printed to check if correct!
		
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String boardFileLocation = utility.getProperty("house_file");
		
		ArrayList<HouseCards> houseList = new ArrayList<HouseCards>();
		houseList = HouseCards.getListOfCards(boardFileLocation);
		for (int i=0;i<houseList.size();i++) {
			houseList.get(i).printCardDetails();
		} 
		*/
		
		// open scanner
		Scanner keyboard = new Scanner(System.in);

		
		// create list of players and initialise
		ArrayList<Player> new_listOfPlayers = new ArrayList<Player>();
		new_listOfPlayers = InitialiseGame.initialisePawns();
		
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
		ArrayList<String> actionCardList = new ArrayList<String>();
		actionCardList = InitialiseGame.initialiseActionCardDeck();
		
		// Read in spaces and save to arraylist
		ArrayList<Space> boardSpacesList = BoardReader.readBoard();
		for(int i=0;i<boardSpacesList.size();i++) {
			boardSpacesList.get(i).printSpace();
			System.out.println();
		}
		
		// Create a new spinner
		Spinner spinner = new Spinner();
		
		
		// PLAYERS PLAY
		
		for(int x=0;x<new_listOfPlayers.size();x++) {
				
			// spin spinner and print results
			spinner.spinSpinner();
			int moves = spinner.getNumber();
			System.out.println("Value: "+moves+"\nColour: "+spinner.getColour());
		
			for(int y=1;y<=moves;y++) {
				new_listOfPlayers.get(x).movePlayer();
				String current_space = new_listOfPlayers.get(x).getCurrentSpace();
				System.out.println("Player "+x+" now at: "+current_space);
				java.util.List<String> space_type = boardSpacesList.get(Integer.parseInt(current_space)-1).getSpaceType();
				if(space_type.contains("STOP")) {
					System.out.println("Stop");
					System.out.println(space_type.get(1));
					break;
				}
			}
		}
		
		
		
		keyboard.close();
		
	}
}
