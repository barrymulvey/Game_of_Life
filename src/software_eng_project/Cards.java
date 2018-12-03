package software_eng_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// READ IN DETAILS FROM TEXT FILE TO CREATE CARDS OBJECTS

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
	
	// constructor
	public Cards(String fileLocation, String name, int value1) {
		this.fileLocation = fileLocation;
		this.name = name;
		this.value1 = value1;
	}

	// read in details of cards from text file, create card objects, return list of card objects
	public static List<String> readInCards(String fileLocation) {
		List<String> inputList = null;
		
		// attempt to read in list of cards and details from text file
		// throw exception if file not found or if error in reading from file
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

	// populate an ArrayList of strings with names of cards
	public static ArrayList<String> getNameList(List<String> inputList) {
		ArrayList<String> nameList = new ArrayList<String>();
		// name of card is first element in each line of text file
		for (int x = 3; x < inputList.size(); x++)
		{
			String cardName = inputList.get(x).split("---")[0];
			nameList.add(cardName);
		}
		return nameList;
	}

	// make an ArrayList of the first value present in the text file
	public static ArrayList<String> getValue1List(List<String> inputList) {
		// value1 is the second element on each line of the text file
		ArrayList<String> value1List = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String value1 = inputList.get(x).split("---")[1];
			value1List.add(value1);
		}
		return value1List;
	}
	
	
	// method to return name of Cards object
	public String getName() {
		return name;
	}
	
	// method to return value1 of Cards object
	public int getValue1() {
		return value1;
	}
	
	// method to remove a Cards object from the deck (i.e. the ArrayList of Cards objects)
	public ArrayList<Cards> removeCard(ArrayList<Cards> cardList) {
		for (int x=0; x<cardList.size(); x++) {

			if (this.name == cardList.get(x).getName()) {
				cardList.remove(x);
			}
		}
		System.out.println(this.name + " has been removed from the card deck"); // include name of card deck
		return cardList;
	}
}