package application;


import java.util.List;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public abstract class Entablable<T>{
	
	private TableView<T> tabla = new TableView<T>();
	private Button btnRefrescar = new Button("Actualizar Tabla");
	
	public TableView<T> iniciarTabla(VBox cajaCentro) {
		tabla.getStyleClass().add("table-view");
		tabla.getColumns().addAll(inicializarColumnas());
		tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabla.setPrefWidth(800);
		tabla.setMaxWidth(800);
		tabla.setMinWidth(800);
		btnRefrescar.getStyleClass().add("btn"); 
		btnRefrescar.setOnAction(e -> this.cargarDatos());
		
		this.cargarDatos();
		
		Node tablaScroll = inicializarScroll(tabla);
		
		HBox contenedorTabla = inicializarCajaTabla(tablaScroll);
		
		cajaCentro.getChildren().addAll(contenedorTabla, btnRefrescar);
		cajaCentro.setAlignment(Pos.CENTER);
		
		return tabla;
	}
	
	public abstract List<TableColumn<T,?>> inicializarColumnas();
	
	public HBox inicializarCajaTabla(Node tabla) {
		HBox contenedorTabla = new HBox(20);
		contenedorTabla.setAlignment(Pos.CENTER);
		contenedorTabla.setPadding(new Insets(20));
		
		contenedorTabla.getChildren().addAll(tabla);
		
		return contenedorTabla;
	}
	
	public void formatearTamanoColumna(TableColumn<T, ?> columna, double tamaño) {
		columna.setPrefWidth(tamaño);
		columna.setMaxWidth(tamaño);
		columna.setMinWidth(tamaño);
		
	}
	
	public Node inicializarScroll(TableView<T> tabla) {
		ScrollPane scrollPane = new ScrollPane(tabla);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        return scrollPane;
	}
	
	public TableView<T> getTabla(){
		return this.tabla;
	}
		
	public abstract void cargarDatos();
}
