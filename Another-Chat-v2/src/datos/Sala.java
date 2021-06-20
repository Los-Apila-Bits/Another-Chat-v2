package datos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Sala {
	private int puerto;
	private ServerSocket server;
	private List<Socket> sockets;
	
	public Sala(int puerto) {
		this.puerto = puerto;
		try {
			server = new ServerSocket(this.puerto);
			sockets = new ArrayList<Socket>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void ejecutar() {
		Socket socket;
		try {
			while(true) {
				socket = server.accept();
				sockets.add(socket);
				//new HiloSala(socket, sockets);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Sala(5000).ejecutar();
	}
}