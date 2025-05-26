package utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropFile {

	Properties properties;
	FileInputStream fStream;

	public Properties getCredData() {
		String propFilePath = System.getProperty("user.dir");
		try{fStream = new FileInputStream(propFilePath + "\\config\\cred.properties");
		properties = new Properties();
		properties.load(fStream);}catch(Exception e) {}
		return properties;
	}
	public Properties getRunConfig() {
		String propFilePath = System.getProperty("user.dir");
		try{fStream = new FileInputStream(propFilePath + "\\config\\runConfig.properties");
			properties = new Properties();
			properties.load(fStream);}catch(Exception e) {}
		return properties;
	}
	public Properties getDBData() {
		String propFilePath = System.getProperty("user.dir");
		try{fStream = new FileInputStream(propFilePath + "\\config\\db.properties");
		properties = new Properties();
		properties.load(fStream);}catch(Exception e) {}
		return properties;
	}
	public Properties getQuery() {
		String propFilePath = System.getProperty("user.dir");
		try{fStream = new FileInputStream(propFilePath + "\\config\\queries.properties");
		properties = new Properties();
		properties.load(fStream);}catch(Exception e) {}
		return properties;
	}

}
