package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parametres {

	public Properties getProperties() {

		Properties properties = new Properties();

		try {
			FileInputStream in = new FileInputStream("D://workspace/fr.projet3/src/main/resources/config.properties");
			properties.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Erreur de chargement du fichier de configuration !");
		}
		return properties;
	}
}
