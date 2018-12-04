package software_eng_project;

import java.util.ArrayList;

public class Spinner {
	int value;
	int spin;
	String colour;
	
	// constructor
	protected Spinner() {
		value = 10;
		spin = 0;
		colour = null;
	}
	
	// method to spin the spinner to select a random number 1-10
	protected void spinSpinner(ArrayList<Player> listOfPlayers) {				
        // spin is a random number in the range 1-10
        this.spin = (int) (Math.random()*value) + 1;
        
        // print colour of value (black if even)
        if (spin%2==0) {
        	this.colour = "black";
        }
        // if value of spin is odd
        else {
        	this.colour = "red";
        }
        
        // print info
        System.out.println("Spin value: "+getNumber()+"\nColour: "+getColour());
        
        // check if any player's bonus number = number spun
        giveBonus(listOfPlayers, spin);
    }
	
	// method to return number spun
	public int getNumber() {
		return spin;
	}
	
	// method to return colour associated with number spun
	public String getColour() {
		return colour;
	}
	
	// method to allocate bonus to a player if their bonus number is selected
	public static void giveBonus(ArrayList<Player> listOfPlayers, int spin) {
		int numPlayers = listOfPlayers.size();
		for (int x=0;x<numPlayers; x++) {
			// increase balance of player if bonus number selected
			if (spin == listOfPlayers.get(x).getBonus()) {
				System.out.println("\n"+listOfPlayers.get(x).getName()+"'s bonus number matches the number spun");
				System.out.println(listOfPlayers.get(x).getName()+" receives a bonus of 20K!");
				listOfPlayers.get(x).walletBalance(20, "add");	
				
				// print updated balances following transaction
				System.out.println(listOfPlayers.get(x).getName()+"'s updated balance is: "+listOfPlayers.get(x).getBalance()+"K\n");
			}
		}
	}	
}