package Infrastructure;
import java.sql.*;

public class ConnectionManager {

	private static ConnectionManager instance;
	private Connection connection;
	
	public Connection getConnection() {
			return connection;
	}
	
	public static ConnectionManager getInstance() {
		if(instance == null) {
			instance = new ConnectionManager();
		}
			return instance;
		
	}
	private ConnectionManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url="jdbc:mysql://localhost:3306/barber-appoinement-system";
		String user ="root";
		String password ="";
		try {
			connection = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
