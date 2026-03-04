package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Producto;

public class ProductoDAO {
	
	PreparedStatement ps;
	Connection conexion;
	
	public boolean registrarProducto(Producto producto) {
		String statement = "INSERT INTO producto(nombre,precio, tamaño) VALUES(?,?,?)";
		
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
			ps.setDouble(3, producto.getTamaño());
			ps.execute();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.print("Error al insertar datos");
			return false;
		}finally {
			try {
				conexion.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar la conexion");
			}
		}
	}
}
