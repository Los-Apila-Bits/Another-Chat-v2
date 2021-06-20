package datos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerLobby {
	private int puerto;
	private ServerSocket server;
	private List<Socket> sockets;
	//private List<Sala> sala;
	public ServerLobby(int puerto) {
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
				System.out.println("esperando conexion");
				socket = server.accept();
				System.out.println("Conexion exitosa");
				sockets.add(socket);
				new HiloServerLobby(socket, sockets).start();
				//new HiloServerSala(socket, sockets).start();;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void inicializarHiloClienteLobby(socketLobby,JLobby ventana) {
//		new HiloServerLobby(ventana,this.entrada).start();
//	}
	
	public static void main(String[] args) {
		new ServerLobby(9000).ejecutar();
	}
}
