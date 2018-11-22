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
	
	public static void doAction(String cardChosen, Player player, ArrayList<Player> listOfPlayers) {
		
		// Player receives money from bank (add money)
		if (cardChosen.contains("Receive")) {
			if(cardChosen.contains("10")) {
				player.walletBalance(10, "add");
			}
			else if(cardChosen.contains("20")) {
				player.walletBalance(20, "add");
			}
			else if(cardChosen.contains("30")) {
				player.walletBalance(30, "add");
			}
			else if(cardChosen.contains("40")) {
				player.walletBalance(40, "add");
			}
			else if(cardChosen.contains("50")) {
				player.walletBalance(50, "add");
			}
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalanceWallet()+"K");
		}
		
		// Player pays money to bank (subtract money)
		if (cardChosen.contains("Pay bank")) {
			if(cardChosen.contains("10")) {
				player.walletBalance(10, "subtract");
			}
			else if(cardChosen.contains("20")) {
				player.walletBalance(20, "subtract");
			}
			else if(cardChosen.contains("30")) {
				player.walletBalance(30, "subtract");
			}
			else if(cardChosen.contains("40")) {
				player.walletBalance(40, "subtract");
			}
			else if(cardChosen.contains("50")) {
				player.walletBalance(50, "subtract");
			}
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalanceWallet()+"K");
		}
		
		// Player chooses another player and gets money off them
		if (cardChosen.contains("Players pay")) {

			Scanner keyboard = new Scanner(System.in);
			System.out.println("Choose a player! (Enter number)");
				
			// print list of players without current player
			int index = listOfPlayers.indexOf(player);
			listOfPlayers.remove(index);
			
			for(int x=1;x<=listOfPlayers.size();x++) {
				System.out.println(x+": "+listOfPlayers.get(x-1).getName());				
			}
			
			listOfPlayers.add(player);
			
			// read in chosen player to pay money
			int chosenPlayer = keyboard.nextInt();
			//keyboard.close();
			System.out.println("Chosen player is: "+listOfPlayers.get(chosenPlayer-1).getName());
			
			// receive money
			System.out.println(listOfPlayers.get(chosenPlayer-1).getName()+" pay "+player.getName()+" 20K!");
			player.walletBalance(20, "add");
			// pay money
			listOfPlayers.get(chosenPlayer-1).walletBalance(20, "subtract");
			
			System.out.println(player.getName()+"'s updated balance is: "+player.getBalanceWallet()+"K");
			System.out.println(listOfPlayers.get(chosenPlayer-1).getName()+"'s updated balance is: "+listOfPlayers.get(chosenPlayer-1).getBalanceWallet()+"K");
			
		}
		if (cardChosen.contains("Career Change")) {
			System.out.println("Career change!");
			//player.changeCareer(CareerCards.getListOfCards("careers_file"));
			//player.changeCareer(collegeCareerCardList);
		}
		
	}
	
	public static ArrayList<String> chooseActionCard (ArrayList<String> cardList, Player player, ArrayList<Player> listOfPlayers) {
        // Choose a card at random between 0 and 54
        int size = cardList.size();
		Random rand = new Random();
    	int  i = rand.nextInt(size);
    	String cardChosen = cardList.get(i);
    	
    	// Print number and type of card chosen
    	//System.out.println("Card chosen is " + i);
    	System.out.println("Card chosen is " + cardChosen);
    	
    	
    	// increment_number of action cards held by person
    	player.increment_num_action_cards();
    	ActionCards.doAction(cardChosen, player, listOfPlayers);
        
    	// Remove card and print updated deck
    	cardList.remove(i);
        //System.out.println("Updated Deck: " + cardList);
        return cardList;
    }
}
