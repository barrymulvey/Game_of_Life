package software_eng_project;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//READ IN DETAILS OF ACTION CARDS FROM TEXT FILE
/* Deck of 55 cards. Composed of: 
 * Career Change (x5)
 * Players Pay (x10)
 * Pay the Bank (5x4): 4x10k; 4x20k; 4x30k; 4x40k; 4x50k
 * Get Cash from the Bank (5x4): 4x10k; 4x20k; 4x30k; 4x40k; 4x50k
 */

public class ActionCards extends Cards {

	// Constructor
	public ActionCards(String fileLocation) {
		super(fileLocation);
	}

	// Constructor
	public ActionCards(String fileLocation, String name, int value1) {
		super(fileLocation, name, value1);
	}

	// Card objects created and saved to an ArrayList
	public static ArrayList<ActionCards> getListOfCards(String fileLocation) {
		// read in list of all lines from text file
		List<String> inputList = Cards.readInCards(fileLocation);
		// make an arrayList of strings of the names of the action cards
		ArrayList<String> actionList = Cards.getNameList(inputList);
		// make an arrayList of strings of the value associated with each action card
		ArrayList<String> actionValue1List = Cards.getValue1List(inputList);

		// create ActionCards objects, using arrayLists above
		ArrayList<ActionCards> listOfCards = new ArrayList<ActionCards>();
		for (int x = 0; x<actionList.size(); x++) {
			// define value associated with each action card as an integer
			int amount = Integer.parseInt(actionValue1List.get(x));
			ActionCards actionCardObj = new ActionCards(fileLocation, actionList.get(x), amount);
			// add new ActionCards object to arrayList of ActionCards
			listOfCards.add(actionCardObj);
		}
		return listOfCards;
	}

	// Choose an ActionCards object at random
	public static ActionCards chooseActionCards(ArrayList<ActionCards> cardList) {
		// Choose a card at random between 0 and (number of cards available)-1
		int number_cards = cardList.size();
		Random rand1 = new Random();
		int  i = rand1.nextInt(number_cards-1);

		// choose a card and return the object
		return cardList.get(i);
	}
	
	// implement the action defined by the ActionCards object
	public static void doAction(ActionCards actionCard, Player player, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList, ArrayList<ActionCards> actionCardList, Scanner keyboard, Utility utility) {
		// get the title of the ActionCards object
		String action = actionCard.getName();
		
		// initialise output flag as false
		boolean flag = false;
		
		// switch statement to execute all possible actions
		switch(action) {
		
		// change career of player
		case "Career Change": System.out.println("Card chosen is "+action);
			flag = careerChange(player, collegeCareerCardList);
			break;
		// a selected player will pay current player 20K	
		case "Players pay": System.out.println("Card chosen is "+action);
			flag = playersPay(player, listOfPlayers, utility);
			break;
		// current player must pay bank the amount defined by the card
		case "Pay bank": System.out.println("Card chosen is "+action+" "+actionCard.getValue1()+"K");
			payBank(actionCard, player);
			break;
		// current player receives amount specified by card from the bank
		case "Receive": System.out.println("Card chosen is "+action+" "+actionCard.getValue1()+"K");
			receive(actionCard, player);
			break;
		// by default, draw another action card!
		default: SpaceTypes.actionSpace(player, actionCardList, listOfPlayers, collegeCareerCardList, keyboard, utility);
		}
		
		if(flag) {
			// if flag is true, this action card was not applicable and so current player must draw another
			System.out.println("\nThis Action Card is not applicable. Choose again!");
			SpaceTypes.actionSpace(player, actionCardList, listOfPlayers, collegeCareerCardList, keyboard, utility);
		}
	}

