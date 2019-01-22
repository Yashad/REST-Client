package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class RestBase {

	public Properties prop;
	public RestBase() {
		try {
		
			prop = new Properties();
			FileInputStream inStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/config/config.properties");
			prop.load(inStream);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e) {
		e.printStackTrace();
		}
	}
}
