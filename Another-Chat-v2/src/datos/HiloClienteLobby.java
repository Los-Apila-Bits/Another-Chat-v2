package datos;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JScrollPane;

import Ventanas.JLobby;

public class HiloClienteLobby extends Thread{
	private ObjectInputStream entrada;
	private JLobby ventana;
	public HiloClienteLobby(ObjectInputStream entrada, JLobby ventana) {
		this.entrada = entrada;
		this.ventana = ventana;
	}
	
	public void run() {
		JScrollPane lista;
		while(true) {
			try {
				try {
					lista = (JScrollPane) entrada.readObject();
					ventana.listScroller = lista;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}