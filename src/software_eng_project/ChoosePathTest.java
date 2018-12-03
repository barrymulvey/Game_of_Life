package software_eng_project;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ChoosePathTest {


	@Test
	void TestChoosePath() {
		
		List<String> nextSpaces = new ArrayList<String>();
		String choice1 = "Family";
		String choice2 = "Life";
		nextSpaces.add("69");
		nextSpaces.add("79");
		Space currentSpace = new Space("68",  "STOP_FAMILYORLIFE", nextSpaces);

		int result = ChoosePath.choosePath(currentSpace, choice1, choice2);
		System.out.println("result: "+result);
		
		// user should choose choice1 i.e. the 'family' option for testing here
		int chosen = 69;
		
		// user should choose choice2 i.e. the 'life' option for testing here
		//int chosen = 79;
		
		assertEquals("choosePath should return an integer value of the next space chosen by user input", chosen, result);
	}

}
