package software_eng_project;

import java.util.List;

public class Space {
	private String number;
	private String type;
	// set this attribute to null if it is not input to constructor
	private List<String> next = null;
	
	// constructor
	public Space(String number, String type, List<String> next){
		this.number = number;
		this.type = type;
		this.next = next;
	}
	
	// print details of Space object
	public void printSpace(){
		System.out.println("Current Space: "+number);
		System.out.print("Type: "+type);
		System.out.print("\nNext Space(s): ");
		for(String n: next){
			System.out.print(n +" ");	
		}
		System.out.println(" ");
	}
	
	// return type of Space object
	public String getSpaceType() {
		return type;
	}
	
	// return next space(s) of Space object
	public List<String> getNextSpace() {
		return next;
	}
	
	// return number of Space object
	public String getNumberSpace() {
		return number;
	}
}