package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Построитель соединения с базой данных
public class EmpConnBuilder implements ConnectionBuilder {
	public EmpConnBuilder() {
		try {
			// Настройка драйвера базы данных
			Class.forName("org.postgresql.Driver");
		 } 
		catch (ClassNotFoundException ex) {
		 ex.printStackTrace();
		}
	}
	// Получение соединения с базой данных
	@Override
	public Connection getConnection() throws SQLException {
//		String url = ConnectionProperty.getProperty("db.url");
//		String login = ConnectionProperty.getProperty("db.login");
//		String password = ConnectionProperty.getProperty("db.password");
		String url = "jdbc:postgresql://containers-us-west-190.railway.app:5786/railway";
		String login = "postgres";
		String password = "hqaPDBFTHFiIqYLKfGJh";
		return DriverManager.getConnection(url, login, password);
	}
}
