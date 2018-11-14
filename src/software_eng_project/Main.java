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
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers = InitialiseGame.initialisePawns();

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
		
		// initialise string to hold space type
		String output_space_type = null;
		for (int num_turns=0;num_turns<3;num_turns++) { 				// for 3 turns
			for(int x=0;x<listOfPlayers.size();x++) {
				String current_player=listOfPlayers.get(x).getName();
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
					String current_space = listOfPlayers.get(x).getCurrentSpace();
					// what are the next space choices?
					
					// current space -1 because the array 'boardSpacesList' starts at 0
					java.util.List<String> next_space_choices = boardSpacesList.get(Integer.parseInt(current_space)).getNextSpace();
					// ask user if there is a choice!
					
				    // move the player
				    if(next_space_choices.size() > 1) {
						System.out.println("Choose Path: " + next_space_choices.get(0) + " or " + next_space_choices.get(1));
						int next_space_int = keyboard.nextInt();
						next_space = Integer.toString(next_space_int);
					} else {
						next_space = next_space_choices.get(0);
					}
					listOfPlayers.get(x).movePlayer(next_space);
					
					// add in exception here! do an else: space_type = null;
					String space_type = null;
					current_space = listOfPlayers.get(x).getCurrentSpace();
					space_type = boardSpacesList.get(Integer.parseInt(current_space)).getSpaceType();
					
					System.out.println(current_player+" moves to space "+current_space);
					
					if(y>0 && space_type.contains("STOP")) { // Player lands on stop (if not starting space!)
						System.out.println("Stop! You reached a stop space");
						break;
					}
					if (y==moves-1) { // print type of last space landed on!
						switch(space_type) {
							case "ACTION": System.out.println("Draw an action card!");
										   ActionCards.chooseActionCard(actionCardList, listOfPlayers.get(x), listOfPlayers);
										   break;
							case "PAYDAY": output_space_type = "Payday!";
										   break;
							case "HOUSE": output_space_type ="Draw house cards!";
										  break;
							case "TWINS": output_space_type ="Congrats, twins!";
										  listOfPlayers.get(x).addChildren(2);
										  break;
							case "HOLIDAY":	output_space_type ="Holiday time!";
											break;
							case "BABY": output_space_type ="Congrats, baby!";
							  			 listOfPlayers.get(x).addChildren(1);
							             break;
							case "SPIN_TO_WIN":	output_space_type ="Spin to Win!";
												break;
							case "RETIREMENT": output_space_type ="You made it, retirement!";							
											   break;
						    default: output_space_type = null;
						}	
					}
				}


				//System.out.println(current_player+", press enter to end your turn.");
				//keyboard.nextLine();
				//System.out.println("\nNext player's turn");
			}

		}
		System.out.println("\nEnd of game!");

		keyboard.close();
	}
}
