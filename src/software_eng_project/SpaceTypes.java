package software_eng_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpaceTypes extends Space {

	public SpaceTypes(String number, String type, List<String> next) {
		super(number, type, next);
	}

	public static int startGame(boolean college, Player player) {
		int nextSpace = 0;
		if(college) nextSpace = 4;
		else nextSpace = 1;

		ChoosePath.selectPath(nextSpace, player);
		return nextSpace;
	}

	public static void Payday (Player player, boolean bonus) {
		System.out.println("Payday!");
		int currentSalary = player.getSalary();
		player.walletBalance(currentSalary, "add");
		if(bonus) {
			player.walletBalance(100, "add");
			System.out.println(player.getName()+" receives bonus of 100K");
		}
		System.out.println(player.getName()+" receives salary of "+currentSalary+"K");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	public static void actionSpace(Player player, ArrayList<ActionCards> actionCardList, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList, Scanner keyboard) {
		System.out.println(player.getName()+", press enter to draw an action card!");
		keyboard.nextLine();
		ActionCards.chooseActionCard(actionCardList, player, listOfPlayers, collegeCareerCardList, keyboard);
	}

	public static void houseSpace(Player player, ArrayList<HouseCards> houseCardList, ArrayList<Player> listOfPlayers) {
		boolean flag = true;
		while (flag == true) {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("\nProperty time!");
			System.out.println("Enter 1 to buy a house");
			System.out.println("Enter 2 to sell a house");
			System.out.println("Enter 3 to do nothing");
			int houseChoice = 0;
			houseChoice = ErrorCheck.rangeCheck("Choose what you would like to do: ", 1, 3, houseChoice);

			if (houseChoice == 1) {
				System.out.println(player.getName()+", press enter to draw 2 house cards!");
				keyboard.nextLine();
				HouseCards.buyHouse(houseCardList, player);
				flag = false;
			}
			else if (houseChoice == 2) {
				//int houseListSize = player.getHouses().size();
				flag = HouseCards.sellHouse(houseCardList, player, listOfPlayers, keyboard);
			}
			else 
				flag = false;
		}
	}

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

	public static void holiday() {
		System.out.println("Holiday - time to relax!");
	}

	public static void spinToWin(ArrayList<Player> listOfPlayers, Player player, Spinner spinner, Scanner keyboard) {
		System.out.println("Spin to Win!");

		int[] spinChoice = new int[listOfPlayers.size()+1];
		String[] playerSpinList = new String[listOfPlayers.size()+1];
		ArrayList<Player> temporaryPlayerList = new ArrayList<Player>();

		ArrayList<Integer> spinnerList=new ArrayList<>();
		for (int q=1;q<=10;q++) {
			spinnerList.add(q);
		}

		// Current player picks two numbers
		for (int w=0;w<=1;w++) {
			String spinnerListString = spinnerList.toString().replace("[","").replace("]","");
			System.out.println("Numbers available: "+spinnerListString);
			
			int numChosen = 0;
			numChosen = ErrorCheck.containsIntCheck(player.getName()+", enter a number from the list: ", spinnerList, numChosen);

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
		// print results		
		System.out.println("The numbers chosen are: ");
		for (int w=0;w<listOfPlayers.size()+1;w++) {
			System.out.println(playerSpinList[w]+": "+spinChoice[w]);
		}

		Player winningPlayer = null;
		boolean winner = false;
		System.out.println("");
		while (winner == false) {	
			System.out.println(player.getName()+", press enter to spin the spinner!");
			keyboard.nextLine();

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

	public static int retirementSpace(ArrayList<Player> listOfPlayers, ArrayList<Player> retiredList, Player player, int numRetired, ArrayList<HouseCards> listOfCards, Scanner keyboard) {
		System.out.println("You made it, retirement!");

		player.retirePlayer(numRetired, listOfCards, listOfPlayers, keyboard);
		System.out.println("\n"+player.getName()+"'s updated details summary: ");
		player.printDetailsSummary();

		retiredList.add(player);
		listOfPlayers.remove(player);
		numRetired = numRetired+1;
		return numRetired;
	}
}


