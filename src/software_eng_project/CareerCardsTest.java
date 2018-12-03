package software_eng_project;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CareerCardsTest {
	
	@Test
	void testGetName() {
		
		// initialise career card deck
		ArrayList<CareerCards> careerCardList = new ArrayList<CareerCards>();
		careerCardList = InitialiseGame.initialiseCareerCardDeck("careers_file");
		
		String cardName = "Actor";
		String result = careerCardList.get(0).getName();
		
		assertEquals("getName output should be equal to name of card", cardName, result);
	}
	
	@Test
	void testGetValue1() {
		
		// initialise career card deck
		ArrayList<CareerCards> careerCardList = new ArrayList<CareerCards>();
		careerCardList = InitialiseGame.initialiseCareerCardDeck("careers_file");
		
		int cardName = 100;
		int result = careerCardList.get(0).getValue1();
		
		assertEquals("getValue1 output should be equal to value 1 of card", cardName, result);
	}
	
	@Test
	void testGetValue2() {
		
		// initialise career card deck
		ArrayList<CareerCards> careerCardList = new ArrayList<CareerCards>();
		careerCardList = InitialiseGame.initialiseCareerCardDeck("careers_file");
		
		int cardName = 5;
		int result = careerCardList.get(0).getValue2();
		
		assertEquals("getValue1 output should be equal to value 1 of card", cardName, result);
	}
}
	