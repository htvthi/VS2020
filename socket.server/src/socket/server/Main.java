package socket.server;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			final Server server = new Server();
			try {
				server.startListening();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
