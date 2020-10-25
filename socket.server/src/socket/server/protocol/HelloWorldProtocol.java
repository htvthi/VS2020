package socket.server.protocol;

public class HelloWorldProtocol implements Protocol {

	@Override
	public String process(String input) {
		if(input.contains("Hello"))
		{
			return "World";
		}
		else {
			return null;
		}
		
	}

}
