package gameboard;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BoardReader {
	
	
	public static void readBoard(){
		//identifies were the board file is located
		Utility utility = Utility.getInstance();
		String boardLocation = utility.getProperty("board_file");
		
		try {
			//Reads the board file line by line
			BufferedReader reader = new BufferedReader(new FileReader(boardLocation)	);
			String line;
			while((line = reader.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line," ");
				String areaID = st.nextToken();
				String areaType = st.nextToken();
				List<String> nextAreas = new ArrayList<String>();
				while(st.hasMoreTokens()){
					nextAreas.add(st.nextToken());
				}
				Area area = new Area(areaID, areaType,nextAreas);
				area.printArea();
				System.out.println("\n");
						
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
