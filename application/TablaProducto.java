package application;

import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;
import model.Transaccion;

public class TablaProducto implements Entablable{
	
	public ArrayList<TableColumn<?, ?>> inicializarColumnas(){
		ArrayList<TableColumn<?, ?>> columnas = new ArrayList<>();
		
		TableColumn<Producto, Character> colNom = new TableColumn<>("Nombre");
		colNom.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		TableColumn<Producto, Double> colPre = new TableColumn<>("Precio");
		colPre.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		
		TableColumn<Producto, Double> colTam= new TableColumn<>("Tamaño");
		colTam.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		
		
		columnas.add(colNom);
		columnas.add(colPre);
		columnas.add(colTam);
		
		return columnas;
	}
	
}
