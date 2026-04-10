package database;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB{
	private static Connection conexion = null;
	
	public static Connection getConnection() {
		if(conexion == null) {
			try {            
				conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/schema","root", "");
	            System.out.println("Conexión OK");
			}
			catch(SQLException e){
				System.out.println("Error de conexion");
	            e.printStackTrace();
				
			}
		}
		return conexion;
	}
	
	public static void closeConnection() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
