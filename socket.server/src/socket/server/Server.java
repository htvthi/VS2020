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

public class Server {
	
	private final InetSocketAddress serverAddress = new InetSocketAddress("localhost", 1024);
	private final Executor executor = Executors.newCachedThreadPool();

	
	private BufferedReader mRequestReader;
	private PrintWriter mResponseWriter;
	
	public void startListening() {
		
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try(final ServerSocket serverSocket = new ServerSocket()) {
					
					System.out.println("Starting server...");
					serverSocket.bind(serverAddress);
					System.out.println("Waiting for incoming connection on port " + serverAddress);
					
					try(final Socket openSocket = serverSocket.accept();
							final BufferedReader reader = new BufferedReader(new InputStreamReader(openSocket.getInputStream()));
							final PrintWriter writer = new PrintWriter(openSocket.getOutputStream());
					) {
						mRequestReader = reader;
						mResponseWriter = writer;
						System.out.println("Connection established. Waiting for messages.");

						while(true) {
							final String clientMessage = mRequestReader.readLine();
							if(clientMessage != null) {
								processClientMessage(clientMessage);	
							}
						}	
					}
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			
		});
	}

	private void processClientMessage(final String clientMessage) throws IOException {
				
		System.out.println("Message received from client: " + clientMessage);
		if(clientMessage.equals("Hello")) {
			System.out.println("Message was Hello, sending World ");
			mResponseWriter.write("World");
			mResponseWriter.println();
			mResponseWriter.flush();
		}	
	}

}
