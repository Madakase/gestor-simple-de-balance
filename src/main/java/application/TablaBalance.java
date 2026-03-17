package application;

import java.time.LocalDate;
import java.util.ArrayList;

import database.BalanceDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaccion;

public class TablaBalance extends TablaTransaccion{
	
	BalanceDAO balanceDB = new BalanceDAO();
	
	public ArrayList<TableColumn<Transaccion, ?>> inicializarColumnas(){
		
		ArrayList<TableColumn<Transaccion, ?>> columnas = new ArrayList<>();
		
		TableColumn<Transaccion, LocalDate> colFecha = new TableColumn<>("Fecha");
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		TableColumn<Transaccion, Character> colDesc = new TableColumn<>("Descripcion");
		colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.formatearTamanoColumna(colDesc,300);
		
		TableColumn<Transaccion, Character> colSubCat= new TableColumn<>("Tipo");
		colSubCat.setCellValueFactory(new PropertyValueFactory<>("subcategoria"));
		
		
		TableColumn<Transaccion, Character> colVal= new TableColumn<>("Valor");
		colVal.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		columnas.add(colFecha);
		columnas.add(colDesc);
		columnas.add(colSubCat);
		columnas.add(colVal);
		
		return columnas;
	}
	
	public void cargarDatos() {
		ArrayList<Transaccion> transacciones = balanceDB.listarTransacciones();
		ObservableList<Transaccion> data = FXCollections.observableArrayList(transacciones);
		this.tabla.setItems(data);
	}

}
