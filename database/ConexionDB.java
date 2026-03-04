package database;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB{
	private static Connection conexion = null;
	
	public static Connection getConnection() {
		if(conexion == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");	            
				conexion= DriverManager.getConnection("jbdc:mysql:localhost//3306/kefir_pedidos","root", "ISRAel16");
	            System.out.println("Conexión OK");

			}catch(ClassNotFoundException e) {
				System.out.println("Error de driver");
	            e.printStackTrace();
			}
			catch(SQLException e){
				System.out.println("Error de conexion");
	            e.printStackTrace();
				
			}
		}
		return conexion;
	}
}
