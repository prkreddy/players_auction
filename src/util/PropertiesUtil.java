package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties props;

	public static Properties getProperties() {

		if (props == null) {

			props = new Properties();
			try {
				props.load(new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\db.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return props;

	}
}
