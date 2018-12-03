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
	
	public HouseCards(String fileLocation, String name, int value1, int value2, int value3) {
		super(fileLocation, name, value1);
		this.value2 = value2;
		this.value3 = value3;
	}

	// Add methods
	public static ArrayList<String> getValue2List(List<String> inputList) {
		// names of houses saved to ArrayList
		ArrayList<String> value2List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String cardName = inputList.get(x).split("---")[2];
			value2List.add(cardName);
			//System.out.println(cardName);
		}
		return value2List;
	}	

	public static ArrayList<String> getValue3List(List<String> inputList) {
		// names of houses saved to ArrayList
		ArrayList<String> value3List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String cardName = inputList.get(x).split("---")[3];
			value3List.add(cardName);
			//System.out.println(cardName);
		}
		return value3List;
	}
	
	// card objects created and saved to an ArrayList
	public static ArrayList<HouseCards> getListOfCards(String fileLocation) {
		//HouseCards houseCard1 = new HouseCards(fileLocation); 
		List<String> inputList = Cards.readInCards(fileLocation);
		ArrayList<String> houseNameList = Cards.getNameList(inputList);
		ArrayList<String> houseValue1List = Cards.getValue1List(inputList);
		ArrayList<String> houseValue2List = HouseCards.getValue2List(inputList);
		ArrayList<String> houseValue3List = HouseCards.getValue3List(inputList);
		
		ArrayList<HouseCards> listOfCards = new ArrayList<HouseCards>();
		for (int x = 0; x<houseNameList.size(); x++) {
			// name, purchase price, sale price red, sale price black
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
	
	
public static HouseCards chooseHouseCards(ArrayList<HouseCards> cardList) {
		// Choose a card at random between 0 and (number of cards available)-1
		int number_cards = cardList.size();
		Random rand1 = new Random();
		int  i = rand1.nextInt(number_cards-1);

		// choose a card
		return cardList.get(i);
	}
	
	
	public static void buyHouse(ArrayList<HouseCards> listOfCards, Player player) {
		// choose 2 career cards
		HouseCards card1 = HouseCards.chooseHouseCards(listOfCards);
		System.out.println("1st card chosen is: "+card1.getName()+" with a purchase price of "+card1.getValue1()+"K");
		listOfCards.remove(card1);

		HouseCards card2 = HouseCards.chooseHouseCards(listOfCards);
		System.out.println("2nd card chosen is: "+card2.getName()+" with a purchase price of "+card2.getValue1()+"K");

		System.out.println("Choose a card! Enter 1 or 2: ");
		Scanner keyboard = new Scanner(System.in);
		int houseCardChoice = keyboard.nextInt();
		HouseCards chosenCard = null;

		if (houseCardChoice==1) {
			chosenCard = card1;
			player.addHouse(card1);
		}
		else if (houseCardChoice==2) {
			chosenCard = card2;
			player.addHouse(card2);
			listOfCards.remove(card2);
			listOfCards.add(card1);
		}
		else {
			//player.career = null;
			System.out.println("Error - choice must be 1 or 2.");
		}
		
		int purchasePrice = chosenCard.getValue1();
		
		while (player.getBalance() < purchasePrice) {
			player.takeLoan();
		}
		
		player.walletBalance(purchasePrice, "subtract");
		
		System.out.println(player.getName()+" buys "+chosenCard.getName()+" for "+chosenCard.getValue1()+"K");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		
		
		//return listOfCards;
		//keyboard.close();
	}
	
	public static boolean sellHouse(ArrayList<HouseCards> listOfCards, Player player, ArrayList<Player> listOfPlayers) {
		boolean flag = false;
		int houseListSize = player.getHouses().size();
		
		if (houseListSize<=0) {
			System.out.println("You don't have any houses to sell - you must choose another option!");
			flag = true;
			return flag;
		}
		
		for (int x=0;x<houseListSize;x++) {
			HouseCards currentHouse = player.getHouses().get(x);
			System.out.println((x+1)+": "+currentHouse.getName()+" with a red sale price of "+currentHouse.getValue2()+"K and a black sale price of "+currentHouse.getValue3()+"K");
		}
		System.out.println("Enter number of house to sell: ");
		Scanner keyboard = new Scanner(System.in);
		int houseCardChoice = keyboard.nextInt();
		HouseCards chosenCard = player.getHouses().get(houseCardChoice-1);
		
		Spinner spinner = new Spinner();
		
		// spin spinner and print results
		System.out.println(player.getName()+", press enter to spin the spinner!");
		keyboard.nextLine();
		
		spinner.spinSpinner(listOfPlayers);
		int houseValue = 0;
		
		if (spinner.getColour().equals("red")) {
			houseValue = chosenCard.getValue2();
		}
		else houseValue = chosenCard.getValue3();
		
		player.removeHouse(chosenCard);
		player.walletBalance(houseValue, "add");
		System.out.println(player.getName()+", your house has been sold for "+houseValue+"K");
		System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		
		listOfCards.add(chosenCard); // add sold house back to House card deck
		return flag;
	}
	
	public static void sellAllHouses(ArrayList<HouseCards> listOfCards, Player player, ArrayList<Player> listOfPlayers) {
		int houseListSize = player.getHouses().size();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nTime to sell your houses!");
		
		for (int x=0;x<houseListSize;x++) {
			HouseCards currentHouse = player.getHouses().get(0);
			//System.out.println((x+1)+": "+currentHouse.getName()+" with a red sale price of "+currentHouse.getValue2()+"K and a black sale price of "+currentHouse.getValue3()+"K");
			
			Spinner spinner = new Spinner();
			
			// spin spinner and print results
			System.out.println(player.getName()+", press enter to spin the spinner!");
			keyboard.nextLine();
			
			spinner.spinSpinner(listOfPlayers);
			int houseValue = 0;
			
			if (spinner.getColour().equals("red")) {
				houseValue = currentHouse.getValue2();
			}
			else houseValue = currentHouse.getValue3();
			
			player.removeHouse(currentHouse);
			player.walletBalance(houseValue, "add");
			System.out.println(player.getName()+", "+currentHouse.getName()+" has been sold for "+houseValue+"K");
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
			
			listOfCards.add(currentHouse); // add sold house back to House card deck
		
		
		}
		
		
	}
	
	/*
	// PRINT DETAILS OVERRIDE
	@Override
	public void printCardDetails() {
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("House Type: "+getName()+"\nPurchase Price: "+getValue1()+"\nSale Price (red spin): "+getValue2()+"\nSale Price (black spin): " +getValue3());
		System.out.println("*** *** *** *** *** *** *** ***");	
	}
	*/
}