package p3;

import org.apache.log4j.xml.DOMConfigurator;

public class FileLog {


	public void file() {
				
		String logs = System.getProperty("user.dir") + "\\" + "p3\resources\\logs.txt";
		DOMConfigurator.configure(logs);
		
	}
}
