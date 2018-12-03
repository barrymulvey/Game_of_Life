package software_eng_project;

import java.util.ArrayList;

public class Spinner {
	int value;
	int spin;
	String colour;
	
	protected Spinner() {
		value = 10;
		spin = 0;
		colour = null;
	}
	
	protected void spinSpinner(ArrayList<Player> listOfPlayers) {
    //public static void main(String[] args) {
        //int value = 10;   // number of values on spinner
				
        // spin is a random number in the range 1-10
        this.spin = (int) (Math.random()*value) + 1;

        // print value
        //System.out.println(spin);
        
        // print colour of value
        if (spin%2==0) {
        	this.colour = "black";
        	//System.out.println("Even number: black");
        }
        else {
        	this.colour = "red";
        	//System.out.println("Odd number: red");
        }
        
        // print info
        System.out.println("Spin value: "+getNumber()+"\nColour: "+getColour());
        giveBonus(listOfPlayers, spin);
    }
	
	public int getNumber() {
		return spin;
	}
	public String getColour() {
		return colour;
	}
	
	public static void giveBonus(ArrayList<Player> listOfPlayers, int spin) {
		int numPlayers = listOfPlayers.size();
		for (int x=0;x<numPlayers; x++) {
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
