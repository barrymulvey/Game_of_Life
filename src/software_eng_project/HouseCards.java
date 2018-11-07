package software_eng_project;

import java.util.*;

// READ IN DETAILS FROM TEXT FILE
public class HouseCards extends Cards {
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
	
	// PRINT DETAILS OVERRIDE
	@Override
	public void printCardDetails() {
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("House Type: "+getName()+"\nPurchase Price: "+getValue1()+"\nSale Price (red spin): "+getValue2()+"\nSale Price (black spin): " +getValue3());
		System.out.println("*** *** *** *** *** *** *** ***");	
	}
}