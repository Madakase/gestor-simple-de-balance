package model;
public class Gasto extends Transaccion{
	private Categoria tipo;
	
	public Gasto () {
		super();
	}
	
	public Categoria getTipo() {
		return tipo;
	}
	
	public void setTipo (Categoria tipo) {
		this.tipo= tipo;
	}
	
}
	
	


