package software_eng_project;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

	// constructor player
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

	// constructor player
	public Player() {

	}

	// initialise players
	public Player initialisePlayer(ArrayList<String> carColour, ArrayList<CareerCards> listOfCards) { 
		int startingSalary = 200;
		// 25th October

		//String userColour = null;

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter name of player: ");
		String playerName = keyboard.next();
		int playerAge = 0;

		// Check if user inputs are valid
		while (true) {
			try {
				System.out.println("Enter age of "+playerName+": ");
				playerAge = keyboard.nextInt();
				if (playerAge>=0) break;
				else System.out.println("Invalid input - please try again!");
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input - please try again!");
				keyboard.next();
			}
		}

		String carColourString = carColour.toString().replace("[","").replace("]","");

		System.out.println("Enter colour of car for "+playerName+". Car colours still available: "+carColourString);
		System.out.println("Enter first letter of colour to select: ");
		String carCol = keyboard.next();
		char firstLetter = carCol.charAt(0);

		if (firstLetter == 'p' || firstLetter=='P') {
			colour="Pink";
		}
		else if (firstLetter=='b' || firstLetter=='B') {
			colour="Blue";
		}
		else if (firstLetter=='g' || firstLetter=='G') {
			colour="Green";
		}
		else if (firstLetter=='y' || firstLetter=='Y') {
			colour="Yellow";
		}
		else {
			colour=null;
		}

		System.out.println(playerName+", choose your life path!");
		System.out.println("Enter L for College Path (costs 100K) or R for Career Path: ");
		String lifeDecision = keyboard.next();
		if(lifeDecision.equals("L")||lifeDecision.equals("l")) {
			lifeDecision = "College";
		}
		else if(lifeDecision.equals("R")||lifeDecision.equals("r")) {
			lifeDecision = "Career";
		}

		Player player = new Player(playerName, playerAge, startingSalary, colour, lifeDecision);

		if(lifeDecision.equals("College")) {
			//ChoosePath.selectPath(4, player);
			player.walletBalance(100, "subtract");
			player.getStudentCard();
		}
		else if(lifeDecision.equals("Career")) {
			//ChoosePath.selectPath(1, player);
			System.out.println(player.getName()+", time to choose a career!");
			player.changeCareer(listOfCards);
		}

		return player;
	}

	public void walletBalance(float money, String operation) {
		if (operation.equals("add")) {
			wallet = wallet+money;
		}
		else {
			wallet = wallet-money;
		}
	}

	public void movePlayer(String next_space) {
		current_space = next_space;
	}

	public void addHouse(HouseCards newHouse) {
		this.houses.add(newHouse);
		this.houseList.add(newHouse.getName());
	}

	public void removeHouse(HouseCards houseChosen) {
		this.houses.remove(houseChosen);
		this.houseList.remove(houseChosen.getName());
	}

	public ArrayList<HouseCards> getHouses() {
		return houses;
	}

	public String getHouseList() {
		String houseListString = houseList.toString().replace("[","").replace("]","");
		return houseListString;
	}


	// Change Career
	public void changeCareer(ArrayList<CareerCards> listOfCards) {
		// Draw 2 career cards
		CareerCards card1 = CareerCards.chooseCareerCards(listOfCards);
		System.out.println("1st card chosen is: "+card1.getName()+" with a salary of "+card1.getValue1()+"K");
		listOfCards.remove(card1);

		CareerCards card2 = CareerCards.chooseCareerCards(listOfCards);
		System.out.println("2nd card chosen is: "+card2.getName()+" with a salary of "+card2.getValue1()+"K");

		// Choose Career Card
		int careerCardChoice = 0;
		careerCardChoice = ErrorCheck.rangeCheck("Choose a card! Enter 1 or 2: ", 1, 2, careerCardChoice);
		
		if (careerCardChoice==1) {
			this.career = card1;
		}
		else {
			this.career = card2;
			listOfCards.remove(card2);
			listOfCards.add(card1);
		}
		
		//return listOfCards;
		//keyboard.close();
	}

	public CareerCards getStudentCard() {
		CareerCards careerCardObj = new CareerCards(null, "Student", 0, 0);
		this.career = careerCardObj;
		return careerCardObj;
	}


	public void numLoans(int numberLoans) {
		int loanValue = numberLoans*50;
		this.walletBalance(loanValue, "add");
		num_loans = num_loans + numberLoans;
	}

	public void takeLoan() {
		System.out.println(this.getName()+", your balance of "+this.getBalance()+"K is too low! You need to take out loans...");
		System.out.println("Each loan gives you 50K (but incurs 20% interest!)");
		//System.out.println("Enter the number of loans you would like to take out: ");
		//Scanner keyboard = new Scanner(System.in);
		int numberLoans = 0;
		numberLoans = ErrorCheck.rangeCheck("Enter the number of loans you would like to take out: ", 0, 100, numberLoans);
		this.numLoans(numberLoans);
		System.out.println(this.getName()+"'s updated balance is: "+this.getBalance()+"K");
	}

	public void getMarried(ArrayList<Player> listOfPlayers, Player current_player, Spinner spinner) {
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


		System.out.println("\nTime to organise wedding gifts!");
		// players pay married player 50K/100K depending on spin
		for(int x = 0; x<temporaryPlayerList.size();x++) {
			// spin spinner and print results
			System.out.println(temporaryPlayerList.get(x).getName()+", press enter to spin the spinner!");
			Scanner keyboard = new Scanner(System.in);
			keyboard.nextLine();

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
			while (temporaryPlayerList.get(x).getBalance() < giftValue) {
				temporaryPlayerList.get(x).takeLoan();
			}
			System.out.println(temporaryPlayerList.get(x).getName()+" give a wedding gift of "+giftValue+"K to "+current_player.getName()+"!");
			temporaryPlayerList.get(x).walletBalance(giftValue, "subtract");
			current_player.walletBalance(giftValue, "add");	

			// print updated balances following transaction
			System.out.println(temporaryPlayerList.get(x).getName()+"'s updated balance is: "+temporaryPlayerList.get(x).getBalance()+"K");
			System.out.println(current_player.getName()+"'s updated balance is: "+current_player.getBalance()+"K");
		}
	}

	public int takeExtraTurn(ArrayList<Player> listOfPlayers, int x) {
		System.out.println("\n"+listOfPlayers.get(x).getName()+" gets an extra turn!");
		if(x == 0) x = listOfPlayers.size()-1;
		else x = x-1;
		return x;
	}

	// evaluates player's assets
	public void retirePlayer(int x, ArrayList<HouseCards> listOfCards, ArrayList<Player> listOfPlayers) {
		Scanner keyboard = new Scanner(System.in);

		//sell houses and add value of houses
		HouseCards.sellAllHouses(listOfCards, this, listOfPlayers);

		// pay off loans
		int loans = this.getNumLoans();
		int valueLoans = loans*60;
		this.walletBalance(valueLoans, "subtract");

		// action cards
		int numActionCards = this.getNumActionCards();
		int valueActionCards = numActionCards*100;
		this.walletBalance(valueActionCards, "add");

		// children
		int numChildren = this.getNumChildren();
		int valueChildren = numChildren*50;
		this.walletBalance(valueChildren, "add");

		// award bonus
		int bonus = 0;
		if(x == 0) bonus = 400;
		else if(x == 1) bonus = 300;
		else if(x == 2) bonus = 200;
		else if(x == 3) bonus = 100;
		this.walletBalance(bonus, "add");

		System.out.println(this.getName()+", press enter to tot up your winnings!");
		keyboard.nextLine();
		System.out.println("\n"+this.getName()+", your winnings have been added up! \n");
		System.out.println("You paid:\n"+valueLoans+"K to the bank for your "+loans+" Loans");
		System.out.println("\nYou were awarded:\n"+valueActionCards+"K from the bank for your "+numActionCards+" Action Cards");
		System.out.println(valueChildren+"K from the bank for your "+numChildren+" children");
		System.out.println(bonus+"K for being player number "+(x+1)+" to finish the game");
		System.out.println("Happy retirement!");
	}

	// evaluates player's assets
	public static void determineWinner(ArrayList<Player> retiredList) {

		Player winner = null;
		int playerListSize = retiredList.size();
		ArrayList<Player> winnerList =  new ArrayList<Player>();

		while (playerListSize > 1) {
			// starting condition- to compare to
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
		System.out.println("\nFinal placings of players:");
		for(int y=0;y<winnerList.size(); y++) {
			System.out.println((y+1)+": "+winnerList.get(y).getName()+", with a final balance of: "+winnerList.get(y).getBalance());
		}

		System.out.println("\nCongratulations "+winnerList.get(0).getName()+", you have won the Game of Life!");
	}


	public String getMaritalStatus() {
		return spouse;
	}
	public String getName() {
		return name;
	}
	public String getColour() {
		return colour;
	}
	public int getAge() {
		return age;
	}
	public double getBalance() {
		return wallet;
	}
	public int addChildren(int add) {
		num_children = num_children + add;
		return num_children;
	}
	public int getNumChildren() {
		return num_children;
	}
	public int getNumLoans() {
		return num_loans;
	}
	public String getPath() {
		return path;
	}
	public String getCareer() {
		return career.getName();		
	}
	public int getSalary() {
		return career.getValue1();
	}
	public int getBonus() {
		return career.getValue2();
	}
	public String getCurrentSpace() {
		return current_space;
	}
	public void setCurrentSpace(String new_current_space) {
		current_space = new_current_space;
	}
	public void addActionCard() {
		numActionCards = numActionCards + 1;
	}
	public int getNumActionCards() {
		return numActionCards;
	}
	protected void printDetails(){
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Name: "+getName()+"\nAge: "+getAge()+"\nCar: "+getColour()+"\nWallet Balance: " +getBalance()+"K\nNumber of Children: "+getNumChildren()+"\nNumber of Loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nInitial Path Choice: "+getPath()+"\nCareer: "+getCareer()+"\nBonus Number: "+getBonus()+"\nHouses: "+getHouseList()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber of Action Cards: "+getNumActionCards());
		System.out.println("*** *** *** *** *** *** *** ***");	

	}
	protected void printDetailsSummary(){
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Wallet Balance: "+getBalance()+"K\nNumber of Children: "+getNumChildren()+"\nNumber of Loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nCareer: "+getCareer()+"\nBonus Number: "+getBonus()+"\nHouses: "+getHouseList()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber of Action Cards: "+getNumActionCards());
		System.out.println("*** *** *** *** *** *** *** ***");	

	}

}