package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Estado;
import model.Transaccion;
import model.Venta;

public class VentaDAO implements TransaccionDAO{
		
	PreparedStatement ps;
	Connection conexion;
	
	ResultSet resultQuery;
		
	public boolean registrarTransaccion(Transaccion t) {
		String statement = "INSERT INTO venta(fecha,descripcion,producto,estado,pago) VALUES(?,?,?,?,?)";
		Venta venta = (Venta) t;
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			ps.setDate(1, java.sql.Date.valueOf(venta.getFecha()));
			ps.setString(2, venta.getDescripcion());
			ps.setString(3, venta.getProducto());
			ps.setString(4, (venta.getEstado()).toString());
			ps.setDouble(5, venta.getValor());
			ps.execute();
			return true;
				
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.print("Error al insertar datos");
			return false;
		}finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar la conexion");
			}
		}
	}
	
	public ArrayList<Transaccion> listarTransacciones(){
		ArrayList<Transaccion> ventas = new ArrayList<>();
		String statement = "SELECT * FROM venta";
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while (resultQuery.next()) {
				Venta venta = new Venta();
				venta.setFecha((resultQuery.getDate("fecha")).toLocalDate());
				venta.setDesc(resultQuery.getString("descripcion"));
				venta.setProducto(resultQuery.getString("producto"));
				venta.setEstado(Estado.valueOf(resultQuery.getString("estado")));
				venta.setValor(resultQuery.getDouble("pago"));
				ventas.add(venta);
			}
			return ventas;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.printf("Error al listar las ventas");
			return null;
			
		}finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar el statement");
			}
		}
	}
	
}
