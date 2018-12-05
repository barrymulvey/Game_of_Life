package software_eng_project;

import java.util.List;

// class to choose one of two paths at a crossroads on the board
public class ChoosePath extends Space {

	// constructor
	public ChoosePath(String number, String type, List<String> next) {
		super(number, type, next);
	}

	// choosePath method, takes choices and currentSpace Space object as inputs
	public static int choosePath(Space currentSpace, String choice1, String choice2) {
		// inform user of choices and save choice they enter
		System.out.println("Time to choose your path!");
		int lifeChoice = 0;
		lifeChoice = ErrorCheck.rangeCheck("Enter 1 for "+choice1+" or enter 2 for "+choice2+": ", 1, 2, lifeChoice);
		
		// get next space choices associated with currentSpace Space object
		List<String> next_space_choices = currentSpace.getNextSpace();
		
		// Choose either element 0 or 1 of next_space_choices
		// next space choice integer is returned, this is the space number describing where current player is on the board
		String nextSpaceChoice = next_space_choices.get(lifeChoice-1);
		int nextSpace = Integer.parseInt(nextSpaceChoice);
		return nextSpace;
	}

	// method to set the players current space attribute to the next space
	public static void selectPath(int nextSpace, Player player) {
		String nextSpaceString = Integer.toString(nextSpace-1);
		player.setCurrentSpace(nextSpaceString);
	}
}
