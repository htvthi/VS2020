package socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import socket.server.protocol.ExitProtocol;
import socket.server.protocol.HelloWorldProtocol;
import socket.server.protocol.MathServiceProtocol;
import socket.server.protocol.Protocol;

public class ClientHandler implements Runnable {

	private static final String CHOOSE_SERVICE_MESSAGE = "Hi Client, welchen Service möchtest du nutzen?"
			+ " Sende 'Hello' oder nutze einen der Mathe Services (+,-,*) (z.B. via '+ 4 12')";
	private static final String DEFAULT_ERROR_MESSAGE = "Das habe ich leider nicht verstanden.";

	private final Socket mSocket;
	private Protocol helloWorldProtocol = new HelloWorldProtocol();
	private Protocol mathServiceProtocol = new MathServiceProtocol();
	private Protocol exitProtocol = new ExitProtocol();

	// Konstruktor mit Socket erzeugt, der die Client-Verbindung managt
	public ClientHandler(Socket pSocket) {

		mSocket = pSocket;

	}

	@Override
	public void run() {

		try (final PrintWriter writer = new PrintWriter(mSocket.getOutputStream());
				final BufferedReader reader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));) {
			System.out.println("Connection established.");
			sayClientWhichServiceIsAvailable(writer); // initiale Nachricht an Client schicken
			continueCommunicateWithClient(reader, writer); // Kommunikation wird fortgesetzt
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void continueCommunicateWithClient(BufferedReader reader, PrintWriter writer) throws IOException {
		while (true) {
			final String clientMessage = reader.readLine();

			if (clientMessage != null) {
				System.out.println("Message received from client: " + clientMessage);

				/*
				 * überprüft, ob das helloWordProtocol zuständig ist (hat nutzer "hello"
				 * eingegeben?) Wenn Nachricht nach dem Aufruf von helloworldprotocol null ist,
				 * dann war das hello world protocol nicht zuständig und wir überprüfen das
				 * mathserviceprotocol
				 */
				String response = helloWorldProtocol.process(clientMessage);
				if (response == null) {
					response = mathServiceProtocol.process(clientMessage);
				}
				if (response == null) {
					response = exitProtocol.process(clientMessage);
				}

				// wenn Response immer noch null ist(d.h. der Nutzer hat was anderes eingegeben)
				// --> dann wird Fehlermeldung an den Nutzer geschickt
				if (response == null) {
					sendMessageToClient(DEFAULT_ERROR_MESSAGE, writer);
				} else {
					sendMessageToClient(response, writer);
				}
			}
		}

	}

	// teilt dem Client mit, welche Services verfügbar sind
	private void sayClientWhichServiceIsAvailable(final PrintWriter writer) throws IOException {

		sendMessageToClient(CHOOSE_SERVICE_MESSAGE, writer); // Nachricht wird an den Client geschickt
	}

	// Nachricht vom Protocol geht an den Server
	private void sendMessageToClient(final String response, final PrintWriter writer) {
		writer.write(response);
		writer.println();
		writer.flush();
	}

}
