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
	private String career;
	private String house;
	private String current_space;
	private int num_action_cards;

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
		this.num_action_cards = 0;
	}

	public Player() {
		
	}

	public Player initialisePlayer(ArrayList<String> carColour) { // initialise players
		double startingSalary = 100.0;
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

		System.out.println(playerName+", choose College (L) or Career (R): ");
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
			player1.current_space = "4";
		}
		else if(lifeDecision.equals("Career")) {
			player1.current_space = "0";
		}
		
		//keyboard.close();
		
		return player1;
	}

	public void walletBalance(float money) {
		wallet = wallet+money;
	}
	
	public void movePlayer(String next_space) {
		current_space = next_space;
	}
	
	public void typeHouse(HouseCards houseChoice) {
		house = houseChoice.getName();
	}

	// TO DO- check this 'getter' works!
	public void changeCareer(CareerCards careerChoice) {
		career = careerChoice.getName();
	}
	public void numLoans(int number_loans) {
		num_loans = num_loans + number_loans;
	}
	public void married() {
		spouse = "Married";
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
		return career;		
	}
	public String getHouse() {
		return house;
	}
	public String getCurrentSpace() {
		return current_space;
	}
	public void increment_num_action_cards() {
		num_action_cards = num_action_cards + 1;
	}
	public int getNumActionCards() {
		return num_action_cards;
	}
	protected void printDetails(){
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("Name: "+getName()+"\nAge: "+getAge()+"\nCar: "+getColour()+"\nWallet Balance: " +getBalanceWallet()+"\nNumber children: "+getNumChildren()+"\nNumber loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nPath Choice: "+getPath()+"\nCareer: "+getCareer()+"\nHouse: "+getHouse()+"\nCurrent Space: "+getCurrentSpace()+"\nNumber Action Cards: "+getNumActionCards());
		System.out.println("*** *** *** *** *** *** *** ***");			
	}

}