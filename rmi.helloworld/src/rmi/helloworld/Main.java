package rmi.helloworld;

public class Main {

	public static void main(String[] args) {

		// Server-Instanz wird erstellt und Service wird registriert
		Server server = new Server();
		server.init();

		// Client-Instanz wird erstellt und Verbindung zum Server aufgebaut
		Client client = new Client();
		client.connectToServer();

	}

}
