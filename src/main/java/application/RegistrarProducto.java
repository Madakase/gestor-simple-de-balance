package application;

import java.util.ArrayList;

import database.ProductoDAO;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Producto;

public class RegistrarProducto extends Registrable{
	
	private Producto producto;
	private ProductoDAO productoDB = new ProductoDAO();
	private TablaProducto tProducto = new TablaProducto();
	
	public void inicializarInterfazRegistro(VBox cajaCentro) {
		HBox cajaBotones = inicializarCajaBotones("Producto");
	
		VBox cajaCampos = inicializarCajaCampos();
		cajaCampos.setAlignment(Pos.CENTER);
		
		VBox cajaRegistro = new VBox(30);
		cajaRegistro.setAlignment(Pos.CENTER);
		cajaRegistro.getChildren().addAll(cajaCampos, cajaBotones);
			
		cajaCentro.getChildren().add(cajaRegistro);
		tProducto.setFilasACampos(getCampos());
		tProducto.iniciarTabla(cajaCentro);
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
	
	public void iniciarRegistro() {
		
		if (valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.producto = new Producto(valores.get(0), Double.valueOf(valores.get(1)));
			
			productoDB.registrarProducto(producto);
			limpiarCampos(getCampos());
			
			System.out.print("Producto registrado correctamente");
		}else {
			System.out.print("Existen campos obligatorios sin llenar");
		}
		
	}
	
	public void iniciarEdicion() {
		String idFilaSeleccionada = tProducto.getIdSeleccionado();
		if(valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.producto = new Producto(valores.get(0), Double.valueOf(valores.get(1)));
			productoDB.editarFila(producto, idFilaSeleccionada);
			System.out.print("Producto editado correctamente");
			limpiarCampos(getCampos());
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void iniciarEliminacion() {
		String idFilaSeleccionada = tProducto.getIdSeleccionado();
		if (idFilaSeleccionada != null || idFilaSeleccionada != "") {
			productoDB.eliminarFila(idFilaSeleccionada);
			System.out.print("Producto editado correctamente");
			limpiarCampos(getCampos());
		}
	}
}
