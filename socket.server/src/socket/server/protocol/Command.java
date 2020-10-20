package socket.server.protocol;

public interface Command {

	public String process(final String[]args);
}
