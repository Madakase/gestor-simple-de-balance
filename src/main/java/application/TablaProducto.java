package application;

import java.util.ArrayList;

import database.ProductoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;


public class TablaProducto extends Entablable<Producto> implements Seleccionable{
	
	private ProductoDAO productoDB = new ProductoDAO();
	
	private String idFilaSeleccionada= null;
	
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
        getTabla().setItems(data);
	}
	
	public void setFilasACampos(ArrayList<Node> campos) {
		TableView<Producto> tabla = getTabla(); 	    
		
		tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection == null || campos == null) return;
	        
	        Producto producto = (Producto) newSelection;
	        
	        int indiceCampo = 0;
	        
	        
	        for (Node nodo : campos) {
	            if (nodo instanceof TextField) {
	                TextField tf = (TextField) nodo;
	                
	                switch (indiceCampo) {
	                	case 1:
	                		idFilaSeleccionada = producto.getNombre();
	                        tf.setText(idFilaSeleccionada);
	                        break;
	                    case 2:
	                        tf.setText(String.valueOf(producto.getPrecio()));
	                    
	                }
	                indiceCampo++;
	            }
	        }
	    });
	}
	
	public String getIdSeleccionado() {
	    Producto seleccionado = getTabla().getSelectionModel().getSelectedItem();
	    
	    return String.valueOf(((Producto) seleccionado).getNombre());
	}
}
