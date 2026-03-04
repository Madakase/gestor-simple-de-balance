package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Venta;

public class VentaDAO {
		
	PreparedStatement ps;
	Connection conexion;
		
	public boolean registrarVenta(Venta venta) {
		String statement = "INSERT INTO venta(fecha,descripcion,producto,estado,pago) VALUES(?,?,?)";
			
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			ps.setDate(2, java.sql.Date.valueOf(venta.getFecha()));
			ps.setString(3, venta.getDescripcion());
			ps.setString(4, venta.getProducto());
			ps.setString(5, String.valueOf(venta.getEstado()));
			ps.setDouble(6, venta.getValor());
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
