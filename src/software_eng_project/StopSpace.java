package software_eng_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StopSpace extends Space {
	
	public StopSpace(String number, String type, List<String> next) {
		super(number, type, next);
		// TODO Auto-generated constructor stub
	}
	
	public static void graduation(Player player, ArrayList<CareerCards> collegeCareerCardList) {
	    System.out.println("Happy graduation!");
		player.changeCareer(collegeCareerCardList);
	}
	
	public static int wedding(Player player, ArrayList<Player> listOfPlayers, Spinner spinner, int currentTurn) {
	    System.out.println("Wedding bells!");
		player.getMarried(listOfPlayers, player, spinner);
		// Player gets another turn
		currentTurn = currentTurn-1;
		return currentTurn;
	}
	
	public static int nightSchool(Player player, ArrayList<CareerCards> collegeCareerCardList, int currentTurn) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Night school stop! What would you like to do?");
		System.out.println("1: Keep current job");
		System.out.println("2: Change career (costs 100K)");
		int nightChoice = keyboard.nextInt();
		//keyboard.nextLine();
		if (nightChoice==2) {
			while (player.getBalance() < 100) {
				player.takeLoan();
			}
			player.walletBalance(100, "subtract");
			player.changeCareer(collegeCareerCardList);
			currentTurn = currentTurn-1; // current player gets another turn
		}
		return currentTurn;
	}
	
	public static void familyOrLife(ArrayList<Space> boardSpacesList, Player player, int spaceNumber) {
		System.out.println("Family time! Choose family or life");
		ChoosePath.selectPath(boardSpacesList.get(spaceNumber), "Family", "Life", player);
	}
	
	public static void haveChildren(Spinner spinner, Player player) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("You're expecting! Spin the spinner to find out how many babies you're having!");
		System.out.println("(1-3 = 0 kids. 4-6 = 1 kid. 7-8 = 2 kids. 9-10 = 3 kids.)");
		keyboard.nextLine();
		spinner.spinSpinner();
		int spinNum = spinner.getNumber();
		if (spinNum>=4 && spinNum<=6) {
			player.addChildren(1);
			System.out.println("Congrats! You had 1 kid");
		}
		else if (spinNum==7 || spinNum==8) {
			player.addChildren(2);
			System.out.println("Congrats! You had 2 kids");
		}
		else if (spinNum==9 || spinNum==10) {
			player.addChildren(3);
			System.out.println("Congrats! You had 3 kids");
		}
		else System.out.println("No kids this time!");
	}
	
		
}
