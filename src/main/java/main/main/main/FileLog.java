package main;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class FileLog {

	private static Logger logger = Logger.getLogger(Logger.class);

	public void file() {

		try {
			FileAppender fi = new FileAppender(new PatternLayout(), "C://Users/Fred/Desktop/logs.txt");
			fi.setName("fileLog");
			logger.addAppender(fi);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}