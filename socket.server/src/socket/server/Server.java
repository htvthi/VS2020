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

	private void sayClientWhichServiceIsAvailable(final PrintWriter writer) throws IOException {
		
		sendMessageToClient(CHOOSE_SERVICE_MESSAGE, writer);
	}

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
	

	private void sendMessageToClient(final String response, final PrintWriter writer) {
		writer.write(response);
		writer.println();
		writer.flush();		
	}
}
