package software_eng_project;

public class Spinner {
	int value;
	int spin;
	String colour;
	
	protected Spinner() {
		value = 10;
		spin = 0;
		colour = null;
	}
	
	protected void spinSpinner() {
    //public static void main(String[] args) {
        //int value = 10;   // number of values on spinner
				
        // spin is a random number in the range 1-10
        this.spin = (int) (Math.random()*value) + 1;

        // print value
        System.out.println(spin);
        
        // print colour of value
        if (spin%2==0) {
        	this.colour = "black";
        	System.out.println("Even number: black");
        }
        else {
        	this.colour = "red";
        	System.out.println("Odd number: red");
        }
        
    }
	//public int getNumber() {
		//return spin;
	//}
	//public String getColour() {
		//return colour;
	//}

	
}
