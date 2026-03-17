package application;

import java.util.ArrayList;

import database.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;

public class TablaProducto extends Entablable<Producto>{
	
	ProductoDAO productoDB = new ProductoDAO();
	
	public ArrayList<TableColumn<Producto, ?>> inicializarColumnas(){
		ArrayList<TableColumn<Producto, ?>> columnas = new ArrayList<>();
		
		TableColumn<Producto, Character> colNom = new TableColumn<>("Nombre");
		colNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		TableColumn<Producto, Double> colPre = new TableColumn<>("Precio ($)");
		colPre.setCellValueFactory(new PropertyValueFactory<>("precio"));
		
		
		columnas.add(colNom);
		columnas.add(colPre);
		
		return columnas;
	}
	
	public void cargarDatos() {
		ArrayList<Producto> productos = productoDB.listarProductos();
        ObservableList<Producto> data = FXCollections.observableArrayList(productos);
        this.tabla.setItems(data);
	}
}
