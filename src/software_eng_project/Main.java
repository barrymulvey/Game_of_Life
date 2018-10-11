package software_eng_project;

import software_eng_project.Player;

public class Main {

	public static void main(String[] args) {
		
		Player player1 = new Player("Brid", 22, 0.0, "blue", "life", "laywer", "town house");
		player1.printDetails();
		
		Player player2 = new Player("Barry", 23, 0.0, "green", "college", "student", "homeless");
		player2.printDetails();

	}

}
