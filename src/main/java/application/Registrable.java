package application;

import java.util.ArrayList;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

public abstract class  Registrable  {
	
	private ArrayList<Node> campos = new ArrayList<>();
	
	
	public abstract void inicializarInterfazRegistro(VBox cajaCentro);
	
	public abstract void iniciarRegistro();
	public abstract void iniciarEdicion();
	public abstract void iniciarEliminacion();
	
	public abstract VBox inicializarCajaCampos();
	
	public HBox inicializarCajaBotones(String tipo) {
		Button btnRegistrar = new Button("Registrar "+tipo);
		Button btnEditar= new Button("Editar "+ tipo);
		Button btnEliminar= new Button("Eliminar "+tipo);
		
		btnRegistrar.getStyleClass().add("btn-primary");
		btnRegistrar.setOnAction(e -> iniciarRegistro());
		
		btnEditar.getStyleClass().add("btn-primary");
		btnEditar.setOnAction(e -> iniciarEdicion());
		
		btnEliminar.getStyleClass().add("btn-primary");
		btnEliminar.setOnAction(e -> iniciarEliminacion());
		
		HBox cajaBotones = new HBox(10);
		cajaBotones.setAlignment(Pos.CENTER);
		cajaBotones.getChildren().addAll(btnRegistrar,btnEditar,btnEliminar);
		
		return cajaBotones;
	}
	
	
	public HBox crearCampoTexto(String titulo) {
		HBox cajaCampos= new HBox(10);
		cajaCampos.setAlignment(Pos.CENTER);
	    
	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    label.getStyleClass().add("field-label");
	    
	    TextField campo = new TextField();
	    campo.setPrefWidth(500);
	    campo.getStyleClass().add("text-field");
	    campos.add(campo);
	    
	    cajaCampos.getChildren().addAll(label, campo);
	    
	    return cajaCampos;
	}
	
	public HBox crearCampoTexto(String titulo, String valorInicial) {
		HBox cajaCampos= new HBox(10);
		cajaCampos.setAlignment(Pos.CENTER);
	    
	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    label.getStyleClass().add("field-label");
	    
	    TextField campo = new TextField();
	    campo.setPrefWidth(500);
	    campo.setText(valorInicial);
	    campo.getStyleClass().add("text-field");
	    campos.add(campo);
	    
	    cajaCampos.getChildren().addAll(label, campo);
	    
	    return cajaCampos;
	}
	
	public <T extends Enum<T>> HBox crearCampoDesplegableEnum(String titulo, T[] opciones, T predeterminado) {
	    HBox cajaCampos = new HBox(10);
	    cajaCampos.setAlignment(Pos.CENTER);

	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    label.getStyleClass().add("field-label");

	    ChoiceBox<T> choiceBox = new ChoiceBox<>();
	    choiceBox.getItems().addAll(opciones);
	    if (predeterminado != null) {
	    	choiceBox.setValue(predeterminado);
	    }
	    choiceBox.setPrefWidth(500);
	    choiceBox.getStyleClass().add("choice-box"); 
	    campos.add(choiceBox);

	    cajaCampos.getChildren().addAll(label, choiceBox);
	    return cajaCampos;
	}
	
	public HBox crearCampoDesplegable(String titulo, ArrayList<String> opciones) {
	    HBox cajaCampos = new HBox(10);
	    cajaCampos.setAlignment(Pos.CENTER);

	    Label label = new Label(titulo);
	    label.setPrefWidth(100);
	    label.getStyleClass().add("field-label");

	    ChoiceBox<String> choiceBox = new ChoiceBox<>();
	    choiceBox.getItems().addAll(opciones); 
	    choiceBox.setPrefWidth(500);
	    choiceBox.getStyleClass().add("choice-box"); 
	    
	    campos.add(choiceBox);

	    cajaCampos.getChildren().addAll(label, choiceBox);
	    return cajaCampos;
	}
	
	
	public boolean valoresEstanCompletos() {
        for (Node campo : campos) {
            if (campo instanceof TextField) {
            	if (((TextField) campo).getText().trim().isEmpty()) {
            		return false;
            	}
            	
            }else if (campo instanceof ChoiceBox) {
            	if(((ChoiceBox<?>) campo).getValue() == null){
            		return false;
            	}
            }
        }
        return true;
    }
	
	public ArrayList<Node> getCampos(){
		return this.campos;
	}
	
	public ArrayList<String> getValores(){
		ArrayList<String> valores = new ArrayList<>();
		for (Node campo: campos) {
			if (campo instanceof TextField) {
				valores.add(((TextField) campo).getText());
			}
			else if (campo instanceof ChoiceBox) {
				 ChoiceBox<?> choiceBox = (ChoiceBox<?>) campo;
				 Object valor = choiceBox.getValue();
				 valores.add(valor != null ? valor.toString() : ""); /*Esto necesita ser estudiado*/
			}
		}
		return valores;
	}
	
	public void limpiarCampos(ArrayList<Node> campos) {
		for (Node nodo : campos) {
	        if (nodo instanceof TextField) {
	            ((TextField) nodo).clear(); 
	        } 
	        else if (nodo instanceof ChoiceBox) {
	            ((ChoiceBox<?>) nodo).setValue(null);
	        }
		}
	}
	
}
