package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Producto;

public class ProductoDAO{
	
	PreparedStatement ps;
	Connection conexion;
	ResultSet resultQuery;
	
	public boolean registrarProducto(Producto producto) {
		String statement = "INSERT INTO producto(nombre,precio) VALUES(?,?)";
		
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			ps.setString(1, producto.getNombre());
			ps.setDouble(2, producto.getPrecio());
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
	
	public ArrayList<Producto> listarProductos(){
		ArrayList<Producto> productos = new ArrayList<>();
		String statement = "SELECT * FROM producto";
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while (resultQuery.next()) {
				String nombre= resultQuery.getString("nombre");
				Double precio = resultQuery.getDouble("precio");
				Producto producto = new Producto(nombre,precio);
				
				productos.add(producto);
			}
			return productos;
			
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
	
	public ArrayList<String> listarNombreProductos(){
		ArrayList<String> nombres = new ArrayList<>();
		String statement = "SELECT DISTINCT nombre FROM producto";
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while (resultQuery.next()) {
				String nombre = resultQuery.getString("nombre");
				nombres.add(nombre);
			}
			return nombres;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar el statement"); /*Esto se repite quizas pueda ser un metodo aparte*/
			}
		}
	}
	
	public void editarFila(Producto producto) {
		String statement = String.format("UPDATE producto SET precio = %.2f WHERE nombre = %s", producto.getPrecio(), producto.getNombre());
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
	
	public void eliminarFila(Producto producto) {
		String statement = String.format("DELETE FROM producto WHERE nombre = %s",  producto.getNombre());
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
