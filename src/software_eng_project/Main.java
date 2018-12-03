package software_eng_project;

import software_eng_project.Player;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

import software_eng_project.HouseCards;

public class Main {

	public static void main(String[] args) {
		// open scanner
		Scanner keyboard = new Scanner(System.in);
		
		// Initialise house deck
		ArrayList<HouseCards> houseCardList = new ArrayList<HouseCards>();
		houseCardList = InitialiseGame.initialiseHouseDeck();

		// initialise career card deck
		ArrayList<CareerCards> careerCardList = new ArrayList<CareerCards>();
		careerCardList = InitialiseGame.initialiseCareerCardDeck("careers_file");
		
		// create list of players and initialise
		ArrayList<Player> listOfPlayers = new ArrayList<Player>();
		listOfPlayers = InitialiseGame.initialisePawns(careerCardList);
		ArrayList<Player> retiredList = new ArrayList<Player>();
		
		// initialise college career card deck
		ArrayList<CareerCards> collegeCareerCardList = new ArrayList<CareerCards>();
		collegeCareerCardList = InitialiseGame.initialiseCareerCardDeck("college_careers_file");

		// initialise action card deck
		ArrayList<ActionCards> actionCardList = new ArrayList<ActionCards>();
		actionCardList = InitialiseGame.initialiseActionCardDeck("action_file");

		// Read in spaces and save to arraylist
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard();

		// Create a new spinner
		Spinner spinner = new Spinner();

		ArrayList<Cards> cardDecks = new ArrayList<Cards>();
		cardDecks.addAll(houseCardList);
		cardDecks.addAll(careerCardList);
		cardDecks.addAll(collegeCareerCardList);
		cardDecks.addAll(actionCardList);
		
		
		
		
		// PLAYERS PLAY		
		ArrayList<Player> retiredPlayerList = new ArrayList<Player>();
		retiredPlayerList = PlayGame.playersPlay(listOfPlayers, retiredList, spinner, keyboard, boardSpacesList, collegeCareerCardList, houseCardList, actionCardList);
		PlayGame.EndOfGame(retiredPlayerList);
		


		keyboard.close();
	}
}
