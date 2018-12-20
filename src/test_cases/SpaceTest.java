package test_cases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import software_eng_project.InitialiseGame;
import software_eng_project.Space;
import software_eng_project.Utility;

class SpaceTest {

	@Test
	void testGetSpaceType() {
		// create new instance of Utility to read in properties from config.properties file
		Utility utility = Utility.getInstance();
		
		// Read in spaces and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard(utility);
		
		String result = boardSpacesList.get(1).getSpaceType();
		String chosen = "ACTION";
		
		assertEquals("getSpaceType should return String 'ACTION'", chosen, result);	
	}
	
	@Test
	void testGetNextSpace() {
		// create new instance of Utility to read in properties from config.properties file
		Utility utility = Utility.getInstance();
		
		// Read in spaces and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard(utility);
		
		List<String> result = boardSpacesList.get(1).getNextSpace();
		List<String> chosen = new ArrayList<String>();
		chosen.add("2");
		
		assertEquals("getNextSpace should return String '2' ", chosen, result);	
	}
	
	@Test
	void testGetNumberSpace() {
		
		// create new instance of Utility to read in properties from config.properties file
		Utility utility = Utility.getInstance();
		
		// Read in spaces and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard(utility);
		
		String result = boardSpacesList.get(1).getNumberSpace();
		String chosen = "1";
		
		assertEquals("getSpaceType should return String '1'", chosen, result);	

	}

}
