package software_eng_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BoardReader {
	
	public static ArrayList<Space> readBoard(){
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String boardLocation = utility.getProperty("board_file");
		ArrayList<Space> boardSpacesList = new ArrayList<Space>();
		
		try {
			//Reads the board file line by line
			BufferedReader reader = new BufferedReader(new FileReader(boardLocation));
			String line;
			while((line = reader.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line," ");
				String current_space = st.nextToken();
				List<String> space_type = new ArrayList<String>();
				space_type.add(st.nextToken());
				if(space_type.get(0).equals("STOP")) {
					space_type.add(st.nextToken());
				}
			
				List<String> nextAreas = new ArrayList<String>();
				while(st.hasMoreTokens()){
					nextAreas.add(st.nextToken());
				}
				
				Space space = new Space(current_space, space_type, nextAreas);
				boardSpacesList.add(space);
				//space.printSpace();
				//System.out.println("\n");
						
			}
			reader.close();
			
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
