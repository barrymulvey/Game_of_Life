package software_eng_project;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

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
        
        /*
        // Print full deck details
        System.out.println("Full Deck: " + cardList);
        */
	return cardList;
	}
	
	public static void doAction(String cardChosen, Player player, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList) {
		
		int getValue = 0;
		// Player receives money from bank (add money)
		if (cardChosen.contains("Receive")) {
			if(cardChosen.contains("10")) {
				getValue = 10;
			}
			else if(cardChosen.contains("20")) {
				getValue = 20;
			}
			else if(cardChosen.contains("30")) {
				getValue = 30;
			}
			else if(cardChosen.contains("40")) {
				getValue = 40;
			}
			else if(cardChosen.contains("50")) {
				getValue = 50;
			}
			player.walletBalance(getValue, "add");
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		}
		
		int payValue = 0;
		// Player pays money to bank (subtract money)
		if (cardChosen.contains("Pay bank")) {
			if(cardChosen.contains("10")) {
				payValue = 10;
			}
			else if(cardChosen.contains("20")) {
				payValue = 20;
			}
			else if(cardChosen.contains("30")) {
				payValue = 30;
			}
			else if(cardChosen.contains("40")) {
				payValue = 40;
			}
			else if(cardChosen.contains("50")) {
				payValue = 50;
			}
			
			while (player.getBalance() < 100) {
				player.takeLoan();
			}
			player.walletBalance(payValue, "subtract");
			
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
		}
		
		// Player chooses another player and gets money off them
		if (cardChosen.contains("Players pay")) {
			ArrayList<Player> tempPlayerList = new ArrayList<>();
			int numOfPlayers = listOfPlayers.size();
			
			for (int p=0;p<numOfPlayers;p++) {
				if (!listOfPlayers.get(p).getName().equals(player.getName())) {
					Player playerToAdd = listOfPlayers.get(p);
					tempPlayerList.add(playerToAdd);
				}
			}
			
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Choose a player! (Enter number)");
				
			for(int x=1;x<=tempPlayerList.size();x++) {
				System.out.println(x+": "+tempPlayerList.get(x-1).getName());				
			}
			
			//listOfPlayers.add(player);
			
			// read in chosen player to pay money
			int chosenPlayer = keyboard.nextInt();
			//keyboard.close();
			System.out.println("Chosen player is: "+tempPlayerList.get(chosenPlayer-1).getName());
			
			// receive money
			System.out.println(tempPlayerList.get(chosenPlayer-1).getName()+" pay "+player.getName()+" 20K!");
			
			while (tempPlayerList.get(chosenPlayer-1).getBalance() < 20) {
				tempPlayerList.get(chosenPlayer-1).takeLoan();
			}
			
			player.walletBalance(20, "add");
			// pay money
			tempPlayerList.get(chosenPlayer-1).walletBalance(20, "subtract");
			
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalance()+"K");
			System.out.println(tempPlayerList.get(chosenPlayer-1).getName()+"'s updated balance is: "+tempPlayerList.get(chosenPlayer-1).getBalance()+"K");
			
		}
		if (cardChosen.contains("Career Change")) {
			System.out.println("Career change!");
			player.changeCareer(collegeCareerCardList);
		}
		
	}
	
	public static ArrayList<String> chooseActionCard (ArrayList<String> cardList, Player player, ArrayList<Player> listOfPlayers, ArrayList<CareerCards> collegeCareerCardList) {
        // Choose a card at random between 0 and 54
        int size = cardList.size();
		Random rand = new Random();
    	int  i = rand.nextInt(size);
    	String cardChosen = cardList.get(i);
    	
    	// Print number and type of card chosen
    	//System.out.println("Card chosen is " + i);
    	System.out.println("Card chosen is " + cardChosen);
    	
    	
    	// increment_number of action cards held by person
    	player.addActionCard();
    	ActionCards.doAction(cardChosen, player, listOfPlayers, collegeCareerCardList);
        
    	// Remove card and print updated deck
    	cardList.remove(i);
        //System.out.println("Updated Deck: " + cardList);
        return cardList;
    }
}
