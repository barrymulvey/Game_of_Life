package software_eng_project;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class SpaceTest {

	@Test
	void testGetSpaceType() {
		// Read in spaces and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard();
		
		String result = boardSpacesList.get(1).getSpaceType();
		String chosen = "ACTION";
		
		assertEquals("getSpaceType should return String 'ACTION'", chosen, result);	
	}
	
	@Test
	void testGetNextSpace() {
		// Read in spaces and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard();
		
		List<String> result = boardSpacesList.get(1).getNextSpace();
		List<String> chosen = new ArrayList<String>();
		chosen.add("2");
		
		assertEquals("getNextSpace should return String '2' ", chosen, result);	
	}
	
	@Test
	void testGetNumberSpace() {
		// Read in spaces and save to ArrayList
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		boardSpacesList = InitialiseGame.initialiseBoard();
		
		String result = boardSpacesList.get(1).getNumberSpace();
		String chosen = "1";
		
		assertEquals("getSpaceType should return String '1'", chosen, result);	

	}

}
