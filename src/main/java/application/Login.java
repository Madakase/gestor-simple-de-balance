package application;

import java.sql.Connection;
import java.util.ArrayList;

import database.ConexionDB;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application{
	
	private ArrayList<TextField> campos = new ArrayList<>();
	private Menu menu = new Menu();
	
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1300, 680);
		scene.getStylesheets().add(
		        getClass().getResource("/application.css").toExternalForm()
		);
		
		Button btnIngresar = new Button("Ingresar");
		btnIngresar.getStyleClass().add("btn-primary");
		btnIngresar.setOnAction(e -> inicioSesion(primaryStage));
		
		HBox usuarioCampo= inicializarCampo("Usuario");
		HBox contraCampo= inicializarCampo("Contraseña");
		
		VBox cajaCentro = new VBox(20);
		cajaCentro.setAlignment(Pos.CENTER);
		cajaCentro.getChildren().addAll(usuarioCampo,contraCampo, btnIngresar);
		
		root.setCenter(cajaCentro);
		primaryStage.setTitle("Inicio de Sesión");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public HBox inicializarCampo(String titulo) {
		HBox cajaCampos= new HBox(10);
		cajaCampos.setAlignment(Pos.CENTER);
	    
	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    label.getStyleClass().add("field-label");
	    
	    TextField campo = new TextField();
	    campo.setPrefWidth(500);
	    campo.getStyleClass().add("text-field");
	    campos.add(campo);
	    cajaCampos.getChildren().addAll(label, campo);
	    
	    return cajaCampos;
	}
	
	public void inicioSesion(Stage primaryStage) {
		String usuario = campos.get(0).getText();
		String contraseña = campos.get(1).getText();
		
		ConexionDB.setUsuario(usuario);
		ConexionDB.setContraseña(contraseña);
		
		if (ConexionDB.getConnection() instanceof Connection) {
			DialogUtils.mostrarInformacion("Éxito", "Ingresado correctamente.");
			menu.start(primaryStage);
			
		}else {
			for(TextField campo : campos) {
				campo.clear();
			}
			DialogUtils.mostrarError("Error", "Usuario o contraseña incorrectas.");
		}
		
	}
	public static void main(String[] args) {
        launch(args);
    }
	
}
