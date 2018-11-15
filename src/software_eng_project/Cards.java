package software_eng_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// READ IN DETAILS FROM TEXT FILE
public abstract class Cards {
	protected String fileLocation;
	protected String name;
	protected int value1;

	// constructor
	public Cards(String fileLocation/*, String name, int value1*/) {
		this.fileLocation = fileLocation;
		//this.name = name;
		//this.value1 = value1;
	}
	
	public Cards(String fileLocation, String name, int value1) {
		this.fileLocation = fileLocation;
		this.name = name;
		this.value1 = value1;
	}

	// read in details of cards from text file, create card objects, return list of card objects
	public static List<String> readInCards(String fileLocation) {
		List<String> inputList = null;
		// attempt to read in list of cards and details from text file
		try {
			inputList = Files.readAllLines(Paths.get(fileLocation));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputList;
	}

	public static ArrayList<String> getNameList(List<String> inputList) {
		// names of cards saved to ArrayList
		ArrayList<String> nameList = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String cardName = inputList.get(x).split("---")[0];
			nameList.add(cardName);
			//System.out.println(cardName);
		}
		return nameList;
	}

	public static ArrayList<String> getValue1List(List<String> inputList) {
		// names of cards saved to ArrayList
		ArrayList<String> value1List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String cardName = inputList.get(x).split("---")[1];
			value1List.add(cardName);
			//System.out.println(cardName);
		}
		return value1List;
	}

	/*
	// Check this: 
	public static Cards chooseCards(ArrayList<Cards> cardList) {
		
		
		Cards card1 = cardList.get(0);
		if (card1 instanceof CareerCards) {
			
		}
		
		
		// Choose a card #1 at random between 0 and (number of cards available)-1
		int number_cards = cardList.size();
		Random rand1 = new Random();
		int  i = rand1.nextInt(number_cards-1);

		// Print number and type of card chosen
		System.out.println("Card chosen is " + i);
		System.out.println("Card chosen is " + cardList.get(i).getName());

		// Choose card #2 at random between 0 and (number of houses available) -1
		Random rand = new Random();
		int j = rand.nextInt(number_cards-1);

		// Print number and type of card chosen
		System.out.println("Card chosen is " + j);
		System.out.println("Card chosen is " + cardList.get(j).getName());

		// only choose one for now
		return cardList.get(i);

	}
	*/

	

	public String getName() {
		return name;
	}
	public int getValue1() {
		return value1;
	}

	public ArrayList<Cards> removeCard(ArrayList<Cards> cardList) {
		for (int x=0; x<cardList.size(); x++) {

			if (this.name == cardList.get(x).getName()) {
				cardList.remove(x);
			}
		}

		System.out.println(this.name + " has been removed from the card deck"); // include name of card deck

		return cardList;
	}

	/*
	// PRINT DETAILS
	public void printCardDetails() {
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Name: "+getName()+"\nValue 1: "+getValue1());
		System.out.println("*** *** *** *** *** *** *** ***");	
	}
	*/
}