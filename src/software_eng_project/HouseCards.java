package software_eng_project;

import java.util.*;

// READ IN DETAILS FROM TEXT FILE
public class HouseCards extends Cards {

	// HouseCards objects have two attributes to add to those inherrited from Cards
	private int value2;
	private int value3;

	// constructor
	public HouseCards(String fileLocation) {
		super(fileLocation);
	}

	// constructor
	public HouseCards(String fileLocation, String name, int value1, int value2, int value3) {
		super(fileLocation, name, value1);
		this.value2 = value2;
		this.value3 = value3;
	}

	// method to construct ArrayList of Strings of the value2 attributes of the HouseCards objects
	public static ArrayList<String> getValue2List(List<String> inputList) {

		// associated value2 Strings are in the third position of each line in the text file
		ArrayList<String> value2List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++) {
			String cardName = inputList.get(x).split("---")[2];
			value2List.add(cardName);
		}
		return value2List;
	}	

	// method to construct ArrayList of Strings of value3 Strings associated with HouseCards objects
	public static ArrayList<String> getValue3List(List<String> inputList) {

		// associated value3 Strings are in the 4th position of each line of the text file
		ArrayList<String> value3List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++) {
			String cardName = inputList.get(x).split("---")[3];
			value3List.add(cardName);
		}
		return value3List;
	}

	// method to create an ArrayList of HouseCards objects
	public static ArrayList<HouseCards> getListOfCards(String fileLocation) {

		// create a List of Strings of the lines in the text file
		List<String> inputList = Cards.readInCards(fileLocation);
		// create an ArrayList of Strings of names associated with HouseCards
		ArrayList<String> houseNameList = Cards.getNameList(inputList);
		// create ArrayList of Strings of value1 associated with HouseCards
		ArrayList<String> houseValue1List = Cards.getValue1List(inputList);
		// create ArrayList of Strings of value2 associated with HouseCards
		ArrayList<String> houseValue2List = HouseCards.getValue2List(inputList);
		// create ArrayList of Strings of value3 associated with HouseCards
		ArrayList<String> houseValue3List = HouseCards.getValue3List(inputList);

		// create ArrayList of HouseCards objects
		ArrayList<HouseCards> listOfCards = new ArrayList<HouseCards>();
		for (int x = 0; x<houseNameList.size(); x++) {
			int purchasePrice = Integer.parseInt(houseValue1List.get(x));
			int salePriceRed = Integer.parseInt(houseValue2List.get(x));
			int salePriceBlack = Integer.parseInt(houseValue3List.get(x));
			HouseCards houseCardObj = new HouseCards(fileLocation, houseNameList.get(x), purchasePrice, salePriceRed, salePriceBlack);
			listOfCards.add(houseCardObj);
		}
		return listOfCards;
	}

	public int getValue2() {
		return value2;
	}
	public int getValue3() {
		return value3;
	}

	// method to select a HouseCards object
	public static HouseCards chooseHouseCards(ArrayList<HouseCards> cardList) {
		// Choose a card at random between 0 and (number of cards available)-1
		int number_cards = cardList.size();
		Random rand1 = new Random();
		int  i = rand1.nextInt(number_cards-1);

		// return HouseCards object selected
		return cardList.get(i);
	}


	// method to buy a house (i.e. add HouseCards object to house attribute of player)
	public static void buyHouse(ArrayList<HouseCards> listOfCards, Player player) {
		// select 2 HouseCards objects from deck
		HouseCards card1 = HouseCards.chooseHouseCards(listOfCards);
		System.out.println("1st card chosen is: "+card1.getName()+" with a purchase price of "+card1.getValue1()+"K");
		// remove HouseCard from deck
		listOfCards.remove(card1);

		HouseCards card2 = HouseCards.chooseHouseCards(listOfCards);
		System.out.println("2nd card chosen is: "+card2.getName()+" with a purchase price of "+card2.getValue1()+"K");

		// ask user to choose a card
		System.out.println("Choose a card! Enter 1 or 2: ");
		// open scanner and save user input
		Scanner keyboard = new Scanner(System.in);
		int houseCardChoice = keyboard.nextInt();
		HouseCards chosenCard = null;

		// if choose card1, update player with corresponding HouseCards object
		if (houseCardChoice==1) {
			chosenCard = card1;
			player.addHouse(card1);
		}
		// if choose card2, update player with corresponding HouseCards object
		else if (houseCardChoice==2) {
			chosenCard = card2;
			// if card 2 chosen, add card 1 back to deck and instead remove card1
			player.addHouse(card2);
			listOfCards.remove(card2);
			listOfCards.add(card1);
		}
		else {
			System.out.println("Error - choice must be 1 or 2.");
		}

		// prompt user to take out loan if player's wallet has insufficient funds to buy selected house
		// keep asking them to take out loans until player has enough money to buy house
		int purchasePrice = chosenCard.getValue1();
		while (player.getBalance() < purchasePrice) {
			player.takeLoan();
		}
		
		// subtract price of house from player's wallet
		player.walletBalance(purchasePrice, "subtract");

		// print player's updated details
		System.out.println(player.getName()+" buys "+chosenCard.getName()+" for "+chosenCard.getValue1()+"K");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
	}

	// method to choose a house to see
	public static boolean sellHouse(ArrayList<HouseCards> listOfCards, Player player, ArrayList<Player> listOfPlayers) {
		// initialise output flag
		boolean flag = false;
		int houseListSize = player.getHouses().size();

		// return flag if player doesn't own any houses
		if (houseListSize<=0) {
			System.out.println("You don't have any houses to sell - you must choose another option!");
			flag = true;
			return flag;
		}
		
		// ask user which house they would like to sell (loop through player's attribute 'houses')
		for (int x=0;x<houseListSize;x++) {
			HouseCards currentHouse = player.getHouses().get(x);
			System.out.println((x+1)+": "+currentHouse.getName()+" with a red sale price of "+currentHouse.getValue2()+"K and a black sale price of "+currentHouse.getValue3()+"K");
		}
		
		// save user's choice of which house to sell
		System.out.println("Enter number of house to sell: ");
		Scanner keyboard = new Scanner(System.in);
		int houseCardChoice = keyboard.nextInt();
		HouseCards chosenCard = player.getHouses().get(houseCardChoice-1);

		// spin spinner to determine sale price of selected house
		Spinner spinner = new Spinner();
		System.out.println(player.getName()+", press enter to spin the spinner!");
		keyboard.nextLine();
		spinner.spinSpinner(listOfPlayers);
		
		// assign sale price depending on if spinner lands on a black/red number
		int houseValue = 0;
		if (spinner.getColour().equals("red")) {
			houseValue = chosenCard.getValue2();
		}
		else houseValue = chosenCard.getValue3();

		// remove chosen HouseCard object from players attribute 'houses'
		player.removeHouse(chosenCard);
		// add sale price to player's wallet
		player.walletBalance(houseValue, "add");
		// print updated details
		System.out.println(player.getName()+", your house has been sold for "+houseValue+"K");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		
		// add sold house back to House card deck
		listOfCards.add(chosenCard); 
		return flag;
	}

	// method to see all of a player's houses (when they retire)
	public static void sellAllHouses(ArrayList<HouseCards> listOfCards, Player player, ArrayList<Player> listOfPlayers) {
		int houseListSize = player.getHouses().size();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nTime to sell your houses!");

		for (int x=0;x<houseListSize;x++) {
			HouseCards currentHouse = player.getHouses().get(0);

			// spin spinner and print results
			Spinner spinner = new Spinner();
			System.out.println(player.getName()+", press enter to spin the spinner!");
			keyboard.nextLine();
			spinner.spinSpinner(listOfPlayers);
			
			// sale price depends on if spinner lands on black/red number
			int houseValue = 0;
			if (spinner.getColour().equals("red")) {
				houseValue = currentHouse.getValue2();
			}
			else houseValue = currentHouse.getValue3();

			// remove HouseCards object from player's attribute 'houses'
			player.removeHouse(currentHouse);
			// add sale price to player's wallet
			player.walletBalance(houseValue, "add");
			// print updated details
			System.out.println(player.getName()+", "+currentHouse.getName()+" has been sold for "+houseValue+"K");
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");

			// add sold house back to House card deck
			listOfCards.add(currentHouse); 
		}
	}
}