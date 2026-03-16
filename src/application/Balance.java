package application;


import database.BalanceDAO;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;

public class Balance{
	
	BalanceDAO balanceDB = new BalanceDAO();
	
	public void inicializarBalance(VBox cajaCentro) {
		HBox contenedorTarjetas = new HBox(20);
		contenedorTarjetas.setAlignment(Pos.CENTER);
		contenedorTarjetas.setPadding(new Insets(20));
		contenedorTarjetas.setPrefWidth(800);
		contenedorTarjetas.setMaxWidth(800);
		contenedorTarjetas.setMinWidth(800);
		contenedorTarjetas.getStyleClass().add("form-card");
		
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
		panel.getStyleClass().add("summary-card");
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
		String mensajeTotal = "+"+ String.valueOf(balanceDB.mostrarTotalVenta());
		Label label = new Label(mensajeTotal);
		label.getStyleClass().add("card-amount-green");
		contenidoVentas.getChildren().add(label);
		tarjetaVentas.setContent(contenidoVentas);
		return tarjetaVentas;
	}
	
	public TitledPane iniciarTarjetaGastos() {
		TitledPane tarjetaGastos = iniciarTarjeta("TOTAL GASTOS");
		VBox contenidoGastos = iniciarContenidoTarjeta();
		String mensajeTotal = "-"+ String.valueOf(balanceDB.mostrarTotalGasto());
		Label label = new Label(mensajeTotal);
		label.getStyleClass().add("card-amount-red");
		contenidoGastos.getChildren().add(label);
		tarjetaGastos.setContent(contenidoGastos);
		return tarjetaGastos;
	}
	
	public TitledPane iniciarTarjetaBalance() {
		TitledPane tarjetaBalance = iniciarTarjeta("BALANCE NETO");
		VBox contenidoBalance = iniciarContenidoTarjeta();
		Double balanceTotal= balanceDB.mostrarTotalVenta() - balanceDB.mostrarTotalGasto();
		Label label = new Label(String.valueOf(balanceTotal));
		label.getStyleClass().add("card-amount-gold");
		contenidoBalance.getChildren().add(label);
		tarjetaBalance.setContent(contenidoBalance);
		return tarjetaBalance;
	}
}
