package application;

import java.util.ArrayList;


import javafx.scene.control.*;

import model.Transaccion;

public abstract class TablaTransaccion extends Entablable<Transaccion>{
	
	
	public abstract ArrayList<TableColumn<Transaccion, ?>> inicializarColumnas();
	
	public abstract void cargarDatos();
	
}
