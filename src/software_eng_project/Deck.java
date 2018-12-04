package software_eng_project;

import java.util.ArrayList;

public class Deck {
	
	private ArrayList<ActionCards> actionCardDeck;
	private ArrayList<CareerCards> careerCardDeck;
	private ArrayList<CareerCards> collegeCareerCardDeck;
	private ArrayList<HouseCards> houseCardDeck;
	
	// deck constructor
	public Deck(ArrayList<ActionCards> actionCardDeck, ArrayList<CareerCards> careerCardDeck, ArrayList<CareerCards> collegeCareerCardDeck, ArrayList<HouseCards> houseCardDeck){
		this.actionCardDeck = actionCardDeck;
		this.careerCardDeck = careerCardDeck;
		this.collegeCareerCardDeck = collegeCareerCardDeck;
		this.houseCardDeck = houseCardDeck;
	}
	
	// empty constructor
	public Deck() {
		this.actionCardDeck = null;
		this.careerCardDeck = null;
		this.collegeCareerCardDeck = null;
		this.houseCardDeck = null;
	}
	
	// return the action card deck
	public ArrayList<ActionCards> getActionCardDeck(){
		return actionCardDeck;
	}
	
	// return the career card deck
	public ArrayList<CareerCards> getCareerCardDeck(){
		return careerCardDeck;
	}
	
	// return the college career card deck
	public ArrayList<CareerCards> getCollegeCareerCardDeck(){
		return collegeCareerCardDeck;
	}
	
	// return the house card deck
	public ArrayList<HouseCards> getHouseCardDeck(){
		return houseCardDeck;
	}
}
