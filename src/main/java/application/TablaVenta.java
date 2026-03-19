package application;

import java.time.LocalDate;
import java.util.ArrayList;

import database.VentaDAO;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaccion;
import model.Venta;

public class TablaVenta extends TablaTransaccion implements Seleccionable{
	VentaDAO ventaDB = new VentaDAO();
	String idFilaSeleccionada;
	
	public void cargarDatos() {
		ArrayList<Transaccion> ventas = ventaDB.listarTransacciones();
		ObservableList<Transaccion> data = FXCollections.observableArrayList(ventas);
		getTabla().setItems(data);
	}
	
	public String setFilasACampos(ArrayList<Node> campos) {
		TableView<Transaccion> tabla = getTabla(); 	    
		if (tabla == null) return null;
		
		tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection == null || campos == null) return;
	        
	        Venta venta = (Venta) newSelection;
	        int indiceCampo = 0;
	        
	        for (Node nodo : campos) {
	            if (nodo instanceof TextField) {
	                TextField tf = (TextField) nodo;
	                
	                switch (indiceCampo) {
	                	case 0:
	                		idFilaSeleccionada = String.valueOf(venta.getId());
	                
	                    case 1:
	                        tf.setText(venta.getFecha().toString());
	                        break;
	                    case 2:
	                        tf.setText(venta.getDescripcion());
	                        break;
	                    
	                    case 5:
	                    	tf.setText(String.valueOf(venta.getValor()));
	                }
	                indiceCampo++;
	            }
	            else if (nodo instanceof ChoiceBox) {
	            	
	                @SuppressWarnings("unchecked")
					ChoiceBox<String> cb = (ChoiceBox<String>) nodo;
	                
	                switch (indiceCampo) {
	                	case 3: 
	                		cb.setValue(venta.getProducto());
	                		break;
	                	case 4:
	                		cb.setValue(venta.getEstado().toString());
	                }
	                indiceCampo++;
	            }
	        }
	    });
		return idFilaSeleccionada;
	}
	
	public ArrayList<TableColumn<Transaccion, ?>> inicializarColumnas(){
		
		ArrayList<TableColumn<Transaccion, ?>> columnas = new ArrayList<>();
		
		TableColumn<Transaccion, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		this.formatearTamanoColumna(colId, 50);
		
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
		
		columnas.add(colId);
		columnas.add(colFecha);
		columnas.add(colDesc);
		columnas.add(colCat);
		columnas.add(colEst);
		columnas.add(colVal);
		
		return columnas;
	}


}
