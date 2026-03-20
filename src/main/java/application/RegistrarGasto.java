package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.GastoDAO;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Categoria;
import model.Estado;
import model.Gasto;

public class RegistrarGasto extends Registrable{
	
	private Gasto gasto = new Gasto();
	private GastoDAO gastoDB = new GastoDAO();
	private TablaGasto tGasto = new TablaGasto();
	
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
	
	public void inicializarInterfazRegistro(VBox cajaCentro) {
		HBox cajaBotones = inicializarCajaBotones("Gasto");
	
		VBox cajaCampos = inicializarCajaCampos();
		cajaCampos.setAlignment(Pos.CENTER);
		
		VBox cajaRegistro = new VBox(30);
		cajaRegistro.setAlignment(Pos.CENTER);
		cajaRegistro.getChildren().addAll(cajaCampos, cajaBotones);
		
		cajaCentro.getChildren().add(cajaRegistro);
		tGasto.setFilasACampos(getCampos());
		tGasto.iniciarTabla(cajaCentro);
	}
	
	public void iniciarRegistro() {
		if (valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarVenta(valores);
			gastoDB.registrarTransaccion(this.gasto);
			
			System.out.print("Gasto registrado correctamente");
			limpiarCampos(getCampos());
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void iniciarEdicion() {
		String idFilaSeleccionada = tGasto.getIdSeleccionado();
		if(valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarVenta(valores);
			gastoDB.editarFila(gasto, idFilaSeleccionada);
			System.out.print("Gasto editado correctamente");
			limpiarCampos(getCampos());
		}else {
			System.out.print("Existen campos sin llenar");
		}
	}
	
	public void iniciarEliminacion() {
		String idFilaSeleccionada = tGasto.getIdSeleccionado();
		if (idFilaSeleccionada != null || idFilaSeleccionada != "") {
			gastoDB.eliminarFila(idFilaSeleccionada);
			System.out.print("Gasto eliminada correctamente");
			limpiarCampos(getCampos());
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
