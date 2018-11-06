package software_eng_project;

import java.util.List;

public class Space {
	private String number;
	private List<String> type;
	// set to null if not input, null by default
	private List<String> next = null;
	
	public Space(String number, List<String> type, List<String> next){
		this.number = number;
		this.type = type;
		this.next = next;
	}
	
	public void printSpace(){
		System.out.println("Current Space: "+number);
		System.out.print("Type: ");
		for(String t: type) {
			System.out.print(t +" ");
		}
		System.out.print("\nNext Space(s): ");
		for(String n: next){
			System.out.print(n +" ");	
		}
		System.out.println(" ");
	}
	public List<String> getSpaceType() {
		return type;
	}
	
	public List<String> getNextSpace() {
		return next;
	}
	
	public String getNumberSpace() {
		return number;
	}
}
