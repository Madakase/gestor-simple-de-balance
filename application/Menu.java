package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Menu extends Application{
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		

		Scene scene = new Scene(root, 1300, 680);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		this.inicializarTitulo(root);
		this.inicializarCajaBotones(root);
		this.inicializarCentroBalance(root);
		
		
		
		primaryStage.setTitle("Gestor de Balance");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	
	public ArrayList<Button> inicializarBotones(BorderPane root){
		Button btnRegistrarVenta = new Button("Registrar Venta");
		btnRegistrarVenta.setOnAction(e -> inicializarCentroVenta(root));
		Button btnRegistrarGasto= new Button("Registrar Gasto");
		btnRegistrarGasto.setOnAction(e -> inicializarCentroGasto(root));
		Button btnRegistrarProducto= new Button("Registrar Producto");
		btnRegistrarProducto.setOnAction(e -> inicializarCentroProducto(root));
		Button btnConsultarBalance= new Button("Balance General");
		btnConsultarBalance.setOnAction(e -> inicializarCentroBalance(root));
		
		
		ArrayList<Button> botones = new ArrayList<>();
		botones.add(btnConsultarBalance);
		botones.add(btnRegistrarProducto);
		botones.add(btnRegistrarVenta);
		botones.add(btnRegistrarGasto);
		
		for (Button boton:botones) {
			boton.setPrefSize(150, 50);
			boton.setFocusTraversable(false);
		}
		
		return botones;
	}
	
	
	public void inicializarCajaBotones(BorderPane root) {
		VBox cajaMenu= new VBox(10);
		cajaMenu.setAlignment(Pos.CENTER);
		
		ObservableList<Node> elementosCajaMenu= cajaMenu.getChildren();
		elementosCajaMenu.addAll(inicializarBotones(root));
		
		BorderPane.setMargin(cajaMenu, new Insets(0,0,100,20));
		root.setLeft(cajaMenu);
	}
	
	
	public void inicializarTitulo(BorderPane root) {
		Label tituloMenu = new Label("Bienvenido a Gestor de Balance");
		HBox cajaTitulo= new HBox(tituloMenu);
		cajaTitulo.setAlignment(Pos.CENTER);
		BorderPane.setMargin(cajaTitulo, new Insets(25,0,0,0));
		tituloMenu.setFont(Font.font("Arial", 16));
		root.setTop(cajaTitulo);
	}
	
	
	public void inicializarCentroBalance(BorderPane root) {
		VBox cajaCentro = new VBox(10);
		Balance interfazBalance = new Balance();
		TablaTransaccion tTransaccion= new TablaTransaccion();
		
		interfazBalance.inicializarBalance(cajaCentro);
		tTransaccion.iniciarTabla(cajaCentro);
		
		root.setCenter(cajaCentro);
	}
	
	public void inicializarCentroVenta(BorderPane root) {
		VBox cajaCentro = new VBox (10);
		Registrable interfazVenta = new RegistrarVenta();
		TablaTransaccion tTransaccion= new TablaTransaccion();
		
		interfazVenta.inicializarInterfazRegistro(cajaCentro, "Venta");
		tTransaccion.iniciarTabla(cajaCentro);
		
		cajaCentro.setPadding(new Insets(40,0,40,0));
		
		root.setCenter(cajaCentro);
	}
	
	public void inicializarCentroGasto(BorderPane root) {
		VBox cajaCentro = new VBox (10);
		Registrable interfazVenta = new RegistrarGasto();
		TablaTransaccion tTransaccion= new TablaTransaccion();
		
		interfazVenta.inicializarInterfazRegistro(cajaCentro, "Gasto");
		tTransaccion.iniciarTabla(cajaCentro);
		
		cajaCentro.setPadding(new Insets(40,0,40,0));
		
		root.setCenter(cajaCentro);
	}
	
	public void inicializarCentroProducto(BorderPane root) {
		VBox cajaCentro = new VBox (10);
		Registrable interfazProducto = new RegistrarProducto();
		TablaProducto tProducto= new TablaProducto();
		
		interfazProducto.inicializarInterfazRegistro(cajaCentro, "Producto");
		tProducto.iniciarTabla(cajaCentro);
		
		cajaCentro.setPadding(new Insets(40,0,40,0));
		
		root.setCenter(cajaCentro);
	}
	
	

	
	public static void main(String[] args) {
        launch(args);
    }
}
