package software_eng_project;

import software_eng_project.Player;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

import software_eng_project.HouseCards;

public class Main {

	public static void main(String[] args) {
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

		for (int num_turns=0;num_turns<3;num_turns++) { 				// for 3 turns
			for(int x=0;x<new_listOfPlayers.size();x++) {
				String current_player=new_listOfPlayers.get(x).getName();
				// Next player's turn
				System.out.println("\n"+current_player+"'s turn");
				
				// spin spinner and print results
				System.out.println(current_player+", press enter to spin the spinner!");
				keyboard.nextLine();
				
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
					if(y>0 && space_type.contains("STOP")) { 						// Player lands on stop
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
				System.out.println(current_player+" moves to space "+current_space);
				//System.out.println(current_player+", press enter to end your turn.");
				//keyboard.nextLine();
				//System.out.println("\nNext player's turn");
			}

		}
		System.out.println("\nEnd of game!");

		keyboard.close();
	}
}
