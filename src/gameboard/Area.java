package gameboard;

import java.util.List;

public class Area {
	private String id;
	private String type;
	private List<String> next = null;
	
	public Area(String id, String type, List<String> next){
		this.id = id;
		this.type = type;
		this.next = next;
	}
	
	public void printArea(){
		System.out.println("ID: "+id);
		System.out.println("Type: "+type);
		System.out.print("Next Areas: ");
		for(String n: next){
			System.out.print(n +" ");	
		}
		System.out.println(" ");

	}

}
