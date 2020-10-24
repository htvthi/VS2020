package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import socket.server.protocol.MathServiceProtocol;
import socket.server.protocol.Protocol;

public class Server {


	// Kommunikation mit Client, um zu erklären wie die app funktioniert
	private static final String DEFAULT_ERROR_MESSAGE = "Das habe ich leider nicht verstanden.";
	private static final String CHOOSE_SERVICE_MESSAGE = "Hi Client, welchen mathe Service möchtest du nutzen? (z.B. '+ 4 12')";

	
	
	private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 1024);
	private Protocol mathServiceProtocol = new MathServiceProtocol();


	public void startListening() {
		
		try(final ServerSocket serverSocket = new ServerSocket()) {
			
			System.out.println("Starting server...");
			serverSocket.bind(serverAddress);
			System.out.println("Waiting for incoming connection on port " + serverAddress);
			
			try(final Socket openSocket = serverSocket.accept();
					final BufferedReader reader = new BufferedReader(new InputStreamReader(openSocket.getInputStream()));
					final PrintWriter writer = new PrintWriter(openSocket.getOutputStream());
			) {
				System.out.println("Connection established.");
				sayClientWhichServiceIsAvailable(writer);
				continueCommunicateWithClient(reader, writer);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// teilt dem Client mit, welche Services verfügbar sind
	private void sayClientWhichServiceIsAvailable(final PrintWriter writer) throws IOException {
		
		sendMessageToClient(CHOOSE_SERVICE_MESSAGE, writer);
	}

	// Client schreibt etwas, Server reagiert
	private void continueCommunicateWithClient(final BufferedReader reader, final PrintWriter writer) throws IOException {
		
		while(true) {
			final String clientMessage = reader.readLine();
			if(clientMessage != null) {
				System.out.println("Message received from client: " + clientMessage);
				final String response = mathServiceProtocol.process(clientMessage);
				
				if(response == null) {
					sendMessageToClient(DEFAULT_ERROR_MESSAGE, writer);
				} else {
					sendMessageToClient(response, writer);
				}
			}
		}
	}
	
    //Nachricht vom Protocol geht an den Server
	private void sendMessageToClient(final String response, final PrintWriter writer) {
		writer.write(response);
		writer.println();
		writer.flush();		
	}
}
