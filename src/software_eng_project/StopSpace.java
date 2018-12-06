package software_eng_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StopSpace extends Space {
	
	// constructor
	public StopSpace(String number, String type, List<String> next) {
		super(number, type, next);
	}
	
	// method for graduation stop (player must choose a career from collegeCareerCardDeck)
	public static void graduation(Player player, ArrayList<CareerCards> collegeCareerCardList) {
	    System.out.println("Happy graduation!");
		player.changeCareer(collegeCareerCardList);
	}
	
	// method for wedding stop space 
	public static int wedding(ArrayList<Player> listOfPlayers, Spinner spinner, int currentPlayer, Scanner keyboard) {
		Player player = listOfPlayers.get(currentPlayer);
		System.out.println("Wedding bells!");
		
		// calls method to update marital status of player and to receive money from other players
		player.getMarried(listOfPlayers, player, spinner, keyboard);
		
		// married player gets another turn
		int x = player.takeExtraTurn(listOfPlayers, currentPlayer);
		return x;
	}
	
	// method to prompt user to choose family or life path
	public static void nightSchool(ArrayList<Space> boardSpacesList, Player player, int spaceNumber) {
		System.out.println("Family time! Choose family or life");
		int pathChosen = ChoosePath.choosePath(boardSpacesList.get(spaceNumber), "Family", "Life");
		ChoosePath.selectPath(pathChosen, player);
	}
	
	// method for nightschool stop space (player can choose whether to go to nightschool)
	public static int nightSchool(ArrayList<CareerCards> collegeCareerCardList, int currentPlayer, ArrayList<Player> listOfPlayers, ArrayList<Space> boardSpacesList, Player player, int spaceNumber, Utility utility) {
		//identifies the parameters in config.properties file
		String fee = utility.getProperty("fee");
		String nightSchool = utility.getProperty("nightSchoolPath");
		// convert to integers
		int nightSchoolFee = Integer.parseInt(fee);
		int nightSchoolPath = Integer.parseInt(nightSchool);
		
		
		System.out.println("Night school stop! Choose to change your career or keep your current job");
		
		// method asking player to choose their desired path
		int pathChosen = ChoosePath.choosePath(boardSpacesList.get(spaceNumber), "Changing Career (costs 100K)", "Keeping Current Job");
		
		// if player chooses to go to night school, deduct 100K
		if (pathChosen==nightSchoolPath) {
			while (player.getBalance() < nightSchoolFee) {
				player.takeLoan();
			}
			player.walletBalance(nightSchoolFee, "subtract");
			player.changeCareer(collegeCareerCardList);
		}
		
		// method to update current space of player
		ChoosePath.selectPath(pathChosen, player);
		
		// player gets an extra turn, regardless of choice
		int x = player.takeExtraTurn(listOfPlayers, currentPlayer);
		return x;
	}
	
	// method to prompt user to choose family or life path
	public static void familyOrLife(ArrayList<Space> boardSpacesList, Player player, int spaceNumber) {
		System.out.println("Family time! Choose family or life");
		int pathChosen = ChoosePath.choosePath(boardSpacesList.get(spaceNumber), "Family", "Life");
		ChoosePath.selectPath(pathChosen, player);
	}
	
	// method for haveChildren stop space- determines number of children player will have
	public static void haveChildren(Spinner spinner, int x, ArrayList<Player> listOfPlayers, Scanner keyboard) {
		Player player = listOfPlayers.get(x);
		System.out.println("You're expecting! Spin the spinner to find out how many babies you're having!");
		System.out.println("(1-3 = 0 kids. 4-6 = 1 kid. 7-8 = 2 kids. 9-10 = 3 kids.)");
		keyboard.nextLine();
		
		// spin spinner to determine number of children
		spinner.spinSpinner(listOfPlayers);
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