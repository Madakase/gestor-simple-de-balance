package model;

public class Venta extends Transaccion{
	private String idProducto;

	public Venta() {
		super();
	}
	
	public String getProducto() {
		return idProducto;
	}
	public void setProducto(String producto) {
		this.idProducto= producto;
	}
}
	

