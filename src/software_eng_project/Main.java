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
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard();

		// Create a new spinner
		Spinner spinner = new Spinner();


		// PLAYERS PLAY

		/*
		for(int x=0;x<new_listOfPlayers.size();x++) {

			// spin spinner and print results
			spinner.spinSpinner();
			int moves = spinner.getNumber();
			System.out.println("Value: "+moves+"\nColour: "+spinner.getColour());

			for(int y=1;y<=moves;y++) {
				String next_space = null;
				String current_space = new_listOfPlayers.get(x).getCurrentSpace();
				System.out.println("Player "+x+" now at: "+current_space);
				// what are the next space choices?
				java.util.List<String> next_space_choices = boardSpacesList.get(Integer.parseInt(current_space)).getNextSpace();
				// ask user if there is a choice!
				String space_type = boardSpacesList.get(Integer.parseInt(current_space)).getSpaceType();
				if(space_type.contains("STOP")) {
					System.out.println("Stop");
				}
				if(next_space_choices.size() > 1) {
					System.out.println("Choose Path: " + next_space_choices.get(0) + " or " + next_space_choices.get(1));
					int next_space_int = keyboard.nextInt();
					next_space = Integer.toString(next_space_int);
				} else {
					next_space = next_space_choices.get(0);
				}
				new_listOfPlayers.get(x).movePlayer(next_space);

			}
			System.out.println("Next player's turn");
		} */

		// 1st TURN

		for(int x=0;x<new_listOfPlayers.size();x++) {

			// spin spinner and print results
			spinner.spinSpinner();
			int moves = spinner.getNumber();
			System.out.println("Spin value: "+moves+"\nColour: "+spinner.getColour());

			for(int y=0;y<moves;y++) {
				String next_space = null;
				String current_space = new_listOfPlayers.get(x).getCurrentSpace();
				// what are the next space choices?
				java.util.List<String> next_space_choices = boardSpacesList.get(Integer.parseInt(current_space)-1).getNextSpace();
				// ask user if there is a choice!
				String space_type = boardSpacesList.get(Integer.parseInt(current_space)-1).getSpaceType();
				if(y>0 && space_type.contains("STOP")) {
					System.out.println("Stop");
					break;
				}
				if(next_space_choices.size() > 1) {
					System.out.println("Choose Path: " + next_space_choices.get(0) + " or " + next_space_choices.get(1));
					int next_space_int = keyboard.nextInt();
					next_space = Integer.toString(next_space_int);
				} else {
					next_space = next_space_choices.get(0);
				}
				new_listOfPlayers.get(x).movePlayer(next_space);
			}

			String current_space = new_listOfPlayers.get(x).getCurrentSpace();
			System.out.println("Player "+x+" now at: "+current_space);
			System.out.println("\nNext player's turn");
		}

		// 2nd TURN

		for(int x=0;x<new_listOfPlayers.size();x++) {

			// spin spinner and print results
			spinner.spinSpinner();
			int moves = spinner.getNumber();
			System.out.println("Spin value: "+moves+"\nColour: "+spinner.getColour());

			for(int y=0;y<moves;y++) {
				String next_space = null;
				String current_space = new_listOfPlayers.get(x).getCurrentSpace();
				// what are the next space choices?
				java.util.List<String> next_space_choices = boardSpacesList.get(Integer.parseInt(current_space)-1).getNextSpace();
				// ask user if there is a choice!
				String space_type = boardSpacesList.get(Integer.parseInt(current_space)-1).getSpaceType();
				if(y>0&&space_type.contains("STOP")) {
					System.out.println("Stop");
					break;
				}
				if(next_space_choices.size() > 1) {
					System.out.println("Choose Path: " + next_space_choices.get(0) + " or " + next_space_choices.get(1));
					int next_space_int = keyboard.nextInt();
					next_space = Integer.toString(next_space_int);
				} else {
					next_space = next_space_choices.get(0);
				}
				new_listOfPlayers.get(x).movePlayer(next_space);
			}
			String current_space = new_listOfPlayers.get(x).getCurrentSpace();
			System.out.println("Player "+x+" now at: "+current_space);
			System.out.println("\nNext player's turn");
		}

		// 3rd TURN

		for(int x=0;x<new_listOfPlayers.size();x++) {

			// spin spinner and print results
			spinner.spinSpinner();
			int moves = spinner.getNumber();
			System.out.println("Spin value: "+moves+"\nColour: "+spinner.getColour());

			for(int y=0;y<moves;y++) {
				String next_space = null;
				String current_space = new_listOfPlayers.get(x).getCurrentSpace();
				// what are the next space choices?
				java.util.List<String> next_space_choices = boardSpacesList.get(Integer.parseInt(current_space)-1).getNextSpace();
				// ask user if there is a choice!
				String space_type = boardSpacesList.get(Integer.parseInt(current_space)-1).getSpaceType();
				if(y>0&&space_type.contains("STOP")) {
					System.out.println("Stop");
					break;
				}
				if(next_space_choices.size() > 1) {
					System.out.println("Choose Path: " + next_space_choices.get(0) + " or " + next_space_choices.get(1));
					int next_space_int = keyboard.nextInt();
					next_space = Integer.toString(next_space_int);
				} else {
					next_space = next_space_choices.get(0);
				}
				new_listOfPlayers.get(x).movePlayer(next_space);
			}
			String current_space = new_listOfPlayers.get(x).getCurrentSpace();
			System.out.println("Player "+x+" now at: "+current_space);
			System.out.println("\nNext player's turn");
		}


		keyboard.close();

	}
}
