package application;

import java.time.LocalDate;
import java.util.ArrayList;

import database.VentaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaccion;

public class TablaVenta extends TablaTransaccion{
	VentaDAO ventaDB = new VentaDAO();
	
	public void cargarDatos() {
		ArrayList<Transaccion> ventas = ventaDB.listarTransacciones();
		ObservableList<Transaccion> data = FXCollections.observableArrayList(ventas);
		this.tabla.setItems(data);
	}
	
	public ArrayList<TableColumn<Transaccion, ?>> inicializarColumnas(){
		
		ArrayList<TableColumn<Transaccion, ?>> columnas = new ArrayList<>();
		
		TableColumn<Transaccion, LocalDate> colFecha = new TableColumn<>("Fecha");
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		TableColumn<Transaccion, Character> colDesc = new TableColumn<>("Descripcion");
		colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.formatearTamanoColumna(colDesc,300);
		
		TableColumn<Transaccion, Character> colCat= new TableColumn<>("Producto");
		colCat.setCellValueFactory(new PropertyValueFactory<>("producto"));
		
		TableColumn<Transaccion, Character> colEst= new TableColumn<>("Estado");
		colEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		TableColumn<Transaccion, Character> colVal= new TableColumn<>("Valor ($)");
		colVal.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		columnas.add(colFecha);
		columnas.add(colDesc);
		columnas.add(colCat);
		columnas.add(colEst);
		columnas.add(colVal);
		
		return columnas;
	}


}
