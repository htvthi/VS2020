package rmi.helloworld;

public class Main {

	public static void main(String[] args) {

		Server server = new Server();
		server.init();

		Client client = new Client();
		client.connectToServer();

	}

}
