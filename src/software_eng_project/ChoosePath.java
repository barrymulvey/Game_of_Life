package software_eng_project;

import java.util.List;
import java.util.Scanner;

public class ChoosePath extends Space {

	public ChoosePath(String number, String type, List<String> next) {
		super(number, type, next);

	}

	public static void selectPath(Space currentSpace, String choice1, String choice2, Player player) {
		// open scanner
		Scanner keyboard = new Scanner(System.in);

		List<String> next_space_choices = currentSpace.getNextSpace();
		String nextSpace = null;
		System.out.println("Time to Choose Your Life Path!");
		System.out.println("Enter 1 for "+choice1+" or enter 2 for "+choice2+": ");
		int lifeChoice = keyboard.nextInt();
		nextSpace = Integer.toString(lifeChoice);

		if(lifeChoice == 1) {
			player.setCurrentSpace(next_space_choices.get(0));
		}
		else if (lifeChoice == 2) {
			player.setCurrentSpace(next_space_choices.get(1));
		}
		
		// TODO
		else {
			System.out.println("error");
		}
		

	}

}
