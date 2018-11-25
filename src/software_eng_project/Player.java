package software_eng_project;

import java.util.ArrayList;
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
	private String house;
	private String current_space;
	private int numActionCards;

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
		this.house = null;
		this.current_space = "0";
		this.numActionCards = 0;
	}

	public Player() {

	}

	public Player initialisePlayer(ArrayList<String> carColour, ArrayList<CareerCards> listOfCards) { // initialise players
		double startingSalary = 200.0;
		// 25th October

		//String userColour = null;

		Scanner keyboard = new Scanner(System.in);

		System.out.println("Enter name of player: ");
		String playerName = keyboard.next();

		System.out.println("Enter age of "+playerName+": ");
		int playerAge = keyboard.nextInt();

		System.out.println("Enter colour of car for "+playerName+". Car colours still available: "+carColour);
		System.out.println("Enter first letter of colour to select: ");
		String carCol = keyboard.next();
		//System.out.println(carCol);
		char firstLetter = carCol.charAt(0);
		//System.out.println(firstLetter);

		if (firstLetter == 'p' || firstLetter=='P') {
			colour="Pink";
			//carColour.remove("Pink");
		}
		else if (firstLetter=='b' || firstLetter=='B') {
			colour="Blue";
			//carColour.remove("Blue");
		}
		else if (firstLetter=='g' || firstLetter=='G') {
			colour="Green";
			//carColour.remove("Green");
		}
		else if (firstLetter=='y' || firstLetter=='Y') {
			colour="Yellow";
			//carColour.remove("Yellow");
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

		Player player1 = new Player(playerName, playerAge, startingSalary, colour, lifeDecision);
		//keyboard.close();
		if(lifeDecision.equals("College")) {
			player1.walletBalance(100, "subtract");
			player1.current_space = "4";
		}
		else if(lifeDecision.equals("Career")) {
			player1.current_space = "0";
			System.out.println(player1.getName()+", time to choose a career!");
			
			player1.changeCareer(listOfCards);
		}

		//keyboard.close();

		return player1;
	}

	// ** must add which player (as an input)
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

	public void typeHouse(HouseCards houseChoice) {
		house = houseChoice.getName();
	}

	// Change Career
	public void changeCareer(ArrayList<CareerCards> listOfCards) {
		// choose 2 career cards
		CareerCards card1 = CareerCards.chooseCareerCards(listOfCards);
		System.out.println("1st card chosen is: "+card1.getName()+" with a salary of "+card1.getValue1()+"K");
		listOfCards.remove(card1);
		
		CareerCards card2 = CareerCards.chooseCareerCards(listOfCards);
		System.out.println("2nd card chosen is: "+card2.getName()+" with a salary of "+card2.getValue1()+"K");
		
		System.out.println("Choose a card! Enter 1 or 2: ");
		Scanner keyboard = new Scanner(System.in);
		int careerCardChoice = keyboard.nextInt();
		
		if (careerCardChoice==1) {
			this.career = card1;
			//listOfCards.remove(card1);
		}
		else if (careerCardChoice==2) {
			this.career = card2;
			listOfCards.remove(card2);
			listOfCards.add(card1);
		}
		else {
			//player.career = null;
			System.out.println("Error - choice must be 1 or 2.");
		}
		
		//return listOfCards;
		//keyboard.close();
	}
	
	public void numLoans(int number_loans) {
		num_loans = num_loans + number_loans;
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
			
			spinner.spinSpinner();
			int spinNumber = spinner.getNumber();
			System.out.println("Spin value: "+spinNumber+"\nColour: "+spinner.getColour());
			
			// if even, pay married person 50K
			if(spinNumber%2 == 0) {
				System.out.println(temporaryPlayerList.get(x).getName()+" give a wedding gift of 50K to "+current_player.getName()+"!");
				temporaryPlayerList.get(x).walletBalance(50, "subtract");
				current_player.walletBalance(50, "add");				
			}
			// if odd, pay married person 100K
			else {
				System.out.println(temporaryPlayerList.get(x).getName()+" give a wedding gift of 100K to "+current_player.getName()+"!");
				temporaryPlayerList.get(x).walletBalance(100, "subtract");
				current_player.walletBalance(100, "add");	
			}
		}
		
		
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
	public double getBalanceWallet() {
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
	
	public String getHouse() {
		return house;
	}
	public String getCurrentSpace() {
		return current_space;
	}
	public void addActionCard() {
		numActionCards = numActionCards + 1;
	}
	public int getNumActionCards() {
		return numActionCards;
	}
	protected void printDetails(){
		if (this.getPath().equals("College")) {
			System.out.println("*** *** *** *** *** *** *** ***");
			System.out.println("Name: "+getName()+"\nAge: "+getAge()+"\nCar: "+getColour()+"\nWallet Balance: " +getBalanceWallet()+"\nNumber children: "+getNumChildren()+"\nNumber loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nPath Choice: "+getPath()+"\nCareer: Student"+"\nHouse: "+getHouse()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber Action Cards: "+getNumActionCards());
			System.out.println("*** *** *** *** *** *** *** ***");	
		}
		else {
			System.out.println("*** *** *** *** *** *** *** ***");
			System.out.println("Name: "+getName()+"\nAge: "+getAge()+"\nCar: "+getColour()+"\nWallet Balance: " +getBalanceWallet()+"\nNumber children: "+getNumChildren()+"\nNumber loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nPath Choice: "+getPath()+"\nCareer: "+getCareer()+"\nHouse: "+getHouse()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber Action Cards: "+getNumActionCards());
			System.out.println("*** *** *** *** *** *** *** ***");	
		}
				
	}
	protected void printDetailsSummary(){
		if (this.getPath().equals("College")) {
			System.out.println("*** *** *** *** *** *** *** ***");
			System.out.println("Wallet Balance: " +getBalanceWallet()+"\nNumber children: "+getNumChildren()+"\nNumber loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nPath Choice: "+getPath()+"\nCareer: Student"+"\nHouse: "+getHouse()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber Action Cards: "+getNumActionCards());
			System.out.println("*** *** *** *** *** *** *** ***");	
		}
		else {
			System.out.println("*** *** *** *** *** *** *** ***");
			System.out.println("Wallet Balance: " +getBalanceWallet()+"\nNumber children: "+getNumChildren()+"\nNumber loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nPath Choice: "+getPath()+"\nCareer: "+getCareer()+"\nHouse: "+getHouse()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber Action Cards: "+getNumActionCards());
			System.out.println("*** *** *** *** *** *** *** ***");	
		}
				
	}

}