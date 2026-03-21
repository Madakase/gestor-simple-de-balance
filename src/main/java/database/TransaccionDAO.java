package database;

import java.util.ArrayList;

import model.Transaccion;

public interface TransaccionDAO {
	public boolean registrarTransaccion(Transaccion transaccion);
	public ArrayList<Transaccion> listarTransacciones();
	public boolean editarFila (Transaccion transaccion, String id);
	public boolean eliminarFila(String id);
}
