package datos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JScrollPane;

import Ventanas.JChatCliente;
import Ventanas.JLobby;

public class Cliente {
	private Socket socketLobby;
	private List<Socket> socketSalas;
	private int puerto;
	private String ip;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;
	private DataOutputStream salidaMsj;
	private DataInputStream entradaMsj;
	public Cliente(int puerto, String ip) {
		this.puerto = puerto;
		this.ip = ip;
		try {
			socketLobby = new Socket(ip, puerto);
			salida = new ObjectOutputStream(socketLobby.getOutputStream());
			System.out.println("Creando salida stream");
			entrada = new ObjectInputStream(socketLobby.getInputStream());
			//salidaMsj = new DataOutputStream(salida);
			//entradaMsj = new DataInputStream(entrada);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void crearSala(JScrollPane listaServers) {
		try {
			salida.writeObject(listaServers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void inicializarHiloClienteLobby(JLobby ventana) {
		new HiloClienteLobby(this.entrada,ventana).start();
	}
		
	public void inicializarHiloClienteSala(JChatCliente ventana) {
		new HiloClienteSala(this.entradaMsj, ventana).start();
	}
}
