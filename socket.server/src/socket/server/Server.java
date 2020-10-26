package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import socket.server.protocol.HelloWorldProtocol;
import socket.server.protocol.MathServiceProtocol;
import socket.server.protocol.Protocol;

public class Server {


	// Kommunikation mit Client, um zu erklären wie die app funktioniert, Konstanten
	private static final String DEFAULT_ERROR_MESSAGE = "Das habe ich leider nicht verstanden.";
	private static final String CHOOSE_SERVICE_MESSAGE = "Hi Client, welchen Service möchtest du nutzen?"
													+ " Sende 'Hello' oder nutze einen der Mathe Services (+,-,*) (z.B. via '+ 4 12')";

	
	private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 1024);
	// Erzeugen der Protokolle helloWorld und mathService, damit beides unabhängig voneinander genutzt werden kann
	private Protocol helloWorldProtocol = new HelloWorldProtocol();
	private Protocol mathServiceProtocol = new MathServiceProtocol();

	public void startListening() {
		
		try(final ServerSocket serverSocket = new ServerSocket()) {
			
			System.out.println("Starting server...");
			serverSocket.bind(serverAddress); // Serversocket wird an die Adresse gebunden
			System.out.println("Waiting for incoming connection on port " + serverAddress);
			
			try(final Socket openSocket = serverSocket.accept(); // Programm wartet, bis CLient die Verbindung aufbaut
					final BufferedReader reader = new BufferedReader(new InputStreamReader(openSocket.getInputStream())); // Lesewerkzeug: Was schickt der Client dem Server?
					final PrintWriter writer = new PrintWriter(openSocket.getOutputStream()); // Schreibwerkzeug: Um Client Antwort zu schicken
			) {
				System.out.println("Connection established.");
				sayClientWhichServiceIsAvailable(writer); //initiale Nachricht an Client schicken
				continueCommunicateWithClient(reader, writer); // Kommunikation wird fortgesetzt
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// teilt dem Client mit, welche Services verfügbar sind
	private void sayClientWhichServiceIsAvailable(final PrintWriter writer) throws IOException {
		
		sendMessageToClient(CHOOSE_SERVICE_MESSAGE, writer); //Nachricht wird an den Client geschickt
	}

	// Client schreibt etwas, Server reagiert
	private void continueCommunicateWithClient(final BufferedReader reader, final PrintWriter writer) throws IOException {
		
		while(true) {
			final String clientMessage = reader.readLine();
			
			if(clientMessage != null) {
				System.out.println("Message received from client: " + clientMessage);
				
				/*
				 * überprüft, ob das helloWordProtocol zuständig ist (hat nutzer "hello" eingegeben?) 
				 * Wenn Nachricht nach dem Aufruf von helloworldprotocol null ist, dann war das hello world protocol nicht zuständig 
				 * und wir überprüfen das mathserviceprotocol
				 */
				String response = helloWorldProtocol.process(clientMessage);
				if(response == null) {
					response = mathServiceProtocol.process(clientMessage);
				}
				
				//wenn Response immer noch null ist(d.h. der Nutzer hat was anderes eingegeben) --> dann wird Fehlermeldung an den Nutzer geschickt
				if(response == null) {
					sendMessageToClient(DEFAULT_ERROR_MESSAGE, writer);
				} else 
				{
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
