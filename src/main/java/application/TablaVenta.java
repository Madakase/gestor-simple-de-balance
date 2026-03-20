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
	private VentaDAO ventaDB = new VentaDAO();
	private String idFilaSeleccionada = null;
	
	public void cargarDatos() {
		ArrayList<Transaccion> ventas = ventaDB.listarTransacciones();
		ObservableList<Transaccion> data = FXCollections.observableArrayList(ventas);
		getTabla().setItems(data);
	}
	
	public void setFilasACampos(ArrayList<Node> campos) {
		getTabla().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSel) -> {
			if (newSel != null && campos != null) {
	            Venta venta = (Venta) newSel;
	            idFilaSeleccionada = String.valueOf(venta.getId());
	            int indiceCampo = 0;
	            for (Node nodo : campos) {
	                if (nodo instanceof TextField) {
	                    TextField tf = (TextField) nodo;
	                    switch (indiceCampo) {
	                        case 0: // fecha
	                            tf.setText(venta.getFecha().toString());
	                            break;
	                        case 1: // descripcion
	                            tf.setText(venta.getDescripcion());
	                            break;
	                        case 4: // valor (ajusta índice)
	                            tf.setText(String.valueOf(venta.getValor()));
	                            break;
	                    }
	                } else if (nodo instanceof ChoiceBox) {
	                    @SuppressWarnings("unchecked")
	                    ChoiceBox<String> cb = (ChoiceBox<String>) nodo;
	                    switch (indiceCampo) {
	                        case 2: // producto
	                            cb.setValue(venta.getProducto());
	                            break;
	                        case 3: // estado
	                            cb.setValue(venta.getEstado().toString());
	                            break;
	                    }
	                }
	                indiceCampo++;
	            }
	        } else {
	            idFilaSeleccionada = null;
	        }
	    });
	}
	
	public String getIdSeleccionado() {
	    Transaccion seleccionado = getTabla().getSelectionModel().getSelectedItem();
	    if (seleccionado instanceof Venta) {
	        return String.valueOf(((Venta) seleccionado).getId());
	    }
	    return null;
	}
	
	public ArrayList<TableColumn<Transaccion, ?>> inicializarColumnas(){
		
		ArrayList<TableColumn<Transaccion, ?>> columnas = new ArrayList<>();
		
		TableColumn<Transaccion, Integer> colId = new TableColumn<>("ID");
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.formatearTamanoColumna(colId, 50);
		
		TableColumn<Transaccion, LocalDate> colFecha = new TableColumn<>("Fecha");
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		TableColumn<Transaccion, Character> colDesc = new TableColumn<>("Descripcion");
		colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.formatearTamanoColumna(colDesc,200);
		
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
