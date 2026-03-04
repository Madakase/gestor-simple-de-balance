package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Transaccion;

public interface Entablable<T> {
	public default void iniciarTabla(VBox cajaCentro) {
		TableView<T> tabla = new TableView<>();

		tabla.getColumns().addAll(inicializarColumnas());
		tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabla.setPrefWidth(800);
		tabla.setMaxWidth(800);
		tabla.setMinWidth(800);
		
		HBox contenedorTabla = inicializarCajaTabla(tabla);
		
		cajaCentro.getChildren().add(contenedorTabla);
	}
	
	public List<TableColumn<T,?>> inicializarColumnas();
	
	public default HBox inicializarCajaTabla(TableView<?> tabla) {
		HBox contenedorTabla = new HBox(20);
		contenedorTabla.setAlignment(Pos.CENTER);
		contenedorTabla.setPadding(new Insets(20));
		
		contenedorTabla.getChildren().add(tabla);
		
		return contenedorTabla;
	}
	
	public default void formatearTamanoColumna(TableColumn<T, ?> columna, double tamaño) {
		columna.setPrefWidth(tamaño);
		columna.setMaxWidth(tamaño);
		columna.setMinWidth(tamaño);
		
	}
}
