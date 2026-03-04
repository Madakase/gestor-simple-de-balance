package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class  Registrable  {
	
	ArrayList<String> valoresCampos = new ArrayList<>();
	
	
	public abstract void inicializarInterfazRegistro(VBox cajaCentro, String tipo);
	
	public abstract VBox inicializarCajaCampos();
	
	
	public HBox crearCampoTexto(String titulo) {
		HBox cajaCampos= new HBox(10);
		cajaCampos.setAlignment(Pos.CENTER);
	    
	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    
	    TextField campo = new TextField();
	    campo.setPrefWidth(500);
	    
	    cajaCampos.getChildren().addAll(label, campo);
	    
	    return cajaCampos;
	}
	
	public HBox crearCampoTexto(String titulo, String valorInicial) {
		HBox cajaCampos= new HBox(10);
		cajaCampos.setAlignment(Pos.CENTER);
	    
	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    
	    TextField campo = new TextField();
	    campo.setPrefWidth(500);
	    campo.setText(valorInicial);
	    
	    cajaCampos.getChildren().addAll(label, campo);
	    
	    return cajaCampos;
	}
	
	public <T extends Enum<T>> HBox crearCampoDesplegableEnum(String titulo, T[] opciones, T predeterminado) {
	    HBox cajaCampos = new HBox(10);
	    cajaCampos.setAlignment(Pos.CENTER);

	    Label label = new Label(titulo);
	    label.setPrefWidth(100);

	    ChoiceBox<T> choiceBox = new ChoiceBox<>();
	    choiceBox.getItems().addAll(opciones);
	    if (predeterminado != null) {
	    	choiceBox.setValue(predeterminado);
	    }
	    choiceBox.setPrefWidth(500);

	    cajaCampos.getChildren().addAll(label, choiceBox);
	    return cajaCampos;
	}
	
	public HBox crearCampoDesplegable(String titulo, ArrayList<String> opciones) {
	    HBox cajaCampos = new HBox(10);
	    cajaCampos.setAlignment(Pos.CENTER);

	    Label label = new Label(titulo);
	    label.setPrefWidth(100);

	    ChoiceBox<String> choiceBox = new ChoiceBox<>();
	    choiceBox.getItems().addAll(opciones); 
	    choiceBox.setPrefWidth(500);

	    cajaCampos.getChildren().addAll(label, choiceBox);
	    return cajaCampos;
	}
	
	
	protected boolean valoresEstanCompletos() {
        for (String valor : valoresCampos) {
            if (valor == null || valor.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
	
	
	
	
}
