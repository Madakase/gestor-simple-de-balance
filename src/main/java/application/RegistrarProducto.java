package application;

import java.util.ArrayList;

import database.ProductoDAO;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Producto;

public class RegistrarProducto extends Registrable{
	
	Producto producto;
	ProductoDAO productoDB = new ProductoDAO();
	
	public void inicializarInterfazRegistro(VBox cajaCentro, String tipo) {
		Button btnRegistrar = new Button("Registrar "+ tipo);
		btnRegistrar.getStyleClass().add("btn-primary");
		btnRegistrar.setOnAction(e-> ejecutarRegistroProducto());
	
		VBox cajaCampos = inicializarCajaCampos();
		cajaCampos.setAlignment(Pos.CENTER);
		
		VBox cajaRegistro = new VBox(30);
		cajaRegistro.setAlignment(Pos.CENTER);
		cajaRegistro.getChildren().addAll(cajaCampos, btnRegistrar);
		
		cajaCentro.getChildren().add(cajaRegistro);
	}
	
	public VBox inicializarCajaCampos(){
		ArrayList<HBox> campos = new ArrayList<>();
		
		campos.add(this.crearCampoTexto("Nombre"));
		campos.add(this.crearCampoTexto("Precio"));
		
		
		VBox cajaDatos = new VBox(10);
		cajaDatos.setAlignment(Pos.CENTER);
		cajaDatos.getChildren().addAll(campos);
		
		return cajaDatos;
		
	}
	
	public void ejecutarRegistroProducto() {
		
		if (valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.producto = new Producto(valores.get(0), Double.valueOf(valores.get(1)));
			
			productoDB.registrarProducto(producto);
			
			System.out.print("Producto registrado correctamente");
		}else {
			System.out.print("Existen campos obligatorios sin llenar");
		}
		
	}
}
