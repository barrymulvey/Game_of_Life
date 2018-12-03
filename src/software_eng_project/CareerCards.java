package software_eng_project;

import java.util.*;

// READ IN DETAILS FROM TEXT FILE
public class CareerCards extends Cards {
	
	// CareerCards objects has one attribute to add to those inherited from Cards
	private int value2;

	// constructor
	public CareerCards(String fileLocation) {
		super(fileLocation);
	}

	// constructor
	public CareerCards(String fileLocation, String name, int value1, int value2) {
		super(fileLocation, name, value1);
		this.value2 = value2;
	}

	// method to return an ArrayList of strings containing all value2 entries in the text file
	public static ArrayList<String> getValue2List(List<String> inputList) {
		// value2 entries are stored in the third position of each line of the text file
		ArrayList<String> value2List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String cardName = inputList.get(x).split("---")[2];
			value2List.add(cardName);
		}
		return value2List;
	}	

	// CareerCards objects are created and saved to an ArrayList of CareerCards
	public static ArrayList<CareerCards> getListOfCards(String fileLocation) {
		// read in career cards details from text file
		List<String> inputList = Cards.readInCards(fileLocation);
		// create an ArrayList of Strings of the names of the career cards
		ArrayList<String> careerNameList = Cards.getNameList(inputList);
		// create an ArrayList of Strings of the value1s of the career cards
		ArrayList<String> careerValue1List = Cards.getValue1List(inputList);
		// create an ArrayList of Strings of the value2s of the career cards
		ArrayList<String> careerValue2List = CareerCards.getValue2List(inputList);

		// create ArrayList of CareerCards objects using ArrayLists above
		ArrayList<CareerCards> listOfCards = new ArrayList<CareerCards>();
		for (int x = 0; x<careerNameList.size(); x++) {
			int salary = Integer.parseInt(careerValue1List.get(x));
			int bonus = Integer.parseInt(careerValue2List.get(x));
			CareerCards careerCardObj = new CareerCards(fileLocation, careerNameList.get(x), salary, bonus);
			listOfCards.add(careerCardObj);
		}
		return listOfCards;
	}

	// method to return value2 associated with career card
	public int getValue2() {
		return value2;
	}

	// method to choose a CareerCards object
	public static CareerCards chooseCareerCards(ArrayList<CareerCards> cardList) {
		// Choose a card at random between 0 and (number of cards available)-1
		int number_cards = cardList.size();
		Random rand1 = new Random();
		int  i = rand1.nextInt(number_cards-1);

		// return chosen CareerCards object
		return cardList.get(i);
	}

}