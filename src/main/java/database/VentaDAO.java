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
		String statement = "SELECT * FROM venta ORDER BY fecha DESC";
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while (resultQuery.next()) {
				Venta venta = new Venta();
				venta.setId(resultQuery.getInt("id_venta"));
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
	
	public void editarFila(Transaccion tr, String id) {
		Venta venta = (Venta) tr;
		String fechaFormateada = venta.getFecha().toString();
		String estadoFormateado= venta.getEstado().toString();
		
		String statement = String.format("UPDATE venta SET fecha = %s, descripcion = %s, producto = %s, estado = %s, pago = %s WHERE id_venta = %d"
				, fechaFormateada, venta.getDescripcion(), venta.getProducto(), estadoFormateado, venta.getValor(), id);
		try{
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar el statement"); /*Esto se repite quizas pueda ser un metodo aparte*/
			}
		}
	}
	
	public void eliminarFila(String id) {
		String statement = String.format("DELETE FROM venta WHERE id_venta = %s",  id);
		try{
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar el statement"); /*Esto se repite quizas pueda ser un metodo aparte*/
			}
		}
	}
	
}
