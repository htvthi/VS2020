package socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class Client {
	
	private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 1024);
	
	public void openConnection() {
		
		try(final Socket socket = new Socket();) {
			System.out.println("Starting client...");
			socket.connect(serverAddress);
			System.out.println("Client connected to a server on " + serverAddress);
			talkWithServer(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
	}

	private void talkWithServer(final Socket socket) {
		
		System.out.println("Sending 'Hello' message to server...");
		try(final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			final PrintWriter writer = new PrintWriter(socket.getOutputStream());
				final Scanner scanner = new Scanner(System.in);){
			//writer.write("Hello");
			
			
			while(true) {
				final String response = reader.readLine();
				if(response != null) {
					System.out.println("Server: " + response);
					final String userInput = scanner.nextLine();
					writer.write(userInput);
					writer.println();
					writer.flush();
				}

			}		
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
