package software_eng_project;

import java.util.List;
import java.util.Scanner;

public class ChoosePath extends Space {

	public ChoosePath(String number, String type, List<String> next) {
		super(number, type, next);

	}

	public static int choosePath(Space currentSpace, String choice1, String choice2) {
		// open scanner
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Time to Choose Your Life Path!");
		System.out.println("Enter 1 for "+choice1+" or enter 2 for "+choice2+": ");
		int lifeChoice = keyboard.nextInt();
		
		List<String> next_space_choices = currentSpace.getNextSpace();
		
		//String nextSpace = null;
		String nextSpaceChoice = next_space_choices.get(lifeChoice-1);
		int nextSpace = Integer.parseInt(nextSpaceChoice);
		return nextSpace;
		
	}

	public static void selectPath(int nextSpace, Player player) {
		String nextSpaceString = Integer.toString(nextSpace-1);
		player.setCurrentSpace(nextSpaceString);

	}

}
