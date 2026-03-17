package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.GastoDAO;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Categoria;
import model.Estado;
import model.Gasto;

public class RegistrarGasto extends Registrable{
	
	Gasto gasto = new Gasto();
	GastoDAO gastoDB = new GastoDAO();
	
	
	public VBox inicializarCajaCampos() {
		ArrayList<HBox> campos = new ArrayList<>();
	
		campos.add(this.crearCampoTexto("Fecha",  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		campos.add(this.crearCampoTexto("Descripcion"));
		campos.add(this.crearCampoDesplegableEnum("Categoria", Categoria.values(), null));
		campos.add(this.crearCampoDesplegableEnum("Estado", Estado.values(), Estado.CANCELADO));  /* Viola Open Closed y es Primitive Obsession -> Hay que encontrar la 
		forma de hacer un array del enum Estado*/
		campos.add(this.crearCampoTexto("Valor"));
		
		VBox cajaDatos = new VBox(10);
		cajaDatos.setAlignment(Pos.CENTER);
		cajaDatos.getChildren().addAll(campos);
		
		return cajaDatos;
	}
	
	public void inicializarInterfazRegistro(VBox cajaCentro, String tipo) {
		Button btnRegistrar = new Button("Registrar "+ tipo);
		btnRegistrar.getStyleClass().add("btn-primary");
		btnRegistrar.setOnAction(e -> iniciarRegistroGasto());
	
		VBox cajaCampos = inicializarCajaCampos();
		cajaCampos.setAlignment(Pos.CENTER);
		
		VBox cajaRegistro = new VBox(30);
		cajaRegistro.setAlignment(Pos.CENTER);
		cajaRegistro.getChildren().addAll(cajaCampos, btnRegistrar);
		
		cajaCentro.getChildren().add(cajaRegistro);
	}
	
	public void iniciarRegistroGasto() {
		if (valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarVenta(valores);
			gastoDB.registrarTransaccion(this.gasto);
			
			System.out.print("Gasto registrado correctamente");
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void configurarVenta(ArrayList<String> valores) {
		gasto.setFecha(LocalDate.parse(valores.get(0)));
		gasto.setDesc(valores.get(1));
		gasto.setTipo(Categoria.valueOf(valores.get(2)));
		gasto.setEstado(Estado.valueOf(valores.get(3)));
		gasto.setValor(Double.valueOf(valores.get(4)));
	}
}
