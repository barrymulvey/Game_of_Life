package software_eng_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
	private String name;
	private int num_children;
	private double wallet;
	private int age;
	private int num_loans;
	private String colour;
	private String spouse;
	private String path;
	private CareerCards career;
	private ArrayList<HouseCards> houses;
	private List<String> houseList = new ArrayList<String>();
	private String current_space;
	private int numActionCards;

	// constructor with inputs
	public Player(String name, int age, double money, String colour, String path){
		this.name = name;
		this.path = path;
		this.colour = colour;
		this.age = age;
		this.wallet = money;

		// initialise these to null/0 as they are not assigned at the start of the game- they are acquired
		this.num_children = 0;
		this.num_loans = 0;		
		this.spouse = "Single";
		this.career = null;
		this.houses = new ArrayList<HouseCards>();
		this.houseList = new ArrayList<String>();
		this.current_space = "0";
		this.numActionCards = 0;
	}

	// constructor with no inputs
	public Player() {
		this.name = null;
		this.path = null;
		this.colour = null;
		this.age = 0;
		this.wallet = 0;
		this.num_children = 0;
		this.num_loans = 0;		
		this.spouse = "Single";
		this.career = null;
		this.houses = new ArrayList<HouseCards>();
		this.houseList = new ArrayList<String>();
		this.current_space = "0";
		this.numActionCards = 0;

	}

	// initialise players
	public Player initialisePlayer(ArrayList<String> carColour, ArrayList<CareerCards> listOfCards, Scanner keyboard) { 
		int startingSalary = 200;

		// user must enter name of player
		System.out.println("Enter name of player: ");
		String playerName = keyboard.next();
		
		// user must enter age of player
		int playerAge = 0;
		playerAge = ErrorCheck.rangeCheck("Enter age of "+playerName+": ", 0, 100, playerAge);

		// convert arrayList to a string and remove all brackets to print out to screen
		String carColourString = carColour.toString().replace("[","").replace("]","");

		// ask user to enter pawn colour
		System.out.println("Choose car colour for "+playerName+". Car colours still available: "+carColourString);
		String carCol = null;
		
		// check user enters a valid option
		carCol = ErrorCheck.startsWithCheck("Enter first letter of colour to select: ", carColour, carCol);
		System.out.println(carCol);
		
		if (carCol.startsWith("P")) {
			colour="Pink";
		}
		else if (carCol.startsWith("B")) {
			colour="Blue";
		}
		else if (carCol.startsWith("G")) {
			colour="Green";
		}
		else if (carCol.startsWith("Y")) {
			colour="Yellow";
		}
		else {
			colour=null;
		}

		// ask for user to choose player's life path (college/career)
		System.out.println(playerName+", choose your life path!");
		String lifeDecision = null;
		
		// check that user enters a valid input
		lifeDecision = ErrorCheck.pathCheck("Enter L for College Path (costs 100K) or R for Career Path: ", lifeDecision);
		
		if(lifeDecision.equalsIgnoreCase("L") || lifeDecision.equalsIgnoreCase("College")) {
			lifeDecision = "College";
		}
		else if(lifeDecision.equalsIgnoreCase("R") || lifeDecision.equalsIgnoreCase("Career")) {
			lifeDecision = "Career";
		}

		// create Player object using information obtained from user
		Player player = new Player(playerName, playerAge, startingSalary, colour, lifeDecision);

		// set player's career as 'Student' if they selected the college path and deduct 100K
		if(lifeDecision.equals("College")) {
			player.walletBalance(100, "subtract");
			player.getStudentCard();
		}
		// Ask player to choose career if they selected career path
		else if(lifeDecision.equals("Career")) {
			System.out.println(player.getName()+", time to choose a career!");
			player.changeCareer(listOfCards);
		}
		return player;
	}

	// method to add/subtract money from players wallet
	public void walletBalance(float money, String operation) {
		if (operation.equals("add")) {
			wallet = wallet+money;
		}
		else {
			wallet = wallet-money;
		}
	}

	// method to change current space of player (to move them)
	public void movePlayer(String next_space) {
		current_space = next_space;
	}

	// method to add a house to player's 'houses' attribute ArrayList
	public void addHouse(HouseCards newHouse) {
		this.houses.add(newHouse);
		this.houseList.add(newHouse.getName());
	}

	// method to remove a house from player's 'houses' attribute ArrayList
	public void removeHouse(HouseCards houseChosen) {
		this.houses.remove(houseChosen);
		this.houseList.remove(houseChosen.getName());
	}

	// method to return 'houses' ArrayList
	public ArrayList<HouseCards> getHouses() {
		return houses;
	}

	// method to return names of all houses owned by player in a string
	public String getHouseList() {
		String houseListString = houseList.toString().replace("[","").replace("]","");
		return houseListString;
	}


	// method to change career of player
	public void changeCareer(ArrayList<CareerCards> listOfCards) {
		// Draw 2 career cards
		CareerCards card1 = CareerCards.chooseCareerCards(listOfCards);
		System.out.println("1st card chosen is: "+card1.getName()+" with a salary of "+card1.getValue1()+"K");
		// remove card from deck
		listOfCards.remove(card1);

		CareerCards card2 = CareerCards.chooseCareerCards(listOfCards);
		System.out.println("2nd card chosen is: "+card2.getName()+" with a salary of "+card2.getValue1()+"K");

		// Choose Career Card and check for valid user input
		int careerCardChoice = 0;
		careerCardChoice = ErrorCheck.rangeCheck("Choose a card! Enter 1 or 2: ", 1, 2, careerCardChoice);
		
		// update 'career' attribute of player to reflect choice
		if (careerCardChoice==1) {
			this.career = card1;
		}
		else {
			this.career = card2;
			// if card2 chosen, remove card2 from deck and add back in card1
			listOfCards.remove(card2);
			listOfCards.add(card1);
		}
	}

	// method to set player's career to 'Student'
	public CareerCards getStudentCard() {
		CareerCards careerCardObj = new CareerCards(null, "Student", 0, 0);
		this.career = careerCardObj;
		return careerCardObj;
	}

	// method to increment number of loans player has and to add to their wallet
	public void numLoans(int numberLoans) {
		int loanValue = numberLoans*50;
		this.walletBalance(loanValue, "add");
		num_loans = num_loans + numberLoans;
	}

	// method to take out a loan
	public void takeLoan() {
		// user asked to input number of loans required
		System.out.println(this.getName()+", your balance of "+this.getBalance()+"K is too low! You need to take out loans...");
		System.out.println("Each loan gives you 50K (but incurs 20% interest!)");
		int numberLoans = 0;
		// check for valid user input
		numberLoans = ErrorCheck.rangeCheck("Enter the number of loans you would like to take out: ", 0, 100, numberLoans);
		// increment number of loans player has
		this.numLoans(numberLoans);
		// print updated balance
		System.out.println(this.getName()+"'s updated balance is: "+this.getBalance()+"K");
	}

	// method to update player's marital status
	public void getMarried(ArrayList<Player> listOfPlayers, Player current_player, Spinner spinner, Scanner keyboard) {
		// set 'spouse' attribute to married
		spouse = "Married";

		// create list of players excluding current player
		int numOfPlayers = listOfPlayers.size();
		ArrayList<Player> temporaryPlayerList = new ArrayList<Player>();
		for (int p=0;p<numOfPlayers;p++) {
			if (!listOfPlayers.get(p).getName().equals(current_player.getName())) {
				Player playerToAdd = listOfPlayers.get(p);
				temporaryPlayerList.add(playerToAdd);
			}
		}

		// other players must give cash gifts to current player
		System.out.println("\nTime to organise wedding gifts!");
		// players pay married player 50K/100K depending on spin
		for(int x = 0; x<temporaryPlayerList.size();x++) {
			// spin spinner and print results
			System.out.println(temporaryPlayerList.get(x).getName()+", press enter to spin the spinner!");
			keyboard.nextLine();

			// spin spinner and store number spun
			spinner.spinSpinner(listOfPlayers);
			int spinNumber = spinner.getNumber();

			int giftValue = 0;

			// if even, pay married person 50K
			if(spinNumber%2 == 0) {
				giftValue = 50;
			}	
			// if odd, pay married person 100K
			else {
				giftValue = 100;
			}
			
			// check if player has enough money to give gift, if not- prompt to take out loan
			while (temporaryPlayerList.get(x).getBalance() < giftValue) {
				temporaryPlayerList.get(x).takeLoan();
			}
			// update balances
			System.out.println(temporaryPlayerList.get(x).getName()+" give a wedding gift of "+giftValue+"K to "+current_player.getName()+"!");
			temporaryPlayerList.get(x).walletBalance(giftValue, "subtract");
			current_player.walletBalance(giftValue, "add");	

			// print updated balances following transaction
			System.out.println(temporaryPlayerList.get(x).getName()+"'s updated balance is: "+temporaryPlayerList.get(x).getBalance()+"K");
			System.out.println(current_player.getName()+"'s updated balance is: "+current_player.getBalance()+"K");
		}
	}

	// method to give current player an extra turn
	public int takeExtraTurn(ArrayList<Player> listOfPlayers, int x) {
		System.out.println("\n"+listOfPlayers.get(x).getName()+" gets an extra turn!");
		if(x == 0) x = listOfPlayers.size()-1;
		else x = x-1;
		return x;
	}

	// method to retire a player and evaluate their assets
	public void retirePlayer(int x, ArrayList<HouseCards> listOfCards, ArrayList<Player> listOfPlayers, Scanner keyboard) {

		//sell houses and add value of houses
		HouseCards.sellAllHouses(listOfCards, this, listOfPlayers, keyboard);

		// pay off loans
		int loans = this.getNumLoans();
		int valueLoans = loans*60;
		this.walletBalance(valueLoans, "subtract");

		// count action cards and increase balance by 100K for each
		int numActionCards = this.getNumActionCards();
		int valueActionCards = numActionCards*100;
		this.walletBalance(valueActionCards, "add");

		// count children and increase balance by 50 for each
		int numChildren = this.getNumChildren();
		int valueChildren = numChildren*50;
		this.walletBalance(valueChildren, "add");

		// award bonus for finishing position of player
		// x is the finishing position of the player passed in as an input 
		int bonus = 0;
		if(x == 0) bonus = 400;
		else if(x == 1) bonus = 300;
		else if(x == 2) bonus = 200;
		else if(x == 3) bonus = 100;
		this.walletBalance(bonus, "add");

		// print updated balance
		System.out.println(this.getName()+", press enter to tot up your winnings!");
		keyboard.nextLine();
		System.out.println("\n"+this.getName()+", your winnings have been added up! \n");
		System.out.println("You paid:\n"+valueLoans+"K to the bank for your "+loans+" Loans");
		System.out.println("\nYou were awarded:\n"+valueActionCards+"K from the bank for your "+numActionCards+" Action Cards");
		System.out.println(valueChildren+"K from the bank for your "+numChildren+" children");
		System.out.println(bonus+"K for being player number "+(x+1)+" to finish the game");
		System.out.println("Happy retirement!");
	}

	// evaluates player's assets and determines winning player
	public static void determineWinner(ArrayList<Player> retiredList) {

		Player winner = null;
		int playerListSize = retiredList.size();
		ArrayList<Player> winnerList =  new ArrayList<Player>();

		while (playerListSize > 1) {
			// starting condition- assume player at position 0 has greatest balance and compare all other players
			double mostMoney = retiredList.get(0).getBalance();
			for(int y=0;y<retiredList.size(); y++) {
				if (retiredList.get(y).getBalance() >= mostMoney) {
					mostMoney = retiredList.get(y).getBalance();
					winner = retiredList.get(y);
				}
			}
			winnerList.add(winner);
			retiredList.remove(winner);
			playerListSize = retiredList.size();
		}
		// add last player
		winnerList.add(retiredList.get(0));
		
		// print out final placings of players
		System.out.println("\nFinal placings of players:");
		for(int y=0;y<winnerList.size(); y++) {
			System.out.println((y+1)+": "+winnerList.get(y).getName()+", with a final balance of: "+winnerList.get(y).getBalance());
		}
		System.out.println("\nCongratulations "+winnerList.get(0).getName()+", you have won the Game of Life!");
	}


	// return marital status of player as a String
	public String getMaritalStatus() {
		return spouse;
	}
	
	// return name of player as String
	public String getName() {
		return name;
	}
	
	// return colour of player's car pawn as String
	public String getColour() {
		return colour;
	}
	
	// return player's age as Integer
	public int getAge() {
		return age;
	}
	
	// return player's balance as double
	public double getBalance() {
		return wallet;
	}
	
	// method to add children 
	public int addChildren(int add) {
		num_children = num_children + add;
		return num_children;
	}
	
	// return number of children as integer
	public int getNumChildren() {
		return num_children;
	}
	
	// return number of loans as integer
	public int getNumLoans() {
		return num_loans;
	}
	
	// return original path chosen by player, e.g. college or career, as String
	public String getPath() {
		return path;
	}
	
	// return name of player's career as String
	public String getCareer() {
		return career.getName();		
	}
	
	// return salary of player as integer
	public int getSalary() {
		return career.getValue1();
	}
	
	// return bonus number associated with player's career as integer
	public int getBonus() {
		return career.getValue2();
	}
	
	// return player's current position on board
	public String getCurrentSpace() {
		return current_space;
	}
	
	// method to set the player's position on the board
	public void setCurrentSpace(String new_current_space) {
		current_space = new_current_space;
	}
	
	// method to increment number of player's action card
	public void addActionCard() {
		numActionCards = numActionCards + 1;
	}
	
	// return number of action cards as integer
	public int getNumActionCards() {
		return numActionCards;
	}
	
	// print details of player
	protected void printDetails(){
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Name: "+getName()+"\nAge: "+getAge()+"\nCar: "+getColour()+"\nWallet Balance: " +getBalance()+"K\nNumber of Children: "+getNumChildren()+"\nNumber of Loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nInitial Path Choice: "+getPath()+"\nCareer: "+getCareer()+"\nBonus Number: "+getBonus()+"\nHouses: "+getHouseList()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber of Action Cards: "+getNumActionCards());
		System.out.println("*** *** *** *** *** *** *** ***");	
	}
	
	// print a summary of the player's details (shorter, printed after each turn)
	protected void printDetailsSummary(){
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Wallet Balance: "+getBalance()+"K\nNumber of Children: "+getNumChildren()+"\nNumber of Loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nCareer: "+getCareer()+"\nBonus Number: "+getBonus()+"\nHouses: "+getHouseList()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber of Action Cards: "+getNumActionCards());
		System.out.println("*** *** *** *** *** *** *** ***");	

	}

}