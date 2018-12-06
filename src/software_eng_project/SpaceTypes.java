package software_eng_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpaceTypes extends Space {

	// constructor
	public SpaceTypes(String number, String type, List<String> next) {
		super(number, type, next);
	}

	// method to start the game- this method sets player's on the college/career path
	public static int startGame(boolean college, Player player, Utility utility) {
		//identifies the parameters in config.properties file
		String collegePathString = utility.getProperty("collegePath");
		String careerPathString = utility.getProperty("careerPath");
		// convert to integers
		int collegePath = Integer.parseInt(collegePathString);
		int careerPath = Integer.parseInt(careerPathString);
		int nextSpace = 0;
		
		if(college) nextSpace = collegePath;
		else nextSpace = careerPath;

		ChoosePath.selectPath(nextSpace, player);
		return nextSpace;
	}

	// method to increment player's balance
	public static void Payday (Player player, boolean bonus, Utility utility) {
		//identifies the parameter in config.properties file
		String bonusAmount = utility.getProperty("bonus");
		// convert to integer
		int bonusMoney = Integer.parseInt(bonusAmount);
		
		System.out.println("Payday!");
		int currentSalary = player.getSalary();
		player.walletBalance(currentSalary, "add");
		
		// bonus = true if player lands on payday space- they get a bonus of 100K
		if(bonus) {
			player.walletBalance(bonusMoney, "add");
			System.out.println(player.getName()+" receives bonus of "+bonusMoney+"K");
		}
		System.out.println(player.getName()+" receives salary of "+currentSalary+"K");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	// method to prompt user to draw an action card
	public static void actionSpace(Player player, ArrayList<ActionCards> actionCardList, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList, Scanner keyboard, Utility utility) {
		System.out.println(player.getName()+", press enter to draw an action card!");
		keyboard.nextLine();
		// this method will implement action associated with selected card
		ActionCards.chooseActionCard(actionCardList, player, listOfPlayers, collegeCareerCardList, keyboard, utility);
	}
	
	// method to ask user to buy/sell a house or do nothing (options when land on house space)
	public static void houseSpace(Player player, ArrayList<HouseCards> houseCardList, ArrayList<Player> listOfPlayers) {
		boolean flag = true;
		while (flag == true) {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\nProperty time!");
			System.out.println("Enter 1 to buy a house");
			System.out.println("Enter 2 to sell a house");
			System.out.println("Enter 3 to do nothing");
			int houseChoice = 0;
			
			// check for valid user input
			houseChoice = ErrorCheck.rangeCheck("Choose what you would like to do: ", 1, 3, houseChoice);

			// if choose to buy a house, prompt to draw two house cards
			if (houseChoice == 1) {
				System.out.println(player.getName()+", press enter to draw 2 house cards!");
				keyboard.nextLine();
				// call method to update player's details and deduct money 
				HouseCards.buyHouse(houseCardList, player);
				flag = false;
			}
			
			// if choose to sell a house, check first if they have any houses
			// if they have houses, prompt user to choose one 
			else if (houseChoice == 2) {
				flag = HouseCards.sellHouse(houseCardList, player, listOfPlayers, keyboard);
			}
			else 
				flag = false;
		}
	}

	// increments number children of player by 1 or 2 if land on twins space
	public static void babySpace(Player player, boolean twins) {
		if(twins) {
			System.out.println("Congrats, you had twins!");
			player.addChildren(2);
		}
		else {
			System.out.println("Congrats, you had a baby!");
			player.addChildren(1);
		}
	}

	// method to print message to user
	public static void holiday() {
		System.out.println("Holiday - time to relax!");
	}

	// method to implement spin to win game
	public static void spinToWin(ArrayList<Player> listOfPlayers, Player player, Spinner spinner, Scanner keyboard) {
		System.out.println("Spin to Win!");

		int[] spinChoice = new int[listOfPlayers.size()+1];
		String[] playerSpinList = new String[listOfPlayers.size()+1];
		ArrayList<Player> temporaryPlayerList = new ArrayList<Player>();

		// create an ArrayList of integer values from 1-10
		ArrayList<Integer> spinnerList=new ArrayList<>();
		for (int q=1;q<=10;q++) {
			spinnerList.add(q);
		}

		// Current player picks two numbers
		for (int w=0;w<=1;w++) {
			String spinnerListString = spinnerList.toString().replace("[","").replace("]","");
			System.out.println("Numbers available: "+spinnerListString);
			
			// check for valid user input
			int numChosen = 0;
			numChosen = ErrorCheck.containsIntCheck(player.getName()+", enter a number from the list: ", spinnerList, numChosen);

			// update arrays with player's chosen number and name
			for (int z=0;z<=spinnerList.size();z++) {
				if (spinnerList.contains(numChosen)) {
					int chosen = spinnerList.indexOf(numChosen);
					spinnerList.remove(chosen);
					spinChoice[w] = numChosen;
					playerSpinList[w] = player.getName();
				}
			}
		}

		// create list of players excluding current player
		int numOfPlayers = listOfPlayers.size();
		for (int p=0;p<numOfPlayers;p++) {
			if (!listOfPlayers.get(p).getName().equals(player.getName())) {
				Player playerToAdd = listOfPlayers.get(p);
				temporaryPlayerList.add(playerToAdd);
			}
		}

		// Other players pick one number each
		int count = 0;
		for (int w=2;w<listOfPlayers.size()+1;w++) {
			String spinnerListString = spinnerList.toString().replace("[","").replace("]","");
			System.out.println("Numbers available: "+spinnerListString);
			int numChosen = 0;
			numChosen = ErrorCheck.containsIntCheck(temporaryPlayerList.get(count).getName()+", enter a number from the list: ", spinnerList, numChosen);

			// update arrays with user's number choice and name
			for (int z=0;z<=spinnerList.size();z++) {
				if (spinnerList.contains(numChosen)) {
					int chosen = spinnerList.indexOf(numChosen);
					spinnerList.remove(chosen);
					spinChoice[w] = numChosen;
					playerSpinList[w] = temporaryPlayerList.get(count).getName();
				}
			}
			// increment counter
			count++;
		}
		
		// print results		
		System.out.println("The numbers chosen are: ");
		for (int w=0;w<listOfPlayers.size()+1;w++) {
			System.out.println(playerSpinList[w]+": "+spinChoice[w]);
		}

		// spin spinner until a player wins
		Player winningPlayer = null;
		boolean winner = false;
		System.out.println("");
		while (winner == false) {	
			System.out.println(player.getName()+", press enter to spin the spinner!");
			keyboard.nextLine();

			// check if number spun matches any selected by players and if so which player
			spinner.spinSpinner(listOfPlayers);
			int spinWinNum = spinner.getNumber();
			for (int w=0;w<listOfPlayers.size()+1;w++) {
				if (spinChoice[w]==spinWinNum) {
					System.out.println("Number matches! "+playerSpinList[w]+" wins 200K!");

					for(int i = 0; i<listOfPlayers.size(); i++) {
						if (playerSpinList[w].equals(listOfPlayers.get(i).getName())) {
							winningPlayer = listOfPlayers.get(i);
						}
					}

					// update winning players balance
					winningPlayer.walletBalance(200, "add");
					System.out.println(playerSpinList[w]+"'s updated balance is: "+winningPlayer.getBalance()+"K");
					winner = true;
					break;
				}
			}
		}
	}

	// method to retire a player and update the retired ArrayList of players
	public static int retirementSpace(ArrayList<Player> listOfPlayers, ArrayList<Player> retiredList, Player player, int numRetired, ArrayList<HouseCards> listOfCards, Scanner keyboard) {
		System.out.println("You made it, retirement!");

		// call method to add up players assets and update wallet balance
		player.retirePlayer(numRetired, listOfCards, listOfPlayers, keyboard);
		System.out.println("\n"+player.getName()+"'s updated details summary: ");
		player.printDetailsSummary();

		// add retired player to retiredList
		retiredList.add(player);
		// remove retired player from game
		listOfPlayers.remove(player);
		// increment counter indicating number of players retired
		numRetired = numRetired+1;
		return numRetired;
	}
}