	// method defined for current player to receive amount specified by action card
	public static void receive(ActionCards actionCard, Player player) {
		// get amount specified by card and add it to current player's wallet
		player.walletBalance(actionCard.getValue1(), "add");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	// method defined for current player to pay the bank amount specified by action card
	public static void payBank(ActionCards actionCard, Player player) {
		// get amount specified by card and prompt current player to take out loans if balance is too low
		while (player.getBalance() < actionCard.getValue1()) {
			player.takeLoan();
		}
		
		// subtract amount from player's wallet
		player.walletBalance(actionCard.getValue1(), "subtract");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	// method to implement playersPay where current player selects a player to pay them 20K
	public static boolean playersPay(Player player, ArrayList<Player> listOfPlayers, Utility utility) {
		//identifies the parameter in config.properties file
		String playersPay = utility.getProperty("playersPay");
		// convert to integer
		int playersPayAmount = Integer.parseInt(playersPay);
		
		// initialise temporary array list
		ArrayList<Player> tempPlayerList = new ArrayList<>();
		// get number of players
		int numOfPlayers = listOfPlayers.size();
		// initialise output flag
		boolean flag = false;
		
		// if there is only 1 player left in game, this action card is not applicable, return flag
		if(numOfPlayers == 1) {
			flag = true;
			return flag;
		}
		
		// define temporary player list as list of all players excluding current player
		for (int p=0;p<numOfPlayers;p++) {
			if (!listOfPlayers.get(p).getName().equals(player.getName())) {
				Player playerToAdd = listOfPlayers.get(p);
				tempPlayerList.add(playerToAdd);
			}
		}

		// ask current player to choose another player 
		System.out.println("Choose a player from the list!");

		// print out all possible players to choose from
		for(int x=1;x<=tempPlayerList.size();x++) {
			System.out.println(x+": "+tempPlayerList.get(x-1).getName());				
		}

		// read in chosen player to pay money- error check for incorrect input
		int chosenPlayer = 0;
		chosenPlayer = ErrorCheck.rangeCheck("Enter number of player: ", 1, tempPlayerList.size(), chosenPlayer);
		
		//keyboard.close();
		System.out.println("Chosen player is: "+tempPlayerList.get(chosenPlayer-1).getName());

		System.out.println(tempPlayerList.get(chosenPlayer-1).getName()+" pay "+player.getName()+" "+playersPay+"!");

		// if selected player has insufficient funds to pay current player, prompt to take out loans
		while (tempPlayerList.get(chosenPlayer-1).getBalance() < playersPayAmount) {
			tempPlayerList.get(chosenPlayer-1).takeLoan();
		}

		// current player add money to wallet
		player.walletBalance(playersPayAmount, "add");
		// selected player subtract money from wallet
		tempPlayerList.get(chosenPlayer-1).walletBalance(playersPayAmount, "subtract");

		// print updated balances following transaction
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		System.out.println(tempPlayerList.get(chosenPlayer-1).getName()+"'s updated balance is: "+tempPlayerList.get(chosenPlayer-1).getBalance()+"K");
		
		return flag;
	}

	// method to change career of current player
	public static boolean careerChange(Player player, ArrayList<CareerCards> collegeCareerCardList) {
		System.out.println("Career change!");
		
		// initialise output flag
		boolean flag = false;
		
		// current player cannot change career if they are still a student, return flag
		if(player.getCareer().equals("Student")) {
			flag = true;
			return flag;
		}
		
		// if applicable, change career- draw two career cards
		player.changeCareer(collegeCareerCardList);
		return flag;
	}

	// method to draw an action card from deck
	public static ArrayList<ActionCards> chooseActionCard (ArrayList<ActionCards> cardList, Player player, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList, Scanner keyboard, Utility utility) {
		
		// Choose a card at random between 0 and 54
		int size = cardList.size();
		Random rand = new Random();
		int  i = rand.nextInt(size);
		ActionCards cardChosen = cardList.get(i);

		// increment_number of action cards held by person
		player.addActionCard();
		ActionCards.doAction(cardChosen, player, listOfPlayers, collegeCareerCardList, cardList, keyboard, utility);

		// Remove card and print updated deck
		cardList.remove(i);
		
		return cardList;
	}
}