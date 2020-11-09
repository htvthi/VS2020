package socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	//festlegen, wie viel parallel laufende Clients es geben kann
	private static final int POOL_SIZE = 10;
	private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 1024);
	//Executor-Service pool erzeugen
	private final ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

	public void startListening() throws IOException {
		
		final ServerSocket serverSocket = new ServerSocket();
		System.out.println("Starting server...");
		serverSocket.bind(serverAddress); // Serversocket wird an die Adresse gebunden
		System.out.println("Waiting for incoming connection on port " + serverAddress);
		

		for(int i = 0; i < POOL_SIZE; i++)
		{
			// Programm wartet, bis CLient die Verbindung aufbaut
			pool.execute(new ClientHandler(serverSocket.accept()));
			
		}
		pool.shutdown();	
	}
}
