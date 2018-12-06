package software_eng_project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class SpaceTypesTest {

	@Test
	void testStartGame() {
		// create instance of Utility
		Utility utility = Utility.getInstance();
		
		Player player = new Player("Brid", 22, 100, "blue", "College");
		
		int result = SpaceTypes.startGame(true, player, utility);
		int chosen = 4;
		
		assertEquals("startGame should return int 4", chosen, result);	
	}

}
