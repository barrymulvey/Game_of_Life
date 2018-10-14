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
		HouseCards houseCardP1 = new HouseCards("test house", 400, 5, 6);
		ArrayList<HouseCards> houseList = new ArrayList<HouseCards>();
		houseList = houseCardP1.readInHouseCards("C:\\Users\\B Roberts\\OneDrive - University College Dublin\\5th Year\\SoftwareEngineering\\GameOfLife\\houseCards.txt");
		HouseCards house1 = houseList.get(0);
		house1.printHouseDetails();
		System.out.println(houseList.size());
		house1.removeHouseCard(houseList);
		
		// remove a house
		HouseCards house2 = houseList.get(0);
		house2.printHouseDetails();
		System.out.println(houseList.size());

	}

}
