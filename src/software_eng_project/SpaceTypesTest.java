package software_eng_project;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class SpaceTypesTest {

	@Test
	void testStartGame() {
		Player player = new Player("Brid", 22, 100, "blue", "College");
		
		int result = SpaceTypes.startGame(true, player);
		int chosen = 4;
		
		assertEquals("startGame should return int 4", chosen, result);	
	}

}
