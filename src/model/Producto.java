package model;

public class Producto {
	private String nombre;
	private double precio;
	private double tamaño;
	
	public Producto(String nombre, double precio, double tamaño) {
		this.nombre= nombre;
		this.precio= precio;
		this.tamaño= tamaño;
	}
		
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public double getTamaño() {
		return tamaño;
	}
}
