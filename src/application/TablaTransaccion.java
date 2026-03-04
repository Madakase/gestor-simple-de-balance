package application;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Transaccion;

public class TablaTransaccion implements Entablable {
	
	public ArrayList<TableColumn<?, ?>> inicializarColumnas(){
		ArrayList<TableColumn<?, ?>> columnas = new ArrayList<>();
		
		TableColumn<Transaccion, LocalDate> colFecha = new TableColumn<>("Fecha");
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		TableColumn<Transaccion, Character> colDesc = new TableColumn<>("Descripcion");
		colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.formatearTamanoColumna(colDesc,300);
		
		TableColumn<Transaccion, Character> colCat= new TableColumn<>("Item");
		colCat.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		
		TableColumn<Transaccion, Character> colTipo= new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
		TableColumn<Transaccion, Character> colEst= new TableColumn<>("Estado");
		colEst.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		TableColumn<Transaccion, Character> colVal= new TableColumn<>("Valor");
		colVal.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		columnas.add(colFecha);
		columnas.add(colDesc);
		columnas.add(colCat);
		columnas.add(colTipo);
		columnas.add(colEst);
		columnas.add(colVal);
		
		return columnas;
	}
	
}
