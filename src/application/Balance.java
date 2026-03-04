package application;


import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;

public class Balance{
	
	public void inicializarBalance(VBox cajaCentro) {
		HBox contenedorTarjetas = new HBox(20);
		contenedorTarjetas.setAlignment(Pos.CENTER);
		contenedorTarjetas.setPadding(new Insets(20));
		
		TitledPane tVenta = iniciarTarjetaVenta();
		TitledPane tGasto = iniciarTarjetaGastos();
		TitledPane tBalance = iniciarTarjetaBalance();
		
		contenedorTarjetas.getChildren().addAll(tVenta, tGasto, tBalance);
		cajaCentro.getChildren().addAll(contenedorTarjetas);
		
		/*El codigo de crear un contenedor de tarjeta se repite en diff clases, usar herencia*/
		
	}
	
	public TitledPane iniciarTarjeta(String titulo) {
		TitledPane panel =new TitledPane();
		panel.setText(titulo);
		panel.setCollapsible(false);
		panel.setMaxWidth(200);
		panel.setMinWidth(200);
		return panel;
	}
	public VBox iniciarContenidoTarjeta() {
		VBox cajaContenido = new VBox(5);
		cajaContenido.setAlignment(Pos.CENTER);
		cajaContenido.setPadding(new Insets(10));
		return cajaContenido;
	}
	
	public TitledPane iniciarTarjetaVenta() {
		TitledPane tarjetaVentas = iniciarTarjeta("TOTAL VENTAS");
		VBox contenidoVentas = iniciarContenidoTarjeta();
		contenidoVentas.getChildren().addAll(
			    new Label("Aqui va el total"),
			    new Label("Algun Porcentaje")
			);
		tarjetaVentas.setContent(contenidoVentas);
		return tarjetaVentas;
	}
	
	public TitledPane iniciarTarjetaGastos() {
		TitledPane tarjetaGastos = iniciarTarjeta("TOTAL GASTOS");
		VBox contenidoGastos = iniciarContenidoTarjeta();
		contenidoGastos.getChildren().addAll(
			    new Label("Aqui va el total"),
			    new Label("Algun Porcentaje")
			);
		tarjetaGastos.setContent(contenidoGastos);
		return tarjetaGastos;
	}
	
	public TitledPane iniciarTarjetaBalance() {
		TitledPane tarjetaBalance = iniciarTarjeta("BALANCE NETO");
		VBox contenidoBalance = iniciarContenidoTarjeta();
		contenidoBalance.getChildren().addAll(
			    new Label("Aqui va el total"),
			    new Label("Algun Porcentaje")
			);
		tarjetaBalance.setContent(contenidoBalance);
		return tarjetaBalance;
	}
}
