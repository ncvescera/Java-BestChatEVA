package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Classe di avvio del server
 * @author ncvescera e smpiccini
 */
public class MainServer {
	
	public static int ServerPort = 2000;
	public static ArrayList <User> users = new ArrayList();
	
	/**
         * 
         * @param args the command line arguments
         * @throws IOException eccezione durante la connessione client/server
         */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(ServerPort); //il server ascolta la porta
		System.out.println("Il server è in ascolto sulla porta: " + ServerPort);

		Socket socket = null;
		
		try {
			while (true) {
				socket = listener.accept();
				
				new Handler(socket).start(); //avvio il thread che gesità il singolo user
			}
		} catch (IOException e) {
			System.out.println("E' successo qualcosa");
		} finally {
			listener.close();
		}

	}

}
