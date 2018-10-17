package software_eng_project;

import software_eng_project.Player;

import java.util.ArrayList;
import java.util.List;

import software_eng_project.HouseCards;

public class Main {

	public static void main(String[] args) {
		
		// PLAYERS
		// initialise players manually for now (create function: initialise players)
		Player player1 = new Player("Brid", 22, 0.0, "blue", "life", "laywer", "town house");
		player1.printDetails();
		
		Player player2 = new Player("Barry", 23, 0.0, "green", "college", "student", "homeless");
		player2.printDetails();
		
		// HOUSE CARDS
		// house cards read in and sorted into array houseList
		String fileLocation = "C:\\Users\\barry\\eclipse-workspace\\game_of_life\\notes\\houseCards.txt";
		ArrayList<HouseCards> houseList = new ArrayList<HouseCards>();
		
		HouseCards house1 = new HouseCards(fileLocation);
		houseList = house1.getListOfCards();
		//house1.printCardDetails();
		//System.out.println(houseList.get(1).printCardDetails);
		
		HouseCards house2 = new HouseCards(fileLocation);
		house2 = houseList.get(0);
		house2.printCardDetails();
	}

}
