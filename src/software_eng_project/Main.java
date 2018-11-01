package software_eng_project;

import software_eng_project.Player;
import java.util.ArrayList;
import java.util.Scanner;

import software_eng_project.HouseCards;

public class Main {

	public static void main(String[] args) {
		// initialise players
		
		ArrayList<String> carColour = new ArrayList<String>();
		carColour.add("Pink");
		carColour.add("Blue");
		carColour.add("Green");
		carColour.add("Yellow");
		String userColour = null;
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many players? (2-4): ");
		int numPlayers = keyboard.nextInt();
		
		for (int i=1; i<=numPlayers; i++) {
			Player player = new Player(); 
			Player player1 = new Player(); 		
			player1 = player.initialisePlayer(carColour);
			
			String chosenColour = player1.getColour();
			carColour.remove(chosenColour);
			
			listOfPlayers.add(player1);
			}
		
		for (int x=0; x<listOfPlayers.size(); x++) {
			listOfPlayers.get(x).printDetails();
		}
		
		// HOUSE CARDS
		// house cards read in and sorted into array houseList
		String fileLocation = "C:\\Users\\barry\\eclipse-workspace\\game_of_life\\notes\\houseCards.txt";
		ArrayList<HouseCards> houseList = new ArrayList<HouseCards>();
		
		HouseCards house2 = new HouseCards(fileLocation);
		house2 = houseList.get(0);
		house2.printCardDetails();
		
		keyboard.close();
	}
}
