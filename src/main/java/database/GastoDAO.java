package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Categoria;
import model.Estado;
import model.Gasto;
import model.Transaccion;

public class GastoDAO implements TransaccionDAO{
	PreparedStatement ps;
	Connection conexion;
	ResultSet resultQuery;
		
	public boolean registrarTransaccion(Transaccion t) {
		String statement = "INSERT INTO gasto(fecha,descripcion,tipo,estado,deuda) VALUES(?,?,?,?,?)";
		Gasto gasto = (Gasto) t;
			
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			ps.setDate(1, java.sql.Date.valueOf(gasto.getFecha()));
			ps.setString(2, gasto.getDescripcion());
			ps.setString(3, (gasto.getTipo()).toString());
			ps.setString(4, (gasto.getEstado()).toString());
			ps.setDouble(5, gasto.getValor());
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
				System.out.print("No se pudo cerrar el statement");
			}
		}
	}
	
	public ArrayList<Transaccion> listarTransacciones(){
		ArrayList<Transaccion> gastos = new ArrayList<>();
		String statement = "SELECT * FROM gasto ORDER BY fecha DESC";
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while (resultQuery.next()) {
				Gasto gasto = new Gasto();
				gasto.setId(resultQuery.getInt("id_gasto"));
				gasto.setFecha((resultQuery.getDate("fecha")).toLocalDate());
				gasto.setDesc(resultQuery.getString("descripcion"));
				gasto.setTipo(Categoria.valueOf(resultQuery.getString("tipo")));
				gasto.setEstado(Estado.valueOf(resultQuery.getString("estado")));
				gasto.setValor(resultQuery.getDouble("deuda"));
				gastos.add(gasto);
			}
			return gastos;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.printf("Error al listar los gastos");
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
		Gasto gasto = (Gasto) tr;
		String fechaFormateada = gasto.getFecha().toString();
		String estadoFormateado= gasto.getEstado().toString();
		String tipoFormateado= gasto.getTipo().toString();
		
		String statement = String.format("UPDATE venta SET fecha = %s, descripcion = %s, tipo = %s, estado = %s, pago = %s WHERE id_gasto = %d"
				, fechaFormateada, gasto.getDescripcion(), tipoFormateado, estadoFormateado, gasto.getValor(), id);
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
		String statement = String.format("DELETE FROM gasto WHERE id_gasto = %s",  id);
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
