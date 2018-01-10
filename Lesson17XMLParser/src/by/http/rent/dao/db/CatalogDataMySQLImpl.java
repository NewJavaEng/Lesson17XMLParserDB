package by.http.rent.dao.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.http.rent.dao.CatalogData;
import by.http.rent.domain.Catalog;
import by.http.rent.domain.Equipment;

public class CatalogDataMySQLImpl implements CatalogData {

	@Override
	public Catalog readCatalog() {
		Catalog catalog = new Catalog();

		List<Equipment> equipments = selectEquipmentList();
		catalog.setEquipments(equipments);

		return catalog;
	}

	private List<Equipment> selectEquipmentList() {
//		 abstract class ResourceBundle extends Object, works with text file (with extension .properties)
//		 in order to select required object use method ResourceBundle.getBundle(String baseName)
		ResourceBundle rd = ResourceBundle.getBundle("db_config");
//		in order to get concrete value according to the key use method getString(String key)
		String url = rd.getString("db.url");
		String user = rd.getString("db.login");
		String pass = rd.getString("db.pass");
		String driver = rd.getString("db.driver.name");

		List<Equipment> equipments = new ArrayList<>();

		// in order to connect to db use interfact Connection
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {

//			https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
//			download driver
//			https://habrahabr.ru/sandbox/41444/
			// Class.forName(driver);

			// send query to db for simple cases without parameters use interface Statement
//			createStatement() method is from interface Connection: creates a Statement object for sending SQL statements to the database
			Statement st = conn.createStatement();
//			executeQuery() method is from interface Statement
//			interface ResultSet is a result data set; provides access for application to each line of result set after query is executed
//			while executing query ResultSet sets cursor at the line that is in process
//			get method of ResultSet interface provide access to columns of the current line which is in process
//			method ResultSet.next is used to set cursor to the next line of results and makes this line to be current (in process)
			ResultSet rs = st.executeQuery("select * from equipment");
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				double price = rs.getDouble(3);
				Date date = rs.getDate(4);
				System.out.println(id + " " + title + " " + price + " " + date);
			}

//			PreparedStatement ps;
			// CallableStatement cs;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return equipments;
	}

}
