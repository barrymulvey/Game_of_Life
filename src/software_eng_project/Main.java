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
						    System.out.println("Happy graduation!");
							listOfPlayers.get(x).changeCareer(collegeCareerCardList);
						}
						//else if(space_number==27) {
						else if(space_type.contains("WEDDING")) {
						    break;/*
							System.out.println("Wedding bells!");
							listOfPlayers.get(x).getMarried(listOfPlayers, listOfPlayers.get(x), spinner);
							// Player gets another turn
							x = x-1;
							break;*/
						}
						//else if(space_number==39) {
						else if(space_type.contains("NIGHTSCHOOL")) {
						    System.out.println("Night school stop! What would you like to do?");
							System.out.println("1: Keep current job");
							System.out.println("2: Change career (costs 100K)");
							int night_choice = keyboard.nextInt();
							//keyboard.nextLine();
							if (night_choice==2) {
								while (listOfPlayers.get(x).getBalance() < 100) {
									listOfPlayers.get(x).takeLoan();
								}
								listOfPlayers.get(x).walletBalance(100, "subtract");
								listOfPlayers.get(x).changeCareer(collegeCareerCardList);
								x = x-1; // current player gets another turn
							}
						}
						//else if(space_number==68) {
						else if(space_type.contains("FAMILYORLIFE")) {
							System.out.println("Family time! Choose family or life");
							ChoosePath.selectPath(boardSpacesList.get(space_number), "Family", "Life", listOfPlayers.get(x));
						}
						//else if(space_number==78) {
						else if(space_type.contains("CHILDREN")) {
							System.out.println("You're expecting! Spin the spinner to find out how many babies you're having!");
							System.out.println("(1-3 = 0 kids. 4-6 = 1 kid. 7-8 = 2 kids. 9-10 = 3 kids.)");
							keyboard.nextLine();
							spinner.spinSpinner();
							int spinNum = spinner.getNumber();
							if (spinNum>=4 && spinNum<=6) {
								listOfPlayers.get(x).addChildren(1);
								System.out.println("Congrats! You had 1 kid.");
							}
							else if (spinNum==7 || spinNum==8) {
								listOfPlayers.get(x).addChildren(2);
								System.out.println("Congrats! You had 2 kids.");
							}
							else if (spinNum==9 || spinNum==10) {
								listOfPlayers.get(x).addChildren(3);
								System.out.println("Congrats! You had 3 kids.");
							}
							else System.out.println("No kids this time!");
						}
						//else if(space_number==95) System.out.println("Holiday - time to relax!");
						else if(space_type.contains("HOLIDAY")) {
							break;
						}
						break;
					}
					
					// Print spaces over which player travels
					if(y!=(moves-1)) {
						System.out.println(current_player+" moves past "+space_type+" on space "+current_space);
						if (space_type.contains("PAYDAY")) {
							int currentSalary = listOfPlayers.get(x).getSalary();
							listOfPlayers.get(x).walletBalance(currentSalary, "add");
							System.out.println(listOfPlayers.get(x).getName()+" receives salary of "+currentSalary+"K");
							System.out.println(listOfPlayers.get(x).getName()+"'s updated balance is: "+listOfPlayers.get(x).getBalance()+"K");
						}
					}
					
					// Print space on which player lands and take appropriate action
					if (y==moves-1) {
						System.out.println(current_player+" stopped on "+space_type+" on space "+current_space);
						switch(space_type) {
							case "ACTION": System.out.println(current_player+", press enter to draw an action card!");
										   keyboard.nextLine();
										   ActionCards.chooseActionCard(actionCardList, listOfPlayers.get(x), listOfPlayers, collegeCareerCardList);
										   break;
							case "PAYDAY": System.out.println("Payday!");
										   int currentSalary = listOfPlayers.get(x).getSalary();
										   listOfPlayers.get(x).walletBalance(currentSalary, "add");
										   listOfPlayers.get(x).walletBalance(100, "add");
										   System.out.println(listOfPlayers.get(x).getName()+" receives salary of "+currentSalary+"K plus bonus of 100K");
										   System.out.println(listOfPlayers.get(x).getName()+"'s updated balance is: "+listOfPlayers.get(x).getBalance()+"K");
										   break;
							case "HOUSE": //System.out.println("Draw house cards!");
										  System.out.println("\nProperty time!");
										  System.out.println("Enter 1 to buy a house");
										  System.out.println("Enter 2 to sell a house");
										  System.out.println("Enter 3 to do nothing");
										  System.out.println("Choose what you would like to do: ");
										  int houseChoice = keyboard.nextInt();
										  
										  if (houseChoice == 1) {
											  System.out.println(current_player+", press enter to draw 2 house cards!");
											  keyboard.nextLine();
											  HouseCards.buyHouse(houseCardList, listOfPlayers.get(x));
										  }
										  else if (houseChoice == 2) {
											  HouseCards.sellHouse(houseCardList, listOfPlayers.get(x));
										  }
										  else {
											  break;
										  }

										  //HouseCards.chooseHouseCard(houseCardList, listOfPlayers.get(x), listOfPlayers);
										  break;
							case "TWINS": System.out.println("Congrats, you had twins!");
										  listOfPlayers.get(x).addChildren(2);
										  break;
							case "HOLIDAY":	System.out.println("Holiday time!");
											break;
							case "BABY": System.out.println("Congrats, you had a baby!");
							  			 listOfPlayers.get(x).addChildren(1);
							             break;
							case "SPIN_TO_WIN":	System.out.println("Spin to Win!");
												
												int[] spinChoice = new int[listOfPlayers.size()+1];
												String[] playerSpinList = new String[listOfPlayers.size()+1];
												ArrayList<Player> temporaryPlayerList = new ArrayList<Player>();
												
												ArrayList<Integer> spinnerList=new ArrayList<>();
												for (int q=1;q<=10;q++) {
													spinnerList.add(q);
												}
												// Current player picks two numbers
												for (int w=0;w<=1;w++) {
													System.out.println("Numbers available: "+spinnerList);
													System.out.println(current_player+", enter a number from the list: ");
													int numChosen = keyboard.nextInt();
													
													for (int z=0;z<=spinnerList.size();z++) {
														if (spinnerList.contains(numChosen)) {
															int chosen = spinnerList.indexOf(numChosen);
															spinnerList.remove(chosen);
															spinChoice[w] = numChosen;
															playerSpinList[w] = current_player;
														}
													}
												}
												
												
												int numOfPlayers = listOfPlayers.size();
												// create list of players excluding current player
												for (int p=0;p<numOfPlayers;p++) {
													if (!listOfPlayers.get(p).getName().equals(current_player)) {
														Player playerToAdd = listOfPlayers.get(p);
														temporaryPlayerList.add(playerToAdd);
													}
												}
												
												
												int count = 0;
												// Other players pick one number each
												for (int w=2;w<listOfPlayers.size()+1;w++) {
													System.out.println("Numbers available: "+spinnerList);
													System.out.println(temporaryPlayerList.get(count).getName()+", enter a number from the list: ");
													int numChosen = keyboard.nextInt();
													
													for (int z=0;z<=spinnerList.size();z++) {
														if (spinnerList.contains(numChosen)) {
															int chosen = spinnerList.indexOf(numChosen);
															spinnerList.remove(chosen);
															spinChoice[w] = numChosen;
															playerSpinList[w] = temporaryPlayerList.get(count).getName();
														}
													}
													count++;
												}
												
												System.out.println("The numbers chosen are: ");
												for (int w=0;w<listOfPlayers.size()+1;w++) {
													System.out.println(playerSpinList[w]+": "+spinChoice[w]);
												}
												
												Player winningPlayer = null;
												boolean winner = false;
												while (winner == false) {	
													spinner.spinSpinner();
													int spinWinNum = spinner.getNumber();
													System.out.println(current_player+", press enter to spin the spinner!");
													keyboard.nextLine();
													System.out.println("The number spun is: "+spinWinNum);
													for (int w=0;w<listOfPlayers.size()+1;w++) {
														if (spinChoice[w]==spinWinNum) {
															System.out.println("Number matches! "+playerSpinList[w]+" wins 200K!");
															
															for(int i = 0; i<listOfPlayers.size(); i++) {
																if (playerSpinList[w].equals(listOfPlayers.get(i).getName())) {
																	winningPlayer = listOfPlayers.get(i);
																}
															}
															
															winningPlayer.walletBalance(200, "add");
															System.out.println(playerSpinList[w]+"'s updated balance is: "+winningPlayer.getBalance()+"K");
												            winner = true;
															break;
														}
														//else System.out.println("Number does not match");
													}
												}
												
												
												break;
							case "RETIREMENT": System.out.println("You made it, retirement!");
											   retiredList.add(listOfPlayers.get(x));
											   listOfPlayers.remove(x);
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
