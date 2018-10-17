package software_eng_project;

public abstract class Spinner {
    public static void main(String[] args) {
        int value = 10;   // number of values on spinner

        // spin is a random number in the range 1-10
        int spin = (int) (Math.random()*value) + 1;

        // print value
        System.out.println(spin);
        
        // print colour of value
        if (spin%2==0) {
        	System.out.println("Even number: black");
        }
        else {
        	System.out.println("Odd number: red");
        }

    }
}
