package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import socket.server.protocol.HelloWorldProtocol;
import socket.server.protocol.Protocol;

public class Server {
	
	private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 1024);

	
	public void startListening() {
		
		try(final ServerSocket serverSocket = new ServerSocket()) {
			
			System.out.println("Starting server...");
			serverSocket.bind(serverAddress);
			System.out.println("Waiting for incoming connection on port " + serverAddress);
			
			try(final Socket openSocket = serverSocket.accept();
					final BufferedReader reader = new BufferedReader(new InputStreamReader(openSocket.getInputStream()));
					final PrintWriter writer = new PrintWriter(openSocket.getOutputStream());
			) {
		
				System.out.println("Connection established. Waiting for messages.");

				while(true) {
					final String clientMessage = reader.readLine();
					if(clientMessage != null) {
						processClientMessage(clientMessage, writer);	
					}
				}	
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void processClientMessage(final String clientMessage, final PrintWriter writer) throws IOException {
				
		System.out.println("Message received from client: " + clientMessage);
		final Protocol helloWorld = new HelloWorldProtocol();
		final String result = helloWorld.process(clientMessage);
		if(result != null) {
			System.out.println("Message was "+ clientMessage + " response is " + result);
			writer.write(result);
			writer.println();
			writer.flush();
		}	
	}

}
