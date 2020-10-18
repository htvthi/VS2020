package socket.server;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			final Server server = new Server();
			server.startListening();
			
			try(final Scanner scanner = new Scanner(System.in);){
				scanner.nextLine();
				System.exit(0);

			}
	}

}
