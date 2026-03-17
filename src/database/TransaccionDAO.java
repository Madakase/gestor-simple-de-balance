package database;

import java.util.ArrayList;

import model.Transaccion;

public interface TransaccionDAO {
	public boolean registrarTransaccion(Transaccion transaccion);
	public ArrayList<Transaccion> listarTransacciones();
	public void editarFila (Transaccion transaccion, String id);
	public void eliminarFila(String id);
}
