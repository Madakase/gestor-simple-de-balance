package application;

import java.time.LocalDate;
import java.util.ArrayList;
import database.GastoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaccion;

public class TablaGasto extends TablaTransaccion{
	GastoDAO gastoDB = new GastoDAO();
	
	public void cargarDatos() {
		ArrayList<Transaccion> gastos = gastoDB.listarTransacciones();
		ObservableList<Transaccion> data = FXCollections.observableArrayList(gastos);
		this.tabla.setItems(data);
	}
	
	public ArrayList<TableColumn<Transaccion, ?>> inicializarColumnas(){
		
		ArrayList<TableColumn<Transaccion, ?>> columnas = new ArrayList<>();
		
		TableColumn<Transaccion, LocalDate> colFecha = new TableColumn<>("Fecha");
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		TableColumn<Transaccion, Character> colDesc = new TableColumn<>("Descripcion");
		colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.formatearTamanoColumna(colDesc,300);
		
		TableColumn<Transaccion, Character> colCat= new TableColumn<>("Categoría");
		colCat.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
		TableColumn<Transaccion, Character> colEst= new TableColumn<>("Estado");
		colEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		TableColumn<Transaccion, Character> colVal= new TableColumn<>("Valor");
		colVal.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		columnas.add(colFecha);
		columnas.add(colDesc);
		columnas.add(colCat);
		columnas.add(colEst);
		columnas.add(colVal);
		
		return columnas;
	}
}
