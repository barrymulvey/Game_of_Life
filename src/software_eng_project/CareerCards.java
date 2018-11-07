package software_eng_project;

import java.util.*;

// READ IN DETAILS FROM TEXT FILE
public class CareerCards extends Cards {
	private int value2;

	// constructor
	public CareerCards(String fileLocation) {
		super(fileLocation);
	}
	
	public CareerCards(String fileLocation, String name, int value1, int value2) {
		super(fileLocation, name, value1);
		this.value2 = value2;
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
	
	// card objects created and saved to an ArrayList
	public static ArrayList<CareerCards> getListOfCards(String fileLocation) {
		//CareerCards careerCard1 = new CareerCards(fileLocation); 
		List<String> inputList = Cards.readInCards(fileLocation);
		ArrayList<String> careerNameList = Cards.getNameList(inputList);
		ArrayList<String> careerValue1List = Cards.getValue1List(inputList);
		ArrayList<String> careerValue2List = CareerCards.getValue2List(inputList);

		ArrayList<CareerCards> listOfCards = new ArrayList<CareerCards>();
		for (int x = 0; x<careerNameList.size(); x++) {
			// name, purchase price, sale price red, sale price black
			int salary = Integer.parseInt(careerValue1List.get(x));
			int bonus = Integer.parseInt(careerValue2List.get(x));
			CareerCards careerCardObj = new CareerCards(fileLocation, careerNameList.get(x), salary, bonus);
			listOfCards.add(careerCardObj);
		}
		return listOfCards;
	}
	
	public int getValue2() {
		return value2;
	}
	
	// PRINT DETAILS OVERRIDE
	@Override
	public void printCardDetails() {
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Career Type: "+getName()+"\nSalary: "+getValue1()+"\nBonus Number: "+getValue2());
		System.out.println("*** *** *** *** *** *** *** ***");	
	}
}