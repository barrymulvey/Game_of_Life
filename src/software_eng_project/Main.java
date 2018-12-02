package software_eng_project;

import software_eng_project.Player;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import software_eng_project.HouseCards;

public class Main {

	public static void main(String[] args) {
		// open scanner
		Scanner keyboard = new Scanner(System.in);
		
		// Initialise house deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		// initialise career card deck
		ArrayList<CareerCards> careerCardList = new ArrayList<CareerCards>();
		careerCardList = InitialiseGame.initialiseCareerCardDeck("careers_file");
		
		// create list of players and initialise
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers = InitialiseGame.initialisePawns(careerCardList);
		ArrayList<Player> retiredList = new ArrayList<Player>();
		
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

		System.out.println("Start the game! (Youngest goes first)");
		while(listOfPlayers.size() > 0) { 				// until all players retire
			for (int x=0;x<listOfPlayers.size();x++) {
				String current_player=listOfPlayers.get(x).getName();
				// Next player's turn
				System.out.println("\n"+current_player+"'s turn");
				
				while (listOfPlayers.get(x).getBalance() <= 0) {
					listOfPlayers.get(x).takeLoan();
				}
				
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
					if(!(boardSpacesList.get(Integer.parseInt(current_space)).getSpaceType().contains("RETIREMENT"))) {
					    if(next_space_choices.size() > 1 && !(current_space == "68")) {
							System.out.println("Choose Path: " + next_space_choices.get(0) + " or " + next_space_choices.get(1));
							int next_space_int = keyboard.nextInt();
							next_space = Integer.toString(next_space_int);
						} else {
							next_space = next_space_choices.get(0);
						}
						listOfPlayers.get(x).movePlayer(next_space);
					}
					// add in exception here! do an else: space_type = null;
					String space_type = null;
					current_space = listOfPlayers.get(x).getCurrentSpace();
					int space_number = Integer.parseInt(current_space);
					space_type = boardSpacesList.get(space_number).getSpaceType();
					
					if(space_type.contains("STOP")) { // Player lands on stop (if not starting space!)
						System.out.println("Stop! You reached a stop space (space "+current_space+")\n");
						
						//if(space_number==14) {
						if(space_type.contains("GRADUATION")) {
						    StopSpace.graduation(listOfPlayers.get(x), collegeCareerCardList);
						}
						//else if(space_number==27) {
						else if(space_type.contains("WEDDING")) {
							StopSpace.wedding(listOfPlayers.get(x), listOfPlayers, spinner, x);
						}
						//else if(space_number==39) {
						else if(space_type.contains("NIGHTSCHOOL")) {
							StopSpace.nightSchool(listOfPlayers.get(x), collegeCareerCardList, x);
						}
						//else if(space_number==68) {
						else if(space_type.contains("FAMILYORLIFE")) {
							StopSpace.familyOrLife(boardSpacesList, listOfPlayers.get(x), x);
						}
						//else if(space_number==78) {
						else if(space_type.contains("CHILDREN")) {
							StopSpace.haveChildren(spinner, listOfPlayers.get(x));
						}
						//else if(space_number==95) System.out.println("Holiday - time to relax!");
						else if(space_type.contains("HOLIDAY")) {
							SpaceTypes.holiday();
						}
						break;
					}
					
					// Print spaces over which player travels
					if(y!=(moves-1)) {
						System.out.println(current_player+" moves past "+space_type+" on space "+current_space);
						if (space_type.contains("PAYDAY")) {
							SpaceTypes.Payday(listOfPlayers.get(x), false);
						}
					}
					
					// Print space on which player lands and take appropriate action
					if (y==moves-1) {
						System.out.println(current_player+" stopped on "+space_type+" on space "+current_space);
						switch(space_type) {
							case "ACTION": SpaceTypes.actionSpace(listOfPlayers.get(x), actionCardList, listOfPlayers, collegeCareerCardList);
										   break;
							case "PAYDAY": SpaceTypes.Payday(listOfPlayers.get(x), true);
										   break;
										   
							case "HOUSE": SpaceTypes.houseSpace(listOfPlayers.get(x));
										  break;
										  
							case "TWINS": SpaceTypes.babySpace(listOfPlayers.get(x), true);
										  break;
										  
							case "HOLIDAY":	SpaceTypes.holiday();
											break;
											
							case "BABY": SpaceTypes.babySpace(listOfPlayers.get(x), false);
							             break;
							             
							case "SPIN_TO_WIN":	SpaceTypes.spinToWin(listOfPlayers, listOfPlayers.get(x), spinner);
												break;
												
							case "RETIREMENT": SpaceTypes.retirementSpace(listOfPlayers, retiredList, listOfPlayers.get(x));
											   break;
											   
						    default: output_space_type = null;
						}	
					}
				}
				
				System.out.println("\n"+current_player+"'s updated details summary: ");
				listOfPlayers.get(x).printDetailsSummary();


				//System.out.println(current_player+", press enter to end your turn.");
				//keyboard.nextLine();
				//System.out.println("\nNext player's turn");
			}

		}
	
		// retire players
		//update wallet- evaluate assets
		Player.playersRetire(retiredList);
		Player.determineWinner(retiredList);
		
		System.out.println("\nEnd of game!");

		keyboard.close();
	}
}
