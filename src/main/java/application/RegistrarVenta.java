package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.ProductoDAO;
import database.VentaDAO;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Estado;
import model.Venta;

public class RegistrarVenta extends Registrable{
	private Venta venta = new Venta();
	private TablaVenta tVenta= new TablaVenta();
	private VentaDAO ventaDB = new VentaDAO();
	private ProductoDAO productoDB = new ProductoDAO();
	
	public void inicializarInterfazRegistro(VBox cajaCentro) {
		HBox cajaBotones = inicializarCajaBotones("Venta");
		VBox cajaCampos = inicializarCajaCampos();
		cajaCampos.setAlignment(Pos.CENTER);
		
		VBox cajaRegistro = new VBox(30);
		cajaRegistro.setAlignment(Pos.CENTER);
		cajaRegistro.getChildren().addAll(cajaCampos, cajaBotones);
		
		cajaCentro.getChildren().add(cajaRegistro);
		tVenta.setFilasACampos(getCampos());
		tVenta.iniciarTabla(cajaCentro);
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
	
	public void iniciarRegistro() {
		if (valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarVenta(valores);
			ventaDB.registrarTransaccion(this.venta);
			
			System.out.print("Venta registrado correctamente");
			limpiarCampos(getCampos());
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void iniciarEdicion() {
		String idFilaSeleccionada = tVenta.getIdSeleccionado();
		if(valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarVenta(valores);
			ventaDB.editarFila(venta, idFilaSeleccionada);
			System.out.print("Venta editada correctamente");
			limpiarCampos(getCampos());
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void iniciarEliminacion() {
		String idFilaSeleccionada = tVenta.getIdSeleccionado();
		if (idFilaSeleccionada != null || idFilaSeleccionada != "") {
			ventaDB.eliminarFila(idFilaSeleccionada);
			System.out.print("Venta eliminada correctamente");
			limpiarCampos(getCampos());
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
