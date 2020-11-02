package socket.server.protocol;

public class ExitProtocol implements Protocol {

	@Override
	public String process(String input) {
		
		if(input.equals("exit"))
		{
			System.exit(0);
			return "bye"; // wird nie ausgegeben, da der Server bereits beendet wird
		}
		else
		{
			return null;
		}
		
	}

	
	
}
