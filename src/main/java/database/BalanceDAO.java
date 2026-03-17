package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Balance;
import model.Subcategoria;
import model.Transaccion;

public class BalanceDAO {
	
	PreparedStatement ps;
	Connection conexion;
	public final String STATEMENT_BALANCE = "SELECT fecha, descripcion, 'VENTA' AS tipo, pago AS valor FROM venta UNION ALL SELECT fecha, descripcion, 'GASTO' AS tipo, deuda AS valor FROM gasto ORDER BY fecha DESC";
	
	ResultSet resultQuery;
	
	public ArrayList<Transaccion> listarTransacciones(){
		ArrayList<Transaccion> transacciones = new ArrayList<>();
		String statement = STATEMENT_BALANCE;
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while (resultQuery.next()) {
				Balance balance = new Balance();
				balance.setFecha((resultQuery.getDate("fecha")).toLocalDate());
				balance.setDesc(resultQuery.getString("descripcion"));
				balance.setSubCat(Subcategoria.valueOf(resultQuery.getString("tipo")));
				balance.setValor(resultQuery.getDouble("valor"));
				transacciones.add(balance);
			}
			return transacciones;
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.printf("Error al listar las transacciones");
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
	
	public double mostrarTotalVenta() {
		String statement = "SELECT sum(pago) AS total FROM venta";
		Double total = 0.0;
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			
			while(resultQuery.next()) {
				total = resultQuery.getDouble("total");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar el statement");
			}
		} return total;
	}
	
	public double mostrarTotalGasto() {
		String statement = "SELECT sum(deuda) AS total FROM gasto";
		Double total = 0.0;
		try {
			conexion = ConexionDB.getConnection();
			ps = conexion.prepareStatement(statement);
			resultQuery= ps.executeQuery();
			while(resultQuery.next()) {
				total = resultQuery.getDouble("total");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				if (resultQuery != null) resultQuery.close();
	            if (ps != null) ps.close();
			}catch(SQLException e){
				e.printStackTrace();
				System.out.print("No se pudo cerrar el statement");
			}
		} return total;
	}
}
