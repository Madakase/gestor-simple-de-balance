package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Gasto;

public class GastoDAO {
	PreparedStatement ps;
	Connection conexion;
		
	public boolean registrarGasto(Gasto gasto) {
		String statement = "INSERT INTO venta(fecha,descripcion,tipo,estado,pago) VALUES(?,?,?)";
			
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			ps.setDate(2, java.sql.Date.valueOf(gasto.getFecha()));
			ps.setString(3, gasto.getDescripcion());
			ps.setString(4, String.valueOf(gasto.getTipo()));
			ps.setString(5, String.valueOf(gasto.getEstado()));
			ps.setDouble(6, gasto.getValor());
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
