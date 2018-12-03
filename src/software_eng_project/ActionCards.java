package software_eng_project;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//READ IN DETAILS FROM TEXT FILE
/* Deck of 55 cards. Composed of: 
 * Career Change (x5)
 * Players Pay (x10)
 * Pay the Bank (5x4): 4x10k; 4x20k; 4x30k; 4x40k; 4x50k
 * Get Cash from the Bank (5x4): 4x10k; 4x20k; 4x30k; 4x40k; 4x50k
 */
public class ActionCards extends Cards {

	// constructor
	public ActionCards(String fileLocation) {
		super(fileLocation);
	}

	public ActionCards(String fileLocation, String name, int value1) {
		super(fileLocation, name, value1);
	}

	// card objects created and saved to an ArrayList
	public static ArrayList<ActionCards> getListOfCards(String fileLocation) {
		//ActionCards careerCard1 = new ActionCards(fileLocation); 
		List<String> inputList = Cards.readInCards(fileLocation);
		ArrayList<String> actionList = Cards.getNameList(inputList);
		ArrayList<String> actionValue1List = Cards.getValue1List(inputList);

		ArrayList<ActionCards> listOfCards = new ArrayList<ActionCards>();
		for (int x = 0; x<actionList.size(); x++) {
			// name, purchase price, sale price red, sale price black
			int amount = Integer.parseInt(actionValue1List.get(x));
			ActionCards actionCardObj = new ActionCards(fileLocation, actionList.get(x), amount);
			listOfCards.add(actionCardObj);
		}
		return listOfCards;
	}


	public static ActionCards chooseActionCards(ArrayList<ActionCards> cardList) {

		// Choose a card at random between 0 and (number of cards available)-1
		int number_cards = cardList.size();
		Random rand1 = new Random();
		int  i = rand1.nextInt(number_cards-1);

		// choose a card
		return cardList.get(i);
	}
	
	public static void doAction(ActionCards actionCard, Player player, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList, ArrayList<ActionCards> actionCardList) {
		String action = actionCard.getName();
		boolean flag = false;
		switch(action) {
		case "Career Change": System.out.println("Card chosen is "+action);
			flag = careerChange(player, collegeCareerCardList);
			break;
		case "Players pay": System.out.println("Card chosen is "+action);
			flag = playersPay(player, listOfPlayers);
			break;
		case "Pay bank": System.out.println("Card chosen is "+action+" "+actionCard.getValue1()+"K");
			payBank(actionCard, player);
			break;
		case "Receive": System.out.println("Card chosen is "+action+" "+actionCard.getValue1()+"K");
			receive(actionCard, player);
			break;
		default: SpaceTypes.actionSpace(player, actionCardList, listOfPlayers, collegeCareerCardList);
		}
		
		if(flag) {
			System.out.println("\nThis Action Card is not applicable. Choose again!");
			SpaceTypes.actionSpace(player, actionCardList, listOfPlayers, collegeCareerCardList);
		}
	}

	public static void receive(ActionCards actionCard, Player player) {
		player.walletBalance(actionCard.getValue1(), "add");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	public static void payBank(ActionCards actionCard, Player player) {
		while (player.getBalance() < actionCard.getValue1()) {
			player.takeLoan();
		}
		player.walletBalance(actionCard.getValue1(), "subtract");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	public static boolean playersPay(Player player, ArrayList<Player> listOfPlayers) {
		ArrayList<Player> tempPlayerList = new ArrayList<>();
		int numOfPlayers = listOfPlayers.size();
		boolean flag = false;
		
		if(numOfPlayers == 1) {
			flag = true;
			return flag;
		}
		for (int p=0;p<numOfPlayers;p++) {
			if (!listOfPlayers.get(p).getName().equals(player.getName())) {
				Player playerToAdd = listOfPlayers.get(p);
				tempPlayerList.add(playerToAdd);
			}
		}

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Choose a player! (Enter number)");

		for(int x=1;x<=tempPlayerList.size();x++) {
			System.out.println(x+": "+tempPlayerList.get(x-1).getName());				
		}

		//listOfPlayers.add(player);

		// read in chosen player to pay money
		int chosenPlayer = 0;
		
		chosenPlayer = ErrorCheck.rangeCheck("", 1, tempPlayerList.size(), chosenPlayer);
		
		//keyboard.close();
		System.out.println("Chosen player is: "+tempPlayerList.get(chosenPlayer-1).getName());

		// receive money
		System.out.println(tempPlayerList.get(chosenPlayer-1).getName()+" pay "+player.getName()+" 20K!");

		while (tempPlayerList.get(chosenPlayer-1).getBalance() < 20) {
			tempPlayerList.get(chosenPlayer-1).takeLoan();
		}

		player.walletBalance(20, "add");
		// pay money
		tempPlayerList.get(chosenPlayer-1).walletBalance(20, "subtract");

		// print updated balances following transaction
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		System.out.println(tempPlayerList.get(chosenPlayer-1).getName()+"'s updated balance is: "+tempPlayerList.get(chosenPlayer-1).getBalance()+"K");
		
		return flag;
	}

	public static boolean careerChange(Player player, ArrayList<CareerCards> collegeCareerCardList) {
		System.out.println("Career change!");
		boolean flag = false;
		if(player.getCareer().equals("Student")) {
			flag = true;
			return flag;
		}
		player.changeCareer(collegeCareerCardList);
		return flag;
	}

	public static ArrayList<ActionCards> chooseActionCard (ArrayList<ActionCards> cardList, Player player, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList) {
		// Choose a card at random between 0 and 54
		int size = cardList.size();
		Random rand = new Random();
		int  i = rand.nextInt(size);
		ActionCards cardChosen = cardList.get(i);

		// Print number and type of card chosen
		//System.out.println("Card chosen is " + i);
		//System.out.println("Card chosen is " + cardChosen.getName());


		// increment_number of action cards held by person
		player.addActionCard();
		ActionCards.doAction(cardChosen, player, listOfPlayers, collegeCareerCardList, cardList);

		// Remove card and print updated deck
		cardList.remove(i);
		//System.out.println("Updated Deck: " + cardList);
		return cardList;
}
	}