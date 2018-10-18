package software_eng_project;

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
			}
		
		public void walletBalance(float money) {
			wallet = wallet+money;
		}
		public void typeHouse(HouseCards houseChoice) {
			house = houseChoice.getHouseName();
		}
		
		// TO DO- check this 'getter' works!
		//public void changeCareer(CareerCards careerChoice) {
			//career = careerChoice.getCareerName;
		//}
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

		protected void printDetails(){
			System.out.println("*** *** *** *** *** *** *** ***");
			System.out.println("Name: "+getName()+"\nAge: "+getAge()+"\nCar: "+colour+"\nWallet Balance: " +getBalanceWallet()+"\nNumber children: "+getNumChildren()+"\nNumber loans: "+getNumLoans()+"\nMarital Status: "+getMaritalStatus()+"\nPath Choice: "+getPath()+"\nCareer: "+getCareer()+"\nHouse: "+getHouse());
			System.out.println("*** *** *** *** *** *** *** ***");			
		}

	}