package software_eng_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BoardReader {
	
	// read in spaces defined in board text file and create a list of spaces
	public static ArrayList<Space> readBoard(){
		
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String boardLocation = utility.getProperty("board_file");
		// initialise board spaces arrayList of spaces
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		
		try {
			//Reads the board file line by line
			BufferedReader reader = new BufferedReader(new FileReader(boardLocation));
			String line;
			// read in defined 'tokens' on each line until there are no more lines
			while((line = reader.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line," ");
				// define current space
				String current_space = st.nextToken();
				// define space type
				String space_type = st.nextToken();
				// define next areas of space
				List<String> nextAreas = new ArrayList<String>();
				while(st.hasMoreTokens()){
					nextAreas.add(st.nextToken());
				}
				
				// initialise a 'space' object for each line of the text file with all information
				Space space = new Space(current_space, space_type, nextAreas);
				boardSpacesList.add(space);
				//space.printSpace();
				//System.out.println("\n");
						
			}
			reader.close();
			
		   // throw exception if file not found or if cannot read from specified file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardSpacesList;
	}
	

}
