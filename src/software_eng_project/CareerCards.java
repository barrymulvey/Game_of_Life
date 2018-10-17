package software_eng_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


// READ IN DETAILS FROM TEXT FILE
public class CareerCards extends Cards {
	private String title;
	private int salary;
	private int bonus_number;

	// constructor
	public CareerCards(String title, int salary, int bonus_number) {
			this.title = title;
			this.salary = salary;
			this.bonus_number = bonus_number;
		}
	
	// read in details of houses from text file, create house objects, return list of house objects
	public ArrayList<CareerCards> readInCareerCards(String fileLocation) {
		
		List<String> inputList = null;
		// attempt to read in list of houses and details from text file
		try {
			inputList = Files.readAllLines(Paths.get(fileLocation));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// names of careers saved to arraylist
		ArrayList<String> houseNameList = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++)
		{
			String houseName = inputList.get(x).split(":")[0];
			houseNameList.add(houseName);
			//System.out.println(houseName);
		}
		
		// purchase prices saved to array list
		ArrayList<String> purchasePriceList = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++) {
			String purchasePrice = inputList.get(x).split("---")[0].split(": ")[1];
			purchasePriceList.add(purchasePrice);
			//System.out.println(purchasePrice);	
		}
		
		// sale price when red spin saved to array list
		ArrayList<String> salePriceRedList = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++) {
			String salePriceRed = inputList.get(x).split("---")[1];
			salePriceRedList.add(salePriceRed);
			//System.out.println(salePriceRed);	
		}
		
		// sale price when black spin saved to array list
		ArrayList<String> salePriceBlackList = new ArrayList<String>();
		for (int x = 3; x < inputList.size(); x++) {
			String salePriceBlack = inputList.get(x).split("---")[2];
			salePriceBlackList.add(salePriceBlack);
			//System.out.println(salePriceBlack);	
		}
		
		// house card objects created and saved to an array list
		ArrayList<HouseCards> listOfHouses = new ArrayList<HouseCards>();
		for (int x = 0; x<houseNameList.size(); x++) {
			// name, purchase price, sale price red, sale price black
			int purchasePrice = Integer.parseInt(purchasePriceList.get(x));
			int salePriceRed = Integer.parseInt(salePriceRedList.get(x));
			int salePriceBlack = Integer.parseInt(salePriceBlackList.get(x));
			HouseCards houseCardObj = new HouseCards(houseNameList.get(x), purchasePrice, salePriceRed, salePriceBlack);
			listOfHouses.add(houseCardObj);
		}		
		return listOfHouses;
	}
	
	
	// TO DO
	public String chooseHouseCards() {		
		List<String> houseCardsList = new ArrayList<>(Arrays.asList("Ranch", "City Penthouse", "Island Holiday Home", "Dream Villa", "Farmhouse", "Windmill", "Family House", "Luxury Flat", "Eco House", "Studio Flat", "Houseboat", "Teepee", "Cozy Cottage", "Beach Hut"));
			
		int max = 14;
		int min = 1;
		int random_number = (int) (Math.random()* ((max - min) + 1)) + min;
		System.out.println(random_number);
		String test = "hello";
		return test;
	}
	
	public String getHouseName() {
		return name;
	}
	public int getPurchasePrice() {
		return purchase_price;
	}
	public int getRedSalePrice() {
		return sale_price_red;
	}
	public int getBlackSalePrice() {
		return sale_price_black;
	}
	
	public ArrayList<HouseCards> removeHouseCard(ArrayList<HouseCards> houseList) {
		for (int x=0; x<houseList.size(); x++) {
			
			if (this.name == houseList.get(x).getHouseName()) {
				houseList.remove(x);
			}
		}
		String house_removed_message = String.format("'%s' has been removed from the House Card deck", this.name);	
		System.out.println(house_removed_message);
		
		return houseList;
	}
	
	// PRINT DETAILS
	public void printHouseDetails() {
		System.out.println("*** *** *** *** *** *** *** ***");
		System.out.println("House Type: "+getHouseName()+"\nPurchase Price: "+getPurchasePrice()+"\nSale Price (red spin): "+getRedSalePrice()+"\nSale Price (black spin): " +getBlackSalePrice());
		System.out.println("*** *** *** *** *** *** *** ***");	
	}
}
// name: purchase price, spin red, spin black
// Ranch: 600K, 600K, 750K
// City Penthouse: 600K, 650K, 700K
// Island Holiday Home: 600K, 550K, 800K
//Dream Villa: 300K, 250K, 380K
//Farmhouse: 300K, 250K 380K
//Windmill: 350K, 300K, 500K
// Family House: 250K, 200K, 300K
//Luxury Flat: 250K, 200K, 300K
// Eco House: 200K, 180K, 300K
// Studio Flat: 100K, 80K, 150K
//Houseboat: 200K, 180K, 300K
//Teepee: 100K, 80K, 150K
//Cozy Cottage: 150K, 120K, 200K
//Beach Hut: 100K, 80K, 150K
