package software_eng_project;

import software_eng_project.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Open keyboard to receive user input
		Scanner keyboard = new Scanner(System.in);
		
		// Create instance of Utility to read in parameter values from config.properties file
		Utility utility = Utility.getInstance();
		
		// create the spaces which make up the board
		ArrayList<Space> boardSpaces = new ArrayList<Space>();
		boardSpaces = Game.makeBoard(utility);
		
		// create the Action, House, Career and CollegeCareer card decks
		Deck cardDeck = new Deck();
		cardDeck = Game.makeCards(keyboard);
		
		// create the players
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers = Game.makePlayers(cardDeck.getCareerCardDeck(), keyboard, utility);
		
		// play the game
		Game.playGame(cardDeck, boardSpaces, listOfPlayers, keyboard, utility);
		
		// Close keyboard
		keyboard.close();
	}
}