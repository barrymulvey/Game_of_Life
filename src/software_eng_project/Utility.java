package software_eng_project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
	//location of the configuration file
	private String propFileName = "config.properties";
	
	//reference to itself
	private static Utility utility;
	private Properties prop;
	
	private Utility() throws IOException{
		prop = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if(inputStream != null){
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file "+propFileName+ " not Found");
		}
	}
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public static Utility getInstance(){
		if(utility == null)
			try{
				utility = new Utility();
			} catch(IOException exception){
				exception.printStackTrace();
			}
		return utility;
	}

}
