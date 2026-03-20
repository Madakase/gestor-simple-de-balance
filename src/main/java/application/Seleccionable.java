package application;

import java.util.ArrayList;

import javafx.scene.Node;

public interface Seleccionable {
	public void setFilasACampos(ArrayList<Node> campo);
	public String getIdSeleccionado();
		
}
