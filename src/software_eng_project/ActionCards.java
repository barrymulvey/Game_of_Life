package software_eng_project;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public abstract class ActionCards {
	/* Deck of 55 cards. Composed of: 
	 * Career Change (x5)
	 * Players Pay (x10)
	 * Pay the Bank (5x4): 4x10k; 4x20k; 4x30k; 4x40k; 4x50k
	 * Get Cash from the Bank (5x4): 4x10k; 4x20k; 4x30k; 4x40k; 4x50k
	 */
	
	public static ArrayList<String> getListOfCards () {
        ArrayList<String> cardList = new ArrayList<String>(55);
        
        // Add cards to deck
        for (int i=0;i<=54;i++) {
        	if (i<=4) {
        		cardList.add("Career Change");
        	}
        	else if (i<=14) {
        		cardList.add("Players pay");
        	}
        	else if (i<=18) { 
        		cardList.add("Pay bank 10K");
        	}
        	else if (i<=22) { 
        		cardList.add("Pay bank 20K");
        	}
        	else if (i<=26) { 
        		cardList.add("Pay bank 30K");
        	}
        	else if (i<=30) { 
        		cardList.add("Pay bank 40K");
        	}
        	else if (i<=34) { 
        		cardList.add("Pay bank 50K");
        	}
        	else if (i<=38) { 
        		cardList.add("Receive 10K");
        	}
        	else if (i<=42) { 
        		cardList.add("Receive 20K");
        	}
        	else if (i<=46) { 
        		cardList.add("Receive 30K");
        	}
        	else if (i<=50) { 
        		cardList.add("Receive 40K");
        	}
        	else if (i<=54) { 
        		cardList.add("Receive 50K");
        	}
        }
        
        // Print full deck details
        System.out.println("Full Deck: " + cardList);
	return cardList;
	}
	
	
	
	public static ArrayList<String> chooseActionCard (ArrayList<String> cardList) {
        // Choose a card at random between 0 and 54
        Random rand = new Random();
    	int  i = rand.nextInt(54);
    	
    	// Print number and type of card chosen
    	System.out.println("Card chosen is " + i);
    	System.out.println("Card chosen is " + cardList.get(i));
        
    	// Remove card and print updated deck
    	cardList.remove(i);
        System.out.println("Updated Deck: " + cardList);
        return cardList;
    }
}
