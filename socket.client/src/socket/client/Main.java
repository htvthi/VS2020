package socket.client;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		final Client client = new Client();
		client.openConnection();
		
		try(final Scanner scanner = new Scanner(System.in);){
			scanner.nextLine();
			System.exit(0);
		}
	}

}
