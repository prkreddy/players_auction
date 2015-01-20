package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectUtil {

	private static Connection connection;

	public static Connection getConnection() {

		if (connection == null) {

			Properties props = PropertiesUtil.getProperties();

			try {
				Class.forName(props.getProperty("mysql_driver"));

				try {
					connection = DriverManager.getConnection(
							props.getProperty("mysql_db_url"),
							props.getProperty("username"),
							props.getProperty("password"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return connection;

	}

	public static void releaseConnection(Connection conn) {

		if (conn != null) {

			try {
				conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				conn = null;
			}
		}

	}

	public static void releaseConnection() {

		if (connection != null) {

			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				connection = null;
			}
		}

	}

}
