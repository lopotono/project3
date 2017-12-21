package p3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parametres {

	public Properties getProperties() {

		Properties properties = new Properties();

		try {
			String propertyFile = System.getProperty("user.dir") + "\\" + "resources\\config.properties";
			FileInputStream in = new FileInputStream(propertyFile);
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur de chargement du fichier de configuration !");
			System.out.println(e.getMessage());
		}
		return properties;
	}
}