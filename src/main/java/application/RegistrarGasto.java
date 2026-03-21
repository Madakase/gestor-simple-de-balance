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
			this.configurarGasto(valores);
			if(gastoDB.registrarTransaccion(this.gasto)) {
				DialogUtils.mostrarInformacion("Gestor de Balance", "Gasto registrado correctamente");
				limpiarCampos(getCampos());
			}else {
				DialogUtils.mostrarError("ERROR", "El gasto no ha podido registrarse correctamente. Intente de nuevo más tarde.");
			}
		}else {
			DialogUtils.mostrarError("Gestor de Balance", "Existen campos que no fueron llenados correctamente");
		}	
	}
	
	public void iniciarEdicion() {
		String idFilaSeleccionada = tGasto.getIdSeleccionado();
		if(valoresEstanCompletos()) {
			ArrayList<String> valores = getValores();
			this.configurarGasto(valores);
			if(DialogUtils.mostrarConfirmacion("Confirmación", "¿Está seguro de editar este gasto?")) {
				if(gastoDB.editarFila(gasto, idFilaSeleccionada)) {
					DialogUtils.mostrarInformacion("Gestor de Balance", "Gasto editado correctamente");
					limpiarCampos(getCampos());
				}else {
					DialogUtils.mostrarError("ERROR", "El gasto no ha podido editarse correctamente. Intente de nuevo más tarde.");
				}
			}
		}else {
			System.out.print("Existen campos sin llenar");
			DialogUtils.mostrarError("Gestor de Balance", "Existen campos que no fueron llenados correctamente");
		}
	}
	
	public void iniciarEliminacion() {
		String idFilaSeleccionada = tGasto.getIdSeleccionado();
		if (idFilaSeleccionada != null || idFilaSeleccionada != "") {
			if(DialogUtils.mostrarConfirmacion("Confirmación", "¿Está seguro de eliminar este gasto?")) {
				if(gastoDB.eliminarFila(idFilaSeleccionada)) {
					DialogUtils.mostrarInformacion("Gestor de Balance", "Gasto eliminado correctamente");
					limpiarCampos(getCampos());
				}else {
					DialogUtils.mostrarError("ERROR", "El gasto no ha podido eliminarse correctamente. Intente de nuevo más tarde.");
				}
			}
		}
	}
	
	public void configurarGasto(ArrayList<String> valores) {
		gasto.setFecha(LocalDate.parse(valores.get(0)));
		gasto.setDesc(valores.get(1));
		gasto.setTipo(Categoria.valueOf(valores.get(2)));
		gasto.setEstado(Estado.valueOf(valores.get(3)));
		gasto.setValor(Double.valueOf(valores.get(4)));
	}
}
