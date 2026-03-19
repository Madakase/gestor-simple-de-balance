package application;

import java.time.LocalDate;
import java.util.ArrayList;
import database.GastoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaccion;
import model.Gasto;

public class TablaGasto extends TablaTransaccion implements Seleccionable{
	GastoDAO gastoDB = new GastoDAO();
	
	String idFilaSeleccionada;
	
	public void cargarDatos() {
		ArrayList<Transaccion> gastos = gastoDB.listarTransacciones();
		ObservableList<Transaccion> data = FXCollections.observableArrayList(gastos);
		getTabla().setItems(data);
	}
	
	public String setFilasACampos(ArrayList<Node> campos) {
		TableView<Transaccion> tabla = getTabla(); 	    
		if (tabla == null) return null;
		
		tabla.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection == null || campos == null) return;
	        
	        Gasto gasto = (Gasto) newSelection;
	        int indiceCampo = 0;
	        
	        for (Node nodo : campos) {
	            if (nodo instanceof TextField) {
	                TextField tf = (TextField) nodo;
	                
	                switch (indiceCampo) {
	                	case 0:
	                		idFilaSeleccionada = String.valueOf(gasto.getId());
	                
	                	case 1:
	                        tf.setText(gasto.getFecha().toString());
	                        break;
	                    case 2:
	                        tf.setText(gasto.getDescripcion());
	                        break;
	                    
	                    case 5:
	                    	tf.setText(String.valueOf(gasto.getValor()));
	                }
	                indiceCampo++;
	            }
	            else if (nodo instanceof ChoiceBox) {
	            	
	                @SuppressWarnings("unchecked")
					ChoiceBox<String> cb = (ChoiceBox<String>) nodo;
	                
	                switch (indiceCampo) {
	                	case 3: 
	                		cb.setValue(gasto.getTipo().toString());
	                		break;
	                	case 4:
	                		cb.setValue(gasto.getEstado().toString());
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
		this.formatearTamanoColumna(colDesc,250);
		
		TableColumn<Transaccion, Character> colCat= new TableColumn<>("Categoría");
		colCat.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		
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
