package software_eng_project;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayersPlay {
	
	// players take turns playing until all players retire
	public static ArrayList<Player> playersPlay(ArrayList<Player> listOfPlayers, ArrayList<Player> retiredList, Spinner spinner, Scanner keyboard, ArrayList<Space> boardSpacesList, ArrayList<CareerCards> collegeCareerCardList, ArrayList<HouseCards> houseCardList, ArrayList<ActionCards> actionCardList) {		
		// initialise number retired players to 0
		int numRetired = 0;
		
		System.out.println("Start the game! (Youngest goes first)");
		while(listOfPlayers.size() > 0) { 				// until all players retire
			for (int x=0;x<listOfPlayers.size();x++) {
				boolean secondTurn = false;
				boolean retired = false;
				// create a String containing current player's name
				String current_player=listOfPlayers.get(x).getName();
				System.out.println("\n"+current_player+"'s turn");

				// if player has a balance of less than or equal to 0, prompt them to take out a loan
				while (listOfPlayers.get(x).getBalance() <= 0) {
					listOfPlayers.get(x).takeLoan();
				}

				// spin spinner and print results
				System.out.println(current_player+", press enter to spin the spinner!");
				keyboard.nextLine();
				spinner.spinSpinner(listOfPlayers);
				int moves = spinner.getNumber();

				// move player by number of moves selected by spinner
				for(int y=0;y<moves;y++) {
					String next_space = null;
					// get current space of player
					String current_space = listOfPlayers.get(x).getCurrentSpace();

					// get the next space options for the player's current space
					// current space -1 because the array 'boardSpacesList' starts at 0
					java.util.List<String> next_space_choices = boardSpacesList.get(Integer.parseInt(current_space)).getNextSpace();

					// if not retirement 
					if(!(boardSpacesList.get(Integer.parseInt(current_space)).getSpaceType().contains("RETIREMENT"))) {
						// if player is at the startgame space, determine if they chose college/career
						if((boardSpacesList.get(Integer.parseInt(current_space)).getSpaceType().contains("STARTGAME"))) {
							boolean college = false;
							if(listOfPlayers.get(x).getPath().equals("College")) {
								college = true;
							}
							// start player on space 4/1 depending on if they chose college/career
							int nextSpace = SpaceTypes.startGame(college, listOfPlayers.get(x));
							next_space= Integer.toString(nextSpace);
						}
						
						// if not on startgame space, move player
						else {
							next_space = next_space_choices.get(0);
						}
						// move the player
						listOfPlayers.get(x).movePlayer(next_space);
					}

					// get player's current space type and number
					String space_type = null;
					current_space = listOfPlayers.get(x).getCurrentSpace();
					int space_number = Integer.parseInt(current_space);
					space_type = boardSpacesList.get(space_number).getSpaceType();

					// if player lands on stop
					if(space_type.contains("STOP")) { 
						System.out.println("Stop! You reached a stop space (space "+current_space+")\n");

						//if(space_number==14) {
						if(space_type.contains("GRADUATION")) {
							StopSpace.graduation(listOfPlayers.get(x), collegeCareerCardList);
						}
						//else if(space_number==27) {
						else if(space_type.contains("WEDDING")) {
							x = StopSpace.wedding(listOfPlayers, spinner, x, keyboard);
							secondTurn = true;
						}
						//else if(space_number==39) {
						else if(space_type.contains("NIGHTSCHOOL")) {
							x = StopSpace.nightSchool(collegeCareerCardList, x, listOfPlayers, boardSpacesList, listOfPlayers.get(x), space_number);
							secondTurn = true;
						}
						//else if(space_number==68) {
						else if(space_type.contains("FAMILYORLIFE")) {
							StopSpace.familyOrLife(boardSpacesList, listOfPlayers.get(x), Integer.parseInt(current_space));
						}
						//else if(space_number==78) {
						else if(space_type.contains("CHILDREN")) {
							StopSpace.haveChildren(spinner, x, listOfPlayers, keyboard);
						}
						//else if(space_number==95) System.out.println("Holiday - time to relax!");
						else if(space_type.contains("HOLIDAY")) {
							SpaceTypes.holiday();
						}
						break;
					}
					
					// if player lands on retirement space
					if(space_type.contains("RETIREMENT")) {
						numRetired = SpaceTypes.retirementSpace(listOfPlayers, retiredList, listOfPlayers.get(x), numRetired, houseCardList, keyboard);
						retired = true;
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
						case "ACTION": SpaceTypes.actionSpace(listOfPlayers.get(x), actionCardList, listOfPlayers, collegeCareerCardList, keyboard);
						break;

						case "PAYDAY": SpaceTypes.Payday(listOfPlayers.get(x), true);
						break;

						case "HOUSE": SpaceTypes.houseSpace(listOfPlayers.get(x), houseCardList, listOfPlayers);
						break;

						case "TWINS": SpaceTypes.babySpace(listOfPlayers.get(x), true);
						break;

						case "HOLIDAY":	SpaceTypes.holiday();
						break;

						case "BABY": SpaceTypes.babySpace(listOfPlayers.get(x), false);
						break;

						case "SPIN_TO_WIN":	SpaceTypes.spinToWin(listOfPlayers, listOfPlayers.get(x), spinner, keyboard);
						break;
						}	
					}
				}

				// if player gets a second turn (and is not yet retired), do not print summary
				if (!secondTurn && !retired) {
					System.out.println("\n"+current_player+"'s updated details summary: ");
					listOfPlayers.get(x).printDetailsSummary();
				}
			}
		}
		return retiredList;
	}
	
	// determine winner of the game- evaluate each player's assets
	public static void endOfGame(ArrayList<Player> retiredList) {
		Player.determineWinner(retiredList);
		System.out.println("\nEnd of game!");
	}
}
