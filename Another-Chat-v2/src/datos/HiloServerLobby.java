package datos;

import java.io.*;
import java.net.Socket;
import java.util.*;

import javax.swing.JScrollPane;

public class HiloServerLobby extends Thread {
	private List<Socket> sockets;
	private Socket socket;
	
	public HiloServerLobby(Socket socket, List<Socket> sockets) {
		this.socket = socket;
		this.sockets = sockets;
	}
	
	public void run() {
		ObjectInputStream entrada;
		ObjectOutputStream salida;
		JScrollPane panel;
		
		try {
			entrada = new ObjectInputStream(socket.getInputStream());
			System.out.println("Entrada server creada");
			while(true) {
				try {
					panel = (JScrollPane) entrada.readObject();
					for (Socket envio : sockets) {
						salida = new ObjectOutputStream(envio.getOutputStream());
						salida.writeObject(panel);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}