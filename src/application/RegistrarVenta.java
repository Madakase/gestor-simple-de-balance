package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.ProductoDAO;
import database.VentaDAO;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Estado;
import model.Venta;

public class RegistrarVenta extends Registrable{
	
	
	
	Venta venta = new Venta();
	VentaDAO ventaDB = new VentaDAO();
	ProductoDAO productoDB = new ProductoDAO();
	
	public void inicializarInterfazRegistro(VBox cajaCentro, String tipo) {
		Button btnRegistrar = new Button("Registrar "+ tipo);
		btnRegistrar.getStyleClass().add("btn-primary");
		btnRegistrar.setOnAction(e -> iniciarRegistroVenta());
	
		VBox cajaCampos = inicializarCajaCampos();
		cajaCampos.setAlignment(Pos.CENTER);
		
		VBox cajaRegistro = new VBox(30);
		cajaRegistro.setAlignment(Pos.CENTER);
		cajaRegistro.getChildren().addAll(cajaCampos, btnRegistrar);
		
		cajaCentro.getChildren().add(cajaRegistro);
	}
	
	public VBox inicializarCajaCampos() {
		ArrayList<HBox> campos = new ArrayList<>();
		
		campos.add(this.crearCampoTexto("Fecha",  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		campos.add(this.crearCampoTexto("Descripcion"));
		campos.add(this.crearCampoDesplegable("Producto", productoDB.listarNombreProductos()));
		campos.add(this.crearCampoDesplegableEnum("Estado", Estado.values(), Estado.CANCELADO));
		campos.add(this.crearCampoTexto("Valor"));
		
		VBox cajaDatos = new VBox(10);
		cajaDatos.setAlignment(Pos.CENTER);
		cajaDatos.getChildren().addAll(campos);
		
		return cajaDatos;
	}
	
	public void iniciarRegistroVenta() {
		if (valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarVenta(valores);
			ventaDB.registrarTransaccion(this.venta);
			
			System.out.print("Venta registrado correctamente");
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void configurarVenta(ArrayList<String> valores) {
		venta.setFecha(LocalDate.parse(valores.get(0)));
		venta.setDesc(valores.get(1));
		venta.setProducto(valores.get(2));
		venta.setEstado(Estado.valueOf(valores.get(3)));
		venta.setValor(Double.valueOf(valores.get(4)));
	}
}
