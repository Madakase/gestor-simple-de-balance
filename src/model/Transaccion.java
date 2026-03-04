package model;

import java.time.LocalDate;

public abstract class Transaccion {
	private int id;
	private LocalDate fecha;
	private Estado estado;
	private double valor;
	private String descripcion;
	
	public Transaccion() {
		this.estado = Estado.CANCELADO;
		this.fecha = LocalDate.now();
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public double getValor() {
		return valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha= fecha;
	}
	
	public void setEstado(Estado estado) {
		this.estado= estado;
	}
	
	public void setValor(Double valor) {
		this.valor= valor;
	}
	
	public void setDesc(String desc) {
		this.descripcion = desc;
	}
	
	public void actualizarValor(double monto){
		if (this.estado == Estado.PENDIENTE) {
			valor = valor - monto;
			if (valor == 0) {
				estado = Estado.CANCELADO;
			}
			estado = Estado.PENDIENTE;
		}
		
		System.out.println("El valor ya fue complatamente cancelado") /*Colocar throw luego*/;
	}
}

